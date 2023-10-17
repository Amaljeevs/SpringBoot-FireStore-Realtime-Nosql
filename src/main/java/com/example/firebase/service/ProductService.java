package com.example.firebase.service;

import java.util.concurrent.ExecutionException;

import com.example.firebase.dto.ServiceResponse;
import com.example.firebase.entity.Product;

public interface ProductService {

	ServiceResponse saveProduct(Product product) throws InterruptedException, ExecutionException;

	ServiceResponse getProductDetails(String name);
	
	ServiceResponse updateProduct(Product product) throws InterruptedException, ExecutionException;

	ServiceResponse deleteProductDetails(String name);

	ServiceResponse getAllProductDetails();
}
