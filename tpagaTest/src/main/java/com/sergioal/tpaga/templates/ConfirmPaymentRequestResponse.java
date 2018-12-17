package com.sergioal.tpaga.templates;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.sergioal.tpaga.dto.PaymentsRequestDTO;
import com.sergioal.tpaga.model.PaymentsCreatedModel;
import com.sergioal.tpaga.util.JsonFile;

@Service
public class ConfirmPaymentRequestResponse {
private String urlPost = "https://stag.wallet.tpaga.co/merchants/api/v1/payment_requests/";
	
	
	public PaymentsRequestDTO transaction(String token) {
		
	
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		String encode = encode();
		
		headers.add("Authorization", " Basic "+encode);
		headers.add("Cache-Control" , "no-cache");
		headers.add("Content-Type", "application/json");
		
		urlPost = urlPost+token+"/info";
		
				
		HttpEntity<String> request = new HttpEntity<>(headers);
		
		
		try {
			ResponseEntity<PaymentsRequestDTO>  response = restTemplate.exchange(urlPost, HttpMethod.GET, request, PaymentsRequestDTO.class);
			if(response.getBody().getStatus().equalsIgnoreCase("delivered") || response.getBody().getStatus().equalsIgnoreCase("paid")) {
				PaymentsCreatedModel payments = new PaymentsCreatedModel();
				payments.setIdempotency_token(response.getBody().getIdempotency_token());
				payments.setCost(response.getBody().getCost());
				payments.setTpaga_token(response.getBody().getToken());
				payments.setStatus(response.getBody().getStatus());
				JsonFile jsonData = new JsonFile();
				jsonData.JSONFileWriter(payments);
				jsonData.JSONReader(response.getBody().getIdempotency_token());
				
				System.out.println("esto es readAll: "+jsonData.JSONReaderAll());
				
			}
		 
		return response.getBody();
			
			
		}catch(RestClientException e ){
		   
		     if(e instanceof HttpStatusCodeException){
		         String errorResponse=((HttpStatusCodeException)e).getResponseBodyAsString();
		        
		         System.out.println("esto es error response" + errorResponse);
		         return null;
		         
		     }else {
		    	 System.out.println("esto es error extraño "+e);
		    	 return null;
		     }

		  }
		
	
	}
	
	public PaymentsRequestDTO reversePayment(String token) {
		
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		String encode = encode();
		
		headers.add("Authorization", " Basic "+encode);
		headers.add("Cache-Control" , "no-cache");
		headers.add("Content-Type", "application/json");
		
		urlPost = urlPost+"/refund";
		
		Map<String,String> bodyReq = new HashMap<String, String>();
		bodyReq.put("payment_request_token", token);
		HttpEntity<Map> request = new HttpEntity<>(bodyReq,headers);
		
		
		
		
		try {
			ResponseEntity<PaymentsRequestDTO>  response = restTemplate.postForEntity(urlPost,  request, PaymentsRequestDTO.class);
			if(response.getBody().getStatus().equalsIgnoreCase("reverted")) {
				PaymentsCreatedModel payments = new PaymentsCreatedModel();
				payments.setIdempotency_token(response.getBody().getIdempotency_token());
				payments.setCost(response.getBody().getCost());
				payments.setTpaga_token(response.getBody().getToken());
				payments.setStatus(response.getBody().getStatus());
				JsonFile jsonData = new JsonFile();
				jsonData.JSONUpdater(response.getBody().getIdempotency_token(), payments);
				
				jsonData.JSONReader(response.getBody().getIdempotency_token());
				
			}
		 
		return response.getBody();
			
			
		}catch(RestClientException e ){
		   
		     if(e instanceof HttpStatusCodeException){
		         String errorResponse=((HttpStatusCodeException)e).getResponseBodyAsString();
		        
		         System.out.println("esto es error response" + errorResponse);
		         return null;
		         
		     }else {
		    	 System.out.println("esto es error extraño "+e);
		    	 return null;
		     }

		  }
		
	
	}
	
	

	
	private String encode() {
		String secHeader = "miniapp-gato2:miniappma-123";
		Base64 base64 = new Base64();
		String encodedVersion = new String(base64.encode(secHeader.getBytes()));
		return encodedVersion;
	}
	
	

}
