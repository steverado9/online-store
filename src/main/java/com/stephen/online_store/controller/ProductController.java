package com.stephen.online_store.controller;

import com.stephen.online_store.dto.ProductDto;
import com.stephen.online_store.entity.Product;
import com.stephen.online_store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
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

    @DeleteMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/dashboard";
    }

    @GetMapping("/product/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
       try {
           Product product = productService.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

           model.addAttribute("product", product);

       } catch (RuntimeException e) {
           model.addAttribute("error", e.getMessage());
       }

        return "product_details";
    }
}
