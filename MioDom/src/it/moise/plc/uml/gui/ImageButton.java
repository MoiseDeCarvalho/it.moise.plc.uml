package it.moise.plc.uml.gui;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageButton extends JButton{

	public ImageButton(String imageName, String actionCommand, String toolTipText, String altText) throws IOException{
		URL url = ClassLoader.getSystemResource("images/"+imageName);
		//System.out.println("\n" + url);
		//ImageIcon img = new ImageIcon(getClass().getResource(imageName)); 
		 System.out.println("Bravo! url " + url);
		ImageIcon img =new ImageIcon(url);
	            
	        if (img==null)
	            System.out.println(
	              "C'e' ancora qualcosa di sbagliato nella tua configurazione."
	            );
	        else {
	            	            System.out.println("Bravo! Stavolta ha funzionato!");
	        }
			//ImageIcon img = new ImageIcon("images/"+imageName);
		    this.setIcon(img);
		  // this.setBorderPainted(isEnabled());
		   this.setName(toolTipText);
		    
		    this.setToolTipText(altText);
	}
   

}
