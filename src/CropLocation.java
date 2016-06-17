import java.sql.Date;

/**
 * Created by Douglas Schonholtz on 6/16/2016.
 */
public class CropLocation {

    private String blockID;// CHAR(2),
    private String bedID;// VARCHAR(32),
    private String cropName; // VARCHAR(128),
    private String variety;// VARCHAR(128),
    //PRIMARY KEY(bedID, blockID, crop_name, variety),

    private Integer numPlants = null;// INT(32),
    private Date projectedHarvest = null;// DATE; //DEFAULT NULL,
    private Date actualHarvest = null;//  DATE DEFAULT NULL,
    private String notes = null; // VARCHAR(512)

    public CropLocation() {
        throw new IllegalArgumentException("Crop location must at least have blockid, bedID, crop name and variety");
    }

    public CropLocation(String blockID, String bedID, String cropName, String variety) {
        if(blockID.length() > 2) {
            throw new IllegalArgumentException("BlockID's are constrained to 2 or less characters");
        }
        if(bedID.length() > 32) {
            throw new IllegalArgumentException("BedID's are constrained to 32 or less characters");
        }
        if(cropName.length() > 128) {
            throw new IllegalArgumentException("cropName's are constrained to 128 or less characters");
        }
        if(variety.length() > 128) {
            throw new IllegalArgumentException("varieties's are constrained to 128 or less characters");
        }
        this.blockID = blockID;
        this.bedID = bedID;
        this.cropName = cropName;
        this.variety = variety;
    }

    public CropLocation(String blockID, String bedID, String cropName, String variety, Integer numPlants,
                        Date projectedHarvest, Date actualHarvest, String notes) {
        if(blockID.length() > 2) {
            throw new IllegalArgumentException("BlockID's are constrained to 2 or less characters");
        }
        if(bedID.length() > 32) {
            throw new IllegalArgumentException("BedID's are constrained to 32 or less characters");
        }
        if(cropName.length() > 128) {
            throw new IllegalArgumentException("cropName's are constrained to 128 or less characters");
        }
        if(variety.length() > 128) {
            throw new IllegalArgumentException("varieties's are constrained to 128 or less characters");
        }
        if(notes != null && notes.length() > 512) {
            throw new IllegalArgumentException("notes cannot be larger than 512 characters");
        }
        this.blockID = blockID;
        this.bedID = bedID;
        this.cropName = cropName;
        this.variety = variety;
        this.numPlants = numPlants;
        this.projectedHarvest = projectedHarvest;
        this.actualHarvest = actualHarvest;
        this.notes = notes;
    }

    public String getBlockID() {
        return blockID;
    }

    public void setBlockID(String blockID) {
        this.blockID = blockID;
    }

    public String getBedID() {
        return bedID;
    }

    public void setBedID(String bedID) {
        this.bedID = bedID;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public Integer getNumPlants() {
        return numPlants;
    }

    public void setNumPlants(Integer numPlants) {
        this.numPlants = numPlants;
    }

    public Date getProjectedHarvest() {
        return projectedHarvest;
    }

    public void setProjectedHarvest(Date projectedHarvest) {
        this.projectedHarvest = projectedHarvest;
    }

    public Date getActualHarvest() {
        return actualHarvest;
    }

    public void setActualHarvest(Date actualHarvest) {
        this.actualHarvest = actualHarvest;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        if(notes != null && notes.length() > 512) {
            throw new IllegalArgumentException("notes cannot be larger than 512 characters");
        }
        this.notes = notes;
    }
}
