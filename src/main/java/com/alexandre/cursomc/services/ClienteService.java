package com.alexandre.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alexandre.cursomc.domain.Cliente;
import com.alexandre.cursomc.domain.Cliente;
import com.alexandre.cursomc.dto.ClienteDTO;
import com.alexandre.cursomc.repositories.ClienteRepository;
import com.alexandre.cursomc.services.exceptions.DataIntegrityException;
import com.alexandre.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repo;
	public Optional<Cliente> find(Integer id) {
		Optional<Cliente> obj= repo.findById(id);
		if (!obj.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo: "+ Cliente.class.getName());
		}
		return obj;
	}
	public Cliente update(Cliente obj) {
		//find(obj.getId()); 
		Optional<Cliente> newObj = find(obj.getId());
		Cliente clientUpdate =updateData(newObj, obj);
		return repo.save(clientUpdate);
	}
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria com produtos amarrado.");
		}
	}
	public List<Cliente> findAll() {
		return repo.findAll();
		}
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	private Cliente updateData(Optional<Cliente> newObj, Cliente obj) {
		Cliente clientUpdate = newObj.get();
		clientUpdate.setNome(obj.getNome());
		clientUpdate.setEmail(obj.getEmail());
		return clientUpdate;
	}
}
