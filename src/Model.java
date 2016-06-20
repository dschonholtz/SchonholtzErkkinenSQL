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
    private String username; // TODO REMOVE AND JUST USE CONN
    private String password; //TODO REMOVE AND JUST USE CONN
    private String dburl = "jdbc:mysql://localhost:3306/finalproject";
                                        //I SPELLED IT WRONG WHILE BUILDING THE DATABASE OK???
    private Connection conn;

  /**
   * Constructor for the Model.
   */
  public Model() {
        data = new ArrayList<>();
    }

  public void connect(String username, String password) throws Exception {

      this.username = username;
      this.password = password;
      Properties connectionProps = new Properties();
      connectionProps.put("user", username);
      connectionProps.put("password", password);
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalproject", connectionProps);

  }

    @Override
    public List<TrayLocation> getHarvestable() throws SQLException{
      List<TrayLocation> returnList = new ArrayList<>();
      CallableStatement pstat = conn.prepareCall("{CALL ready_for_harvest()}");
      pstat.execute();
      // create the java statement
      ResultSet resultSet = pstat.getResultSet();
      while (resultSet.next()) {
        TrayLocation trayLocation = new TrayLocation(resultSet.getString("blockid"),
                resultSet.getString("bedid"),
                resultSet.getString("crop_name"),
                resultSet.getString("variety"),
                resultSet.getDouble("num_trays"),
                resultSet.getInt("tray_type"),
                resultSet.getString("soil_type"),
                resultSet.getInt("seeds_per_cell"));
        returnList.add(trayLocation);
      }
      pstat.closeOnCompletion();
      return returnList;
    }

    @Override
    public List<Bed> GetValidBed(int feet) throws SQLException {
      List<Bed> returnList = new ArrayList<>();
      CallableStatement pstat = conn.prepareCall("{CALL get_valid_beds(?)}");
      pstat.setInt(1, feet);
      pstat.execute();
      // create the java statement
      while (pstat.getResultSet().next()) {
        Bed bed = new Bed(pstat.getResultSet().getString("blockid"),
                pstat.getResultSet().getString("bedid"));
        returnList.add(bed);
      }
      pstat.closeOnCompletion();

      return returnList;
    }

    @Override
    public List<Bed> getValidBed(String name, String variety, int numPlants) throws SQLException {
      List<Bed> returnList = new ArrayList<>();
      CallableStatement pstat = conn.prepareCall("{CALL get_valid_beds_plant(?, ?, ?)}");
      pstat.setString(1, name);
      pstat.setString(2, variety);
      pstat.setInt(3, numPlants);
      pstat.execute();
      // create the java statement
      while (pstat.getResultSet().next()) {
        Bed bed = new Bed(pstat.getResultSet().getString("blockid"),
                pstat.getResultSet().getString("bedid"));
        returnList.add(bed);
      }
      pstat.closeOnCompletion();
      return returnList;
    }

    @Override
    public List<Block> insertOrUpdateBlock(String blockID, String notes) throws SQLException{
      List<Block> returnList = new ArrayList<>();
      CallableStatement pstat = conn.prepareCall("{CALL blocks_cu(?, ?, ?)}");
      pstat.setString(2, blockID);
      pstat.setString(3, notes);
      String query = "SELECT id FROM blocks WHERE blocks.id = \"" + blockID + "\";";

      // create the java statement
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(query);
      if(!rs.next())
      {
        pstat.setBoolean(1, true);
      }
      else {
        pstat.setBoolean(1, false);
      }
      st.closeOnCompletion();
      pstat.execute();
      pstat.closeOnCompletion();
      String querySelect = "SELECT * FROM blocks;";
      // create the java statement
      Statement statement = conn.createStatement();
      ResultSet resultSet = statement.executeQuery(querySelect);
      while (resultSet.next()) {
        Block block = new Block(resultSet.getString("id"));
        block.setNotes(resultSet.getString("notes"));
        returnList.add(block);
      }
      return returnList;
    }

    @Override
    public List<Object> deleteBlock(String id) throws SQLException {
      CallableStatement pstat;
      pstat = conn.prepareCall("{CALL delete_block(?)}");
      pstat.setString(1, id);
      pstat.execute();

      pstat.closeOnCompletion();
      return null;
    }

    @Override
    public List<Bed> insertOrUpdateBed(String blockID, String bedID, String notes) throws SQLException {
      List<Bed> returnList = new ArrayList<>();
      CallableStatement pstat = conn.prepareCall("{CALL bed_cu(?, ?, ?, ?)}");
      pstat.setString(2, bedID);
      pstat.setString(3, blockID);
      pstat.setString(4, notes);

      String query = "SELECT blockid, bedid " +
              "FROM bed WHERE " +
              "bed.bedid = \"" + bedID + "\"" +
              "AND bed.blockid = \"" + blockID + "\";";

      // create the java statement
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(query);
      if(!rs.next())
      {
        pstat.setBoolean(1, true);
      }
      else {
        pstat.setBoolean(1, false);
      }
      st.closeOnCompletion();
      pstat.execute();
      pstat.closeOnCompletion();
      String querySelect = "SELECT * FROM bed;";
      // create the java statement
      Statement statement = conn.createStatement();
      ResultSet resultSet = statement.executeQuery(querySelect);
      while (resultSet.next()) {
        Bed bed = new Bed(resultSet.getString("blockid"),
                resultSet.getString("bedid"));
        bed.setNotes(resultSet.getString("notes"));
        returnList.add(bed);
      }
      return returnList;
    }

    @Override
    public List<Object> deleteBed(String blockID, String bedID) throws SQLException  {
        CallableStatement pstat;
        pstat = conn.prepareCall("{CALL delete_bed(?, ?)}");
        pstat.setString(1, blockID);
        pstat.setString(2, bedID);

        pstat.execute();
        pstat.closeOnCompletion();
        return null;
    }

    @Override
    public List<Crop> insertOrUpdateCrop(String cropName, String variety,
                                         String seed_source, Integer num_seeds,
                                         Double germination_yield_proj, Double germination_yield_act,
                                         Double feet_between_plants, String part_num,
                                         Double cost, Integer qty,
                                         String packType, String notes) throws SQLException {
      List<Crop> returnList = new ArrayList<>();
      CallableStatement pstat = conn.prepareCall("{CALL crop_cu(?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?, ?)}");
      pstat.setString(2, cropName);
      pstat.setString(3, variety);
      pstat.setString(4, seed_source);
      pstat.setInt(5, num_seeds);
      pstat.setDouble(6, germination_yield_proj);
      pstat.setDouble(7, germination_yield_act);
      pstat.setDouble(8, feet_between_plants);
      pstat.setString(9, part_num);
      pstat.setDouble(10, cost);
      pstat.setInt(11, qty);
      pstat.setString(12, packType);
      pstat.setString(13, notes);
      String query = "SELECT crop_name, variety FROM crop WHERE crop.variety = \"" + variety + "\"" +
              "AND crop.crop_name = \"" + cropName + "\";";

      // create the java statement
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(query);
      if(!rs.next())
      {
        pstat.setBoolean(1, true);
      }
      else {
        pstat.setBoolean(1, false);
      }
      st.closeOnCompletion();
      pstat.execute();
      pstat.closeOnCompletion();
      String querySelect = "SELECT * FROM crop;";
      // create the java statement
      Statement statement = conn.createStatement();
      ResultSet resultSet = statement.executeQuery(querySelect);
      while (resultSet.next()) {
        Crop crop = new Crop(resultSet.getString("crop_name"), resultSet.getString("variety"));
        crop.setSeed_source(resultSet.getString("seed_source"));
        crop.setNum_seeds(resultSet.getInt("num_seeds"));
        crop.setGermination_yield_proj(resultSet.getDouble("germination_yield_proj"));
        crop.setGermination_yield_act(resultSet.getDouble("germination_yield_act"));
        crop.setFeet_between_plants(resultSet.getDouble("feet_between_plants"));
        crop.setPart_num(resultSet.getString("part_num"));
        crop.setCost(resultSet.getDouble("cost"));
        crop.setQty(resultSet.getInt("qty"));
        crop.setPackType(resultSet.getString("pack_type"));
        crop.setNotes(resultSet.getString("notes"));
        returnList.add(crop);
      }
      return returnList;
    }

    @Override
    public List<Object> deleteCrop(String cropName, String variety) throws SQLException {
      CallableStatement pstat;
      pstat = conn.prepareCall("{CALL delete_crop(?, ?)}");

      pstat.setString(1, cropName);
      pstat.setString(2, variety);

      pstat.execute();
      return null;
    }

    @Override
    public List<CropLocation> insertOrUpdateCropLocation(String blockID, String bedID,
                                                   String cropName, String variety,
                                                   Integer numPlants, Date projectedHarvest,
                                                   Date actualHarvest, String notes)
            throws SQLException {
      List<CropLocation> returnList = new ArrayList<>();
      CallableStatement pstat = conn.prepareCall("{CALL croplocation_cu(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
      pstat.setString(2, blockID);
      pstat.setString(3, bedID);
      pstat.setString(4, cropName);
      pstat.setString(5, variety);
      pstat.setInt(6, numPlants);
      pstat.setDate(7, projectedHarvest);
      pstat.setDate(8, actualHarvest);
      pstat.setString(9, notes);
      String query = "SELECT blockid, bedid, crop_name, variety " +
              "FROM croplocation WHERE " +
              "croplocation.bedid = \"" + bedID + "\"" +
              "AND croplocation.blockid = \"" + blockID + "\"" +
              "AND croplocation.variety = \"" + variety + "\"" +
              "AND croplocation.crop_name = \"" + cropName + "\";";

      // create the java statement
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(query);
      if(!rs.next())
      {
        pstat.setBoolean(1, true);
      }
      else {
        pstat.setBoolean(1, false);
      }
      st.closeOnCompletion();
      pstat.execute();
      pstat.closeOnCompletion();
      String querySelect = "SELECT * FROM croplocation;";
      // create the java statement
      Statement statement = conn.createStatement();
      ResultSet resultSet = statement.executeQuery(querySelect);
      while (resultSet.next()) {
        CropLocation cropLocation = new CropLocation(resultSet.getString("blockid"),
                resultSet.getString("bedid"),
                resultSet.getString("crop_name"),
                resultSet.getString("variety"));
        cropLocation.setNumPlants(resultSet.getInt("num_plants"));
        cropLocation.setProjectedHarvest(resultSet.getDate("projectedHarvest"));
        cropLocation.setActualHarvest(resultSet.getDate("actualHarvest"));
        cropLocation.setNotes(resultSet.getString("notes"));
        returnList.add(cropLocation);
      }
      return returnList;
    }

    @Override
    public List<Object> deleteCropLocationTrayLocation(String blockID, String bedID,
                                                       String cropName, String variety) throws SQLException {
        CallableStatement pstat;
        pstat = conn.prepareCall("{CALL delete_croploc_or_trayloc(?, ?, ?, ?)}");
        pstat.setString(1, blockID);
        pstat.setString(2, bedID);
        pstat.setString(3, cropName);
        pstat.setString(4, variety);

        pstat.execute();
        pstat.closeOnCompletion();
        return null;
    }

    @Override
    public List<TrayLocation> insertOrUpdateTrayLocation(String blockID, String bedID,
                                                   String cropName, String variety,
                                                   Double numTrays, Integer trayType,
                                                   String soilType, Integer seedsPerCell) throws SQLException {
      List<TrayLocation> returnList = new ArrayList<>();
      CallableStatement pstat = conn.prepareCall("{CALL traylocations_cu(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
      pstat.setString(2, blockID);
      pstat.setString(3, bedID);
      pstat.setString(4, cropName);
      pstat.setString(5, variety);
      pstat.setDouble(6, numTrays);
      pstat.setInt(7, trayType);
      pstat.setString(8, soilType);
      pstat.setInt(9, seedsPerCell);

      String query = "SELECT blockid, bedid, crop_name, variety " +
              "FROM traylocations WHERE " +
              "traylocations.bedid = \"" + bedID + "\"" +
              "AND traylocations.blockid = \"" + blockID + "\"" +
              "AND traylocations.variety = \"" + variety + "\"" +
              "AND traylocations.crop_name = \"" + cropName + "\";";

      // create the java statement
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(query);
      if(!rs.next())
      {
        pstat.setBoolean(1, true);
      }
      else {
        pstat.setBoolean(1, false);
      }
      st.closeOnCompletion();
      pstat.execute();
      pstat.closeOnCompletion();
      String querySelect = "SELECT * FROM traylocations;";
      // create the java statement
      Statement statement = conn.createStatement();
      ResultSet resultSet = statement.executeQuery(querySelect);
      while (resultSet.next()) {
        TrayLocation trayLocation = new TrayLocation(resultSet.getString("blockid"),
                resultSet.getString("bedid"),
                resultSet.getString("crop_name"),
                resultSet.getString("variety"),
                resultSet.getDouble("num_trays"),
                resultSet.getInt("tray_type"),
                resultSet.getString("soil_type"),
                resultSet.getInt("seeds_per_cell"));
        returnList.add(trayLocation);
;
      }
      return returnList;
    }

}
