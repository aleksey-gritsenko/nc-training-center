package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.AchivementDto;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.services.AchivementService;

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
    public AchivementDto createAchievement
            (@RequestParam String achievementName, @RequestParam String action, @RequestParam String genre, @RequestParam int count, @RequestParam String entity){
      AchivementDto a =new AchivementDto(achievementName,action,genre,count,entity);

        achivementService.createAchevementDto(achievementName,action,genre,count,entity
        );
        return a;
    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<AchivementDto> getAll(){

        return achivementService.getAllAchievementDtos();
    }

    @RequestMapping(value = "/all-for-user", method = RequestMethod.POST)
    @ResponseBody
    public List<AchivementDto> getAllForUser(@RequestBody User user){
        //TODO check if user model got from from front has id
        return achivementService.getAllAchievementDtosForUser(user.getId());
    }
}
