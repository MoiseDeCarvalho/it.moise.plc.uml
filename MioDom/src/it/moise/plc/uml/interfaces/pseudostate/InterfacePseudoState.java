package it.moise.plc.uml.interfaces.pseudostate;

public interface InterfacePseudoState<T> {
	public void setId(String id);
	public String getId();
	public void setName(String name);
	public String getName();
	public void setType(String type);
	public String getType();
	public void setKind(String kind);
	public String getKind();
	public void setLevel(int level);
	public int getLevel();
}
