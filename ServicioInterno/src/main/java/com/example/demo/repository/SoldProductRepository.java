package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.SoldProduct;

public interface SoldProductRepository extends CrudRepository<SoldProduct, Integer>{

}
