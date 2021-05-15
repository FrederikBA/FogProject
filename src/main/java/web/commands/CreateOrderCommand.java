package web.commands;

import business.entities.Bom;
import business.entities.BomLine;
import business.entities.User;
import business.exceptions.UserException;
import business.services.BomService;
import business.services.OrderFacade;
import business.services.SVG;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.Locale;

public class CreateOrderCommand extends CommandUnprotectedPage {
    BomService bomService;
    OrderFacade orderFacade;

    public CreateOrderCommand(String pageToShow) {
        super(pageToShow);
        bomService = new BomService(database);
        orderFacade = new OrderFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        Locale.setDefault(new Locale("US"));
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

            //Draw Carport
            SVG svg = new SVG(0, 0, "0 0 855 600", 100, 100);

            for (int x = 0; x < 14; x++) {
                svg.addRect(100 + 50*x, 0, 600, 4.5);
            }

            request.setAttribute("drawing", svg.toString());

            //Save order
            orderFacade.createOrder(userId, length, width, bom.getBomLines());

            //Clear Bill of Materials ArrayList
            bomService.calculateCarportFromMeasurements(width, length).clear();
            bom.getBomLines().clear();

            //Get total price of latest Carport order
            double totalPrice = orderFacade.getOrderPriceByTimestamp();
            DecimalFormat df = new DecimalFormat("#.00");
            session.setAttribute("totalPrice", df.format(totalPrice));


            return "checkout";
        }

        return pageToShow;
    }
}
