package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.Achivement;
import ua.com.nc.nctrainingproject.services.AchivementService;
import ua.com.nc.nctrainingproject.services.ActionTypeService;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/achievement")
public class AchievementController {

    private final AchivementService achivementService;

    @Autowired
    public AchievementController(AchivementService achivementService) {
        this.achivementService = achivementService;
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Achivement createAchievement
            (@RequestParam String achievementName, @RequestParam String action, @RequestParam String genre, @RequestParam int count, @RequestParam String entity){
      Achivement a =new Achivement(achievementName,action,genre,count,entity);

        achivementService.createAchevement(achievementName,action,genre,count,entity
        );
        return a;
    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Achivement> getAl(){

        return achivementService.getAllAchievements();
    }
}
