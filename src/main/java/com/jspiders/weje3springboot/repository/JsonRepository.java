package com.jspiders.weje3springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jspiders.weje3springboot.pojo.JsonModel;

public interface JsonRepository extends JpaRepository<JsonModel, Long>{

}
