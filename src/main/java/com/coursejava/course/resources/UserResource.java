package com.coursejava.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.coursejava.course.entities.User;
import com.coursejava.course.services.UserService;

@RestController //chamados de endpoint
@RequestMapping(value = "/users") //nome do recurso
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping //requisição do tipo get, buscando todos os usuarios
	public ResponseEntity<List<User>> findAll(){
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
		}
	
	@GetMapping(value = "/{id}") // aceitar id na busca da url get
	public ResponseEntity<User> findById(@PathVariable Long id){
	User obj = service.findById(id);
	return ResponseEntity.ok().body(obj);
	}
	
	//inserir novo recurso no banco de dados, usar o PostMapping
	@PostMapping
	//requestBody = informar que o obj vai ser json e ser desserializado para objeto user
	public ResponseEntity<User> insert(@RequestBody User obj){
		obj = service.insert(obj);
		//fazendo com que o retorno http seja 201 - forma adequada de inserir recurso no banco de dados 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
