package com.itpm.fashionretailapi.controller.dto;

import lombok.Data;

@Data
public class OrderRequestDto {
    private String name;
    private Float price;
    private Integer stock;
    private String description;
    private String color;
    private String brand;
    private String imgUrl;
    private Float rating;
    private String reviews;
}
