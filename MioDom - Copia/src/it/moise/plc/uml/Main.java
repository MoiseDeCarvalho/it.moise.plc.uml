/**
 * 
 */
package it.moise.plc.uml;

import it.moise.plc.uml.gui.JFrameContainer;

import java.io.File;
import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Classe main avvio del programma, il programma si può avviare anche da console e può funzionare
 *  anche da remoto senza interfaccia grafica
 * @author De Carvalho Moise
 *
 */
public class Main {
	/**
	 * Metodo costruttore senza parametri
	 */
	public Main() {
		
	}

	
	/**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
	 * @throws IOException  Eccezione
     */
    private static void createAndShowGUI(GlobalVar globalVar) throws IOException {
    	 String nameFileUml = "";
        //Create and set up the window.
    	JFrameContainer win = new JFrameContainer();
        win.setDefaultCloseOperation(JFrameContainer.EXIT_ON_CLOSE);
        //Display the window.
        win.setVisible(true);
        System.out.println("nome file : " + nameFileUml);
        
        
    }
	/**
	 * Lancia il main
	 * @param args Aromenti passati da riga di comando
	 * @throws Exception  Eccezione da gestire
	 */
	public static void main(String[] args) throws Exception{
		if (args.length > 0){
			String filePath = "";
			for (int i=0; i < args.length; i++){
				filePath = filePath +args[i];
				File file = new File(filePath);
				if (file.isFile()){
					String estensione = "";
					estensione = filePath.substring(filePath.length()-3, filePath.length());
					if (estensione.equals("uml")){
						MioDom mioDom;
						System.out.println("ecco il file" + filePath);
						
						try {
							try {
								boolean console = true;
								mioDom = new MioDom(filePath, console);
								
								ConversionToCoDeSys p = new ConversionToCoDeSys(mioDom, "FDB");
							} catch (ClassNotFoundException | InterruptedException e) {
								e.printStackTrace();
							}
							
						} catch (IOException e) {
							e.printStackTrace();
						}
					
					}else{
						System.out.println("Tipo di file non corretto");
					}
				}else{
					System.out.println("Errato non è un file");
				}
			}
			
		}else{
		
		//Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
	                //Turn off metal's use of bold fonts
		        UIManager.put("swing.boldMetal", Boolean.FALSE);
		        
		        	GlobalVar globalVar = new Factory().createGlobalVar();
					try {
						createAndShowGUI(globalVar);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					
				
			
		        System.out.println("ciao");
            }
         
        });		
	}
 }
}
