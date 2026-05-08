package com.stephen.online_store.controller;

import com.stephen.online_store.dto.ProductDto;
import com.stephen.online_store.entity.Product;
import com.stephen.online_store.entity.User;
import com.stephen.online_store.enums.Role;
import com.stephen.online_store.service.ProductService;
import com.stephen.online_store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(@RequestParam(value = "keyword", required = false) String keyword, Model model, Principal principal) {

        if (principal != null) {
            String email = principal.getName();
            Optional<User> existingUser = userService.findByEmail(email);
            model.addAttribute("existingUser", existingUser.orElse(null));
        } else {
            model.addAttribute("existingUser", null);
        }


        List<Product> products;

        if(keyword != null && !keyword.isEmpty()) {
            products = productService.searchProducts(keyword);
        } else {
            products = productService.getAllProducts();
        }

        model.addAttribute("products", products);
        return "/dashboard";
    }

    @GetMapping("/add_product")
    public String addProductForm(Model model, Principal principal) {

        if (principal == null) {
            return "redirect:/login";
        }

        String email = principal.getName();
        Optional<User> existingUser = userService.findByEmail(email);

        existingUser.ifPresent(user -> model.addAttribute("existingUser", user));

        if (existingUser.get().getRole() != Role.ADMIN) {
            model.addAttribute("error", "Unauthorized");
        }

        model.addAttribute("productDto", new ProductDto());
        return "add_product";
    }

    @PostMapping("/add_product")
    public String createProduct(@ModelAttribute("productDto") ProductDto dto) {

        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategory(dto.getCategory());
        product.setImage(dto.getImage());
        product.setRating(dto.getRating());

        productService.saveProduct(product.getTitle(), product.getPrice(), product.getDescription(), product.getCategory(), product.getImage(), product.getRating());
        return "redirect:/dashboard";
    }

    @DeleteMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/dashboard";
    }

    @GetMapping("/product/{id}")
    public String viewProduct(@PathVariable Long id, Model model, Principal principal) {
       try {
           Product product = productService.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

           model.addAttribute("product", product);

       } catch (RuntimeException e) {
           model.addAttribute("error", e.getMessage());
       }

        return "product_details";
    }

    @GetMapping("/product/edit/{id}")
    private String editProduct (@PathVariable Long id, Model model, Principal principal) {

            if(principal == null) {
                return "redirect:/login";
            }

            String email = principal.getName();
            Optional<User> user = userService.findByEmail(email);

            if (user.isEmpty() || user.get().getRole() != Role.ADMIN) {
                return "redirect:/login";
            }

            Optional<Product> product = productService.findById(id);
            if (product.isEmpty()) {
                model.addAttribute("error", new RuntimeException("product is empty"));
            }

            product.ifPresent(value -> model.addAttribute("product", value));

        return "edit_product";
    }

    @PutMapping("/product/edit/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute("product") Product product) {

        Optional<Product> OptionalProduct = productService.findById(id);

        if (OptionalProduct.isPresent()) {
            Product existingProduct = OptionalProduct.get();
            existingProduct.setId(id);
            existingProduct.setImage(product.getImage());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setTitle(product.getTitle());
            existingProduct.setRating(product.getRating());
            existingProduct.setPrice(product.getPrice());

            productService.saveProduct(existingProduct.getTitle(), existingProduct.getPrice(), existingProduct.getDescription(), existingProduct.getCategory(), existingProduct.getImage(), existingProduct.getRating());
        }

        return "redirect:/dashboard";
    }
}
