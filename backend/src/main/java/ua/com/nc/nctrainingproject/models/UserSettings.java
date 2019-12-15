package ua.com.nc.nctrainingproject.models;

public class UserSettings {
	private Boolean notifyAboutAnnouncements;
	private Boolean achievements;
	private Boolean bookNotification;
	private Boolean subscribeOnFriendReview;
	private Boolean notifyAboutNewFriends;
	private Boolean notifyAboutAchievement;

	public UserSettings() {

	}

	public Boolean getNotifyAboutAnnouncements() {
		return notifyAboutAnnouncements;
	}

	public void setNotifyAboutAnnouncements(Boolean subscribeOnFriends) {
		this.notifyAboutAnnouncements = subscribeOnFriends;
	}

	public Boolean getAchievements() {
		return achievements;
	}

	public void setAchievements(Boolean achievements) {
		this.achievements = achievements;
	}

	public Boolean getBookNotification() {
		return bookNotification;
	}

	public void setBookNotification(Boolean bookNotification) {
		this.bookNotification = bookNotification;
	}

	public Boolean getSubscribeOnFriendReview() {
		return subscribeOnFriendReview;
	}

	public void setSubscribeOnFriendReview(Boolean subscribeOnFriendReview) {
		this.subscribeOnFriendReview = subscribeOnFriendReview;
	}

	public Boolean getNotifyAboutNewFriends() {
		return notifyAboutNewFriends;
	}

	public void setNotifyAboutNewFriends(Boolean notifyAboutNewFriends) {
		this.notifyAboutNewFriends = notifyAboutNewFriends;
	}

	public Boolean getNotifyAboutAchievement() {
		return notifyAboutAchievement;
	}

	public void setNotifyAboutAchievement(Boolean notifyAboutAchievement) {
		this.notifyAboutAchievement = notifyAboutAchievement;
	}

	@Override
	public String toString() {
		return "UserSettings{" +
				"subscribeOnFriends=" + notifyAboutAnnouncements +
				", achievements=" + achievements +
				", bookNotification=" + bookNotification +
				", subscribeOnFriendReview=" + subscribeOnFriendReview +
				", notifyAboutNewFriends=" + notifyAboutNewFriends +
				", notifyAboutAchievement=" + notifyAboutAchievement +
				'}';
	}
}
