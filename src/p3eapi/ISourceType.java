package p3eapi;

import com.primavera.common.value.BeginDate;
import com.primavera.common.value.Cost;
import com.primavera.common.value.EndDate;
import com.primavera.common.value.Unit;
import com.primavera.common.value.spread.ResourceAssignmentSpreadPeriod;
import com.primavera.common.value.spread.SpreadFieldNotFoundException;
import com.primavera.integration.client.bo.BusinessObjectException;
import com.primavera.integration.client.bo.object.ResourceAssignment;

interface ISourceType {
	public String[] getResourceAssignmentFields();
	public String[] getSpreadFields();
  public BeginDate getStartDate(ResourceAssignment resAss) throws BusinessObjectException;
  public EndDate getFinishDate(ResourceAssignment resAss) throws BusinessObjectException;
  public Unit getSpreadUnitsField(ResourceAssignmentSpreadPeriod resAssSpreadPeriod) throws SpreadFieldNotFoundException;
  public Cost getSpreadCostField(ResourceAssignmentSpreadPeriod resAssSpreadPeriod) throws SpreadFieldNotFoundException;
}
