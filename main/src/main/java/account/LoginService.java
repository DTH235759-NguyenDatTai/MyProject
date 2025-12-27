package account;

import dao.AccountDAO;
import model.User;

public class LoginService {

    private AccountDAO accountDAO = new AccountDAO();

    public User login(String email, String password) {
        return accountDAO.login(email, password);
    }
}
