/**
 * Created by Douglas Schonholtz on 6/16/2016.
 */
public class Crop {
    /**
     * The comments after these variables are their restrictions in SQL these same restrictions should be enforced in
     * java as not to send bad data to mySQL
     */
    private String cropName; // VARCHAR(128),
    private String variety; // VARCHAR(128),
    //PRIMARY KEY(crop_name, variety),

    private String seed_source = null; //VARCHAR(256) DEFAULT NULL,
    private Integer num_seeds = null; //INT(32) DEFAULT 0,
    private Double germination_yield_proj = null; //DOUBLE(9,8) DEFAULT NULL,
    private Double germination_yield_act = null; // DOUBLE(9,8) DEFAULT NULL,
    private Double feet_between_plants = 1.0; // DOUBLE(9,8) DEFAULT 1.0,
    private String part_num = null; // VARCHAR(256) DEFAULT NULL,
    private Double cost = null; // DOUBLE (8,2) DEFAULT NULL,
    private Integer qty = null;// INT DEFAULT NULL, # The number of packs that were bought
    private String packType = null;
    private String notes = null; // VARCHAR(512)


    public Crop(String cropName, String variety, String seed_source, Integer num_seeds, Double germination_yield_proj,
                Double germination_yield_act, Double feet_between_plants, String part_num, Double cost,
                Integer qty, String packType, String notes) {
        this.cropName = cropName;
        this.variety = variety;
        this.num_seeds = num_seeds;
        this.germination_yield_proj = germination_yield_proj;
        this.germination_yield_act = germination_yield_act;
        this.feet_between_plants = feet_between_plants;
        this.part_num = part_num;
        this.cost = cost;
        this.qty = qty;
        this.packType = packType;
        this.seed_source = seed_source;
        this.notes = notes;
    }

    public Crop(String cropName, String variety) {
        if(cropName == null || cropName.length() > 128) {
            throw new IllegalArgumentException("CropName must be valid");
        }
        if(variety != null || variety.length() > 128) {
            throw new IllegalArgumentException("Variety must be valid");
        }
        this.cropName = cropName;
        this.variety = variety;
    }

    public Crop() {
        throw new IllegalArgumentException("Crops need to have crop_name, and Variety");
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

    public String getSeed_source() {
        return seed_source;
    }

    public void setSeed_source(String seed_source) {
        if(seed_source.length() > 256) {
            throw new IllegalArgumentException("Seed source must be less than 256 characters");
        }
        this.seed_source = seed_source;
    }

    public Integer getNum_seeds() {
        return num_seeds;
    }

    public void setNum_seeds(Integer num_seeds) {
        this.num_seeds = num_seeds;
    }

    public double getGermination_yield_proj() {
        return germination_yield_proj;
    }

    public void setGermination_yield_proj(double germination_yield_proj) {
        this.germination_yield_proj = germination_yield_proj;
    }

    public double getGermination_yield_act() {
        return germination_yield_act;
    }

    public void setGermination_yield_act(double germination_yield_act) {
        this.germination_yield_act = germination_yield_act;
    }

    public double getFeet_between_plants() {
        return feet_between_plants;
    }

    public void setFeet_between_plants(double feet_between_plants) {
        this.feet_between_plants = feet_between_plants;
    }

    public String getPart_num() {
        return part_num;
    }

    public void setPart_num(String part_num) {
        if(part_num.length() > 256) {
            throw new IllegalArgumentException("Part Numbers have a limit of 256 characters.");
        }
        this.part_num = part_num;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getPackType() {
        return packType;
    }

    public void setPackType(String packType) {
        this.packType = packType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        if(notes.length() > 512) {
            throw new IllegalArgumentException("Notes must be less than 512 characters");
        }
        this.notes = notes;
    }


}
