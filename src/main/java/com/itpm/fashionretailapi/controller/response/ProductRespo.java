package com.itpm.fashionretailapi.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRespo {
    private Long id;
    private String name;
    private Float price;
    private Integer stock;
    private String description;
    private String color;
    private String brand;
    private String imgUrl;

}
