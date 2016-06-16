import java.sql.Date;

/**
 * Created by dschonholtz on 6/16/2016.
 */
public class CropLocation {

    private String blockID;// CHAR(2),
    private String bedID;// VARCHAR(32),
    private String cropName; // VARCHAR(128),
    private String variety;// VARCHAR(128),
    //PRIMARY KEY(bedID, blockID, crop_name, variety),

    private Integer numPlants;// INT(32),
    private Date projectedHarvest;// DATE; //DEFAULT NULL,
    private Date actualHarvest;//  DATE DEFAULT NULL,
    private String notes; // VARCHAR(512)


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

        this.numPlants = null;
        projectedHarvest = null;
        actualHarvest = null;
        this.notes = null;

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
        this.notes = notes;
    }
}
