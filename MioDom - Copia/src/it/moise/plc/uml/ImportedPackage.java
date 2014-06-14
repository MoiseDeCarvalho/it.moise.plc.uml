/**
 * 
 **/
package it.moise.plc.uml;

import it.moise.plc.uml.interfaces.InterfaceImportedPackage;

import org.w3c.dom.NamedNodeMap;

/**
 * Costruisce una classe per contenere il valor edi importated package
 * @author De Carvalho Moise
 *
 **/
public class ImportedPackage implements InterfaceImportedPackage {
	private String type = null;
	private String href = null;
	/**
	 * Costruttore con parametro
	 * @param node Oggetto di tipo NamedNodeMap
	 **/
	public ImportedPackage(NamedNodeMap node) {
		String delimiter ="xmi:";
		String temp[] = null;
		for (int i=0; i < node.getLength(); i++){
			temp = null;
			delimiter ="xmi:";
			temp = node.item(i).getNodeName().split(delimiter);
			if (temp[0].equalsIgnoreCase("")) {
				temp[0] = temp[1];
			}
			if (temp.length > 0){
				switch (temp[0]){
				case "type":
					this.setType(node.item(i).getNodeValue());
					break;
				case "href":
					this.setHref(node.item(i).getNodeValue());
					break;
					
				}
			}
		}
	}
	/**
	 * Costruttore senza parametri
	 */
	public ImportedPackage() {
	}

	/**
	 * Set il type dell'ImportedPackage
	 * @param type Indica il tipo
	 * @see it.moise.plc.uml.interfaces.InterfaceImportedPackage#setType(java.lang.String)
	 **/
	@Override
	public void setType(String type) {
		this.type = type;

	}

	/**
	 * Restituisce il type dell'ImportedPackage
	 * @see it.moise.plc.uml.interfaces.InterfaceImportedPackage#getType()
	 **/
	@Override
	public String getType() {
		// 
		return this.type;
	}

	/**
	 * Set l'href
	 * @param  href indica l'href
	 * @see it.moise.plc.uml.interfaces.InterfaceImportedPackage#setHref(java.lang.String)
	 **/
	@Override
	public void setHref(String href) {
		this.href = href;

	}

	/**
	 * Restituisci l'href
	 * @see it.moise.plc.uml.interfaces.InterfaceImportedPackage#getHref()
	 **/
	@Override
	public String getHref() {
		// 
		return this.href;
	}

}
