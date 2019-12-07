package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.ActionType;
import ua.com.nc.nctrainingproject.services.ActionTypeService;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@Controller
@RequestMapping("/actiontype")
public class ActionTypeController {

	private final ActionTypeService actionTypeService;

	@Autowired
	public ActionTypeController(ActionTypeService actionTypeService) {
		this.actionTypeService = actionTypeService;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		List<ActionType> response = actionTypeService.getAllActionTypes();
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/allNames", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<?> getAllActionTypeNames() {
		List<String> response = actionTypeService.getAllActionTypesNames();
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> createActionType(@RequestBody ActionType actionType) {
		ActionType response = actionTypeService.createActionType(actionType);
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public void deleteActionType(@RequestParam(name = "actionTypeId") int actionTypeId) {
		actionTypeService.deleteByActionTypeId(actionTypeId);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> updateActionType(@RequestBody ActionType actionType) {
		ActionType response = actionTypeService.updateActionTypeById(actionType.getActionTypeId(), actionType);
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/get/{actionTypeId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getByActionTypeId(@PathVariable(name = "actionTypeId") int actionTypeId) {
		ActionType response = actionTypeService.getActionTypeByActionTypeId(actionTypeId);
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/get/{name}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getByActionTypeId(@PathVariable(name = "name") String name) {
		ActionType response = actionTypeService.getActionTypeByName(name);
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}
}
