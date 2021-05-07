package business.entities;

import java.util.ArrayList;
import java.util.List;

public class Bom {
    private List<BomLine> bomLines;

    public Bom() {
        this.bomLines = new ArrayList<>();
    }

    public List<BomLine> getBomLines() {
        return bomLines;
    }

    public void addToBill(BomLine bomLine) {
        bomLines.add(bomLine);
    }
}
