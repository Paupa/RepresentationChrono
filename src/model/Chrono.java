package model;

import java.util.*;
import java.util.concurrent.TimeUnit;

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
	
	public long getMeasuredTimeMillis() {
		
		return stopwatch.getMeasuredTime();
	}
	
	public String getMeasuredTime() {
		long millis = getMeasuredTimeMillis();
		
		String measuredTime = String.format("%02d:%02d:%02d", 
			    TimeUnit.MILLISECONDS.toHours(millis),
			    TimeUnit.MILLISECONDS.toMinutes(millis) - 
			    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
			    TimeUnit.MILLISECONDS.toSeconds(millis) - 
			    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
		
		if(TimeUnit.MILLISECONDS.toHours(millis) == 0)
			measuredTime = measuredTime.substring(3);
	
		
		return measuredTime;
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
