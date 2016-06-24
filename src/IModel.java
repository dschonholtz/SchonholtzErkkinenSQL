import java.sql.SQLException;
import java.util.List;

/**
 * Created by Douglas Schonholtz on 6/16/2016.
 * The model contains the currently relevant data and all relevant calls to the SQL database
 *  * Included methods being:
 *  -Get/insert/delete Blocks
 *  -Get/insert/update/delete Beds
 *  -Get/insert/update/delete Crops
 *  -Get/insert/update/delete Crop Locations
 *  -Get/insert/update/delete BedsInBlock
 *  -Get/insert/update/delete Crops in Bed
 *  -Show Harvest ready plants
 *  -getValidBed(String Crop_name, String Variety)
 *  -getValidBed(int feet)
 */
public interface IModel {

    /**
     * Gets all of the harvest ready plants anywhere
     */
    List<FarmOBJ> getHarvestable() throws SQLException;

    /**
     * Returns beds with at least feet space in them
     * @param feet - The feet comparison
     * @return - The beds that have at least that many feet
     */
    List<FarmOBJ> getValidBed(int feet) throws SQLException;

    /**
     *
     * @param crop - The crop that is being checked for
     * @param numPlants - The number of plants to be inserted on
     * @return - The list of beds that that number of plants can be inserted into
     * @throws SQLException
     */
    List<FarmOBJ> getValidBed(Crop crop, int numPlants) throws SQLException;

    /**
     * Returns a list of all of the blocks
     * @return - THe list of all of the blocks
     * @throws SQLException
     */
    List<FarmOBJ> getBlocks() throws SQLException;

    /**
     *
     * @param block - The block to get beds from
     * @return - The list of beds
     * @throws SQLException
     */
    List<FarmOBJ> getBedsInBlock(Block block) throws SQLException;

    /**
     *
     * @param bed - The bed to get crops from
     * @return - The list of crops in the bed
     * @throws SQLException
     */
    List<FarmOBJ> getCropsInBed(Bed bed) throws SQLException;

    /**
     *
     * @param block - Block to be inserted or updated
     * @return - The list be inserted after the operation
     * @throws SQLException
     */
    List<FarmOBJ> insertOrUpdateBlock(Block block) throws SQLException;

    /**
     *
     * @param block - The block to be deleted
     * @return - The list be printed after the operation
     * @throws SQLException
     */
    List<FarmOBJ> deleteBlock(Block block) throws SQLException;

    /**
     * Insert or delete a bed
     * @param bed - The bed to be inserted
     * @return - The list to be printed
     * @throws SQLException
     */
    List<FarmOBJ> insertOrUpdateBed(Bed bed) throws SQLException;

    /**
     *
     * @param bed - The bed to be inserted
     * @return - The list to be printed after the operation
     * @throws SQLException
     */
    List<FarmOBJ> deleteBed(Bed bed) throws SQLException;

    /**
     *
     * @param crop - crop to be inserted or updated
     * @return - The list to be printed
     * @throws SQLException
     */
    List<FarmOBJ> insertOrUpdateCrop(Crop crop) throws SQLException;

    /**
     * Deletes a crop
     * @param crop - THe crop to be deleted
     * @return - The fields to be printed afterwards
     */
    List<FarmOBJ> deleteCrop(Crop crop) throws SQLException;

    /**
     *
     * @param cropLocation -  The crop location to be inserted
     * @return - The value to be printed afterwards
     * @throws SQLException
     */
    List<FarmOBJ> insertOrUpdateCropLocation(CropLocation cropLocation) throws SQLException;

    /**
     *
     * @param cropLocation - The crop location to be inserted
     * @return - The list to be shown after deleting the the crop location
     * @throws SQLException
     */
    List<FarmOBJ> deleteCropLocationTrayLocation(CropLocation cropLocation) throws SQLException;


    /**
     * Inserts a TrayLocation into the database
     * @param trayLocation - The tray location to be inserted
     * @return - The list of data be be printed.
     * @throws SQLException
     */
    List<FarmOBJ> insertOrUpdateTrayLocation(TrayLocation trayLocation) throws SQLException;


}
