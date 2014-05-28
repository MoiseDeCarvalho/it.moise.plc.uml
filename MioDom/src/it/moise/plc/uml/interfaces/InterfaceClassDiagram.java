package it.moise.plc.uml.interfaces;

import it.moise.plc.uml.Extension;
import it.moise.plc.uml.Operation;
import it.moise.plc.uml.PrimitiveType;
import it.moise.plc.uml.attribute.Attribute;

import java.util.ArrayList;
import java.util.Vector;

public interface InterfaceClassDiagram {
	public void setName(String name);
	public String getName();
	public void setId(String id);
	public String getId();
	
	public <T> void setAddVectorAttribute(Attribute<T> attribute);
	public <T> Vector<Attribute<T>> getVectorAttribute();
	
	public void setAddVectorOperation(Operation operation);
	public Vector<Operation> getVectorOperation();
	
	public void setAddVectorExtension(Extension extension);
	public Vector<Extension> getVectorExtension();

	
}
