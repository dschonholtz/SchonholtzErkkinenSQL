import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dschonholtz on 6/16/2016.
 */
public class Model implements IModel{
    List<Object> data;
    private String query;
    final private String username = "root";
    final private String password = "";
    final private String dburl = "jdbc:mysql://localhost:3306/finalporject";
                                        //I SPELLED IT WRONG WHILE BUILDING THE DATABASE OK???

    public Model() {
        query = "";
        data = new ArrayList<>();
    }


    @Override
    public List<Object> getHarvestable() {
        query = "Call ready_for_harvest;";
        try (Connection connection = DriverManager.getConnection(dburl, username, password);
             Statement statement = connection.createStatement();
             ResultSet rs1 = statement.executeQuery(query)) {
            data.clear();
            while(rs1.next()) {


            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    @Override
    public List<Object> GetValidBed(int feet) {
        query = "Call get_valid_bed;";
        try (Connection connection = DriverManager.getConnection(dburl, username, password);
             Statement statement = connection.createStatement();
             ResultSet rs1 = statement.executeQuery(query)) {
            data.clear();
            while(rs1.next()) {


            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    @Override
    public List<Object> getValdBed(Crop c, int numPlants) {
        return null;
    }

    @Override
    public List<Object> insertBlock(String blockID, String notes) {
        return null;
    }

    @Override
    public List<Object> deleteBlock(String blockID) {
        return null;
    }

    @Override
    public List<Object> insertOrUpdateBed(String blockID, String bedID, String notes) {
        return null;
    }

    @Override
    public List<Object> deleteBed(String blockID, String bedID) {
        return null;
    }

    @Override
    public List<Object> insertOrUpdateCrop(String cropName, String variety, String seed_source, Integer num_seeds, Double germination_yield_proj, Double germination_yield_act, Double feet_between_plants, String part_num, Double cost, Integer qty, String packType, String notes) {
        return null;
    }

    @Override
    public List<Object> deleteCrop(String cropName, String variety) {
        return null;
    }

    @Override
    public List<Object> insertOrUpdateCropLocation(String blockID, String bedID, String cropName, String variety, Integer numPlants, Date projectedHarvest, Date actualHarvest, String notes) {
        return null;
    }

    @Override
    public List<Object> deleteCropLocation(String blockID, String bedID, String cropName, String variety) {
        return null;
    }

    @Override
    public List<Object> insertOrUpdateTrayLocation(String blockID, String bedID, String cropName, String variety, Double numTrays, Integer trayType, String soilType, Integer seedsPerCell) {
        return null;
    }

    @Override
    public List<Object> deleteTrayLocation(String blockID, String bedID, String cropName, String variety) {
        return null;
    }
}
