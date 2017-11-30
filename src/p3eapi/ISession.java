package p3eapi;

import com.primavera.integration.client.GlobalObjectManager;
import com.primavera.integration.client.EnterpriseLoadManager;
import com.primavera.integration.client.bo.object.ResourceAssignment;
import com.primavera.common.value.BeginDate;
import com.primavera.common.value.EndDate;

interface ISession {

	boolean login(String url, String dbase, String username, String password);
	public GlobalObjectManager getGlobalObjectManager();
	public EnterpriseLoadManager getEnterpriseLoadManager();
	public ResourceAssignment loadSpread(ResourceAssignment resAss, String[] staticFields, String[] spreadFields, BeginDate startDate, EndDate finishDate);

}
