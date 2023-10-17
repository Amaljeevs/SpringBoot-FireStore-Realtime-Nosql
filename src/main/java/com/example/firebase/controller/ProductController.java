package com.example.firebase.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.firebase.dto.ServiceResponse;
import com.example.firebase.entity.Product;
import com.example.firebase.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@Api(tags = "RESTApi for Product Details",description = "Api For Managing Product Details.")
@RequestMapping("/api/v1")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Operation(summary = "Getting Complete Product Details.", description = "This endpoint gets all the product details saved.")
	@ApiOperation(value = "Getting Complete Product Details.", response = ServiceResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "HTTP STATUS 200 : All Data Fetched Successfully."),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 400, message = "Invalid request message"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping(value = "/getProductDetails", produces = "application/json")
	public ResponseEntity<ServiceResponse> getAllProductDetailsApi() throws InterruptedException, ExecutionException {
		ServiceResponse response = productService.getAllProductDetails();
		return new ResponseEntity<ServiceResponse>(response, new HttpHeaders(), HttpStatus.OK);

	}
	
	@GetMapping("/getProductDetails/{name}")
	@Operation(summary = "Getting a Product Detail.", description = "This endpoint gets a particular product details.")
	@ApiOperation(value = "Getting a Product Details.", response = ServiceResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "HTTP STATUS 200 : Data Fetched Successfully."),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 400, message = "Invalid request message"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity<ServiceResponse> getProductDetailsApi(@PathVariable String name) throws InterruptedException, ExecutionException {

		ServiceResponse response = productService.getProductDetails(name);
		return new ResponseEntity<ServiceResponse>(response, new HttpHeaders(), HttpStatus.OK);

	}

	@PostMapping("/addProduct")
	@Operation(summary = "Adding/Saving a Product Detail.", description = "This endpoint Saves a product into the Database.")
	@ApiOperation(value = "Adding/Saving a Product Detail.", response = ServiceResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "HTTP STATUS 200 : Data Saved Successfully."),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 400, message = "Invalid request message"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity<ServiceResponse> saveProductApi(@RequestBody Product product) throws InterruptedException, ExecutionException {
		ServiceResponse response = productService.saveProduct(product);
		return new ResponseEntity<ServiceResponse>(response, new HttpHeaders(), HttpStatus.OK);

	}
	
	@PutMapping("/updateProductDetails")
	@Operation(summary = "Update a Product Details.", description = "This endpoint updates a particular Product Details.")
	@ApiOperation(value = "Update a Product Details.", response = ServiceResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "HTTP STATUS 200 : Data Updated Successfully."),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 400, message = "Invalid request message"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity<ServiceResponse> updateProductApi(@RequestBody Product product) throws InterruptedException, ExecutionException {
		ServiceResponse response = productService.updateProduct(product);
		return new ResponseEntity<ServiceResponse>(response, new HttpHeaders(), HttpStatus.OK);

	}
	
	@DeleteMapping("/deleteProductDetails/{name}")
	@Operation(summary = "Delete a Product Detail.", description = "This endpoint deletes a particular product detail.")
	@ApiOperation(value = "Delete a Product Detail.", response = ServiceResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "HTTP STATUS 200 : Data Deleted Successfully."),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 400, message = "Invalid request message"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity<ServiceResponse> deleteProductApi(@PathVariable String name) throws InterruptedException, ExecutionException {
		ServiceResponse response = productService.deleteProductDetails(name);
		return new ResponseEntity<ServiceResponse>(response, new HttpHeaders(), HttpStatus.OK);

	}

}
