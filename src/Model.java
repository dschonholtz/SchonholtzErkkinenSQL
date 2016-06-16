import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dschonholtz on 6/16/2016.
 */
public class Model implements IModel{
    List<List<Object>> data;

    public Model() {
        data = new ArrayList<>();
    }

    @Override
    public List<List<Object>> getHarvestable() {
        return null;
    }

    @Override
    public List<Bed> GetValidBed(int feet) {
        return null;
    }

    @Override
    public List<Bed> getValdBed(Crop c, int numPlants) {
        return null;
    }

    @Override
    public List<List<Object>> insertBlock(String blockID, String notes) {
        return null;
    }

    @Override
    public List<List<Object>> insertOrUpdateBed(String blockID, String bedID, String notes) {
        return null;
    }

    @Override
    public List<List<Object>> insertOrUpdateCrop(String cropName, String variety, String seed_source, Integer num_seeds, Double germination_yield_proj, Double germination_yield_act, Double feet_between_plants, String part_num, Double cost, Integer qty, String packType, String notes) {
        return null;
    }

    @Override
    public List<List<Object>> insertOrUpdateCropLocation(String blockID, String bedID, String cropName, String variety, Integer numPlants, Date projectedHarvest, Date actualHarvest, String notes) {
        return null;
    }

    @Override
    public List<List<Object>> insertOrUpdateTrayLocation(String blockID, String bedID, String notes) {
        return null;
    }
}
