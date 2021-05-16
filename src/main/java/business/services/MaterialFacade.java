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
    public Material updateMaterialById(Material material, int id) throws UserException {
        return materialMapper.updateMaterialById(material, id);
    }
    }
