package com.itpm.fashionretailapi.service.impl;

import com.itpm.fashionretailapi.controller.dto.CartDto;
import com.itpm.fashionretailapi.controller.request.CartRequest;
import com.itpm.fashionretailapi.controller.response.CartResponse;
import com.itpm.fashionretailapi.controller.response.SupplierResponse;
import com.itpm.fashionretailapi.exception.ProductNotFound;
import com.itpm.fashionretailapi.model.Cart;
import com.itpm.fashionretailapi.model.Product;
import com.itpm.fashionretailapi.model.User;
import com.itpm.fashionretailapi.repository.CartRepository;
import com.itpm.fashionretailapi.repository.ProductRepository;
import com.itpm.fashionretailapi.repository.UserRepository;
import com.itpm.fashionretailapi.service.CartService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor

public class CartImpl implements CartService {
    private ModelMapper modelMapper;
    CartRepository cartRepository;
    ProductRepository productRepository;
    UserRepository userRepository;

    @Override
    public CartResponse additems(Long id, CartDto cartDto) throws ProductNotFound {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFound("Product not found")
        );
        User user = userRepository.findByName(cartDto.getName());

        if (user == null)
            throw new ProductNotFound("User not found");
        Cart cart = new Cart();
        cart.setPrice((product.getPrice()* cartDto.getQuantity()));
        cart.setQuantity(cartDto.getQuantity());
        cart.setProduct(product);
        cart.setUser(user);
        System.out.println("cart");
        cartRepository.save(cart);

        return CartResponse.builder()
                .responsgeMsg(product.getName() + " added to cart with id: " + cart.getId())
                .build();
    }

    @Override
    public CartResponse deleteitems(Long id) throws ProductNotFound {
        Cart cart = cartRepository.findById(id).orElseThrow(
                () -> new ProductNotFound("Cart id not found")
        );
        cartRepository.deleteById(id);

        return CartResponse.builder()
                .responseMsg(cart.getId() + " Deleted!")
                .build();
    }

    @Override
    public CartResponse updatecart(Long id, CartRequest cartRequest) throws ProductNotFound {

        Cart cart = cartRepository.findById(id).orElseThrow(
                () -> new ProductNotFound("Cart id not found")
        );
        User user = userRepository.findByName(cartRequest.getName());
        cartRepository.findById(id)
                .map(newCart->{
                    newCart.setQuantity(cartRequest.getQuantity());
                    cartRepository.save(newCart);
                            return null;
                        }
                        );

        return CartResponse.builder()
                .responseMsg(" Cart details of id "+cart.getId()+" updated")
                .build();
    }
}
