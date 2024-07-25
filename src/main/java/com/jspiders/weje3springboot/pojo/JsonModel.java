package com.jspiders.weje3springboot.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class JsonModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    private String jsonModel;
}

