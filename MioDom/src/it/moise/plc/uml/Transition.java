package it.moise.plc.uml;

import it.moise.plc.uml.interfaces.InterfaceTransition;

import java.util.ArrayList;
import java.util.Comparator;

import org.w3c.dom.NamedNodeMap;
/**
 * Descrive una transizione ed i suoi elementi fondamentali
 * @author De Carvalho Moise
 * 
 *
 **/
public class Transition implements InterfaceTransition, Comparable<Transition>{
	private String id = null;
	private String name = null;
	private Effect effect;
	private Rule guardRule;
	private String source = null;
	private String target = null;
	private String idGuardRule = "";
	private Comment comment;
	private ArrayList<Trigger> listTrigger = new ArrayList<Trigger>();
	
	/**
	 * Creo l'oggetto Transition con paramentro un nodo
	 * @param node NamedNodeMap 
	 */
	public Transition(NamedNodeMap node){
		this.effect = new Effect();
		this.guardRule = new Rule();
		this.comment = new Comment();
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
				case "name":
					String tmp = node.item(i).getNodeValue();
					tmp = tmp.replace("-|.|!", "_");
					
					this.setName(tmp);
					break;
				case "source":
					this.setSource(node.item(i).getNodeValue());
					break;
				case "target":
					this.setTarget(node.item(i).getNodeValue());
					break;
				case "guard":
					this.setIdGuardRule(node.item(i).getNodeValue());
					break;
				}
			}
		}
	}
	public Transition(){
		
	}
	/**
	 * Implemneta il metodo
	 *
	 */
	static class IdTransitionComparator implements Comparator<Transition> {
		@Override
		public int compare(Transition t1, Transition t2) {
			int result;
			result = t1.getId().compareTo(t2.getId());
			return result;
			}
	}
	/**
	 * Implementa il metodo
	 *
	 */
	static class SourceTransitionComparator implements Comparator<Transition> {
		@Override
		public int compare(Transition t1, Transition t2) {
			int result;
			result = t1.getSource().compareTo(t2.getSource());
			return result;
			}
	}
	
	
	static class TargetTransitionComparator implements Comparator<Transition> {
		@Override
		public int compare(Transition t1, Transition t2) {
			int result;
			result = t1.getTarget().compareTo(t2.getTarget());
			return result;
			}
	}
	
	
	
	@Override
	public void setId(String id){
		this.id = id;
	}
	
	@Override
	public String getId(){
		return this.id;
	}
	
	@Override
	public void setName(String name){
		this.name = name;
	}
	
	@Override
	public String getName(){
		return this.name;
	}
	@Override
	public void setEffect(Effect effect){
		this.effect = effect;
	}
	@Override
	public Effect getEffect(){
		return this.effect;
	}
	
	@Override 
	public void setGuardRule(Rule guardRule){
		this.guardRule = guardRule;
	}
	@Override
	public Rule getGuardRule(){
		return this.guardRule;
	}
	
	@Override
	public void setSource(String source){
		this.source = source;
	}
	
	@Override
	public String getSource(){
		return this.source;
	}
	
	@Override 
	public void setTarget(String target){
		this.target = target;
	}

	@Override
	public String getTarget(){
		return this.target;
	}
	@Override 
	public void setIdGuardRule(String idGuardRule){
		this.idGuardRule = idGuardRule;
	}

	@Override
	public String getIdGuardRule(){
		return this.idGuardRule;
	}
	
	@Override
	public void setAddTrigger(Trigger trigger){
		this.listTrigger.add(trigger);
	}
	
	@Override
	public ArrayList<Trigger> getListTrigger(){
		return this.listTrigger;
	}
	
	@Override
	public void printListTrigger(ArrayList<Trigger> listTrigger){
		
	}
	
	/**
	 * Set il Comment
	 * @param comment oggetto commento
	 **/
	@Override
	public void setComment(Comment comment) {
		// attenzione prima di fare l'assegnazione bisogna separare gli elementi passati sono stringhe unite
		this.comment = comment;

	}
	
	/**
	 * Restituisce il comment
	 **/

	public Comment getComment() {
		return this.comment;
	}
	@Override
	public int compareTo(Transition o) {
		// 
		return 0;
	}
}
