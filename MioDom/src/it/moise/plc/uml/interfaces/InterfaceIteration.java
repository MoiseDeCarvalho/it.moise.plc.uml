package it.moise.plc.uml.interfaces;

import it.moise.plc.uml.Rule;
import it.moise.plc.uml.Specification;

public interface InterfaceIteration extends InterfaceEffect {
	public void setSpecification(Specification specification);
	public Specification getSpecification();
	public void setPreCondition(Rule preCondition);
	public Rule getPreCondition();
	public void setPostCondition(Rule postCondition);
	public Rule getPostCondition();
}
