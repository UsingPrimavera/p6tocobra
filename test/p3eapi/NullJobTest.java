package p3eapi;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class NullJobTest {

	public NullJobTest() {
	}

	@Test
	public void testHasRightName() {

		String[] args ={"username","c:\\Program Files\\Deltek\\Cobra\\p3eapi", "MyProjectId","s","true"};

		ISession session = null;
		NullJob job = new NullJob(new Parameters(args), new P6Connection(new P6RmiUrl(), session));
		assertEquals("Null", job.name());
		assertEquals(Job.class, job.getClass().getSuperclass());
	}

    @Test
    public void testHasRightLogFilename() {
        String filename = "c:\\Program Files\\Deltek\\Cobra\\p3eapi";
        String[] args ={"username",filename, "MyProjectId","s","true"};

		ISession session = null;
		NullJob job = new NullJob(new Parameters(args), new P6Connection(new P6RmiUrl(), session));
		assertEquals("..\\WrongParameters.log", job.getLogfilename());
	}
}
