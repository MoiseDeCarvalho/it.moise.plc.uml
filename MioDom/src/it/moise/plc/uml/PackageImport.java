/**
 * 
 **/
package it.moise.plc.uml;

import it.moise.plc.uml.interfaces.InterfacePackageImport;

import org.w3c.dom.NamedNodeMap;

/**
 * Oppeggto di tipo packageImport
 * @author De Carvalho Moise
 *
 **/
public class PackageImport implements InterfacePackageImport {
	private String id = null;
	private String type = null;
	private ImportedPackage importedPackage = new ImportedPackage();
	/**
	 * Metodo costruttore con parametro
	 * @param node Oggetto di tipo NamedNodeMap da analizzare
	 **/
	public PackageImport(NamedNodeMap node) {
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
				case "id":
					this.setId(node.item(i).getNodeValue());
					break;
				case "type":
					this.setType(node.item(i).getNodeValue());
					break;
					
				}
			}
		}
	}
	/**
	 * Metodo costruttore senza parametri
	 */
	public PackageImport() {
	}

	/**
	 * Set l'id del PackageImport
	 * @param id Contiene l'id
	 * @see it.moise.plc.uml.interfaces.InterfacePackageImport#setId(java.lang.String)
	 **/
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * Restituisce l'id del PackageImport
	 * @see it.moise.plc.uml.interfaces.InterfacePackageImport#getId()
	 **/
	@Override
	public String getId() {
		// 
		return this.id;
	}

	/**
	 * Set il type del PackageImport
	 * @param type Indica il tipo
	 * @see it.moise.plc.uml.interfaces.InterfacePackageImport#setType(java.lang.String)
	 **/
	@Override
	public void setType(String type) {
		this.type = type;

	}

	/**
	 * Restituisce il type dell Packageimport
	 * @see it.moise.plc.uml.interfaces.InterfacePackageImport#getType()
	 **/
	@Override
	public String getType() {
		// 
		return this.type;
	}
	
	/**
	 * Set importedPackage del PackageImport
	 * @param importedPackage Oggetto di tipo ImportedPackage
	 * @see it.moise.plc.uml.interfaces.InterfacePackageImport#setImportedPackage(it.moise.plc.uml.ImportedPackage)
	 **/
	@Override
	public void setImportedPackage(ImportedPackage importedPackage) {
		this.importedPackage = importedPackage;

	}
	

	/**
	 * Restituisce il importedPackage dell Packageimport
	 * @see it.moise.plc.uml.interfaces.InterfacePackageImport#getImportedPackage()
	 **/
	@Override
	public ImportedPackage getImportedPackage() {
		// 
		return this.importedPackage;
	}

}
