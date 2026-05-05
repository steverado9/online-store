package com.stephen.online_store.controller;

import com.stephen.online_store.entity.Cart;
import com.stephen.online_store.entity.CartItem;
import com.stephen.online_store.entity.Order;
import com.stephen.online_store.entity.User;
import com.stephen.online_store.enums.Status;
import com.stephen.online_store.remitaService.RemitaService;
import com.stephen.online_store.service.CartItemService;
import com.stephen.online_store.service.CartService;
import com.stephen.online_store.service.OrderService;
import com.stephen.online_store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class CartController {

    @Autowired
    private RemitaService remitaService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/cart")
    public String viewCart(Model model, Principal principal) {

        if (principal == null) {
            model.addAttribute("items", List.of());
            model.addAttribute("total", 0.0);
            return "cart";
        }

        String email = principal.getName();
        Optional<User> user = userService.findByEmail(email);

        if(user.isEmpty()) {
            model.addAttribute("items", List.of());
            model.addAttribute("total", 0.0);
            return "cart";
        }

        Cart cart = cartService.getCartByUser(user.get());

        List<CartItem> items = (cart != null) ?  cart.getCartItems() : List.of();

        double total = cartService.getCartTotal(items);

        model.addAttribute("items", items);
        model.addAttribute("total", total);

        return "cart";
    }

    @PostMapping("/cart/add/{id}")
    public String addToCart(@PathVariable("id") Long productId, Principal principal, Model model) {

        if (principal == null) {
            model.addAttribute("items", List.of());
            model.addAttribute("total", 0.0);
            return "cart";
        }

        String email = principal.getName();
        if (email == null) {
            return "redirect:/login";
        }

        Optional<User> user = userService.findByEmail(email);

        if(user.isEmpty()) {
            return "redirect:/login";
        }

        try {
            cartService.addToCart(user.get(), productId);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/cart";
    }

    @PostMapping("/cart/increase/{id}")
    public String increaseQuantity(@PathVariable Long id) {
        cartService.increaseQuantity(id);
        return "redirect:/cart";
    }

    @PostMapping("/cart/decrease/{id}")
    public String decreaseQuantity(@PathVariable Long id) {
        cartService.decreaseQuantity(id);
        return "redirect:/cart";
    }

    @DeleteMapping("/cart/delete/{id}")
    public String deleteCartItem(@PathVariable Long id) {
        cartService.deleteCartItem(id);
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout(Principal principal) {

        String email = principal.getName();
        Optional<User> user = userService.findByEmail(email);

        Cart cart = cartService.getCartByUser(user.get());

        List<CartItem> items = cart.getCartItems();

        double total = cartService.getCartTotal(items);

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setAmount(total);
        order.setStatus(Status.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        //dummy for now
        order.setPayerName("Test User");
        order.setPayerEmail("test@email.com");

        //Save order first
        //Then generate RRR
        //If something fails, you do not  lose the order reference
        orderService.saveOrder(order);

        //generate RRR
        String rrr = remitaService.generateRRR(order);

        //save RRR
        order.setRrr(rrr);
        orderService.saveOrder(order);

        //redirect
        return "redirect:https://login.remita.net/remita/onepage/payment/init.reg?rrr=" + rrr;
    }
}
