package com.stephen.online_store.controller;

import com.stephen.online_store.entity.Cart;
import com.stephen.online_store.entity.CartItem;
import com.stephen.online_store.entity.User;
import com.stephen.online_store.service.CartItemService;
import com.stephen.online_store.service.CartService;
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
import java.util.List;
import java.util.Optional;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/cart")
    public String viewCart(Model model, Principal principal) {

        String email = principal.getName();
        Optional<User> user = userService.findByEmail(email);

        if(user.isEmpty()) {
            return "redirect:/login";
        }

        Cart cart = cartService.getCartByUser(user.get());

        List<CartItem> items = cart.getCartItems();

        double total = cartService.getCartTotal(items);

        model.addAttribute("items", items);
        model.addAttribute("total", total);

        return "cart";
    }

    @PostMapping("/cart/add/{id}")
    public String addToCart(@PathVariable("id") Long productId, Principal principal, Model model) {

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
}
