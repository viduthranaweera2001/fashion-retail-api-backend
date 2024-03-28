package com.itpm.fashionretailapi.service;

import com.itpm.fashionretailapi.controller.request.SupplierRequest;
import com.itpm.fashionretailapi.controller.response.IdResponse;
import com.itpm.fashionretailapi.controller.response.SupplierResponse;
import com.itpm.fashionretailapi.exception.CustomerNotFoundException;
import com.itpm.fashionretailapi.exception.SupplierNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupplierService {
    SupplierResponse createSupplier(SupplierRequest supplierRequest);

    SupplierResponse getSupplierDetailsById(Long id) throws SupplierNotFoundException;

    List<SupplierResponse> getAllSuppliers() throws SupplierNotFoundException;

    SupplierResponse updateDetails(Long id, SupplierRequest supplierRequest) throws SupplierNotFoundException;

    IdResponse deleteSupplier(Long id) throws SupplierNotFoundException;
}
