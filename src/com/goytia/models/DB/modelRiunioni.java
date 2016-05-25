package com.goytia.models.DB;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

public class modelRiunioni {
	
	MBOggetto oggetto;
	
	private modelRiunioni(MBOggetto temp){
		this.oggetto = temp;
	}
	
	public modelRiunioni(){
		this.oggetto = MBOggetto.oggettoDaTabella("Riunioni");
	}
	
	private void setResponsabile(String responsabile){
		this.oggetto.setObjectValue("Responsabile", ctrlStringa(responsabile));
	}
	
	private void  setReferenzeResponsabile(String referenze){
		this.oggetto.setObjectValue("Referenze", ctrlStringa(referenze));
	}
	private void setDescrizione(String descrizione){
		this.oggetto.setObjectValue("Descrizione", ctrlStringa(descrizione));
	}
	
	private void setData(Date dataRiunione){
		this.oggetto.setObjectValue("Data", dataRiunione);
	}
	
	private static String ctrlStringa(String str){
		return str != "" ? str : null;
	}
	
	public String getNomeResponsabile(){
		return (String)this.oggetto.getObject("Responsabile");
	}
	
	public String getReferenze(){
		return (String)this.oggetto.getObject("Referenze");
	}
	
	public Date getData(){
		return (Date)this.oggetto.getObject("Data");
	}
	
	public static List<modelRiunioni> elenco(){
		MBQuery query = MBQuery.queryDaTabella("Clienti");
		try {
			return query.find().stream()
					.map(e -> new modelRiunioni(e))
					.collect(Collectors.toList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
