package it.polito.tdp.gestionale.model;

import java.util.ArrayList;
import java.util.List;

public class Corso extends Nodo {

	private String codins;
	private String nome;
	private int crediti;
	private int pd;
	private List<Studente> studentiIscritti;

	public Corso(String codins, int crediti, String nome, int pd) {
		this.codins = codins;
		this.crediti = crediti;
		this.nome = nome;
		this.pd = pd;
		studentiIscritti = new ArrayList<>();
	}

	/*
	 * Getters and Setters
	 */

	public List<Studente> getStudentiIscritti() {
		return studentiIscritti;
	}

	public void setStudentiIscritti(List<Studente> studentiIscritti) {
		this.studentiIscritti = studentiIscritti;
	}

	public String getCodins() {
		if (codins == null)
			return "";
		return codins;
	}

	public void setCodins(String codins) {
		this.codins = codins;
	}

	public String getNome() {
		if (nome == null)
			return "";
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCrediti() {
		return crediti;
	}

	public void setCrediti(int crediti) {
		this.crediti = crediti;
	}

	public int getPd() {
		return pd;
	}

	public void setPd(int pd) {
		this.pd = pd;
	}

	@Override
	public String toString() {
		return "Corso [codins=" + codins + ", nome=" + nome + ", crediti=" + crediti + ", pd=" + pd + "]";
	}

}
