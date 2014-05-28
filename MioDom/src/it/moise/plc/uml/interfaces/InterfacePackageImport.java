package it.moise.plc.uml.interfaces;

import it.moise.plc.uml.ImportedPackage;

public interface InterfacePackageImport {
	public void setId(String id);
	public String getId();
	public void setType(String type);
	public String getType();
	
	public void setImportedPackage(ImportedPackage importedPackage);
	public ImportedPackage getImportedPackage();
}
