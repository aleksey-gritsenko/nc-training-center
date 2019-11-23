package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
	@ResponseBody
	public List<ActionType> getAll() {
		return actionTypeService.getAllActionTypes();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public ActionType createActionType(@RequestParam(name = "actionTypeId") int actionTypeId,
									   @RequestParam(name = "actionName") String actionName) {
		ActionType actionType = new ActionType(actionTypeId, actionName);
		actionTypeService.createActionType(actionType);
		return actionType;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public void deleteActionType(@RequestParam(name = "actionTypeId") int actionTypeId) {
		actionTypeService.deleteByActionTypeId(actionTypeId);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ActionType updateActionType(@RequestParam(name = "actionTypeId") int actionTypeId,
									   @RequestParam(name = "name") String name) {

		ActionType actionType = new ActionType(actionTypeId, name);
		actionTypeService.updateActionTypeById(actionTypeId, actionType);
		return actionType;
	}

	@RequestMapping(value = "/get/{actionTypeId}", method = RequestMethod.GET)
	@ResponseBody
	public ActionType getByActionTypeId(@PathVariable(name = "actionTypeId") int actionTypeId) {
		return actionTypeService.getActionTypeByActionTypeId(actionTypeId);
	}

	@RequestMapping(value = "/get/{name}", method = RequestMethod.GET)
	@ResponseBody
	public ActionType getByActionTypeId(@PathVariable(name = "name") String name) {
		return actionTypeService.getActionTypeByName(name);
	}
}
