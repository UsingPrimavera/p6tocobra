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
		expectLocalLoginOk(context, rmiurl);
		
		// test
		ISession session = null;
		P6Connection connection = new P6Connection(rmiurl, session );

		assertEquals(0,connection.getCallingMode());
		assertEquals("http://localhost",connection.getHostname());
		assertEquals(9099,connection.getPort());
		assertEquals("", connection.getRmiUrl());
	}

	@Test
	public void testLoginNoproblems() {
		// Setup
		final ISession session = context.mock(ISession.class);
		final IRmiUrl rmiurl = context.mock(IRmiUrl.class);

		// expectations
		expectLocalLoginOk(context, rmiurl);

		context.checking(new Expectations(){{
			one(session).login("",null,"admin","secret"); will(returnValue(true));
		}});

		//test
		P6Connection connection = new P6Connection(rmiurl, session);
		connection.login("admin","secret");

		assertEquals(true, connection.isLoggedIn());
	}

	@Test
	public void testLoginWrongCredentials() {
		// Setup
		final ISession session = context.mock(ISession.class);
		final IRmiUrl rmiurl = context.mock(IRmiUrl.class);

		// expectations
		expectLocalLoginOk(context, rmiurl);

		context.checking(new Expectations(){{
			one(session).login("",null,"admin","wrong password"); will(returnValue(false));
		}});

		//test
		P6Connection connection = new P6Connection(rmiurl, session);
		connection.login("admin","wrong password");

		assertEquals(false, connection.isLoggedIn());


	}

	private void expectLocalLoginOk(Mockery context, IRmiUrl rmiurl) {
		context.checking(new Expectations(){{
			allowing(rmiurl).getCompressionRmiService(); will(returnValue(2));
			allowing(rmiurl).getLocalService(); will(returnValue(0));
			allowing(rmiurl).getSSLRmiService(); will(returnValue(3));
			allowing(rmiurl).getStandardRmiService(); will(returnValue(1));
			allowing(rmiurl).getRmiUrl(0, "http://localhost", 9099); will(returnValue(""));
		}});
	

	}

}




