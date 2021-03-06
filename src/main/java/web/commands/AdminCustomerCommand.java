package web.commands;

import business.entities.User;
import business.exceptions.UserException;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminCustomerCommand extends CommandProtectedPage {
    UserFacade userFacade;


    public AdminCustomerCommand(String pageToShow, String role) {
        super(pageToShow, role);
        userFacade = new UserFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        List<User> userList = userFacade.getAllUsers();

        request.setAttribute("userList", userList);

        return pageToShow;
    }
}
