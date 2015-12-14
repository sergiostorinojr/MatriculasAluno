package com.example.matriculasaluno;

import java.io.Serializable;

public class Aluno implements Serializable {

	private Long id;
	private String nome;
	private String site;
	private String endereco;
	private String telefone;
	private Double nota;
	private String foto;

	public Aluno() {
		// TODO Auto-generated constructor stub
	}

	public final Long getId() {
		return id;
	}

	public final String getNome() {
		return nome;
	}

	public final String getSite() {
		return site;
	}

	public final String getEndereco() {
		return endereco;
	}

	public final String getTelefone() {
		return telefone;
	}

	public final Double getNota() {
		return nota;
	}

	public final String getFoto() {
		return foto;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}
	

}
