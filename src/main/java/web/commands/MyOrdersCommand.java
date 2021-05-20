package web.commands;

import business.entities.BomLine;
import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;
import business.services.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MyOrdersCommand extends CommandUnprotectedPage {
    OrderFacade orderFacade;
    BomFacade bomFacade;
    DrawingService drawingService;

    public MyOrdersCommand(String pageToShow) {
        super(pageToShow);
        this.orderFacade = new OrderFacade(database);
        this.bomFacade = new BomFacade(database);
        this.drawingService = new DrawingService(database);

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

            //Drawings
            double length = order.getLength();
            double width = order.getWidth();
            request.setAttribute("length", length);
            request.setAttribute("width", width);

            //Carport seen from above
            SVG carportTop = drawingService.drawCarportTop(width, length, orderId);

            //Arrows with text
            SVG carportTopArrows = drawingService.drawCarportTopArrows(width, length, orderId);

            //Combine drawings
            carportTopArrows.addSvg(carportTop);

            //Save drawing
            request.setAttribute("drawing", carportTopArrows.toString());

            //Carport seen from the side
            SVG carportSide = drawingService.drawCarportSide(width, length, orderId);

            //Arrows with text
            SVG carportSideArrows = drawingService.drawCarportSideArrows(width, length, orderId);

            //Combine drawings:
            carportSideArrows.addSvg(carportSide);

            //Save drawing
            request.setAttribute("sideDrawing", carportSideArrows.toString());

            return "myorderscontent";
        }
        return pageToShow;
    }
}
