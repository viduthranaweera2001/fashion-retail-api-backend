package com.itpm.fashionretailapi.service.impl;

import com.itpm.fashionretailapi.controller.request.SupplierRequest;
import com.itpm.fashionretailapi.controller.response.IdResponse;
import com.itpm.fashionretailapi.controller.response.SupplierResponse;
import com.itpm.fashionretailapi.exception.CustomerNotFoundException;
import com.itpm.fashionretailapi.exception.SupplierNotFoundException;
import com.itpm.fashionretailapi.model.Supplier;
import com.itpm.fashionretailapi.model.User;
import com.itpm.fashionretailapi.repository.SupplierRepository;
import com.itpm.fashionretailapi.service.SupplierService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private SupplierRepository supplierRepository;
    private ModelMapper modelMapper;
    @Override
    public SupplierResponse createSupplier(SupplierRequest supplierRequest) {
        Supplier supplier = modelMapper.map(supplierRequest, Supplier.class);
        supplierRepository.save(supplier);

        SupplierResponse supplierResponse = modelMapper.map(supplier,SupplierResponse.class);
        return supplierResponse;
    }


    @Override
    public SupplierResponse getSupplierDetailsById(Long id) throws SupplierNotFoundException {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(
                () -> new SupplierNotFoundException("Supplier Not Found With Id "+id)
        );

        SupplierResponse supplierResponse = modelMapper.map(supplier, SupplierResponse.class);
        return supplierResponse;
    }

    @Override
    public List<SupplierResponse> getAllSuppliers() throws SupplierNotFoundException {
        List<Supplier> supplierList = supplierRepository.findAll();

        if(supplierList.isEmpty()){
              throw new SupplierNotFoundException("No Any Customer Found Here");
        }
        return supplierList.stream()
                .map((supplierDetails -> modelMapper.map(supplierDetails,SupplierResponse.class)))
                .collect(Collectors.toList());
    }


    @Override
    public SupplierResponse updateDetails(Long id, SupplierRequest supplierRequest) throws SupplierNotFoundException {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(
                () -> new SupplierNotFoundException("Supplier  Not Found With Id "+id)
        );

        supplier.setEmail(supplierRequest.getName());
        supplier.setEmail(supplierRequest.getEmail());
        supplier.setPhoneNo(supplierRequest.getPhoneNo());
        supplier.setNic(supplierRequest.getNic());

        supplierRepository.save(supplier);

        SupplierResponse supplierResponse = modelMapper.map(supplier, SupplierResponse.class);

        return supplierResponse;
    }

    @Override
    public IdResponse deleteSupplier(Long id) throws SupplierNotFoundException {

        Supplier supplier = supplierRepository.findById(id).orElseThrow(
                () -> new SupplierNotFoundException("Supplier  Not Found With Id "+id)
        );

        supplierRepository.deleteById(id);

        return IdResponse.builder()
                .message(supplier.getName()+" Deleted!")
                .build();
//        IdResponse idResponse = new IdResponse();
//        idResponse.setMessage(supplier.getName()+" Deleted!");
//        return idResponse;
    }
}
