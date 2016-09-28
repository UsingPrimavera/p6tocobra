package p3eapi;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.Expectations;
import org.jmock.lib.legacy.ClassImposteriser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TestConnectionJobTest {

	public TestConnectionJobTest() {
	}

    private Mockery context = new JUnit4Mockery() {
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };

	@Test
	public void testHasRightName() {

		String[] args ={"username","password","c:\\Program Files\\Deltek\\Cobra\\p3eapi", "TestConnection","s","true"};

		ISession session = null;
		TestConnectionJob job = new TestConnectionJob(new Parameters(args), new P6Connection(new P6RmiUrl(), session));
		assertEquals("TestConnection", job.name());
		assertEquals(Job.class, job.getClass().getSuperclass());
	}

    @Test
    public void testHasRightLogFilename() {
        String filename = "c:\\Program Files\\Deltek\\Cobra\\p3eapi";
        String[] args ={"username","password",filename, "TestConnection","s","true"};

		ISession session = null;
		TestConnectionJob job = new TestConnectionJob(new Parameters(args), new P6Connection(new P6RmiUrl(), session));
		assertEquals(filename + "\\ConnectionFailed.log", job.getLogfilename());
	}


    @Test
    public void testFailedLogin() {
		// Setup
        final P6Connection p6Conn = context.mock(P6Connection.class);

		// expectations
	    context.checking(new Expectations(){{
           allowing(p6Conn).login("admin","secret");
           allowing(p6Conn).isLoggedIn(); will(returnValue(false));
        }});

        String[] args ={"admin","secret","c:\\Program Files\\Deltek\\Cobra\\p3eapi", "TestConnection","s","true"};
        TestConnectionJob job = new TestConnectionJob(new Parameters(args),p6Conn );
        assertFalse(job.run());
    }

    @Test
    public void testSuccessfulLogin() {
		// Setup
        final P6Connection p6Conn = context.mock(P6Connection.class);

		// expectations
	    context.checking(new Expectations(){{
           allowing(p6Conn).login("admin","secret");
           allowing(p6Conn).isLoggedIn(); will(returnValue(true));
        }});

        String[] args ={"admin","secret","c:\\Program Files\\Deltek\\Cobra\\p3eapi", "TestConnection","s","true"};
        TestConnectionJob job = new TestConnectionJob(new Parameters(args),p6Conn );
        assertTrue(job.run());
    }
}
