package com.itpm.fashionretailapi.controller.request;

import lombok.Data;

@Data
public class ProductCategoryRequset {
    private Long id;
    private String name;
    private String description;
}
