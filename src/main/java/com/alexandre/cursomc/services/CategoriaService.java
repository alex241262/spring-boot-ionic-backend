package com.alexandre.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.alexandre.cursomc.domain.Categoria;
import com.alexandre.cursomc.repositories.CategoriaRepository;
import com.alexandre.cursomc.services.exceptions.DataIntegrityException;
import com.alexandre.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;
	public Optional<Categoria> find(Integer id) {
		Optional<Categoria> obj= repo.findById(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo: "+ Categoria.class.getName());
		}
		return obj;
	}
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria com produtos amarrados.");
		}
	}
	public List<Categoria> findAll() {
		return repo.findAll();
		}
}
