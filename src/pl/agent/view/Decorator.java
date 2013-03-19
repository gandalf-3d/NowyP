package pl.agent.view;

import java.awt.Color;
import java.util.Stack;

import pl.agent.Task;

public class Decorator {

	private String label= "";
	private Color color;
	private  String myName="";
	private Stack<Task> taski = new Stack<Task>();
	private volatile int hashCode=0;
	
	
	public Decorator() {
	}
	public Decorator(String label, Color color, String myName, Stack<Task> taski) {
		this.label=label;
		this.color=color;
		this.myName=myName;
		this.taski=taski;
	}
	public Decorator(String label, Color color, String myName) {
		this.label=label;
		this.color=color;
		this.myName=myName;
	}
	
	public static Decorator decorateView(String label, Color color, String myName){
		return new Decorator(label, color,myName);
	}
	public static Decorator decorateViewWithTask(String label, Color color, String myName,Stack<Task> taski){
		return new Decorator(label, color,myName,taski);
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public String getMyName() {
		return myName;
	}
	public void setMyName(String myName) {
		this.myName = myName;
	}
	@Override
	public String toString(){
		return getMyName()+ " " + getLabel() + " " + getColor().toString();
	}
	
	@Override
	public int hashCode(){
		int result = hashCode;
		if (hashCode==0){
			result=17;
			result=31*result+myName.hashCode();
			result=31*color.hashCode();
			hashCode=result;
		}
		return hashCode;
	}
	@Override
	public boolean equals(Object o){
		if(o==this){
			return true;
		}
		if(!(o instanceof Decorator)){
			return false;
		}
		Decorator dec =(Decorator)o;
		return dec.getMyName().equals(myName);
	}
	public Stack<Task> getTaski() {
		return taski;
	}
	public void setTaski(Stack<Task> taski) {
		this.taski = taski;
	}
}
