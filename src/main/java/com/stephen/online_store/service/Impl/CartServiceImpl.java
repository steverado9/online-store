package com.stephen.online_store.service.Impl;

import com.stephen.online_store.entity.Cart;
import com.stephen.online_store.entity.CartItem;
import com.stephen.online_store.entity.Product;
import com.stephen.online_store.entity.User;
import com.stephen.online_store.repository.CartItemRepository;
import com.stephen.online_store.repository.CartRepository;
import com.stephen.online_store.repository.ProductRepository;
import com.stephen.online_store.repository.UserRepository;
import com.stephen.online_store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void addToCart(User user, Long productId) {

            Cart cart = cartRepository.findByUserId(user.getId()).orElseGet(() -> cartRepository.save(new Cart(user)));

            Product product = productRepository.findById(productId) .orElseThrow(() -> new RuntimeException("Product not found"));

            Optional<CartItem> existingItem = cartItemRepository.findByCartAndProduct(cart.getId(), product.getId());

            if (existingItem.isPresent()) {
                CartItem item = existingItem.get();
                item.setQuantity(item.getQuantity() + 1);
                cartItemRepository.save(item);
            } else {
                CartItem newitem = new CartItem();
                newitem.setCart(cart);
                newitem.setProduct(product);
                newitem.setQuantity(1);

                cartItemRepository.save(newitem);
            }
    }

    @Override
    public Cart getCartByUser(User user) {
        return cartRepository.findByUserId(user.getId()).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        });
    }

    @Override
    public void increaseQuantity(Long cartItemId) {
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        item.setQuantity(item.getQuantity() + 1);
        cartItemRepository.save(item);
    }

    @Override
    public void decreaseQuantity(Long cartItemId) {
        CartItem item = cartItemRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("Item not found"));

        if (item.getQuantity() > 1) {
            item.setQuantity(item.getQuantity() - 1);
            cartItemRepository.save(item);
        } else {
            cartItemRepository.delete(item);
        }

    }

    @Override
    public void deleteCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public double getCartTotal(List<CartItem> items) {
        return items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }
}
