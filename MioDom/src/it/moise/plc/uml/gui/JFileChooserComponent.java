package it.moise.plc.uml.gui;

import it.moise.plc.uml.xmltree.XMLFileFilter;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;


public class JFileChooserComponent extends JFrame {
	private JFileChooser fc;
	private String nameFileUml="";
	private String pathFileUml = "";
	public JFileChooserComponent(final String tipoFile) {
		super();
		this.fc = new JFileChooser();
		//this.fc.setFileHidingEnabled(false);
		this.fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		// creo un filtro che consente di aprire solo file file .uml
		this.fc.addChoosableFileFilter(new FileFilter(){
			private final String typeFile = tipoFile;
			
			public boolean accept(File f){
				if (f.isDirectory())
					return true;
				String nameFile = f.getName();
				String ext = nameFile.split("\\.")[1];
				return ext.contains(typeFile);
			}
			public String getDescription(){ return "Solo file ." + tipoFile; }
			
		});
		 
		int res = this.fc.showOpenDialog(this); // dialog di tipo open
		if (res == JFileChooser.APPROVE_OPTION){  // controllo della scelta
			File f = this.fc.getSelectedFile(); // ottengo il file
			String nomeFile = f.getName();
			String estensione = "";
			
			
			if (nomeFile.length() == 0){
				// file non selezionato
				res = JFileChooser.CANCEL_OPTION;
				//JOptionPane.showMessageDialog(this, "Non hai selezionato nessun File .uml");
			}else{
				estensione = nomeFile.substring(nomeFile.length()-3, nomeFile.length());
				//JOptionPane.showMessageDialog(this, "Estensione uml " + estensione);
				if (estensione.equals(tipoFile)){
				//	JOptionPane.showMessageDialog(this, "Il file scelto è: " + nomeFile);
				}else{
					JOptionPane.showMessageDialog(this, "Il file selezionato non è di tipo ." + tipoFile);
				}
			}
			this.setNameFileUml(f.getName());
			this.setPathFileUml(f.getPath());
		}
		else{
			if (res == JFileChooser.CANCEL_OPTION){} // altro se ho cancellato la scelta
		
			
		}
		this.add(fc, BorderLayout.CENTER);
	}
	
	public void setNameFileUml(String name){
		this.nameFileUml = name;
	}
	public String getNameFileUml(){
		return this.nameFileUml;
	}
	
	public void setPathFileUml(String path){
		this.pathFileUml = path;
	}
	public String getPathFileUml(){
		return this.pathFileUml;
	}

	public void setFileSelectionMode(int filesAndDirectories) {
		this.setFileSelectionMode(filesAndDirectories);
		
	}

	public void setFileFilter(XMLFileFilter xmlFileFilter) {
		this.fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		this.setFileFilter(xmlFileFilter);
		
	}

	public void setCurrentDirectory(File file) {
		this.setCurrentDirectory(file);
		
	}

	public File getSelectedFile() {
		
		return this.fc.getSelectedFile();
	}

	public int showOpenDialog() {
		// TODO Auto-generated method stub
		return this.fc.showOpenDialog(null);
	}

}
