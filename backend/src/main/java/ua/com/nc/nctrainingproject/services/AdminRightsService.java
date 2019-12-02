package ua.com.nc.nctrainingproject.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.AdminRightsDto;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.AdminRightsPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminRightsService {

    private final AdminRightsPostgreDAO adminRightsPostgreDAO;
    private final UserPostgreDAO userPostgreDAO;
    //TODO rewrite to add possibility to add new rights
    private final int NUMBER_OF_RIGHTS = 3;
    private final String role = "moderator";


    @Autowired
    public AdminRightsService(AdminRightsPostgreDAO adminRightsPostgreDAO, UserPostgreDAO userPostgreDAO) {
        this.adminRightsPostgreDAO = adminRightsPostgreDAO;
        this.userPostgreDAO = userPostgreDAO;
    }

    public void updateDb(List<AdminRightsDto> data){

        for (AdminRightsDto dto: data){
            adminRightsPostgreDAO.deleteRightsByUserId(dto.getUser().getId());
            for (int i = 0 ; i < dto.getRights().size(); i++){
                if (dto.getRights().get(i)){
                    adminRightsPostgreDAO.add(dto.getUser().getId() , i);
                }
            }

        }
    }

    public List<AdminRightsDto> getData(){
        List<User> adminsList = userPostgreDAO.getUsersByRole(role);
        List<AdminRightsDto> result = new ArrayList<>();
        for (User admin: adminsList){
            AdminRightsDto dto = new AdminRightsDto();
            dto.setUser(admin);
            List<Boolean> hasRight = new ArrayList<>();
            List<Integer> actualRights = adminRightsPostgreDAO.getRightsIdByUserId(admin.getId());
            for(int i = 0 ; i < NUMBER_OF_RIGHTS ; i++){
                hasRight.add(actualRights.contains(i));
            }
            dto.setRights(hasRight);
            result.add(dto);

        }
        return result;
    }
}
