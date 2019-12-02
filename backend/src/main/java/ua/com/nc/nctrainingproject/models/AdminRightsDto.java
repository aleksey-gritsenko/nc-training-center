package ua.com.nc.nctrainingproject.models;

import java.util.List;

public class AdminRightsDto {
    private User user;
    private List<Boolean> rights;

    public AdminRightsDto(){}
    public AdminRightsDto(User user, List<Boolean> rights){
        this.rights = rights;
        this.user = user;
    }

    public List<Boolean> getRights() {
        return rights;
    }

    public User getUser() {
        return user;
    }

    public void setRights(List<Boolean> rights) {
        this.rights = rights;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
