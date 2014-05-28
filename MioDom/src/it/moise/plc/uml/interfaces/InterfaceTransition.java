package it.moise.plc.uml.interfaces;

import it.moise.plc.uml.Comment;
import it.moise.plc.uml.Effect;
import it.moise.plc.uml.Rule;
import it.moise.plc.uml.Trigger;

import java.util.ArrayList;


public interface InterfaceTransition {
	public void setId(String id);
	public String getId();
	public void setName(String name);
	public String getName();
	public void setSource(String source);
	public String getSource();
	public void setTarget(String target);
	public String getTarget();
	public void setAddTrigger(Trigger trigger);
	public ArrayList<Trigger> getListTrigger();
	public void printListTrigger(ArrayList<Trigger> listTrigger);
	public void setIdGuardRule(String idGuardRule);
	public String getIdGuardRule();
	public void setGuardRule(Rule rule);
	public Rule getGuardRule();
	public void setEffect(Effect effect);
	public Effect getEffect();
	public void setComment(Comment comment);
	public Comment getComment();

}
