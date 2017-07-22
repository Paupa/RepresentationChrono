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
		assertTrue(c.getMeasuredTimeMillis() > 0);
		
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
	
	@Test
	public void testParentChildrenSwitching() {
		
		Chrono women = new Chrono("Women");
		Chrono specificWoman = new Chrono("Specific woman");
		
		women.addChild(specificWoman);
		
		women.start();
		
		assertTrue(women.isRunning());
		assertFalse(specificWoman.isRunning());
		
		women.stop();
		
		assertFalse(women.isRunning());
		
		specificWoman.start();
		
		assertTrue(specificWoman.isRunning());
		assertTrue(women.isRunning());
		
		specificWoman.stop();
		
		assertFalse(specificWoman.isRunning());
		assertTrue(women.isRunning());
		
		specificWoman.start();
		
		assertTrue(specificWoman.isRunning());
		
		women.stop();
		
		assertFalse(women.isRunning());
		assertFalse(specificWoman.isRunning());
	}

}
