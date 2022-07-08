package accounts;

import executor.DBException;
import executor.DBService;

import java.util.HashMap;
import java.util.Map;


public class AccountService {

    public void addNewUser(UserProfile userProfile) throws DBException {
       new  DBService().addUser(userProfile);
    }

    public UserProfile getUserByLogin(String login) throws DBException {
        return new DBService().getUser(login);
    }



}
