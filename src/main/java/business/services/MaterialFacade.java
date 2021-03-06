package business.services;

import business.entities.Material;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.MaterialMapper;

import java.sql.SQLException;
import java.util.List;

public class MaterialFacade {
    MaterialMapper materialMapper;

    public MaterialFacade(Database database) {
        materialMapper = new MaterialMapper(database);
    }

    public List<Material> getAllMaterials() throws UserException {
        return materialMapper.getAllMaterials();
    }

    public Material getMaterialById(int materialId) throws UserException {
        return materialMapper.getMaterialById(materialId);
    }

    public List<Material> getAllWood() throws UserException {
        return materialMapper.getAllWood();
    }

    public List<Material> getAllAccesories() throws UserException {
        return materialMapper.getAllAccesories();
    }

    public int updateMaterialById( int materialId, double price) throws UserException {
       return materialMapper.updateMaterialById(materialId,price);
    }

    public void addMaterial(Material material) throws UserException {
        materialMapper.addMaterial(material);
    }

    public int deleteMaterial(int materialId) throws UserException {
        return materialMapper.deleteMaterial(materialId);
    }



}
