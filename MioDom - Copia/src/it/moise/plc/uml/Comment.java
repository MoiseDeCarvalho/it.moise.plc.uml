/**
 * 
 **/
package it.moise.plc.uml;

import it.moise.plc.uml.interfaces.InterfaceComment;

import org.w3c.dom.NamedNodeMap;

/**
 * Costruisce un oggetto di tipo Comment, che contiene le informazioni realtive ai commenti inseriti in UML
 * @author De Carvalho Moise
 *
 **/
public class Comment implements InterfaceComment {
	private String id = null;
	private String type = null;
	private String body = null;
	private String[] annotatedElement;
	private String idRegionAppartenenza = "-1";
	private String idStateAppartenenza = "-1";
	
	/**
	 * Costruttore con un paramentro node
	 * @param  node Contiene un oggetto di tipo NamedNodeMap che sara' successivamente elaborato e suddiviso in sotto elementi
	 **/
	public Comment(NamedNodeMap node){
		String delimiter ="xmi:";
		String temp[] = null;
		for (int i=0; i < node.getLength(); i++){
			temp = null;
			delimiter ="xmi:";
			temp = node.item(i).getNodeName().split(delimiter);
			if (temp[0].equalsIgnoreCase("") && temp.length > 1) {
				temp[0] = temp[1];
			}
			if (temp.length > 0){
				
				switch (temp[0]){
				case "id":
					this.setId(node.item(i).getNodeValue());
					break;
				case "type":
					this.setType(node.item(i).getNodeValue());
					break;
				case "annotatedElement":
					this.setAnnotatedElement(node.item(i).getNodeValue());
					break;
					
				}
			}
		}
	}
	/**
	 * Costruttore senza parametro
	 **/
	public Comment() {
	}

	/**
	 * Set l'id del commento
	 * @param id Indica il valore id del commento
	 * @see it.moise.plc.uml.interfaces.InterfaceComment#setId(java.lang.String)
	 **/
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * Restituisce l'id del commento
	 * @see it.moise.plc.uml.interfaces.InterfaceComment#getId()
	 **/
	@Override
	public String getId() {
		// 
		return this.id;
	}

	/**
	 * Set il tipo del commento
	 * @param type Contiene il tipo del commento
	 * @see it.moise.plc.uml.interfaces.InterfaceComment#setType(java.lang.String)
	 **/
	@Override
	public void setType(String type) {
		this.type = type;

	}

	/**
	 * Restituisce il tipo del commento
	 * @return type Contiene il valore di type
	 * @see it.moise.plc.uml.interfaces.InterfaceComment#getType()
	 **/
	@Override
	public String getType() {
		// 
		return this.type;
	}

	/**
	 * Set il body del comment
	 * @param body Contiene il valore di body
	 * @see it.moise.plc.uml.interfaces.InterfaceComment#setBody(java.lang.String)
	 **/
	@Override
	public void setBody(String body) {
		this.body = body;

	}

	/**
	 * Restituisce il body del commento
	 * @return body Contiene il valore di body del commento
	 * @see it.moise.plc.uml.interfaces.InterfaceComment#getBody()
	 **/
	@Override
	public String getBody() {
		// 
		return this.body;
	}

	/**
	 * Set gli elementi a cui si riferisce il commento possono essere pi� elementi
	 * @param annotatedElement E' un array che contiene gli id degli elementi che sono legati al commento
	 * @see it.moise.plc.uml.interfaces.InterfaceComment#setAnnotatedElement(java.lang.String[])
	 **/
	@Override
	public void setAnnotatedElement(String annotatedElement) {
		// attenzione prima di fare l'assegnazione bisogna separare gli elementi passati sono stringhe unite
		
		String listElement[] = annotatedElement.split(" ");
		this.annotatedElement = new String[listElement.length];
		for (int i=0; i < listElement.length; i++){
			this.annotatedElement[i] = listElement[i];
		}

	}

	/**
	 * Restituisce l'array di annotatedElement
	 * @return annotatedElement Restituisce l'array degli elementi annotati
	 * @see it.moise.plc.uml.interfaces.InterfaceComment#getAnnotatedelement()
	 **/
	@Override
	public String[] getAnnotatedElement() {
		// 
		return this.annotatedElement;
	}
	/**
	 * Imposta l'id della regione di appartenenza
	 * @param  value Contiene il valore della regione di appartenza del commento
	 * @see it.moise.plc.uml.interfaces.InterfaceComment#setIdRegionAppartenenza(java.lang.String)
	 */
	public void setIdRegionAppartenenza(String value){
		this.idRegionAppartenenza = value;
	}
	/**
	 * Restituisce il valore dell'id della region di appartenenza
	 * @return id Il valore dell'id della regione di appartenenza
	 */
	public String getIdRegionAppartenenza(){
		return this.idRegionAppartenenza;
	}
	/**
	 * Imposta l'id della regione di appartenenza
	 * @param  value Contiene il valore dello stato di appartenza del commento
	 * @see it.moise.plc.uml.interfaces.InterfaceComment#setIdStateAppartenenza(java.lang.String)
	 */
	public void setIdStateAppartenenza(String value){
		this.idStateAppartenenza = value;
	}
	/**
	 * Restituisce il valore dell'id dello stato di appartenenza
	 * @return id Il valore dell'id dello stato di appartenenza
	 */
	public String getIdStateAppartenenza(){
		return this.idStateAppartenenza;
	}

}
