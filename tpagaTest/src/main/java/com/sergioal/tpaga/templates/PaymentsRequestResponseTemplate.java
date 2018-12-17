package com.sergioal.tpaga.templates;

import java.util.Date;

import org.apache.commons.codec.binary.Base64;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


import com.sergioal.tpaga.dto.PaymentsRequestDTO;



@Service
public class PaymentsRequestResponseTemplate {
	private String urlPost = "https://stag.wallet.tpaga.co/merchants/api/v1/payment_requests/create";
	

	public PaymentsRequestDTO transaction(String valor) {
		
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		String encode = encode();
		//headers.add("Autorization", "Basic "+ encode);bWluaWFwcG1hLW1pbmltYWw6YWJjMTIz
		headers.add("Authorization", " Basic "+encode);
		headers.add("Cache-Control" , "no-cache");
		headers.add("Content-Type", "application/json");
		String idemToken = generateIdempotencyToken();
		PaymentsRequestDTO payRequest = new PaymentsRequestDTO();
		payRequest.setCost(valor);
		//payRequest.setPurchase_details_url("https://192.168.0.3:9443/tienda/"+idemToken);
		payRequest.setPurchase_details_url("https://192.168.0.7:9443/generic.html");
		//payRequest.setVoucher_url("https://example.org/voucher/1235");
		payRequest.setIdempotency_token(idemToken);
		payRequest.setOrder_id("00001");
		payRequest.setTerminal_id("caja 1");
		//payRequest.setPurchase_items(" ");
		payRequest.setUser_ip_address("61.1.2.56");
		payRequest.setExpires_at("2018-12-05T20:10:57.549653+00:00");
		payRequest.setPurchase_description("test 1 de integracion");
		payRequest.setMiniapp_user_token("ognh-pp12167233-364545");
		
		HttpEntity<PaymentsRequestDTO> request = new HttpEntity<>(payRequest, headers);
		System.out.println("esto es request: "+ request.getBody());
		
		try {
			ResponseEntity<PaymentsRequestDTO>  response = restTemplate.postForEntity(urlPost, request, PaymentsRequestDTO.class);
			//ResponseEntity<PaymentsRequestDTO> response = restTemplate.exchange(urlPost, HttpMethod.POST,  request, PaymentsRequestDTO.class);
			System.out.println("esto es response"+response.getBody().getStatus());
		 
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
	
	private String generateIdempotencyToken() {
		String idempotencyToken = "tpaga-sergioaltoken-";
		Date date = new Date();
		Long data = date.getTime();
		idempotencyToken = idempotencyToken+data;
		return idempotencyToken;
	}
	

}
