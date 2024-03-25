package com.itpm.fashionretailapi.controller.response;

import lombok.Data;

@Data
public class ProductRespo {
    private String name;
    private Float price;
    private Integer stock;
    private String description;
    private String color;
    private String brand;
    private String imgUrl;

}
