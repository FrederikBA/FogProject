package web.commands;

import business.entities.BomLine;
import business.entities.Order;
import business.exceptions.UserException;
import business.services.BomFacade;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        //Show list of orders
        List<Order> orderList = orderFacade.getAllOrders();


        //Show order content from specific orders
        if (request.getParameter("content") != null) {
            int orderId = Integer.parseInt(request.getParameter("content"));
            request.setAttribute("orderId", orderId);
            List<BomLine> billOfMaterials = bomFacade.getBomByOrderId(orderId);
            request.setAttribute("billOfMaterials", billOfMaterials);
            return "ordercontentpage";
        }

        //Update Order Status
        if (request.getParameter("update") != null) {
            int orderId = Integer.parseInt(request.getParameter("update"));
            request.setAttribute("orderId", orderId);
            orderFacade.updateOrderStatusToConfirmed(orderId);
            orderList = orderFacade.getAllOrders();
        }
        //Delete order
        if (request.getParameter("delete") != null) {
            String deleteId = request.getParameter("delete");
            orderFacade.deleteOrderContent(Integer.parseInt(deleteId));
            orderFacade.deleteOrder(Integer.parseInt(deleteId));
            orderList = orderFacade.getAllOrders();
        }

        request.setAttribute("orderlist", orderList);
        return pageToShow;
    }
}
