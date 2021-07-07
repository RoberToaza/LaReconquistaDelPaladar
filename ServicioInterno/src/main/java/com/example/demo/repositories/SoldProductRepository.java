package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.SoldProduct;

public interface SoldProductRepository extends CrudRepository<SoldProduct, Integer>{

}
