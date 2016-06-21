import java.util.ArrayList;

/**
 * Created by Douglas Schonholtz on 6/16/2016.
 * Checks values of truth elements to go into the SQL database
 */
public class TrayLocation implements FarmOBJ{
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
        if(blockID.length() > 2 || bedID.length() > 32 || cropName.length() > 128) {
            throw new IllegalArgumentException("Tray Location Fields are Incorrect");
        }
        this.blockID = blockID;
        this.bedID = bedID;
        this.cropName = cropName;
        this.variety = variety;
    }

    public TrayLocation(String blockID, String bedID, String cropName, String variety, Double num_trays,
                        Integer tray_type, String soil_type, Integer seeds_per_cell) {
        if(blockID.length() > 2 || bedID.length() > 32 || cropName.length() > 128) {
            throw new IllegalArgumentException("Tray Location Fields are Incorrect");
        }
        this.blockID = blockID;
        this.bedID = bedID;
        this.cropName = cropName;
        this.variety = variety;

        if(soil_type.length() > 128) {
            throw new IllegalArgumentException("Soil type is too long");
        }

        this.num_trays = num_trays;
        this.tray_type = tray_type;
        this.soil_type = soil_type;
        this.seeds_per_cell = seeds_per_cell;
    }

    public String getBlockID() {
        return blockID;
    }

    public String getBedID() {
        return bedID;
    }

    public String getCropName() {
        return cropName;
    }

    public String getVariety() {
        return variety;
    }

    public Double getNum_trays() {
        return num_trays;
    }

    public Integer getTray_type() {
        return tray_type;
    }

    public String getSoil_type() {
        return soil_type;
    }

    public Integer getSeeds_per_cell() {
        return seeds_per_cell;
    }

    @Override
    public String getTypes() {
        return "Block ID, Bed ID, Crop Name, Variety, Number of Trays, " +
                "Tray Type, Soil Type, Seeds Per Cell";
    }

    @Override
    public ArrayList getValues() {
        ArrayList stuff = new ArrayList();
        stuff.add(blockID);
        stuff.add(bedID);
        stuff.add(cropName);
        stuff.add(variety);
        stuff.add(num_trays);
        stuff.add(tray_type);
        stuff.add(soil_type);
        stuff.add(seeds_per_cell);
        return stuff;
    }
}
