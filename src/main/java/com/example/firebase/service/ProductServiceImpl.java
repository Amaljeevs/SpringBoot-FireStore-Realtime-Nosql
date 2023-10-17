package com.example.firebase.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import com.example.firebase.dto.ServiceResponse;
import com.example.firebase.entity.Product;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class ProductServiceImpl implements ProductService {

	private static final String COLLECTION_NAME = "products";

	@SuppressWarnings("null")
	@Override
	public ServiceResponse getAllProductDetails() {
		
		Firestore dbFireStore = FirestoreClient.getFirestore();
		Iterable<DocumentReference> documentReference = dbFireStore.collection(COLLECTION_NAME).listDocuments();
		Iterator<DocumentReference> iterator = documentReference.iterator();
		
		List<Product> productDetails = new ArrayList<>();
		Product product = null;
		int count = 0;
		while(iterator.hasNext()) {
			count++;
			DocumentReference documentReference1 = iterator.next();
			ApiFuture<DocumentSnapshot> future = documentReference1.get();
			DocumentSnapshot document;
			try {
				document = future.get();
				if (document.exists()) {
					product = document.toObject(Product.class);
					productDetails.add(product);
				} else {
					return new ServiceResponse("Failed", "Data Doesn't Exists", null);
				}
			} catch (InterruptedException | ExecutionException e) {
				return new ServiceResponse("Failed", "Exception Occured!", "Error : " + e.getLocalizedMessage());
			}
			
		}
		JSONObject obj = new JSONObject();
		obj.put("details", productDetails);
		return new ServiceResponse("Success", "All Details Fetched Successfully",count + " Datas fetched",obj);
	}

	@Override
	public ServiceResponse getProductDetails(String name) {
		Firestore dbFireStore = FirestoreClient.getFirestore();
		DocumentReference documentReference = dbFireStore.collection(COLLECTION_NAME).document(name);
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		try {
			DocumentSnapshot document = future.get();
			Product productObj = null;
			if (document.exists()) {
				productObj = document.toObject(Product.class);
				return new ServiceResponse("Success", "Data Fetched Successfully", productObj.toString());
			} else {
				return new ServiceResponse("Failed", "Data Doesn't Exists", null);
			}
		} catch (InterruptedException | ExecutionException e) {
			return new ServiceResponse("Failed", "Can't get data", "Error : " + e.getLocalizedMessage());
		}
	}

	@Override
	public ServiceResponse saveProduct(Product product) throws InterruptedException, ExecutionException {
		Firestore dbFireStore = FirestoreClient.getFirestore();
		DocumentReference documentReference = dbFireStore.collection(COLLECTION_NAME).document(product.getName());
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		try {
			DocumentSnapshot document = future.get();
			if (document.exists()) {
				return new ServiceResponse("Failed", "Data Already Exists", "Creation Failed");
			} else {
				ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection(COLLECTION_NAME)
						.document(product.getName()).set(product);
				return new ServiceResponse("Success", "Data Saved SuccessFully",
						"Data Saved at " + collectionApiFuture.get().getUpdateTime().toString());
			}
		} catch (InterruptedException | ExecutionException e) {
			return new ServiceResponse("Failed", "Exception Occured", "Error : " + e.getLocalizedMessage());
		}

	}

	@Override
	public ServiceResponse updateProduct(Product product) throws InterruptedException, ExecutionException {
		Firestore dbFireStore = FirestoreClient.getFirestore();
		DocumentReference documentReference = dbFireStore.collection(COLLECTION_NAME).document(product.getName());
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		try {
			DocumentSnapshot document = future.get();
			if (document.exists()) {
				ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection(COLLECTION_NAME)
						.document(product.getName()).set(product);
				return new ServiceResponse("Success", "Data Updated SuccessFully",
						"Data Updated at " + collectionApiFuture.get().getUpdateTime().toString());
			} else {
				return new ServiceResponse("Failed", "No data found for given product name", "Updation Failed");
			}
		} catch (InterruptedException | ExecutionException e) {
			return new ServiceResponse("Failed", "Exception Occured", "Error : " + e.getLocalizedMessage());
		}

	}

	@Override
	public ServiceResponse deleteProductDetails(String name) {
		Firestore dbFireStore = FirestoreClient.getFirestore();
		DocumentReference documentReference = dbFireStore.collection(COLLECTION_NAME).document(name);
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		try {
			DocumentSnapshot document = future.get();
			if (document.exists()) {
				ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection(COLLECTION_NAME).document(name)
						.delete();
				return new ServiceResponse("Success", "Data Deleted SuccessFully",
						"Data Deleted at " + collectionApiFuture.get().getUpdateTime().toString());
			} else {
				return new ServiceResponse("Failed", "No data found for given product name", "Deletion Failed");
			}
		} catch (InterruptedException | ExecutionException e) {
			return new ServiceResponse("Failed", "Exception Occured", "Error : " + e.getLocalizedMessage());
		}
	}

}