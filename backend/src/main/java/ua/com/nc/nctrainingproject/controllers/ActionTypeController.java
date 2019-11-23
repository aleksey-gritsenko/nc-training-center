package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.nc.nctrainingproject.models.ActionType;
import ua.com.nc.nctrainingproject.services.ActionTypeService;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/actiontype")
public class ActionTypeController {

	private final ActionTypeService actionTypeService;

	@Autowired
	public ActionTypeController(ActionTypeService actionTypeService) {
		this.actionTypeService = actionTypeService;
	}

	@RequestMapping(value = "/all", method = RequestMethod.POST)
	public List<ActionType> getAll() {
		return actionTypeService.getAllActionTypes();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ActionType createActionType(@RequestParam(name = "actionTypeId") int actionTypeId,
									   @RequestParam(name = "actionName") String actionName) {
		ActionType actionType = new ActionType(actionTypeId, actionName);
		actionTypeService.createActionType(actionType);
		return actionType;
	}

	@RequestMapping(name = "/delete", method = RequestMethod.POST)
	public void deleteActionType(@RequestParam(name = "actionTypeId") int actionTypeId) {
		actionTypeService.deleteByActionTypeId(actionTypeId);
	}

	@RequestMapping(name = "/update", method = RequestMethod.POST)
	public ActionType updateActionType(@RequestParam(name = "actionTypeId") int actionTypeId,
									   @RequestParam(name = "name") String name) {

		ActionType actionType = new ActionType(actionTypeId, name);
		actionTypeService.updateActionTypeById(actionTypeId, actionType);
		return actionType;
	}

	@RequestMapping(name = "/get/{actionTypeId}", method = RequestMethod.GET)
	public ActionType getByActionTypeId(@RequestParam(name = "actionTypeId") int actionTypeId) {
		return actionTypeService.getActionTypeByActionTypeId(actionTypeId);
	}

	@RequestMapping(name = "/get/{name}", method = RequestMethod.GET)
	public ActionType getByActionTypeId(@RequestParam(name = "name") String name) {
		return actionTypeService.getActionTypeByName(name);
	}
}
