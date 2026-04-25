package com.stephen.online_store.controller;

import com.stephen.online_store.dto.ProductDto;
import com.stephen.online_store.entity.Product;
import com.stephen.online_store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String home() {
        return "dashboard";
    }

    @GetMapping("/add_product")
    public String addProductForm(Model model) {
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

        productService.saveProduct(product);
        return "redirect:/dashboard";
    }
}
