package com.sergioal.tpaga.model;

import java.util.Date;




public class PaymentsCreatedModel {
	private String id;
	private String idempotency_token;
	private String cost;
	private String tpaga_token;
	private String status;
	private Date date;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getIdempotency_token() {
		return idempotency_token;
	}
	public void setIdempotency_token(String idempotency_token) {
		this.idempotency_token = idempotency_token;
	}
	
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	
	public String getTpaga_token() {
		return tpaga_token;
	}
	public void setTpaga_token(String tpaga_token) {
		this.tpaga_token = tpaga_token;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	

}
