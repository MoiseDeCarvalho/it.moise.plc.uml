package it.moise.plc.uml;

public class Trx_In {

	
	private String nameTRX = ""; 		// [0] indica il nome della transizione in INPUT
	private String nrTRX = "";			// [1] indica il numero della transizione nel vettote vectorTransion 
	private String condition = "";		// [2] indica la condizione da associare in fase di dichiarazione
	private String oldNameTrx = "";     // [3] indica il nome della transizione nel diagramma UML
	
	public Trx_In(){
		
	}
	public Trx_In(String name, String nrTrx, String condition, String oldNameTrx){
		this.nameTRX = name;
		this.nrTRX = nrTrx;
		this.condition = condition;
		this.oldNameTrx = oldNameTrx;
	}
	public void setName (String name){
		this.nameTRX = name;
	}
	public String getName(){
		return this.nameTRX;
	}
	public void setNrTrx(String nrTrx){
		this.nrTRX = nrTrx;
	}
	public String getNrTrx(){
		return this.nrTRX;
	}
	public void setCondition(String condition){
		this.condition = condition;
	}
	public String getCondition(){
		return this.condition;
	}
	public void setOldNameTrx(String oldNameTrx){
		this.oldNameTrx = oldNameTrx;
	}
	public String getOldNameTrx(){
		return this.oldNameTrx;
	}
	

}
