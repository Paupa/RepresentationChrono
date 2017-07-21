package model;

import java.util.*;

public class Chrono {
	
	private Chrono parent;
	private Set<Chrono> children;
	
	private Stopwatch stopwatch;
	
	private String name;
	
	public Chrono(String name) {
		setName(name);
		parent = null;
		children = new TreeSet<>();
		stopwatch = new Stopwatch();
	}
	
	public void start() {
		stopwatch.start();
		
		if(parent != null)
			parent.start();
	}
	
	public void stop() {
		stopwatch.stop();
		
		for(Chrono chronoChildren : children)
			chronoChildren.stop();
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

	public String getName() {
		return name;
	}

	private void setName(String name) {
		
		if(name.isEmpty())
			throw new IllegalArgumentException("You must give a non-empty name to the chrono");
		
		this.name = name;
	}
	
}
