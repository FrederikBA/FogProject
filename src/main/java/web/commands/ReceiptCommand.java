package web.commands;

import business.entities.BomLine;
import business.exceptions.UserException;
import business.services.BomFacade;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ReceiptCommand extends CommandUnprotectedPage {
    BomFacade bomFacade;
    OrderFacade orderFacade;

    public ReceiptCommand(String pageToShow) {
        super(pageToShow);
        bomFacade = new BomFacade(database);
        orderFacade = new OrderFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        //Show Bill of Materials from latest order
        int orderId = orderFacade.getOrderIdByTimestamp();
        List<BomLine> billOfMaterials = bomFacade.getBomByOrderId(orderId);

        session.setAttribute("billOfMaterials", billOfMaterials);

        return pageToShow;
    }

}
