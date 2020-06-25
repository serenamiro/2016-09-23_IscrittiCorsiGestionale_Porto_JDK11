package it.polito.tdp.gestionale;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.gestionale.model.Corso;
import it.polito.tdp.gestionale.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

//controller del turno A --> switchare al master_turnoB per turno B

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCorsiFrequentati(ActionEvent event) {
    	Map<Integer, Integer> res = model.getCorsiFrequenza();
    	if(res!=null) {
    		txtResult.appendText("NUM. CORSI - FREQUENZA\n");
    		for(Integer i : res.keySet()) {
    			txtResult.appendText(i+" - "+res.get(i)+"\n");
    		}
    	}
    }

    @FXML
    void doVisualizzaCorsi(ActionEvent event) {
    	List<Corso> bestPercorso = model.findMinimalePercorso();
    	if(bestPercorso != null) {
    		txtResult.appendText("Percorso minimo:\n");
    		for(Corso c : bestPercorso) {
    			txtResult.appendText(c+"\n");
    		}
    	} else {
    		txtResult.appendText("ERRORE\n");
    	}
    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'DidatticaGestionale.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		model.creaGrafo();
	}
}
