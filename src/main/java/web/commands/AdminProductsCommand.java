package web.commands;

import business.entities.Material;
import business.exceptions.UserException;
import business.services.MaterialFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminProductsCommand extends CommandProtectedPage {

    public MaterialFacade materialFacade;

    public AdminProductsCommand(String pageToShow, String role) {
        super(pageToShow, role);
        materialFacade = new MaterialFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();

        List<Material> materials = materialFacade.getAllMaterials();
        List<Material> wood = materialFacade.getAllWood();
        List<Material> accesories = materialFacade.getAllAccesories();
        Material updateMaterial = materialFacade.updateMaterialById();

        session.setAttribute("materials", materials);
        session.setAttribute("wood", wood);
        session.setAttribute("accesories", accesories);

        if (request.getParameter("materialBtn") != null) {
            session.setAttribute("updateMaterial", updateMaterial);
        }
        return pageToShow;
    }


}
