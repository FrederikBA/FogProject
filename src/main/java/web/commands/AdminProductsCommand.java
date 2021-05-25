package web.commands;

import business.entities.Material;
import business.exceptions.UserException;
import business.services.MaterialFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminProductsCommand extends CommandProtectedPage {

    public MaterialFacade materialFacade;

    public AdminProductsCommand(String pageToShow, String role) {
        super(pageToShow, role);
        materialFacade = new MaterialFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        List<Material> materials = materialFacade.getAllMaterials();
        List<Material> wood = materialFacade.getAllWood();
        List<Material> accesories = materialFacade.getAllAccesories();


        //Update material price
        if (request.getParameter("update") != null) {
            String materialId = request.getParameter("materialeId");
            String MaterialPrice = request.getParameter("MaterialPrice");
            int changePrice = materialFacade.updateMaterialById(Integer.parseInt(materialId), Double.parseDouble(MaterialPrice));
            if (changePrice == 1) {
                materials = materialFacade.getAllMaterials();
                wood = materialFacade.getAllWood();
                accesories = materialFacade.getAllAccesories();

                request.setAttribute("materials", materials);
                request.setAttribute("wood", wood);
                request.setAttribute("accesories", accesories);
            }
        }


        //Add material to product page
        if (request.getParameter("addMaterial") != null) {
            String description = request.getParameter("description");
            String unit = request.getParameter("unit");
            double pricePerUnit = Double.parseDouble(request.getParameter("price"));
            String type = request.getParameter("type");

            materialFacade.addMaterial(new Material(description, unit, pricePerUnit, type));

            materials = materialFacade.getAllMaterials();
            wood = materialFacade.getAllWood();
            accesories = materialFacade.getAllAccesories();

            request.setAttribute("materials", materials);
            request.setAttribute("wood", wood);
            request.setAttribute("accesories", accesories);
            return "adminproducts";
        }

        //Delete material
        if (request.getParameter("delete") != null) {
            String deleteMats = request.getParameter("delete");

            if (materialFacade.deleteMaterial(Integer.parseInt(deleteMats)) > 0) {
                //Update lists
                materials = materialFacade.getAllMaterials();
                wood = materialFacade.getAllWood();
                accesories = materialFacade.getAllAccesories();
            } else {
                request.setAttribute("error", "Dette materiale kan ikke fjernes da det er i brug.");
            }
        }


        request.setAttribute("materials", materials);
        request.setAttribute("wood", wood);
        request.setAttribute("accesories", accesories);

        return pageToShow;
    }
}
