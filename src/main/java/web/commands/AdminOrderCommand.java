package web.commands;

import business.entities.BomLine;
import business.entities.Order;
import business.exceptions.UserException;
import business.services.BomFacade;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminOrderCommand extends CommandProtectedPage {
    OrderFacade orderFacade;
    BomFacade bomFacade;

    public AdminOrderCommand(String pageToShow, String role) {
        super(pageToShow, role);
        orderFacade = new OrderFacade(database);
        bomFacade = new BomFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();

        //Show list of orders
        List<Order> orderList = orderFacade.getAllOrders();

        //Show list of orders for adminOrderPage.jsp
        session.setAttribute("orderlist", orderList);


        //Show order content from specific orders
        if (request.getParameter("content") != null) {
            int orderId = Integer.parseInt(request.getParameter("content"));
            request.setAttribute("orderId", orderId);
            List<BomLine> billOfMaterials = bomFacade.getBomByOrderId(orderId);

            request.setAttribute("billOfMaterials", billOfMaterials);
            return "ordercontentpage";

        }

        return pageToShow;

    }
}
