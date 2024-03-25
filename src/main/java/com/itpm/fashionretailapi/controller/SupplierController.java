package com.itpm.fashionretailapi.controller;

import com.itpm.fashionretailapi.controller.request.SupplierRequest;
import com.itpm.fashionretailapi.controller.response.IdResponse;
import com.itpm.fashionretailapi.controller.response.SupplierResponse;
import com.itpm.fashionretailapi.exception.SupplierNotFoundException;
import com.itpm.fashionretailapi.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class SupplierController {
    private SupplierService supplierService;
    @PostMapping("/suppliers")
    public SupplierResponse createSupplier(@RequestBody SupplierRequest supplierRequest){
        return supplierService.createSupplier(supplierRequest);
    }

    @GetMapping("/suppliers/{id}")
    public SupplierResponse getSupplierDetailsById(@PathVariable  Long id) throws SupplierNotFoundException {
        return supplierService.getSupplierDetailsById(id);
    }
    @GetMapping("/suppliers")
    public List<SupplierResponse> getAllSuppliers() throws  SupplierNotFoundException{
        return supplierService.getAllSuppliers();
    }

    @PutMapping("/suppliers/{id}")
    public SupplierResponse updateSupplierDetails(@PathVariable Long id,@RequestBody SupplierRequest supplierRequest)throws SupplierNotFoundException{
        return supplierService.updateDetails(id, supplierRequest);
    }


    @DeleteMapping("/suppliers/{id}")
    public IdResponse deleteSupplier(@PathVariable  Long id) throws SupplierNotFoundException{
        return supplierService.deleteSupplier(id);
    }

}
