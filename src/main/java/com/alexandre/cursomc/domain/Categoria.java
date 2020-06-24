package com.alexandre.cursomc.domain;

import java.io.Serializable;

public class Categoria implements Serializable {	
	private static final long serialVersionUID = 1L;//numero de versao padrao numero de classe 1
	
	//inicio atributos
	private Integer id;
	private String nome;
	//fim atributos
	
	public Categoria() {//construto vazio( com ele instacio um projeto sem jogar nada nos atributos
	}

	public Categoria(Integer id, String nome) {//construtor com os atributos
		super();
		this.id = id;
		this.nome = nome;
	}
//inicio get set(metodos de acesso apra os atributos)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}//fim get set

	@Override
	public int hashCode() {//operações para compara objetos por valor
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {//metodo que faz a comparação considerando varias opções
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
