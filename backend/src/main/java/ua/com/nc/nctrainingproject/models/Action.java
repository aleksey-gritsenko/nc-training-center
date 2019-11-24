package ua.com.nc.nctrainingproject.models;

public class Action {
	private int actionId;
	private int userId;
	private int actionTypeId;

	public Action(int actionId, int userId, int actionTypeId) {
		this.actionId = actionId;
		this.userId = userId;
		this.actionTypeId = actionTypeId;
	}

	public Action() {
	}

	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getActionTypeId() {
		return actionTypeId;
	}

	public void setActionTypeId(int actionTypeId) {
		this.actionTypeId = actionTypeId;
	}

	@Override
	public String toString() {
		return "Action{" +
				"userId=" + userId +
				", actionTypeId=" + actionTypeId +
				'}';
	}
}
