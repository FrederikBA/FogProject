package web.commands;

import business.entities.Bom;
import business.exceptions.UserException;
import business.services.BomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateOrderCommand extends CommandUnprotectedPage {
    BomService bomService;

    public CreateOrderCommand(String pageToShow) {
        super(pageToShow);
        bomService = new BomService(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        int width = 0;
        int length = 0;


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

            //Add posts
            bom.addToBill(bomService.calculatePostsFromMeasurements(width, length));

            //Add Rafters
            bom.addToBill(bomService.calculateRaftersFromMeasurements(width, length));

            return "checkout";
        }

        return pageToShow;
    }
}
