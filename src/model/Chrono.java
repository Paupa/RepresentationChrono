package model;

import java.util.*;

public class Chrono {
	
	private Set<Chrono> parents;
	private Set<Chrono> children;
	
	private Stopwatch stopwatch;
	
	private String name;
	
	public Chrono(String name) {
		
		setName(name);
		parents = new HashSet<>();
		children = new HashSet<>();
		stopwatch = new Stopwatch();
	}
	
	public void start() {
		stopwatch.start();
		
		for(Chrono parent : parents) {
			if(!parent.isRunning())
				parent.start();
		}
	}
	
	public void stop() {
		stopwatch.stop();
		
		for(Chrono child : children) {
			if(child.isRunning())
				child.stop();
		}
	}
	
	public void switchState() {
		if(stopwatch.isRunning())
			stop();
		else
			start();
	}
	
	public long getMeasuredTime() {
		return stopwatch.getMeasuredTime();
	}
	
	public boolean isRunning() {
		return stopwatch.isRunning();
	}
	
	private void addParent(Chrono parent) {
		parents.add(parent);
	}
	
	private void removeParent(Chrono parent) {
		parents.remove(parent);
	}
	
	public void addChild(Chrono child) {
		child.addParent(this);
		children.add(child);
	}
	
	public void removeChild(Chrono child) {
		
		child.removeParent(this);
		children.remove(child);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		
		if(name.isEmpty())
			throw new IllegalArgumentException("You must give a non-empty name to the chrono");
		
		this.name = name;
	}
	
}
