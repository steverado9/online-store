package com.stephen.online_store.service.Impl;

import com.stephen.online_store.entity.Cart;
import com.stephen.online_store.entity.CartItem;
import com.stephen.online_store.entity.User;
import com.stephen.online_store.repository.CartItemRepository;
import com.stephen.online_store.repository.CartRepository;
import com.stephen.online_store.repository.UserRepository;
import com.stephen.online_store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void addToCart(User user, Long productId) {

            Cart cart = cartRepository.findByUserId(user.getId()).orElseGet(() -> cartRepository.save(new Cart(user)));

            Optional<CartItem> existingItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);

            if (existingItem.isPresent()) {
                CartItem item = existingItem.get();
                item.setQuantity(item.getQuantity() + 1);
                cartItemRepository.save(item);
            } else {
                CartItem newitem = new CartItem();
                newitem.setCartId(cart.getId());
                newitem.setProductId(productId);
                newitem.setQuantity(1);

                cartItemRepository.save(newitem);
            }
    }
}
