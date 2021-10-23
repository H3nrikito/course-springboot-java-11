package com.coursejava.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coursejava.course.entities.Product;
import com.coursejava.course.services.ProductService;

@RestController
@RequestMapping(value = "/products") //nome do recurso
public class ProductResource {
	
	@Autowired
	private ProductService service;
	
	@GetMapping //requisição do tipo get
	public ResponseEntity<List<Product>> findAll(){
		List<Product> list = service.findAll();
		return ResponseEntity.ok().body(list);
		}
	
	@GetMapping(value = "/{id}") // aceitar id na busca na url get
	public ResponseEntity<Product> findById(@PathVariable Long id){
	Product obj = service.findById(id);
	return ResponseEntity.ok().body(obj);
	}
	
	}
