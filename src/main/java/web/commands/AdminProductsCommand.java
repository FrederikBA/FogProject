package web.commands;

import business.entities.Material;
import business.exceptions.UserException;
import business.services.MaterialFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

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


        //Update material price
        if (request.getParameter("update") != null) {
            String materialId = request.getParameter("materialeId");
            String price = request.getParameter("price");
            int changePrice = materialFacade.updateMaterialById(Integer.parseInt(materialId),Double.parseDouble(price));
            if (changePrice == 1)
            {
                materials = materialFacade.getAllMaterials();
                wood = materialFacade.getAllWood();
                accesories = materialFacade.getAllAccesories();

                session.setAttribute("materials", materials);
                session.setAttribute("wood", wood);
                session.setAttribute("accesories", accesories);
            }
        }


        //Add material to product page
        if (request.getParameter("addMaterial") != null) {
            String description = request.getParameter("description");
            String unit = request.getParameter("unit");
            double pricePerUnit = Double.parseDouble(request.getParameter("price"));
            String type = request.getParameter("type");

            materialFacade.addMaterial(new Material(description,unit,pricePerUnit,type));

            materials = materialFacade.getAllMaterials();
            wood = materialFacade.getAllWood();
            accesories = materialFacade.getAllAccesories();

            session.setAttribute("materials", materials);
            session.setAttribute("wood", wood);
            session.setAttribute("accesories", accesories);
            return "adminproducts";
        }

        //Delete material
        if (request.getParameter("delete") != null){
            String deleteMats = request.getParameter("delete");

            materialFacade.deleteMaterial(Integer.parseInt(deleteMats));
            materials = materialFacade.getAllMaterials();
            wood = materialFacade.getAllWood();
            accesories = materialFacade.getAllAccesories();

        }
        session.setAttribute("materials", materials);
        session.setAttribute("wood", wood);
        session.setAttribute("accesories", accesories);

        return pageToShow;
    }
}
