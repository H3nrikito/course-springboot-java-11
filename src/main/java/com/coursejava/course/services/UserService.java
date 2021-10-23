package com.coursejava.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.coursejava.course.entities.User;
import com.coursejava.course.repositories.UserRepository;
import com.coursejava.course.services.exceptions.DatabaseException;
import com.coursejava.course.services.exceptions.ResourceNotFoundException;

@Service //declarando a classe como um componente do framework spring boot
public class UserService {
	
	@Autowired
	private UserRepository repository;

	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id)); //senao encontrar o usuario, lança a exceção
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
		
	}
	
	public User update (Long id, User obj) {
	 User entity = repository.getOne(id); //vai instanciar o user - melhor q o findById(vai buscar o id) - mais eficiente
	 updateData(entity, obj); //atualizar o entity com o obj
	 return repository.save(entity);
	 	 
	}

	private void updateData(User entity, User obj) {
		//atualizar os dados do entity com base no q chegou no obj
		entity.setName(obj.getName()); 
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
	
	
	
}
