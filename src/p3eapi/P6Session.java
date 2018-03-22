package p3eapi;

import java.lang.Exception;
import com.primavera.integration.client.Session;
import com.primavera.ServerException;
import com.primavera.integration.client.ClientException;
import com.primavera.integration.network.NetworkException;
import com.primavera.integration.client.GlobalObjectManager;
import com.primavera.integration.client.EnterpriseLoadManager;

class P6Session implements ISession {

	private Session p6session = null;

	private Exception caughtException = null;

	public P6Session() {
	}

	public boolean login(String url, String dbase, String username, String password) {

		boolean result = false;
		try {
			Session p6session = Session.login(url,dbase,username,password);
			this.p6session = p6session;
			result = true;
		}
		catch (ServerException ex)
		{
			this.caughtException = ex;
		}
		catch (ClientException ex)
		{
			this.caughtException = ex;
		}
		catch (NetworkException ex)
		{
			this.caughtException = ex;
		}
		finally
		{
			return result;
		}

	}

	public GlobalObjectManager getGlobalObjectManager() {
		return p6session.getGlobalObjectManager();
	}

	public EnterpriseLoadManager getEnterpriseLoadManager() {
		return p6session.getEnterpriseLoadManager();
	}

	public Session getSession() {
		return p6session;
	}


}
