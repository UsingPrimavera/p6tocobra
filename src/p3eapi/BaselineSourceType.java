package p3eapi;

import com.primavera.common.value.BeginDate;
import com.primavera.common.value.Cost;
import com.primavera.common.value.EndDate;
import com.primavera.common.value.Unit;
import com.primavera.common.value.spread.ResourceAssignmentSpreadPeriod;
import com.primavera.common.value.spread.SpreadFieldNotFoundException;
import com.primavera.integration.client.bo.BusinessObjectException;
import com.primavera.integration.client.bo.object.ResourceAssignment;

public class BaselineSourceType implements ISourceType {

  public BaselineSourceType() {
    super();
  }
  public String[] getResourceAssignmentFields() {
    return new String[]{"ObjectId","PlannedStartDate","PlannedFinishDate"};
  }

	public String[] getSpreadFields() {
    return new String[]{"RemainingUnits", "RemainingCost", "PlannedUnits", "PlannedCost"};
  }

  public BeginDate getStartDate(ResourceAssignment resAss) throws BusinessObjectException {
    return resAss.getPlannedStartDate();
  }

  public EndDate getFinishDate(ResourceAssignment resAss) throws BusinessObjectException {
    return resAss.getPlannedFinishDate();
  }

  public Unit getSpreadUnitsField(ResourceAssignmentSpreadPeriod resAssSpreadPeriod) throws SpreadFieldNotFoundException {
    return resAssSpreadPeriod.getPlannedUnits();
  }

  public Cost getSpreadCostField(ResourceAssignmentSpreadPeriod resAssSpreadPeriod) throws SpreadFieldNotFoundException {
    return resAssSpreadPeriod.getPlannedCost();
  }
}
