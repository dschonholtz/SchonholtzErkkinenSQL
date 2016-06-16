/**
 * Created by Douglas Schonholtz on 6/16/2016.
 * Checks values of truth elements to go into the SQL database
 * TODO DATA CHECKS STILL NEEDED!!!
 */
public class TrayLocation {
    private String blockID;// CHAR(2),
    private String bedID;// VARCHAR(32),
    private String cropName; // VARCHAR(128),
    private String variety;// VARCHAR(128),

    private Double num_trays = null;// DOUBLE,
    private Integer tray_type = null;// INT,
    private String soil_type = null; //VARCHAR(128) DEFAULT NULL,
    private Integer seeds_per_cell = null;// INT,

    public TrayLocation() {
        throw new IllegalArgumentException("Cannot access TrayLocations default constructor");
    }

    //The bare minimum, These are the values for a tray location primary key
    public TrayLocation(String blockID, String bedID, String cropName, String variety) {
        this.blockID = blockID;
        this.bedID = bedID;
        this.cropName = cropName;
        this.variety = variety;
    }

    public TrayLocation(String blockID, String bedID, String cropName, String variety, Double num_trays,
                        Integer tray_type, String soil_type, Integer seeds_per_cell) {
        this.blockID = blockID;
        this.bedID = bedID;
        this.cropName = cropName;
        this.variety = variety;
        this.num_trays = num_trays;
        this.tray_type = tray_type;
        this.soil_type = soil_type;
        this.seeds_per_cell = seeds_per_cell;
    }



}
