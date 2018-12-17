package com.sergioal.tpaga.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PaymentsRequestDTO implements Serializable {
	
	
	private static final long serialVersionUID = -2857965474896883268L;
	
	private String cost;
	private String expires_at;
	private String idempotency_token;
	private String merchant_user_id;
	private String miniapp_user_token;
	private String order_id;
	private String purchase_description;
	private String purchase_details_url;
	private PurchaseItemsDTO purchase_items;
	private String terminal_id;
	private String user_ip_address;
	private String voucher_url;
	private String status;
	private String token;
	private String cancelled_at;
	private String checked_by_merchant_at;
	private String delivery_notification_at;
	private String tpaga_payment_url;
	
	
	
	public String getTpaga_payment_url() {
		return tpaga_payment_url;
	}
	public void setTpaga_payment_url(String tpaga_payment_url) {
		this.tpaga_payment_url = tpaga_payment_url;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getCancelled_at() {
		return cancelled_at;
	}
	public void setCancelled_at(String cancelled_at) {
		this.cancelled_at = cancelled_at;
	}
	public String getChecked_by_merchant_at() {
		return checked_by_merchant_at;
	}
	public void setChecked_by_merchant_at(String checked_by_merchant_at) {
		this.checked_by_merchant_at = checked_by_merchant_at;
	}
	public String getDelivery_notification_at() {
		return delivery_notification_at;
	}
	public void setDelivery_notification_at(String delivery_notification_at) {
		this.delivery_notification_at = delivery_notification_at;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getExpires_at() {
		return expires_at;
	}
	public void setExpires_at(String expires_at) {
		this.expires_at = expires_at;
	}
	public String getIdempotency_token() {
		return idempotency_token;
	}
	public void setIdempotency_token(String idempotency_token) {
		this.idempotency_token = idempotency_token;
	}
	public String getMerchant_user_id() {
		return merchant_user_id;
	}
	public void setMerchant_user_id(String merchant_user_id) {
		this.merchant_user_id = merchant_user_id;
	}
	public String getMiniapp_user_token() {
		return miniapp_user_token;
	}
	public void setMiniapp_user_token(String miniapp_user_token) {
		this.miniapp_user_token = miniapp_user_token;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getPurchase_description() {
		return purchase_description;
	}
	public void setPurchase_description(String purchase_description) {
		this.purchase_description = purchase_description;
	}
	public String getPurchase_details_url() {
		return purchase_details_url;
	}
	public void setPurchase_details_url(String purchase_details_url) {
		this.purchase_details_url = purchase_details_url;
	}
	public PurchaseItemsDTO getPurchase_items() {
		return purchase_items;
	}
	public void setPurchase_items(PurchaseItemsDTO purchase_items) {
		this.purchase_items = purchase_items;
	}
	public String getTerminal_id() {
		return terminal_id;
	}
	public void setTerminal_id(String terminal_id) {
		this.terminal_id = terminal_id;
	}
	public String getUser_ip_address() {
		return user_ip_address;
	}
	public void setUser_ip_address(String user_ip_address) {
		this.user_ip_address = user_ip_address;
	}
	public String getVoucher_url() {
		return voucher_url;
	}
	public void setVoucher_url(String voucher_url) {
		this.voucher_url = voucher_url;
	}
	
	
	

}
