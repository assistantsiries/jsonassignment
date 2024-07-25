package com.jspiders.weje3springboot.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jspiders.weje3springboot.pojo.JsonModel;
import com.jspiders.weje3springboot.repository.JsonRepository;

@Service
public class JsonService {
	
	@Autowired
	private JsonRepository repository;
    
	private final ObjectMapper mapper=new ObjectMapper();
	
	public String manipulateJson(List<Map<String,String>> replacements) throws Exception{
		String jsonModel="{ \"menu\": { \"id\": \"file\","
				+ " \"value\": \"file\", \"popup\": {"
				+ " \"menuitem\": [ { \"value\": \"New\", \"onclick\": \"CreateDoc()\" },"
				+ "{ \"value\": \"Open\", \"onclick\": \"OpenDoc()\" },"
				+ "{ \"value\": \"Save\", \"onclick\": \"SaveDoc()\" } ] } } }";
			
		JsonNode root=mapper.readTree(jsonModel);
		
		
		for(Map<String,String> replacement:replacements) {
		
			String key=replacement.get("Key");
			String value=replacement.get("value");
	       replaceValue(root, key, value);
		}
		String modifiedJson=mapper.writeValueAsString(root);
		
		JsonModel jsonModelEntity=new JsonModel();
		jsonModelEntity.setJsonModel(modifiedJson);
		
		repository.save(jsonModelEntity);

		return modifiedJson;
	}
	
	private void replaceValue(JsonNode root, String key, String value) {
		if(root.isObject()) {
			root.fields().forEachRemaining(entry ->{
				if(entry.getKey().equals(key)) {
					((ObjectNode)root).put(key, value);
				}else {
					replaceValue(entry.getValue(), key, value);
				}
				
			});
		}else if(root.isArray()) {
			root.forEach(node -> replaceValue(node, key, value));
			
		}
	}
	

}
