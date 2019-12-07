package ua.com.nc.nctrainingproject.models;

public class ActionType {
	private int actionTypeId;
	private String actionName;
	private String entity;

	public ActionType(int actionTypeId, String actionName, String entity) {
		this.actionTypeId = actionTypeId;
		this.actionName = actionName;
		this.entity = entity;
	}

	public ActionType(String actionName, String entity) {
		this.actionName = actionName;
		this.entity = entity;
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

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	@Override
	public String toString() {
		return "ActionType{" +
				"actionTypeId=" + actionTypeId +
				", actionName='" + actionName + '\'' +
				'}';
	}
}
