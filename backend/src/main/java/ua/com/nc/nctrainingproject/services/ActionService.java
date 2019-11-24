package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.Action;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.ActionPostgreDAO;

import java.util.List;

@Service
public class ActionService {

	private final ActionPostgreDAO actionPostgreDAO;

	@Autowired
	public ActionService(ActionPostgreDAO actionPostgreDAO) {
		this.actionPostgreDAO = actionPostgreDAO;
	}

	public List<Action> getAllActions() {
		return actionPostgreDAO.getAllActions();
	}

	public Action getActionById(int actionId) {
		return actionPostgreDAO.getActionById(actionId);
	}

	public Action getActionByUserId(int userId) {
		return actionPostgreDAO.getActionByUserId(userId);
	}

	public Action getActionByActionTypeId(int actionTypeId) {
		return actionPostgreDAO.getActionByActionTypeId(actionTypeId);
	}

	public void deleteActionByActionId(int actionId) {
		if (actionPostgreDAO.getActionById(actionId) != null) {
			actionPostgreDAO.deleteActionByActionId(actionId);
		}
	}

	public void createAction(Action action) {
		if (actionPostgreDAO.getActionById(action.getActionId()) != null) {
			actionPostgreDAO.createAction(action);
		}
	}

	public void updateAction(int actionId, Action action) {
		if (actionPostgreDAO.getActionById(actionId) != null) {
			actionPostgreDAO.updateAction(actionId, action);
		}
	}
}
