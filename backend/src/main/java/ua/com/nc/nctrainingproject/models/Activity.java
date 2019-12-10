package ua.com.nc.nctrainingproject.models;

public class Activity extends Entity {
	private int userId;
	private int actionId;

	public Activity(int activityId, int userId, int actionId) {
		super(activityId);
		this.userId = userId;
		this.actionId = actionId;
	}

	public Activity() {
	}

	public Activity(int actionId, int userId) {
		this.actionId = actionId;
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	@Override
	public String toString() {
		return "Activity{" +
				"userId=" + userId +
				", actionId=" + actionId +
				'}';
	}
}
