package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Stopwatch;

public class StopwatchTest {
	
	Stopwatch sw;
	
	@Before
	public void setUp() {
		sw = new Stopwatch();
	}

	@Test
	public void testInitialization() {
		
		assertTrue(sw.getMeasuredTime() == 0);
		assertTrue(!sw.isRunning());
	}
	
	@Test
	public void testSimpleStart() {
		
		sw.start();
		
		for(int i = 0; i < 1000000; i++);
		
		assertTrue(sw.isRunning());
		assertTrue(sw.getMeasuredTime() > 0);
	}
	
	@Test
	public void testSimpleStop() {
		
		sw.start();
		
		for(int i = 0; i < 100000; i++);
		
		sw.stop();
		
		assertTrue(!sw.isRunning());
		assertTrue(sw.getMeasuredTime() > 0);
	}
	
	@Test
	public void testDoubleStart() {
		
		boolean exceptionThrown = false;
		
		sw.start();
		
		try {
			sw.start();
		} catch (IllegalStateException e) {
			exceptionThrown = true;
		}
		
		assertTrue(exceptionThrown);
	}
	
	@Test
	public void testDoubleStop() {
		
		boolean exceptionThrown = false;
		
		sw.start();
		
		sw.stop();
		
		try {
			sw.stop();
		} catch (IllegalStateException e) {
			exceptionThrown = true;
		}
		
		assertTrue(exceptionThrown);
	}
	
	@Test
	public void testMeasuredCorrectyAfterPause() {
		
		Stopwatch sw2 = new Stopwatch();
		
		sw.start();
		sw2.start();
		
		for(int i = 0; i < 100000; i++);
		
		sw.stop();
		
		for(int i = 0; i < 200000; i++);
		
		sw.start();
		
		for(int i = 0; i < 100000; i++);
		
		sw.stop();
		sw2.stop();
		
		System.out.println("sw = " + sw.getMeasuredTime() + ", sw2 = " + sw2.getMeasuredTime());
		
		assertTrue(sw.getMeasuredTime() < sw2.getMeasuredTime());
		
	}
}
