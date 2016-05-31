package com.goytia.models.DB;

import java.util.List;
import java.util.stream.Collectors;
import java.sql.Date;
import com.infoMng.controller.MBOggetto;


public class modelReceipts implements modelReceiptsI{
	
	MBOggetto oggetto;
	
	protected modelReceipts(MBOggetto temp){
		this.oggetto=temp;
	}
	/***
	 * Ottengo un record nuovo dalla tabella Scontrini
	 */
	public modelReceipts(){
		this.oggetto = MBOggetto.oggettoDaTabella("Scontrini");
	}
	
	protected void setReceipt(int nScontrino){
		this.oggetto.setObjectValue("nScontrino", nScontrino);
	}
	protected void setClient(Integer idCliente){
		this.oggetto.setObjectValue("IDCliente", idCliente);
	}
	
	protected void setDate(Date data){
		this.oggetto.setObjectValue("Data", data);
	}
	
	protected void setIva(float iva){
		this.oggetto.setObjectValue("IVA", iva);
	}
	
	protected void setNumberPaymentReceipt(int nRicevuta){
		this.oggetto.setObjectValue("nRicevuta", nRicevuta);
	}
	
	protected int getNumberPaymentReceipt(){
		return (int)this.oggetto.getObject("nRicevuta");
	}
	
	public int getNumberReceipt(){
		return (int)this.oggetto.getObject("nScontrino");
	}
	
	public Integer getIDClient(){
		return (Integer)this.oggetto.getObject("IDCliente");
	}
	
	public float getIVA(){
		return (float) this.oggetto.getObject("IVA");
	}
	
	public Date getDate(){
		return (Date)this.oggetto.getObject("Data");
	}
	/***
	 * metodo alternativo che ritorno il cliente con tutti i dati filtrando con l'idCliente
	 * @return
	 * lo specifico cliente a qui va rivolta lo scontrino
	 */
	public modelClientsI client(){
		return modelClientsI.clientsList().stream()
				.filter(c -> c.getID().equals(this.getIDClient()))
				.findFirst()
				.get();
	}

	public List<transactionsProducts> soldProducts(){
		return modelTransactionsI.transactionsList().stream()
				.filter( m -> m.getNumberPaymentRicevuta() == this.getNumberPaymentReceipt())
				.map( p -> {
					return new transactionsProducts(p.getIDProduct(), Math.abs(p.getQuantity()), p.getPrice());
				})
				.collect(Collectors.toList());
				
	}
	
	public boolean deleteReceipt(){
		return this.oggetto.elimina();
	}
}
