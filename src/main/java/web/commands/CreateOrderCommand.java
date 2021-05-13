package web.commands;

import business.entities.Bom;
import business.entities.BomLine;
import business.entities.User;
import business.exceptions.UserException;
import business.services.BomService;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

            //Save order
            orderFacade.createOrder(userId, length, width, bom.getBomLines());

            //Clear Bill of Materials
            bom.getBomLines().clear();

            //Get total price of latest Carport order
            double totalPrice = orderFacade.getOrderPriceByTimestamp();
            session.setAttribute("totalPrice", totalPrice);

            return "checkout";
        }

        return pageToShow;
    }
}
