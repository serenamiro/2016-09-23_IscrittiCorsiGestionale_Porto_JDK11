package it.polito.tdp.gestionale.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.gestionale.db.DidatticaDAO;

public class Model {
	
	public DidatticaDAO dao;
	private Map<Integer, Studente> mappa_studenti;
	private Map<String, Corso> mappa_corsi;
	private Graph<Nodo, DefaultEdge> grafo;
	
	public Model() {
		dao = new DidatticaDAO();
		mappa_studenti = new HashMap<>();
		mappa_corsi = new HashMap<>();
	}
	
	public void getStudenti() {
		mappa_studenti = dao.getStudenti();
	}
	
	public void getCorsi() {
		mappa_corsi = dao.getCorsi();
	}
	
	public void getStudentiCorsi() {
		for(Corso c : mappa_corsi.values()) {
			List<Studente> lista = dao.getStudentiCorso(c, this.mappa_studenti);
			c.setStudentiIscritti(lista);
		}
	}
	
	public void getCorsiStudente() {
		for(Studente s : mappa_studenti.values()) {
			List<Corso> lista = dao.getCorsiStudente(s, this.mappa_corsi);
			s.setCorsi(lista);
		}
	}
	
	public void creaGrafo() {
		this.grafo = new SimpleGraph<Nodo, DefaultEdge>(DefaultEdge.class);
		
		this.getStudenti();
		System.out.println("Studenti #: " + mappa_studenti.size());
		
		this.getCorsi();
		System.out.println("Corsi #: " + mappa_corsi.size());
		
		this.getStudentiCorsi();
		this.getCorsiStudente();
			
		Graphs.addAllVertices(this.grafo, mappa_corsi.values());
		Graphs.addAllVertices(this.grafo, mappa_studenti.values());
		
		for(Corso c : mappa_corsi.values()) {
			for(Studente s : c.getStudentiIscritti()) {
				if(this.grafo.vertexSet().contains(s) && this.grafo.vertexSet().contains(c)) {
					Graphs.addEdgeWithVertices(this.grafo, c, s);
				}
			}
		}
		
		System.out.format("Grafo creato con %d vertici e %d archi\n", this.grafo.vertexSet().size(), this.grafo.edgeSet().size());
	}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}

	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public Map<Integer, Integer> getCorsiFrequenza(){
		Map<Integer, Integer> corsi_frequenza = new HashMap<>(); // numero corsi - frequenza
		
		for(Studente s : mappa_studenti.values()) {
			Integer corsiSeguiti = s.getCorsi().size();
			if(corsi_frequenza.get(corsiSeguiti)!=null) {
				corsi_frequenza.put(corsiSeguiti, corsi_frequenza.get(corsiSeguiti)+1);
			} else {
				corsi_frequenza.put(corsiSeguiti, 1);
			}
		}
		
		return corsi_frequenza;
	}
	
	public List<Corso> findMinimalePercorso(){
		List<Corso> parziale = new ArrayList<Corso>();
		List<Corso> best = new ArrayList<Corso>();
		
		cerca(parziale, best);
		return best;
	}

	private void cerca(List<Corso> parziale, List<Corso> best) {
		
		HashSet<Studente> setStudenti = new HashSet<>(dao.getStudenti().values());
		for(Corso c : parziale) {
			setStudenti.removeAll(setStudenti);
		}
		
		if(setStudenti.isEmpty()) {
			if(best.isEmpty()) {
				best.addAll(parziale);
				return;
			}
			if(parziale.size()<best.size()) {
				best.clear();
				best.addAll(parziale);
				return;
			}
		}
		
		for(Corso c : mappa_corsi.values()) {
			if(!parziale.contains(c)) {
				parziale.add(c);
				cerca(parziale, best);
				parziale.remove(c);
			}
		}
	}
	
}
