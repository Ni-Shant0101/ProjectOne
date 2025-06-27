package com.solutions.crm.commom.responses;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonFormat;

public class JsonResponses {
	
	@JsonFormat(timezone="dd-MM-yyyy ' T ' HH:mm:ss ", locale = "Locale.ENGLISH")
	static
	LocalDateTime today = LocalDateTime.now();
	static String time=today.toString();
	
	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", message);
			map.put("TimeStamp", time);
            map.put("status", status.value());
            map.put("data", responseObj);

            return new ResponseEntity<Object>(map,status);
    }
	
	public static Map<String, Object> generateResponse1(Boolean code,Object responseObj, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        	map.put("Success", code);
        	map.put("data", responseObj);
            map.put("message", message);
            
			//return new ResponseEntity<Object>(map,status);
			return map;
    }
	
	public static Map<String, Object> generateResponse2(Boolean code, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        	map.put("Success", code);
            map.put("message", message);
            
			return map;
    }
	
	public static ResponseEntity<Object> RequestResponse( HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
       
            map.put("status", status.value());
            map.put("data", responseObj);

            return new ResponseEntity<Object>(map,status);
    }

	public static Map<String, Object> generateResponse2(Boolean code,Object responseObj,String url, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        	map.put("Success", code);
        	map.put("data", responseObj);
            map.put("message", message);
            map.put("url", url);
			//return new ResponseEntity<Object>(map,status);
			return map;
    }
	
	public static Map<String, Object> generateResponse3(Boolean code,Object responseObj,int category_id, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        	map.put("Success", code);
        	map.put("data", responseObj);
            map.put("message", message);
            map.put("category_id", category_id);
			//return new ResponseEntity<Object>(map,status);
			return map;
    }
}
