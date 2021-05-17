package web.commands;

import business.entities.BomLine;
import business.exceptions.UserException;
import business.services.BomFacade;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ConfirmOrderCommand extends CommandUnprotectedPage {
    OrderFacade orderFacade;
    BomFacade bomFacade;

    public ConfirmOrderCommand(String pageToShow) {
        super(pageToShow);
        orderFacade = new OrderFacade(database);
        bomFacade = new BomFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();

        //Show Bill of Materials from latest order
        int orderId = orderFacade.getOrderIdByTimestamp();
        List<BomLine> billOfMaterials = bomFacade.getBomByOrderId(orderId);

        //Change order status to pending.
        if (request.getParameter("confirm") != null) {
            orderFacade.updateOrderStatusToPending(orderId);
            session.setAttribute("billOfMaterials", billOfMaterials);
            return "receipt";
        }

        if (request.getParameter("return") != null) {
            return "carport";
        }

        return pageToShow;
    }
}
