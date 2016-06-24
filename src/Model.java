import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by dschonholtz on 6/16/2016.
 */
public class Model implements IModel {
    List<FarmOBJ> data;
    private Connection conn;

    /**
     * Constructor for the Model.
     */
    public Model() {
        data = new ArrayList<>();
    }

    public void connect(String username, String password) throws Exception {
        Properties connectionProps = new Properties();
        connectionProps.put("user", username);
        connectionProps.put("password", password);
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmville", connectionProps);

    }

    @Override
    public List<FarmOBJ> getBlocks() throws SQLException {
        List<FarmOBJ> returnList = new ArrayList<>();
        CallableStatement pstat = conn.prepareCall("{CALL get_blocks()}");
        pstat.execute();

        while (pstat.getResultSet().next()) {
            Block block = new Block(pstat.getResultSet().getString("block_id"));
            block.setNotes(pstat.getResultSet().getString("note"));
            returnList.add(block);
        }

        pstat.closeOnCompletion();
        data = returnList;
        return returnList;
    }

    @Override
    public List<FarmOBJ> getBedsInBlock(Block block) throws SQLException {
        List<FarmOBJ> returnList = new ArrayList<>();
        CallableStatement pstat = conn.prepareCall("{CALL get_beds_in_block(?)}");
        pstat.setString(1, block.getBlockID());
        pstat.execute();
        // create the java statement
        while (pstat.getResultSet().next()) {
            Bed bed = new Bed(pstat.getResultSet().getString("block_id"),
                    pstat.getResultSet().getString("bed_id"));
            bed.setNotes(pstat.getResultSet().getString("note"));
            returnList.add(bed);
        }
        pstat.closeOnCompletion();

        data = returnList;
        return returnList;
    }

    @Override
    public List<FarmOBJ> getCropsInBed(Bed bed) throws SQLException {
        List<FarmOBJ> returnList = new ArrayList<>();
        CallableStatement pstat = conn.prepareCall("{CALL get_crops_in_bed(?, ?)}");
        pstat.setString(1, bed.getBlockID());
        pstat.setString(2, bed.getBedID());
        pstat.execute();

        while (pstat.getResultSet().next()) {
            Crop crop = new Crop(pstat.getResultSet().getString("crop_name"),
                    pstat.getResultSet().getString("variety"));
            crop.setSeed_source(pstat.getResultSet().getString("seed_source"));
            crop.setNum_seeds(pstat.getResultSet().getInt("num_seeds"));
            crop.setGermination_yield_proj(pstat.getResultSet().getDouble("germination_yield_proj"));
            crop.setGermination_yield_act(pstat.getResultSet().getDouble("germination_yield_act"));
            crop.setFeet_between_plants(pstat.getResultSet().getDouble("feet_between_plants"));
            crop.setPart_num(pstat.getResultSet().getString("part_num"));
            crop.setCost(pstat.getResultSet().getDouble("cost"));
            crop.setQty(pstat.getResultSet().getInt("quantity"));
            crop.setPackType(pstat.getResultSet().getString("pack_type"));
            crop.setNotes(pstat.getResultSet().getString("note"));
            returnList.add(crop);
        }
        pstat.closeOnCompletion();

        data = returnList;
        return returnList;
    }

    @Override
    public List<FarmOBJ> getHarvestable() throws SQLException {
        List<FarmOBJ> returnList = new ArrayList<>();
        CallableStatement pstat = conn.prepareCall("{CALL ready_for_harvest()}");
        pstat.execute();
        // create the java statement
        ResultSet resultSet = pstat.getResultSet();
        while (resultSet.next()) {
            TrayLocation trayLocation = new TrayLocation(resultSet.getString("block_id"),
                    resultSet.getString("bed_id"),
                    resultSet.getString("crop_name"),
                    resultSet.getString("variety"),
                    resultSet.getDouble("num_trays"),
                    resultSet.getInt("tray_type"),
                    resultSet.getString("soil_type"),
                    resultSet.getInt("seeds_per_cell"));
            returnList.add(trayLocation);
        }
        pstat.closeOnCompletion();
        data = returnList;
        return returnList;
    }

    @Override
    public List<FarmOBJ> getValidBed(int feet) throws SQLException {
        List<FarmOBJ> returnList = new ArrayList<>();
        CallableStatement pstat = conn.prepareCall("{CALL get_valid_beds(?)}");
        pstat.setInt(1, feet);
        pstat.execute();
        // create the java statement
        while (pstat.getResultSet().next()) {
            Bed bed = new Bed(pstat.getResultSet().getString("block_id"),
                    pstat.getResultSet().getString("bed_id"));
            returnList.add(bed);
        }
        pstat.closeOnCompletion();
        data = returnList;
        return returnList;
    }

    @Override
    public List<FarmOBJ> getValidBed(Crop crop, int numPlants) throws SQLException {
        List<FarmOBJ> returnList = new ArrayList<>();
        CallableStatement pstat = conn.prepareCall("{CALL get_valid_beds_plant(?, ?, ?)}");
        pstat.setString(1, crop.getCropName());
        pstat.setString(2, crop.getVariety());
        pstat.setInt(3, numPlants);
        pstat.execute();
        // create the java statement
        while (pstat.getResultSet().next()) {
            Bed bed = new Bed(
                    pstat.getResultSet().getString("block_id"),
                    pstat.getResultSet().getString("bed_id"));
            returnList.add(bed);
        }
        pstat.closeOnCompletion();
        data = returnList;
        return returnList;
    }

    @Override
    public List<FarmOBJ> insertOrUpdateBlock(Block block) throws SQLException {
        List<FarmOBJ> returnList = new ArrayList<>();
        CallableStatement pstat = conn.prepareCall("{CALL block_cu(?, ?, ?)}");
        String blockID = block.getBlockID();
        String notes = block.getNotes();
        pstat.setString(2, blockID);
        pstat.setString(3, notes);
        String query = "SELECT block_id FROM blocks WHERE blocks.block_id = \"" + blockID + "\";";

        // create the java statement
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        if (!rs.next()) {
            pstat.setBoolean(1, true);
        } else {
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
            Block block2 = new Block(resultSet.getString("block_id"));
            block2.setNotes(resultSet.getString("note"));
            returnList.add(block2);
        }
        data = returnList;
        return returnList;
    }

    @Override
    public List<FarmOBJ> deleteBlock(Block block) throws SQLException {
        List<FarmOBJ> returnList = new ArrayList<>();
        CallableStatement pstat;
        pstat = conn.prepareCall("{CALL delete_block(?)}");
        pstat.setString(1, block.getBlockID());
        pstat.execute();

        pstat.closeOnCompletion();
        String querySelect = "SELECT * FROM blocks;";
        // create the java statement
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(querySelect);
        while (resultSet.next()) {
            Block block2 = new Block(resultSet.getString("block_id"));
            block2.setNotes(resultSet.getString("note"));
            returnList.add(block2);
        }
        data = returnList;
        return returnList;
    }

    @Override
    public List<FarmOBJ> insertOrUpdateBed(Bed bed) throws SQLException {
        List<FarmOBJ> returnList = new ArrayList<>();
        CallableStatement pstat = conn.prepareCall("{CALL bed_cu(?, ?, ?, ?)}");
        pstat.setString(2, bed.getBlockID());
        pstat.setString(3, bed.getBedID());
        pstat.setString(4, bed.getNotes());

        String query = "SELECT block_id, bed_id " +
                "FROM beds WHERE " +
                "beds.bed_id = \"" + bed.getBedID() + "\"" +
                "AND beds.block_id = \"" + bed.getBlockID() + "\";";

        // create the java statement
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        if (!rs.next()) {
            pstat.setBoolean(1, true);
        } else {
            pstat.setBoolean(1, false);
        }
        st.closeOnCompletion();
        pstat.execute();
        pstat.closeOnCompletion();
        String querySelect = "SELECT * FROM beds;";
        // create the java statement
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(querySelect);
        while (resultSet.next()) {
            Bed bed2 = new Bed(
                    resultSet.getString("block_id"),
                    resultSet.getString("bed_id"));
            bed2.setNotes(resultSet.getString("note"));
            returnList.add(bed2);
        }
        data = returnList;
        return returnList;
    }

    @Override
    public List<FarmOBJ> deleteBed(Bed bed) throws SQLException {
        CallableStatement pstat;
        pstat = conn.prepareCall("{CALL delete_bed(?, ?)}");
        pstat.setString(1, bed.getBlockID());
        pstat.setString(2, bed.getBedID());

        pstat.execute();
        pstat.closeOnCompletion();

        String querySelect = "SELECT * FROM beds;";
        // create the java statement
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(querySelect);
        List<FarmOBJ> returnList = new ArrayList<>();
        while (resultSet.next()) {
            Bed bed2 = new Bed(resultSet.getString("block_id"),
                    resultSet.getString("bed_id"));
            bed2.setNotes(resultSet.getString("note"));
            returnList.add(bed2);
        }
        pstat.closeOnCompletion();
        data = returnList;
        return returnList;
    }

    @Override
    public List<FarmOBJ> insertOrUpdateCrop(Crop crop) throws SQLException {
        List<FarmOBJ> returnList = new ArrayList<>();
        CallableStatement pstat = conn.prepareCall("{CALL crop_cu(?, ?, ?, ?, ?, ?, " +
                "?, ?,?, ?, ?, ?, ?)}");
        pstat.setString(2, crop.getCropName());
        pstat.setString(3, crop.getVariety());
        pstat.setString(4, crop.getSeed_source());
        pstat.setObject(5, crop.getNum_seeds());
        pstat.setObject(6, crop.getGermination_yield_proj());
        pstat.setObject(7, crop.getGermination_yield_act());
        pstat.setObject(8, crop.getFeet_between_plants());
        pstat.setString(9, crop.getPart_num());
        pstat.setObject(10, crop.getCost());
        pstat.setObject(11, crop.getQty());
        pstat.setString(12, crop.getPackType());
        pstat.setString(13, crop.getNotes());
        String query = "SELECT crop_name, variety FROM crops WHERE crops.variety = \""
                + crop.getVariety() + "\"" + "AND crops.crop_name = \"" + crop.getCropName() + "\";";

        // create the java statement
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        if (!rs.next()) {
            pstat.setBoolean(1, true);
        } else {
            pstat.setBoolean(1, false);
        }
        st.closeOnCompletion();
        pstat.execute();
        pstat.closeOnCompletion();
        String querySelect = "SELECT * FROM crops;";
        // create the java statement
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(querySelect);
        while (resultSet.next()) {
            Crop crop2 = new Crop(resultSet.getString("crop_name"), resultSet.getString("variety"));
            crop2.setSeed_source(resultSet.getString("seed_source"));
            crop2.setNum_seeds(resultSet.getInt("num_seeds"));
            crop2.setGermination_yield_proj(resultSet.getDouble("germination_yield_proj"));
            crop2.setGermination_yield_act(resultSet.getDouble("germination_yield_act"));
            crop2.setFeet_between_plants(resultSet.getDouble("feet_between_plants"));
            crop2.setPart_num(resultSet.getString("part_num"));
            crop2.setCost(resultSet.getDouble("cost"));
            crop2.setQty(resultSet.getInt("quantity"));
            crop2.setPackType(resultSet.getString("pack_type"));
            crop2.setNotes(resultSet.getString("note"));
            returnList.add(crop2);
        }
        data = returnList;
        return returnList;
    }

    @Override
    public List<FarmOBJ> deleteCrop(Crop crop) throws SQLException {
        CallableStatement pstat;
        pstat = conn.prepareCall("{CALL delete_crop(?, ?)}");

        pstat.setString(1, crop.getCropName());
        pstat.setString(2, crop.getVariety());

        pstat.execute();
        return null;
    }

    @Override
    public List<FarmOBJ> insertOrUpdateCropLocation(CropLocation cropLocation)
            throws SQLException {
        List<FarmOBJ> returnList = new ArrayList<>();
        CallableStatement pstat = conn.prepareCall("{CALL croplocation_cu(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        pstat.setString(2, cropLocation.getBlockID());
        pstat.setString(3, cropLocation.getBedID());
        pstat.setString(4, cropLocation.getCropName());
        pstat.setString(5, cropLocation.getVariety());
        pstat.setObject(6, cropLocation.getNumPlants());
        pstat.setDate(7, cropLocation.getProjectedHarvest());
        pstat.setDate(8, cropLocation.getActualHarvest());
        pstat.setString(9, cropLocation.getNotes());
        String query = "SELECT block_id, bed_id, crop_name, variety " +
                "FROM croplocations WHERE " +
                "croplocations.bed_id = \"" + cropLocation.getBedID() + "\"" +
                "AND croplocations.block_id = \"" + cropLocation.getBlockID() + "\"" +
                "AND croplocations.variety = \"" + cropLocation.getVariety() + "\"" +
                "AND croplocations.crop_name = \"" + cropLocation.getCropName() + "\";";

        // create the java statement
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        if (!rs.next()) {
            pstat.setBoolean(1, true);
        } else {
            pstat.setBoolean(1, false);
        }
        st.closeOnCompletion();
        pstat.execute();
        pstat.closeOnCompletion();
        String querySelect = "SELECT * FROM croplocations;";
        // create the java statement
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(querySelect);
        while (resultSet.next()) {
            CropLocation cropLocation2 = new CropLocation(resultSet.getString("block_id"),
                    resultSet.getString("bed_id"),
                    resultSet.getString("crop_name"),
                    resultSet.getString("variety"));
            cropLocation2.setNumPlants(resultSet.getInt("num_plants"));
            cropLocation2.setProjectedHarvest(resultSet.getDate("projected_harvest"));
            cropLocation2.setActualHarvest(resultSet.getDate("actual_harvest"));
            cropLocation2.setNotes(resultSet.getString("note"));
            returnList.add(cropLocation2);
        }
        data = returnList;
        return returnList;
    }

    @Override
    public List<FarmOBJ> deleteCropLocationTrayLocation(CropLocation cropLocation) throws SQLException {
        CallableStatement pstat;
        pstat = conn.prepareCall("{CALL delete_croploc_or_trayloc(?, ?, ?, ?)}");
        pstat.setString(1, cropLocation.getBlockID());
        pstat.setString(2, cropLocation.getBedID());
        pstat.setString(3, cropLocation.getCropName());
        pstat.setString(4, cropLocation.getVariety());

        pstat.execute();
        pstat.closeOnCompletion();
        String querySelect = "SELECT * FROM croplocations;";
        // create the java statement
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(querySelect);
        ArrayList returnList = new ArrayList();
        while (resultSet.next()) {
            CropLocation cropLocation2 = new CropLocation(resultSet.getString("block_id"),
                    resultSet.getString("bed_id"),
                    resultSet.getString("crop_name"),
                    resultSet.getString("variety"));
            cropLocation2.setNumPlants(resultSet.getInt("num_plants"));
            cropLocation2.setProjectedHarvest(resultSet.getDate("projected_harvest"));
            cropLocation2.setActualHarvest(resultSet.getDate("actual_harvest"));
            cropLocation2.setNotes(resultSet.getString("note"));
            returnList.add(cropLocation2);
        }
        data = returnList;
        return returnList;
    }

    @Override
    public List<FarmOBJ> insertOrUpdateTrayLocation(TrayLocation trayLocation) throws SQLException {
        List<FarmOBJ> returnList = new ArrayList<>();
        CallableStatement pstat = conn.prepareCall("{CALL traylocation_cu(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        pstat.setString(2, trayLocation.getBlockID());
        pstat.setString(3, trayLocation.getBedID());
        pstat.setString(4, trayLocation.getCropName());
        pstat.setString(5, trayLocation.getVariety());
        pstat.setObject(6, trayLocation.getNum_trays());
        pstat.setObject(7, trayLocation.getTray_type());
        pstat.setString(8, trayLocation.getSoil_type());
        pstat.setObject(9, trayLocation.getSeeds_per_cell());

        String query = "SELECT block_id, bed_id, crop_name, variety " +
                "FROM traylocations WHERE " +
                "traylocations.bed_id = \"" + trayLocation.getBedID() + "\"" +
                "AND traylocations.block_id = \"" + trayLocation.getBlockID() + "\"" +
                "AND traylocations.variety = \"" + trayLocation.getVariety() + "\"" +
                "AND traylocations.crop_name = \"" + trayLocation.getCropName() + "\";";

        // create the java statement
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        if (!rs.next()) {
            pstat.setBoolean(1, true);
        } else {
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
            TrayLocation trayLocation2 = new TrayLocation(resultSet.getString("block_id"),
                    resultSet.getString("bed_id"),
                    resultSet.getString("crop_name"),
                    resultSet.getString("variety"),
                    resultSet.getDouble("num_trays"),
                    resultSet.getInt("tray_type"),
                    resultSet.getString("soil_type"),
                    resultSet.getInt("seeds_per_cell"));
            returnList.add(trayLocation2);

        }
        data = returnList;
        return returnList;
    }

    public List<FarmOBJ> getData() {
        return this.data;
    }

}
