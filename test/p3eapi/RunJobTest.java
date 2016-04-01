package p3eapi;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RunJobTest {

	public RunJobTest() {
	}

	@Test
	public void testHasRightName() {

		String[] args ={"username","password","c:\\Program Files\\Deltek\\Cobra\\p3eapi", "MyProjectId","s"};

		RunJob job = new RunJob(new Parameters(args), new P6Connection(new P6RmiUrl()));
		assertEquals("Run", job.name());
		assertEquals(Job.class, job.getClass().getSuperclass());
	}
}
