package pl.agent;

import jade.content.onto.basic.Equals;
import jade.util.HashCache;

public class Task {

	private String coffeName;
	private String volumen; 
	
	public Task() {}
	
	public static Task createTask(String coffeName, String volumen){
		Task task = new Task();
		task.setCoffeName(coffeName);
		task.setVolumen(volumen);
		return task ;
	}
	
	public String getCoffeName() {
		return coffeName;
	}

	public void setCoffeName(String coffeName) {
		this.coffeName = coffeName;
	}

	public String getVolumen() {
		return volumen;
	}

	public void setVolumen(String volumen) {
		this.volumen = volumen;
	}
	
	@Override
	public int hashCode(){
		return Integer.valueOf(getCoffeName())+Integer.valueOf(volumen);
	}
	
	 @Override
	   public boolean equals(Object o){
	     if(o!=this)
	     return false;
	     Task t = (Task)o;
	     if (t.getCoffeName()!=this.getCoffeName()&&t.getVolumen()!= getVolumen())
	       return false;
	     return true;
	   } 
	
	
	@Override
	public String toString(){
		return getCoffeName()+Const.spilt+getVolumen(); 
	}
}

