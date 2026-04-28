package com.stephen.online_store.service;

import com.stephen.online_store.entity.CartItem;

import java.util.Optional;

public interface CartItemService {
    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);

    void saveItem(CartItem item);
}
