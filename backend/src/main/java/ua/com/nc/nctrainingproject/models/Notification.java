package ua.com.nc.nctrainingproject.models;

public class Notification extends Entity {
	private int userId;
	private int actionId;

	public Notification(int notificationId, int userId, int actionId) {
		super(notificationId);
		this.userId = userId;
		this.actionId = actionId;
	}

	public Notification() {
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
		return "Notification{" +
				"userId=" + userId +
				", actionId=" + actionId +
				'}';
	}
}
