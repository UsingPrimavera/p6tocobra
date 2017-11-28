package p3eapi;

import com.primavera.integration.client.bo.BOIterator;
import com.primavera.integration.client.bo.object.ResourceAssignment;

interface IProject {
	public BOIterator<ResourceAssignment> getResourceAssignments(String[] fields);
}
