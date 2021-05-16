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

        session.setAttribute("materials", materials);
        session.setAttribute("wood", wood);
        session.setAttribute("accesories", accesories);

        if (request.getParameter("materialBtn") != null) {
            //  Material updateMaterial = materialFacade.updateMaterialById(,2);
            //  session.setAttribute("updateMaterial", updateMaterial);
        }


        if (request.getParameter("addMaterial") != null) {
            int materialId = Integer.parseInt(request.getParameter("materialId"));
            String description = request.getParameter("description");
            String unit = request.getParameter("unit");
            double pricePerUnit = Double.parseDouble(request.getParameter("price"));
            String type = request.getParameter("type");

            materialFacade.addMaterial(new Material(materialId,description,unit,pricePerUnit,type));
            session.setAttribute("tmp", materials);
            return "adminproducts";
        }

        if (request.getParameter("deleteMaterial") != null){
            String deleteMats = request.getParameter("delete");
            materialFacade.deleteMaterial(Integer.parseInt(deleteMats));
        }
        return pageToShow;
    }
}
