package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Chrono;

public class ChronoTest {

	@Test
	public void testInitialization() {
		
		String name = "Test";
		
		Chrono c = new Chrono(name);
		
		c.start();
		
		for(int i = 0; i < 1000000; i++);
		
		assertTrue(c.isRunning());
		assertTrue(c.getMeasuredTime() > 0);
		
		c.stop();
		
		assertFalse(c.isRunning());
		assertEquals(c.getName(), name);
	}
	
	@Test
	public void testSwitch() {
		
		Chrono c = new Chrono("Switch");
		
		assertFalse(c.isRunning());
		
		c.switchState();
		
		assertTrue(c.isRunning());
		
		c.switchState();
		
		assertFalse(c.isRunning());
	}

}
