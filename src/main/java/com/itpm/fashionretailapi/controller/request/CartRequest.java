package com.itpm.fashionretailapi.controller.request;

import com.itpm.fashionretailapi.model.Product;
import com.itpm.fashionretailapi.model.User;
import lombok.Data;

@Data
public class CartRequest {
    private String name;
    private Integer quantity;

}
