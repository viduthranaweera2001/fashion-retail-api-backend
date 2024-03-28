package com.itpm.fashionretailapi.controller.dto;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String name;
    private String sup_name;
    private Float price;
    private Integer stock;
    private String description;
    private String color;
    private String brand;
    private String imgUrl;
    private Float rating;
    private String reviews;
}
