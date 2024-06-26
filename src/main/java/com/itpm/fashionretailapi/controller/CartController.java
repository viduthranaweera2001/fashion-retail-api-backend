package com.itpm.fashionretailapi.controller;

import com.itpm.fashionretailapi.controller.dto.CartDto;
import com.itpm.fashionretailapi.controller.request.CartRequest;
import com.itpm.fashionretailapi.controller.response.CartResponse;
import com.itpm.fashionretailapi.exception.ProductNotFound;
import com.itpm.fashionretailapi.model.Product;
import com.itpm.fashionretailapi.model.User;
import com.itpm.fashionretailapi.service.CartService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CartController {
    private CartService cartService;
    private ModelMapper modelMapper;

    @PostMapping("products/{product-id}/cartitems")
    public ResponseEntity<CartResponse> additems(@PathVariable("product-id")Long id,@RequestBody CartRequest cartRequest)throws ProductNotFound {
        CartDto cartDto = modelMapper.map(cartRequest,CartDto.class);
        CartResponse cartResponse = cartService.additems(id,cartDto);
        return ResponseEntity.status(HttpStatus.OK).body(cartResponse);
    }
    @DeleteMapping("/cart/{cart_id}")
    public CartResponse deleteitems(@PathVariable ("cart_id")Long id)throws ProductNotFound{
        return cartService.deleteitems(id);
    }

    @PutMapping("/product/{cart-id}/editdetails")
    public CartResponse updatecart(@PathVariable ("cart-id") Long id,@RequestBody CartRequest cartRequest) throws ProductNotFound{
//        CartDto cartDto =modelMapper.map(cartRequest,CartDto.class);
//        CartResponse cartResponse=cartService.updatecart(id,cartRequest);
        return cartService.updatecart(id,cartRequest);
    }
}
