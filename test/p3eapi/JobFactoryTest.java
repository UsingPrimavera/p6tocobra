package p3eapi;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class JobFactoryTest {

	public JobFactoryTest() {
	}

	@Test
	public void testCreatesRunJob() {

		String[] args ={"username","password","c:\\Program Files\\Deltek\\Cobra\\p3eapi", "MyProjectId","s"};

		Job runJob = JobFactory.getJob(new Parameters(args),new P6Connection());
		assertEquals("Run", runJob.name());
		assertEquals(Job.class, runJob.getClass().getSuperclass());
	}

	@Test
	public void testCreatesTestConnectionJob() {

		String[] args ={"username","password","c:\\Program Files\\Deltek\\Cobra\\p3eapi", "TestConnection","s","true"};

		Job testConnectionJob = JobFactory.getJob(new Parameters(args),new P6Connection());
		assertEquals("TestConnection", testConnectionJob.name());
		assertEquals(Job.class, testConnectionJob.getClass().getSuperclass());
	}

	@Test
	public void testCreatesNullJob() {

		String[] args ={"username","c:\\Program Files\\Deltek\\Cobra\\p3eapi", "TestConnection","s","true"};

		Job nullJob = JobFactory.getJob(new Parameters(args),new P6Connection());
		assertEquals("Null", nullJob.name());
		assertEquals(Job.class,nullJob.getClass().getSuperclass());
	}
}
