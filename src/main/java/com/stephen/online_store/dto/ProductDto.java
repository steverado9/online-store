package com.stephen.online_store.dto;

import jakarta.validation.constraints.NotEmpty;

public class ProductDto {

    private Long id;

    @NotEmpty(message = "title should not be empty")
    private String title;

    @NotEmpty(message = "price should not be empty")
    private Double price;

    @NotEmpty(message = "description should not be empty")
    private String description;

    @NotEmpty(message = "category should not be empty")
    private String category;

    @NotEmpty(message = "image should not be empty")
    private String image;

    @NotEmpty(message = "rating should not be empty")
    private Double rating;

    public ProductDto() {
    }

    public ProductDto(Long id, String title, Double price, String description, String category, String image, Double rating) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.image = image;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
