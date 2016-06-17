import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by dschonholtz on 6/16/2016.
 */
public class Model implements IModel{
    List<Object> data;
    private String query;
    private String username;
    private String password;
    private String dburl = "jdbc:mysql://localhost:3306/finalporject";
                                        //I SPELLED IT WRONG WHILE BUILDING THE DATABASE OK???
    private Connection conn;
    public Model() {
        data = new ArrayList<>();
    }

    private void connect(String username, String password) {
        this.username = username;
        this.password = password;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.username);
        connectionProps.put("password", this.password);

        try {
            conn = DriverManager.getConnection("jdbc:mysql://"
                    + "jdbc:mysql://localhost:3306/finalporject", connectionProps);
            System.out.println("Connected to database");
        } catch (Exception e) {
            System.out.println("ERROR: Could not connect to the database");
        }

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
    public List<Object> deleteBed(String blockID, String bedID) throws SQLException  {
        CallableStatement pstat;
        pstat = conn.prepareCall("{call track_character(?)}");
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
