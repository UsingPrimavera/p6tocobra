package p3eapi;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.Expectations;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ConnectionTest {

	public ConnectionTest() {
	}

	private Mockery context = new JUnit4Mockery();

	@Test
	public void testLoadsDefaults() {

		// Setup
		final IRmiUrl rmiurl = context.mock(IRmiUrl.class);

		// expectations
		context.checking(new Expectations(){{
			allowing(rmiurl).getCompressionRmiService(); will(returnValue(2));
			allowing(rmiurl).getLocalService(); will(returnValue(0));
			allowing(rmiurl).getSSLRmiService(); will(returnValue(3));
			allowing(rmiurl).getStandardRmiService(); will(returnValue(1));
			allowing(rmiurl).getRmiUrl(0, "http://localhost", 9099); will(returnValue(""));
		}});
		
		// test
		P6Connection connection = new P6Connection(rmiurl);

		assertEquals(0,connection.getCallingMode());
		assertEquals("http://localhost",connection.getHostname());
		assertEquals(9099,connection.getPort());
		assertEquals("", connection.getRmiUrl());
	}
}




