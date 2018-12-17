package com.sergioal.tpaga.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergioal.tpaga.model.PaymentsCreatedModel;

public class JsonFile {
	//esto es un create/ insert
	public void JSONFileWriter(PaymentsCreatedModel payments) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		Date date = new Date();
		JSONObject obj = new JSONObject();
		obj.put("idempotency_token", payments.getIdempotency_token());
		obj.put("date", formatter.format(date));
		obj.put("cost", payments.getCost());
		obj.put("tpaga_token", payments.getTpaga_token());
		
		
		
		try{
			File f = new File("transaction.json");

			if(f.exists()) {
				FileReader fReader = new FileReader(f);
				if(fReader.read()>0) {
				System.out.println("dentro del si existe");
				ObjectMapper mapper = new ObjectMapper();
				JSONObject jsonObject = mapper.readValue(new File("transaction.json"), JSONObject.class);
				System.out.println("aqui abrio el archivo");
				jsonObject.put(payments.getIdempotency_token(), obj);
				System.out.println("aqui escribio en el creo el jsonObject"+jsonObject);
				FileWriter file = new FileWriter("transaction.json");
				file.write(jsonObject.toJSONString());
				System.out.println("aqui escribio el archivo que ya existe: ");
				file.close();
				}
			}else {
				System.out.println("dentro del no existe");
				JSONObject jsonObject = new JSONObject();
				jsonObject.put(payments.getIdempotency_token(), obj);
				FileWriter file = new FileWriter("transaction.json");
				file.write(jsonObject.toJSONString());
				file.close();
			}
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//esto es un selectById
	public PaymentsCreatedModel JSONReader(String idemToken) {
		JSONParser jsonParser = new JSONParser();
		PaymentsCreatedModel returnedPayments = new PaymentsCreatedModel();
		try {
			Object obj  = jsonParser.parse(new FileReader("transaction.json"));
			JSONObject jsonObject = (JSONObject) obj;
			JSONObject jsonObject2 = (JSONObject) jsonObject.get(idemToken);
			Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse((String) jsonObject2.get("date"));
			returnedPayments.setCost((String) jsonObject2.get("cost"));
			returnedPayments.setDate(date);
			returnedPayments.setIdempotency_token((String) jsonObject2.get("idempotency_token"));
			returnedPayments.setTpaga_token((String) jsonObject2.get("idempotency_token"));
			return returnedPayments;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	//esto es un findAll
	public List<PaymentsCreatedModel> JSONReaderAll() {
		JSONParser jsonParser = new JSONParser();
		PaymentsCreatedModel returnedPayments = new PaymentsCreatedModel();
		try {
			Object obj  = jsonParser.parse(new FileReader("transaction.json"));
			JSONObject jsonObject = (JSONObject) obj;
			Iterator<JSONObject> jsonIterator = jsonObject.values().iterator();
			List<PaymentsCreatedModel> iteratorPaymentsCreatedModel = new ArrayList<PaymentsCreatedModel>();
			while(jsonIterator.hasNext()) {
			JSONObject jsonObject2 = jsonIterator.next();
			Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse((String) jsonObject2.get("date"));
			
			returnedPayments.setCost((String) jsonObject2.get("cost"));
			returnedPayments.setDate(date);
			returnedPayments.setIdempotency_token((String) jsonObject2.get("idempotency_token"));
			returnedPayments.setTpaga_token((String) jsonObject2.get("idempotency_token"));
			iteratorPaymentsCreatedModel.add(returnedPayments);
			
			}
			return iteratorPaymentsCreatedModel;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	//esto es un update
	public void JSONUpdater(String IdemToken, PaymentsCreatedModel payments) {
		JSONObject obj = new JSONObject();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		Date date = new Date();
		
		
		obj.put("idempotency_token", payments.getIdempotency_token());
		obj.put("date", formatter.format(date));
		obj.put("cost", payments.getCost());
		obj.put("tpaga_token", payments.getTpaga_token());
		
		
		ObjectMapper mapper = new ObjectMapper();
		JSONObject jsonObject;
		try {
			jsonObject = mapper.readValue(new File("transaction.json"), JSONObject.class);
			jsonObject.get(IdemToken);
			jsonObject.put(IdemToken, obj);
			FileWriter file = new FileWriter("transaction.json");
			file.write(jsonObject.toJSONString());
			file.close();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
