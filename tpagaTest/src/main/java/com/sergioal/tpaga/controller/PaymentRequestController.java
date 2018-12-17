package com.sergioal.tpaga.controller;



import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sergioal.tpaga.dto.PaymentsRequestDTO;
import com.sergioal.tpaga.model.PaymentsCreatedModel;
import com.sergioal.tpaga.templates.ConfirmPaymentRequestResponse;
import com.sergioal.tpaga.templates.PaymentsRequestResponseTemplate;
import com.sergioal.tpaga.util.JsonFile;

@RestController
@RequestMapping("/tienda")
public class PaymentRequestController {
	
	@RequestMapping("/crearPago")
	public PaymentsRequestDTO crearPago(@RequestBody String valor) {
		PaymentsRequestResponseTemplate payRequest = new PaymentsRequestResponseTemplate();
		System.out.println("esto es el valor: "+ valor);
		String[] cadena = valor.split("=");
		String valorBack = cadena[1];
		//ResponseEntity<PaymentsRequestDTO>
		//payRequest.transaction(valorBack);
		return payRequest.transaction(valorBack);
	}
	@RequestMapping("/confirmarPago")
	public PaymentsRequestDTO confirmarPago(@RequestBody String token) {
		ConfirmPaymentRequestResponse confRequest = new ConfirmPaymentRequestResponse();
		System.out.println("aqui esta confirmar pago, esto es idemToken"+token);
		String[] cadena = token.split("=");
		String valorBack = cadena[1];
		return confRequest.transaction(valorBack);
	}
	
	@RequestMapping("/listarPagos")
	public List<PaymentsCreatedModel> listarPagos(){
	JsonFile jsonData = new JsonFile();
	return jsonData.JSONReaderAll();
	}
	
	@RequestMapping("/reversarPagos")
	public PaymentsRequestDTO reversarPagos(@RequestBody String token) {
		ConfirmPaymentRequestResponse confRequest = new ConfirmPaymentRequestResponse();
		return confRequest.reversePayment(token);
	}

}
