package ua.com.nc.nctrainingproject.persistance.dao;

public interface AdministratorDAO {

    Admin getAdministratorByName(String name);

    void createAdministrator(Admin administrator);


}
