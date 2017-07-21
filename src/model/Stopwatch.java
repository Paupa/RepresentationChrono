package model;

public class Stopwatch {
	
	private long alreadyMeasuredTime;
	
	private boolean running;
	
	private long startMilliseconds;

	public Stopwatch() {
		
		alreadyMeasuredTime = 0;
		running = false;
	}

	public void start() {
		
		if(running)
			throw new IllegalStateException("You can't start a stopwatch that's already running!");
		
		running = true;
		
		startMilliseconds = System.currentTimeMillis();
		
	}

	public void stop() {
		
		if(!running)
			throw new IllegalStateException("You can't stop a stopwatch that's already stopped!");
		
		alreadyMeasuredTime += getTimeMeasuringNow();
		
		running = false;
		
	}

	public boolean isRunning() {
		return running;
	}

	public long getMeasuredTime() {
		
		long measuredTime = alreadyMeasuredTime;
		
		if(running)
			measuredTime += getTimeMeasuringNow();
		
		return measuredTime;
	}
	
	private long getTimeMeasuringNow() {
		
		return System.currentTimeMillis() - startMilliseconds;
	}
}
