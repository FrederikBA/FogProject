package web.commands;

import business.entities.BomLine;
import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;
import business.services.BomFacade;
import business.services.OrderFacade;
import business.services.SVG;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MyOrdersCommand extends CommandUnprotectedPage {
    OrderFacade orderFacade;
    BomFacade bomFacade;

    public MyOrdersCommand(String pageToShow) {
        super(pageToShow);
        this.orderFacade = new OrderFacade(database);
        this.bomFacade = new BomFacade(database);

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
        //Get all orders
        List<Order> orders = orderFacade.getAllOrdersByUserId(userId);

        session.setAttribute("orders", orders);

        //Show Order content
        if (request.getParameter("content") != null) {
            //Get orderId
            int orderId = Integer.parseInt(request.getParameter("content"));

            //Get specific order
            Order order = orderFacade.getOrder(orderId);

            //Get Bill of Materials from specific order.
            List<BomLine> billOfMaterials = bomFacade.getBomByOrderId(orderId);
            request.setAttribute("billOfMaterials", billOfMaterials);

            //Get Order Status from specific order
            String status = order.getStatus();
            request.setAttribute("status", status);

            //Get Order Price from specific order
            DecimalFormat df = new DecimalFormat("#.00");
            Double totalPrice = order.getPrice();
            request.setAttribute("totalPrice", df.format(totalPrice));

            //Drawing
            double length = order.getLength();
            double width = order.getWidth();
            request.setAttribute("length", length);
            request.setAttribute("width", width);

            //Draw Carport
            SVG svg = new SVG(0, 0, "0 0 855 600", 100, 100);

            //Draw Frame
            svg.addRect(0, 0, width, length);

            //Draw Spær
            BomLine spær = billOfMaterials.get(1);
            double dquantity = spær.getQuantity();
            double distance = length / (dquantity - 1);
            for (int x = 0; x < spær.getQuantity(); x++) {
                svg.addSpær(distance * x, 0, width, 4.5);
            }

            //Save Drawing
            request.setAttribute("drawing", svg.toString());

            return "myorderscontent";
        }
        return pageToShow;
    }
}
