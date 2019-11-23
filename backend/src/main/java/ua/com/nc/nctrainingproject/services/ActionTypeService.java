package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.ActionType;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.ActionTypePostgreDAO;

import java.util.List;

@Service
public class ActionTypeService {

	private final ActionTypePostgreDAO actionTypePostgreDAO;

	@Autowired
	public ActionTypeService(ActionTypePostgreDAO actionTypePostgreDAO) {
		this.actionTypePostgreDAO = actionTypePostgreDAO;
	}

	public List<ActionType> getAllActionTypes() {
		return actionTypePostgreDAO.getAllActionTypes();
	}

	public ActionType getActionTypeByActionTypeId(int actionTypeId) {
		return actionTypePostgreDAO.getActionTypeByActionTypeId(actionTypeId);
	}

	public ActionType getActionTypeByName(String name) {
		return actionTypePostgreDAO.getActionTypeByName(name);
	}

	void deleteByActionTypeId(int actionTypeId) {
		if (actionTypePostgreDAO.getActionTypeByActionTypeId(actionTypeId) != null) {
			actionTypePostgreDAO.deleteByActionTypeId(actionTypeId);
		}
	}

	void createActionType(ActionType actionType) {
		if (actionTypePostgreDAO.getActionTypeByActionTypeId(actionType.getActionTypeId()) != null) {
			actionTypePostgreDAO.createActionType(actionType);
		}
	}

	void updateActionTypeById(int actionTypeId, ActionType actionType) {
		if (actionTypePostgreDAO.getActionTypeByActionTypeId(actionTypeId) != null) {
			actionTypePostgreDAO.updateActionTypeById(actionTypeId, actionType);
		}
	}
}
