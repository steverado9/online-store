package com.stephen.online_store.service.Impl;

import com.stephen.online_store.entity.Cart;
import com.stephen.online_store.entity.CartItem;
import com.stephen.online_store.entity.User;
import com.stephen.online_store.repository.CartItemRepository;
import com.stephen.online_store.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;



    @Override
    public Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId) {
        return cartItemRepository.findByCartIdAndProductId(cartId, productId);
    }

    @Override
    public void saveItem(CartItem item) {
        cartItemRepository.save(item);
    }
}
