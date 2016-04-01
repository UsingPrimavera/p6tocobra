package p3eapi;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestConnectionJobTest {

	public TestConnectionJobTest() {
	}

	@Test
	public void testHasRightName() {

		String[] args ={"username","password","c:\\Program Files\\Deltek\\Cobra\\p3eapi", "TestConnection","s","true"};

		ISession session = null;
		TestConnectionJob job = new TestConnectionJob(new Parameters(args), new P6Connection(new P6RmiUrl(), session));
		assertEquals("TestConnection", job.name());
		assertEquals(Job.class, job.getClass().getSuperclass());
	}
}
