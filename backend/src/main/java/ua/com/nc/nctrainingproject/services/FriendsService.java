package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.FriendsPostgreDAO;

import java.util.List;

@Service

public class FriendsService {

	private final FriendsPostgreDAO friendsPostgreDAO;
	private final ActionService actionService;

	@Autowired
	public FriendsService(FriendsPostgreDAO friendsPostgreDAO, ActionService actionService) {
		this.friendsPostgreDAO = friendsPostgreDAO;
		this.actionService = actionService;
	}

	public void sendRequest(int sender, int receiver) {
		friendsPostgreDAO.sendRequest(sender, receiver);
	}

	public void acceptRequest(int sender, int receiver) {
		friendsPostgreDAO.acceptRequest(sender, receiver);
		actionService.addNewAction(receiver, 1);
		actionService.addNewAction(sender, 1);
	}

	public void rejectRequest(int sender, int receiver) {
		friendsPostgreDAO.rejectRequest(sender, receiver);
	}

	public List<User> getAllFriends(int user) {
		return friendsPostgreDAO.getAllFriends(user);
	}

	public List<User> getAllNewRequests(int user) {
		return friendsPostgreDAO.getAllNewRequests(user);
	}

	public boolean checkRequest(int sender, int receiver) {

		return sender != receiver
				& friendsPostgreDAO.getCountApplications(sender, receiver) == 0;
	}

}
