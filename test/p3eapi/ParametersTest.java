package p3eapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class ParametersTest {

	public ParametersTest() {
	}

	@Test
	public void testCorrectTestConnectionParameters() {

		String[] args ={"username","password","c:\\Program Files\\Deltek\\Cobra\\p3eapi", "TestConnection","s","true"};

		Parameters params = new Parameters(args);
		assertEquals(args[0], params.username());
		assertEquals(args[1], params.password());
		assertEquals(args[2], params.pathName());
		assertEquals(true, params.testConnection());
		assertEquals(args[4],params.dateSource());
		assertTrue(params.flag());
		assertNull(params.projectId());
	}

	@Test
	public void testCorrectRunParameters() {

		String[] args ={"username","password","c:\\Program Files\\Deltek\\Cobra\\p3eapi", "ProjectId","s"};

		Parameters params = new Parameters(args);
		assertEquals(args[0], params.username());
		assertEquals(args[1], params.password());
		assertEquals(args[2], params.pathName());
		assertEquals(false, params.testConnection());
		assertEquals(true, params.Run());
		assertEquals(args[3],params.projectId());
		assertEquals(args[4], params.dateSource());
		assertFalse(params.flag());
	}

	@Test
	public void testSpaceInProjectIdRunParameters() {

		String[] args ={"username","password","c:\\Program Files\\Deltek\\Cobra\\p3eapi", "Project","Id","s"};

		Parameters params = new Parameters(args);
		assertEquals("Project Id",params.projectId());
	}

	@Test
	public void testSpacesInProjectIdRunParameters() {

		String[] args ={"username","password","c:\\Program Files\\Deltek\\Cobra\\p3eapi", "ProjectId","Has","Three","Spaces","s"};

		Parameters params = new Parameters(args);
		assertEquals("ProjectId Has Three Spaces",params.projectId());

	}

}
