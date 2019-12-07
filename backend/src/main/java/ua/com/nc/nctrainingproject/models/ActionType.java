package ua.com.nc.nctrainingproject.models;

public class ActionType {
	private int actionTypeId;
	private String actionName;

	public ActionType(int actionTypeId, String actionName) {
		this.actionTypeId = actionTypeId;
		this.actionName = actionName;
	}

	public ActionType(String actionName) {
		this.actionName = actionName;
	}

	public ActionType() {
	}

	public int getActionTypeId() {
		return actionTypeId;
	}

	public void setActionTypeId(int actionTypeId) {
		this.actionTypeId = actionTypeId;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	@Override
	public String toString() {
		return "ActionType{" +
				"actionTypeId=" + actionTypeId +
				", actionName='" + actionName + '\'' +
				'}';
	}
}
