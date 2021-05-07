package web.commands;

import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateOrderCommand extends CommandUnprotectedPage {
    public CreateOrderCommand(String pageToShow) {
        super(pageToShow);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();

        String width = request.getParameter("width");
        String length = request.getParameter("length");
        session.setAttribute("width", width);
        session.setAttribute("length", length);

        if (request.getParameter("create") != null) {
            return "checkout";
        }


        return pageToShow;
    }
}
