package web.commands;

import business.entities.Bom;
import business.entities.BomLine;
import business.entities.User;
import business.exceptions.UserException;
import business.services.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.Locale;

public class CreateOrderCommand extends CommandUnprotectedPage {
    BomService bomService;
    OrderFacade orderFacade;
    BomFacade bomFacade;
    DrawingService drawingService;

    public CreateOrderCommand(String pageToShow) {
        super(pageToShow);
        bomService = new BomService(database);
        orderFacade = new OrderFacade(database);
        bomFacade = new BomFacade(database);
        drawingService = new DrawingService(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        User user;
        int width = 0;
        int length = 0;
        int userId = 1;

        //Get user ID
        if (session.getAttribute("user") != null) {
            user = (User) session.getAttribute("user");
            userId = user.getId();
        }

        //Create Bill of Materials
        Bom bom = (Bom) session.getAttribute("bom");
        if (bom == null) {
            bom = new Bom();
            session.setAttribute("bom", bom);
        }

        //Check for login
        if (request.getParameter("begin") != null && session.getAttribute("user") == null) {
            throw new UserException("Du skal være logged ind for at skræddersy en carport.");
        }

        //Get value from dropdowns and parse to Integer
        if (request.getParameter("width") != null || request.getParameter("length") != null) {
            width = Integer.parseInt(request.getParameter("width"));
            length = Integer.parseInt(request.getParameter("length"));
            session.setAttribute("width", width);
            session.setAttribute("length", length);
        }

        //Place order
        if (request.getParameter("create") != null) {

            //Add to Bill of Materials
            for (BomLine bomLine : bomService.calculateCarportFromMeasurements(width, length)) {
                bom.getBomLines().add(bomLine);
            }

            //Save order
            orderFacade.createOrder(userId, length, width, bom.getBomLines());

            //Clear Bill of Materials ArrayList
            bomService.calculateCarportFromMeasurements(width, length).clear();
            bom.getBomLines().clear();

            //Get total price of latest Carport order
            double totalPrice = orderFacade.getOrderPriceByTimestamp();
            DecimalFormat df = new DecimalFormat("#.00");
            session.setAttribute("totalPrice", df.format(totalPrice));

            //Drawings
            //Get Order ID
            int orderId = orderFacade.getOrderIdByTimestamp();

            //Carport seen from above
            SVG carportTop = drawingService.drawCarportTop(width, length, orderId);

            //Arrows with text
            SVG carportTopArrows = drawingService.drawCarportTopArrows(width, length, orderId);

            //Combine drawings
            carportTopArrows.addSvg(carportTop);

            //Carport seen from the side
            SVG carportSide = drawingService.drawCarportSide(width, length, orderId);

            //Arrows with text
            SVG carportSideArrows = drawingService.drawCarportSideArrows(width, length, orderId);

            //Combine drawings
            carportSideArrows.addSvg(carportSide);

            //Save drawings
            request.setAttribute("sideDrawing", carportSideArrows.toString());
            request.setAttribute("drawing", carportTopArrows.toString());

            return "checkout";
        }

        return pageToShow;
    }
}
