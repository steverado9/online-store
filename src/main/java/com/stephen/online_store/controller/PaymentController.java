package com.stephen.online_store.controller;

import com.stephen.online_store.entity.*;
import com.stephen.online_store.enums.Status;
import com.stephen.online_store.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/api/payment")
public class PaymentController {

    @Value("${base-url}")
    private String baseUrl;

    @Value("${secret-key}")
    private String secretKey;

    @Autowired
    private CartService cartService;

    @Autowired
    private FlutterwaveService flutterwaveService;

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(Principal principal) {

        String email = principal.getName();

        Optional<User> user = userService.findByEmail(email);

        Cart cart = cartService.getCartByUser(user.get());

        List<CartItem> items = cart.getCartItems();

        double total = cartService.getCartTotal(items);

        String txRef = "TX-" + System.currentTimeMillis();
        Payment payment = new Payment(txRef, email, total, Status.PENDING, "");
        paymentService.savePayment(payment.getTxRef(), payment.getEmail(), payment.getAmount(), payment.getStatus(), payment.getTransactionId());

        String paymentLink = flutterwaveService.createPayment(email, "Customer", total, txRef);

        return ResponseEntity.ok(Map.of("paymentLink", paymentLink));
    }

    @GetMapping("/callback")
    public String handleCallback(
            @RequestParam String status,
            @RequestParam("transaction_id") String transactionId,
            @RequestParam("tx_ref") String txRef,
            Model model
    ) {

        if(!status.equals("successful")) {
            return "payment-failed";
        }

        Map<String, Object> data = flutterwaveService.verifyPayment(transactionId);

        String paymentStatus = (String) data.get("status");
        Double paidAmount = Double.valueOf(data.get("amount").toString());

        if (!"successful".equals(paymentStatus)) {
            return "payment-failed";
        }

        //get saved payment
        Payment payment = paymentService.findByTxref(txRef).orElseThrow();

        // Prevent duplicate processing
        if (Status.SUCCESS.equals(payment.getStatus())) {
            System.out.println(" prevent duplicate processing -> " + ResponseEntity.ok("Already processed"));
        }

        // Mark payment successful
        payment.setStatus(Status.SUCCESS);
        payment.setTransactionId(transactionId);
        paymentService.savePayment(payment.getTxRef(), payment.getEmail(), payment.getAmount(), payment.getStatus(), payment.getTransactionId());

        //create Order
        createOrderFromCart(payment);

        ResponseEntity.ok("Payment successful");

        return "payment-success";
    }

    private void createOrderFromCart(Payment payment) {

        String email = payment.getEmail();

        Optional<User> user = userService.findByEmail(email);

        Cart cart = cartService.getCartByUser(user.get());

        List<CartItem> cartItems = cart.getCartItems();

        Order order = new Order();
        order.setUserEmail(email);
        order.setTotalAmount(payment.getAmount());
        order.setStatus(Status.PAID);
        order.setTxRef(payment.getTxRef());
        order.setTransactionId(payment.getTransactionId());
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> orderItems = cartItems.stream().map(item -> {
            OrderItem oi = new OrderItem();
            oi.setProductName(item.getProduct().getTitle());
            oi.setQuantity(item.getQuantity());
            oi.setPrice(item.getProduct().getPrice());
            oi.setOrder(order);
            return oi;
        }).toList();

        order.setItems(orderItems);
        orderService.saveOrder(order.getUserEmail(), order.getTotalAmount(), order.getStatus(), order.getTxRef(), order.getTransactionId(), order.getCreatedAt());

        //Clear cart
        cartService.clearCart(email);
    }


}
