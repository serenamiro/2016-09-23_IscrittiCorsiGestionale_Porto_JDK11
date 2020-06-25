package it.polito.tdp.gestionale.db;

import it.polito.tdp.gestionale.model.Studente;

public class TestDAO {

	// Test main
	public static void main(String[] args) {
		
		DidatticaDAO dd = new DidatticaDAO();
		//System.out.println(dd.getCorso("01JEFPG"));
		System.out.println(dd.getStudenti().size());
		
		for(Studente s : dd.getStudenti().values()) {
			System.out.println(s);
		}
		
	}
}
