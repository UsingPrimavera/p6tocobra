package p3eapi;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RunJobTest {

	public RunJobTest() {
	}

	@Test
	public void testHasRightName() {

		String[] args ={"username","password","c:\\Program Files\\Deltek\\Cobra\\p3eapi", "MyProjectId","s"};

		ISession session = null;
		RunJob job = new RunJob(new Parameters(args), new P6Connection(new P6RmiUrl(), session));
		assertEquals("Run", job.name());
		assertEquals(Job.class, job.getClass().getSuperclass());
	}
    
    @Test
    public void testHasRightLogFilename() {
        String filename = "c:\\Program Files\\Deltek\\Cobra\\p3eapi";
        String[] args ={"username","password",filename, "MyProjectId","s"};

		ISession session = null;
		RunJob job = new RunJob(new Parameters(args), new P6Connection(new P6RmiUrl(), session));
		assertEquals(filename + "\\p3eapiSpread.log", job.getLogfilename());
	}   
}
