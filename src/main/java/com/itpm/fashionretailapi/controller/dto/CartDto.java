package com.itpm.fashionretailapi.controller.dto;

import com.itpm.fashionretailapi.model.Product;
import com.itpm.fashionretailapi.model.User;
import lombok.Data;

@Data
public class CartDto {

     private Long id;
     private String name;
     private Integer quantity;

}
