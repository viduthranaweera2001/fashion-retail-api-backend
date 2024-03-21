package com.itpm.fashionretailapi.controller.dto;

import lombok.Data;

@Data
public class ProductCategoryRequestDto {
    private Long id;
    private String name;
    private String description;
}
