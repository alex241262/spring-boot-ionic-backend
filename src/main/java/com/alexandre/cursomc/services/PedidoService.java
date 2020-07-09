package com.alexandre.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexandre.cursomc.domain.Categoria;
import com.alexandre.cursomc.domain.Pedido;
import com.alexandre.cursomc.repositories.PedidoRepository;
import com.alexandre.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repo;
	public Optional<Pedido> buscar(Integer id) {
		Optional<Pedido> obj= repo.findById(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo: "+ Pedido.class.getName());
		}
		return obj;
	}
}
