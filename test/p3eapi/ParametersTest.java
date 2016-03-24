package p3eapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class ParametersTest {

	public ParametersTest() {
	}

	@Test
	public void testRequestParameters() {

		String[] args ={"username","password","c:\\Program Files\\Deltek\\Cobra\\p3eapi", "TestConnection","s","true"};

		Parameters params = new Parameters(args);
		assertEquals(args[0], params.Username());
		assertEquals(args[1], params.Password());
		assertEquals(args[2], params.Pathname());
		assertEquals(true, params.TestConnection());
		assertEquals(args[4],params.DateSource());
		assertTrue(params.Boolean());
		assertNull(params.ProjectId());
	}

}
