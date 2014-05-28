package it.moise.plc.uml.interfaces;

import it.moise.plc.uml.Specification;

public interface InterfaceFuntionBehavior extends InterfaceEffect {
	public void setSpecification(Specification specification);
	public Specification getSpecification();

}
