package com.stephen.online_store.service;

import com.stephen.online_store.entity.Cart;
import com.stephen.online_store.entity.CartItem;
import com.stephen.online_store.entity.User;
import org.springframework.ui.Model;

import java.util.Optional;

public interface CartService {
    void addToCart(User user, Long productId);
}
