package com.infoMng;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.UIManager;

import com.infoMng.controller.ObserverInterfaceImpl;
import com.mattiaberretti.database.GestioneDB;

import view.ViewInterfaceImpl;
import view.interfaces.ObserverInterface;
import view.interfaces.ViewInterface;

public class infoManager {

	public static void main(String[] args){
		
			try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e){}
			EventQueue.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					try {
						GestioneDB.generaDB();//TODO: da rimuovere per le prossime versioni
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
		    ViewInterface view = new ViewInterfaceImpl();
			ObserverInterface controller = new ObserverInterfaceImpl(view);
			controller.Start();
	}

}
