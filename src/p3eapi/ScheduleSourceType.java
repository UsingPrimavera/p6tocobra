package p3eapi;

import com.primavera.common.value.BeginDate;
import com.primavera.common.value.Cost;
import com.primavera.common.value.EndDate;
import com.primavera.common.value.Unit;
import com.primavera.common.value.spread.ResourceAssignmentSpreadPeriod;
import com.primavera.common.value.spread.SpreadFieldNotFoundException;
import com.primavera.integration.client.bo.BusinessObjectException;
import com.primavera.integration.client.bo.object.ResourceAssignment;

public class ScheduleSourceType implements ISourceType {

  public ScheduleSourceType() {
    super();
  }

  public String[] getResourceAssignmentFields() {
    return new String[]{"ObjectId","StartDate","FinishDate"};
  }

	public String[] getSpreadFields() {
    return new String[]{"RemainingUnits", "RemainingCost", "AtCompletionUnits", "AtCompletionCost"};
  }

  public BeginDate getStartDate(ResourceAssignment resAss) throws BusinessObjectException {
    return resAss.getStartDate();
  }

  public EndDate getFinishDate(ResourceAssignment resAss) throws BusinessObjectException {
    return resAss.getFinishDate();
  }

  public Unit getSpreadUnitsField(ResourceAssignmentSpreadPeriod resAssSpreadPeriod) throws SpreadFieldNotFoundException {
    return resAssSpreadPeriod.getAtCompletionUnits();
  }

  public Cost getSpreadCostField(ResourceAssignmentSpreadPeriod resAssSpreadPeriod) throws SpreadFieldNotFoundException {
    return resAssSpreadPeriod.getAtCompletionCost();
  }
}
