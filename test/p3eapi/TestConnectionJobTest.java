package p3eapi;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestConnectionJobTest {

	public TestConnectionJobTest() {
	}

	@Test
	public void testHasRightName() {

		String[] args ={"username","password","c:\\Program Files\\Deltek\\Cobra\\p3eapi", "TestConnection","s","true"};

		TestConnectionJob job = new TestConnectionJob(new Parameters(args));
		assertEquals("TestConnection", job.name());
		assertEquals(AbstractJob.class, job.getClass().getSuperclass());
	}
}
