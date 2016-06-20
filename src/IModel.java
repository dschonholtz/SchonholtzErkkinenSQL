import java.sql.Date;
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
    List<TrayLocation> getHarvestable() throws SQLException;

    /**
     * Returns beds with at least feet space in them
     * @param feet - The feet comparison
     * @return - The beds that have at least that many feet
     */
    List<Bed> GetValidBed(int feet) throws SQLException;

    /**
     * This
     *
     * @param name - The crop name
     * @param variety - The crop variety
     * @param numPlants - The number of plants that need to be inserted into a bed.
     * @return - All of the beds with crop.feet_between_plants* num plants space left.
     */
    List<Bed> getValidBed(String name, String variety, int numPlants) throws SQLException;

    /**
     * Inserts a block into the table of blocks
     * @param blockID
     * @param notes
     * @return
     */
    List<Block> insertOrUpdateBlock(String blockID, String notes) throws SQLException;

    /**
     * Deletes the block with this primary key
     * @param blockID
     * @return
     */
    List<Object> deleteBlock(String blockID) throws SQLException;

    /**
     * If a record with this primary key already exists then it updates its values.
     * Otherwise create the value and add it.
     * Return the updated table.
     * @param blockID - The blockID; must be 2 characters or less.
     * @param bedID - The BedID; must 32 characters or less
     * @return - Returns the updated beds.
     */
    List<Bed> insertOrUpdateBed(String blockID, String bedID, String notes) throws SQLException;

    /**
     * Deletes a bed
     * @param blockID
     * @param bedID
     * @return
     */
    List<Object> deleteBed(String blockID, String bedID) throws SQLException;

    /**
     *
     * @param cropName
     * @param variety
     * @param seed_source
     * @param num_seeds
     * @param germination_yield_proj
     * @param germination_yield_act
     * @param feet_between_plants
     * @param part_num
     * @param cost
     * @param qty
     * @param packType
     * @param notes
     * @return - Updates the crop table and returns it
     */
    List<Crop> insertOrUpdateCrop(String cropName, String variety,
                                    String seed_source, Integer num_seeds,
                                    Double germination_yield_proj, Double germination_yield_act,
                                    Double feet_between_plants, String part_num,
                                    Double cost, Integer qty,
                                    String packType, String notes) throws SQLException;

    /**
     * Deletes a crop
     * @param cropName
     * @param variety
     * @return
     */
    List<Object> deleteCrop(String cropName, String variety) throws SQLException;

    /**
     *
     * @param blockID
     * @param bedID
     * @param cropName
     * @param variety
     * @param numPlants
     * @param projectedHarvest
     * @param actualHarvest
     * @param notes
     * @return - Updates the cropLocation table and returns it.
     */
    List<CropLocation> insertOrUpdateCropLocation(String blockID, String bedID,
                                            String cropName, String variety,
                                            Integer numPlants, Date projectedHarvest,
                                            Date actualHarvest, String notes) throws SQLException;

    /**
     * Delete croplocation
     * @param blockID
     * @param bedID
     * @param cropName
     * @param variety
     * @return
     */
    List<Object> deleteCropLocationTrayLocation(String blockID, String bedID,
                                                String cropName, String variety) throws SQLException;


    /**
     * In
     * @param blockID
     * @param bedID
     * @param cropName
     * @param variety
     * @param numTrays
     * @param trayType
     * @param soilType
     * @param seedsPerCell
     * @return
     */
    List<TrayLocation> insertOrUpdateTrayLocation(String blockID, String bedID,
                                            String cropName, String variety,
                                            Double numTrays, Integer trayType,
                                            String soilType, Integer seedsPerCell) throws SQLException;


}
