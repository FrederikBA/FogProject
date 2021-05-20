package business.services;

import business.entities.BomLine;
import business.exceptions.UserException;
import business.persistence.Database;

import java.util.List;

public class DrawingService {
    OrderFacade orderFacade;
    BomFacade bomFacade;

    public DrawingService(Database database) {
        this.orderFacade = new OrderFacade(database);
        this.bomFacade = new BomFacade(database);
    }

    public SVG drawCarportTop(double width, double length, int orderId) throws UserException {
        List<BomLine> billOfMaterials = bomFacade.getBomByOrderId(orderId);
        SVG svg = new SVG(0, 0, "0 0 855 855", 100, 100);

        //Draw Frame
        svg.addRect(0, 0, width, length);

        //Draw Spær
        BomLine spær = billOfMaterials.get(1);
        double dquantity = spær.getQuantity();
        double distance = length / (dquantity - 1);
        for (int x = 0; x < spær.getQuantity(); x++) {
            svg.addRect(distance * x, 0, width, 4.5);
        }

        //Draw Rem
        double remDistance = width / 100 * 5.83;
        svg.addRect(0, remDistance - 4.5, 4.5, length);
        svg.addRect(0, width - remDistance, 4.5, length);

        //Draw Stolper
        BomLine stolpe = billOfMaterials.get(0);
        double firstDistance = distance * 2;
        double distanceBetween = distance * 5;
        double secondDistance = distance * spær.getQuantity() - (2 * distance);
        if (length > 630) {
            for (int x = 0; x < stolpe.getQuantity(); x++) {
                svg.addRect(firstDistance + distanceBetween * x - 2.25, width - remDistance - 2.25, 9, 9);
                svg.addRect(firstDistance + distanceBetween * x - 2.25, remDistance - 4.5 - 2.25, 9, 9);
            }
        } else {
            firstDistance = distance * 1;
            svg.addRect(firstDistance - 2.25, width - remDistance - 2.25, 9, 9);
            svg.addRect(secondDistance - 2.25, width - remDistance - 2.25, 9, 9);
            svg.addRect(firstDistance - 2.25, remDistance - 4.5 - 2.25, 9, 9);
            svg.addRect(secondDistance - 2.25, remDistance - 4.5 - 2.25, 9, 9);
        }

        //Draw Hulbånd
        double firstDistanceHulbånd = distance * 1;
        svg.addLine(firstDistanceHulbånd + 4.5, remDistance, secondDistance, width - remDistance);
        svg.addLine(secondDistance, remDistance, firstDistanceHulbånd + 4.5, width - remDistance);

        return svg;
    }

    public SVG drawCarportTopArrows(double width, double length, int orderId) throws UserException {
        SVG svg = new SVG(0, 0, "0 0 855 855", 100, 100);
        //Variables
        double remDistance = width / 100 * 5.83;
        double y2 = width - remDistance;
        double y1 = remDistance;
        double middleLength = width - (remDistance + remDistance);

        //Arrows
        svg.addArrow(length + 50, 0, length + 50, width);
        svg.addArrow(length + 20, y1 - 4.5, length + 20, y2 + 4.5);
        svg.addArrow(0, width + 20, length + 4.5, width + 20);

        //Text
        svg.addText(length + 60, (width / 2) - 30, 90, (int) width);
        svg.addText(length + 30, (width / 2) - 30, 90, (int) middleLength);
        svg.addText(length / 2, width + 40, 0, (int) length);

        return svg;
    }

    public SVG drawCarportSide(double width, double length, int orderId) throws UserException {
        List<BomLine> billOfMaterials = bomFacade.getBomByOrderId(orderId);
        BomLine stolpe = billOfMaterials.get(0);
        BomLine spær = billOfMaterials.get(1);
        SVG svg = new SVG(0, 0, "0 0 855 855", 100, 100);

        //Draw Frame
        double stolpeHeight = stolpe.getLength() - 90;
        double carportHeight = stolpeHeight + 20;
        svg.addSideFrame(0, 0, carportHeight, length);

        //Draw Stolper
        double dquantity = spær.getQuantity();
        double distance = length / (dquantity - 1);
        double firstDistance = distance * 2;
        double distanceBetween = distance * 5;
        double secondDistance = distance * spær.getQuantity() - (2 * distance);

        if (length > 630) {
            for (int x = 0; x < stolpe.getQuantity(); x++) {
                svg.addRect(firstDistance + distanceBetween * x - 2.25, 0, stolpeHeight, 9);
                svg.addRect(firstDistance + distanceBetween * x - 2.25, 0, stolpeHeight, 9);
            }
        } else {
            firstDistance = distance * 1;
            svg.addRect(firstDistance - 2.25, 0, stolpeHeight, 9);
            svg.addRect(secondDistance - 2.25, 0, stolpeHeight, 9);
            svg.addRect(firstDistance - 2.25, 0, stolpeHeight, 9);
            svg.addRect(secondDistance - 2.25, 0, stolpeHeight, 9);
        }

        //Draw Roof
        svg.addRoof(0, 0, 20, length);

        return svg;
    }

    public SVG drawCarportSideArrows(double width, double length, int orderId) throws UserException {
        SVG svg = new SVG(0, 0, "0 0 855 855", 100, 100);

        double stolpeHeight = 210;
        double carportHeight = stolpeHeight + 20;

        //Arrows
        svg.addArrow(length + 20, 0, length + 20, carportHeight - 20);
        svg.addArrow(0, carportHeight + 20, length + 4.5, carportHeight + 20);

        //Text
        svg.addText(length + 40, (carportHeight / 2) - 40, 90, (int) carportHeight);
        svg.addText(length / 2, carportHeight + 40, 0, (int) length);

        return svg;
    }
}