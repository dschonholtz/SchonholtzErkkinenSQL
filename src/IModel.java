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
    List<List<String>> getHarvestable();

    /**
     * If a record with this primary key already exists then it updates its values.
     * Otherwise it
     * @param blockID - The blockID; must be 2 characters or less.
     * @param bedID - The BedID; must 32 characters or less
     * @return - Returns the updated beds.
     */
    List<List<String>> insertOrUpdateBed(String blockID, String bedID, String notes);
}
