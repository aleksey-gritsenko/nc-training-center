package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<ActionType> getAllActionTypes() {
		return actionTypeService.getAllActionTypes();
	}
}
