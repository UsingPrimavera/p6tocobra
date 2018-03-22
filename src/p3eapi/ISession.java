package p3eapi;

import com.primavera.integration.client.GlobalObjectManager;
import com.primavera.integration.client.EnterpriseLoadManager;
import com.primavera.integration.client.Session;

interface ISession {

	boolean login(String url, String dbase, String username, String password);
	public GlobalObjectManager getGlobalObjectManager();
	public EnterpriseLoadManager getEnterpriseLoadManager();
	public Session getSession();

}
