package it.moise.plc.uml.interfaces;

import it.moise.plc.uml.TypeParameter;
import it.moise.plc.uml.value.LowerValue;
import it.moise.plc.uml.value.UpperValue;
import it.moise.plc.uml.value.Value;



public interface InterfaceParameter {
	public void setId(String id);
	public String getId();
	public void setName(String name);
	public String getName();
	
	public void setIdTypeParameter(String type);
	public String getIdTypeParameter();
	
	public void setTypeParameter(TypeParameter typeParameter);
	public TypeParameter getTypeParameter();
	
	public void setXmiType(String xmiType);
	public String getXmiType();
	
	public void setVisibility(String visibility);
	public String getVisibility();
	public void setIsOrdered(boolean isOrdered);
	public boolean getIsOrdered();
	public void setIsException(boolean isExpection);
	public boolean getIsException();
	public void setIsStream(boolean isstream);
	public boolean getIsStream();
	public void setEffect(String effect);
	public String getEffect();
	
	public void setDefaultValue(Value value);
	public Value getDefaultValue();
	
	public void setLowerValue(LowerValue lowerValue);
	public LowerValue getLowerValue();
	
	public void setUpperValue(UpperValue upperValue);
	public UpperValue getUpperValue();
}
