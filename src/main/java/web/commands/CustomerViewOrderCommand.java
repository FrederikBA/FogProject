package web.commands;

import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;
import business.services.OrderFacade;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CustomerViewOrderCommand extends CommandUnprotectedPage {
    OrderFacade orderFacade;

    public CustomerViewOrderCommand(String pageToShow) {
        super(pageToShow);
        this.orderFacade = new OrderFacade(database);

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();

        //Get user ID
        User user;
        int userId = 1;
        if (session.getAttribute("user") != null) {
            user = (User) session.getAttribute("user");
            userId = user.getId();
        }

        List<Order> orders = orderFacade.getAllOrdersByUserId(userId);

        session.setAttribute("orders", orders);


        return pageToShow;
    }
}
