package acountService;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private Map<String, User> dataUser;

    public AccountService() {
        dataUser = new HashMap<>();
    }

    public void addUser(User user) {
        dataUser.put(user.getLogin(), user);
    }

    public User getUserByLogin(String login) {
        return dataUser.get(login);
    }

    public boolean  checkLoginFree (String key) {
        return dataUser.containsKey(key);
    }

}
