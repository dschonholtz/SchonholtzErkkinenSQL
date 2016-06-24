import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Douglas Schonholtz on 6/16/2016.
 */
public class Crop implements FarmOBJ{
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

        if(cropName == null || cropName.length() > 128 || cropName.equals("")) {
            throw new IllegalArgumentException("CropName must be valid");
        }
        if(variety == null || variety.length() > 128 || variety.equals("")) {
            throw new IllegalArgumentException("Variety must be valid");
        }
        if(feet_between_plants <= 0) {
            throw new IllegalArgumentException("Feet Between plants must be valid");
        }
        if(germination_yield_act > 1 || germination_yield_act < 0) {
            throw new IllegalArgumentException("Germination yields must be valid");
        }
        if(germination_yield_proj > 1 || germination_yield_act < 0) {
            throw new IllegalArgumentException("Germination yields must be valid");
        }

        if(part_num.equals("")) {
            this.part_num = null;
        }
        else {
            this.part_num = part_num;
        }

        if(packType.equals("")) {
            this.packType = null;
        }
        else {
            this.packType = packType;
        }

        if(seed_source.equals("")) {
            this.seed_source = null;
        }
        else {
            this.seed_source = seed_source;
        }

        if(notes.equals("")) {
            this.notes = null;
        }
        else {
            this.notes = notes;
        }

        this.cropName = cropName;
        this.variety = variety;
        this.num_seeds = num_seeds;
        this.germination_yield_proj = germination_yield_proj;
        this.germination_yield_act = germination_yield_act;
        this.feet_between_plants = feet_between_plants;
        this.cost = cost;
        this.qty = qty;
    }


    public Crop(String cropName, String variety, String seed_source, String num_seeds, String germination_yield_proj,
                String germination_yield_act, String feet_between_plants, String part_num, String cost,
                String qty, String packType, String notes) throws IllegalArgumentException {
                    Integer numSeedsFinal;
                    try {
                        numSeedsFinal = Integer.parseInt(num_seeds);
                    } catch (NumberFormatException ne) {
                        if(num_seeds.equals("")) {
                            numSeedsFinal = null;
                        }
                        else {
                            throw new IllegalArgumentException("Number formatted incorrectly");
                        }
                    }

                    Double germinationProjFinal;
                    try {
                        germinationProjFinal = Double.parseDouble(germination_yield_proj);
                    } catch (NumberFormatException ne) {
                        if(germination_yield_proj.equals("")) {
                            germinationProjFinal = null;
                        }
                        else {
                            throw new IllegalArgumentException("Number formatted incorrectly");
                        }
                    }

                    Double germinationActFinal;
                    try {
                        germinationActFinal = Double.parseDouble(germination_yield_act);
                    } catch (NumberFormatException ne) {
                        if(germination_yield_act.equals("")) {
                            germinationActFinal = null;
                        }
                        else {
                            throw new IllegalArgumentException("Number formatted incorrectly");
                        }
                    }

                    Double feetBetweenPlantsFinal;
                    try {
                        feetBetweenPlantsFinal = Double.parseDouble(feet_between_plants);
                    } catch (NumberFormatException ne) {
                        if(feet_between_plants.equals("")) {
                            feetBetweenPlantsFinal = null;
                        }
                        else {
                            throw new IllegalArgumentException("Number formatted incorrectly");
                        }
                    }

                    Double costFinal;
                    try {
                        costFinal = Double.parseDouble(cost);
                    } catch (NumberFormatException ne) {
                        if(cost.equals("")) {
                            costFinal = null;
                        }
                        else {
                            throw new IllegalArgumentException("Number formatted incorrectly");
                        }
                    }

                    Integer qtyFinal;
                    try {
                        qtyFinal = Integer.parseInt(qty);
                    } catch (NumberFormatException ne) {
                        if(qty.equals("")) {
                            qtyFinal = null;
                        }
                        else {
                            throw new IllegalArgumentException("Number formatted incorrectly");
                        }
                    }
        if(cropName == null || cropName.length() > 128 || cropName.equals("")) {
            throw new IllegalArgumentException("CropName must be valid");
        }
        if(variety == null || variety.length() > 128 || variety.equals("")) {
            throw new IllegalArgumentException("Variety must be valid");
        }

        if(part_num.equals("")) {
            this.part_num = null;
        }
        else {
            this.part_num = part_num;
        }

        if(packType.equals("")) {
            this.packType = null;
        }
        else {
            this.packType = packType;
        }

        if(seed_source.equals("")) {
            this.seed_source = null;
        }
        else {
            this.seed_source = seed_source;
        }

        if(notes.equals("")) {
            this.notes = null;
        }
        else {
            this.notes = notes;
        }
        this.cropName = cropName;
        this.variety = variety;
        this.num_seeds = numSeedsFinal;
        this.germination_yield_proj = germinationProjFinal;
        this.germination_yield_act = germinationActFinal;
        this.feet_between_plants = feetBetweenPlantsFinal;
        this.cost = costFinal;
        this.qty = qtyFinal;
    }

    public Crop(String cropName, String variety) {
        if(cropName == null || cropName.length() > 128 || cropName.equals("")) {
            throw new IllegalArgumentException("CropName must be valid");
        }
        if(variety == null || variety.length() > 128 || variety.equals("")) {
            throw new IllegalArgumentException("Variety must be valid");
        }
        this.cropName = cropName;
        this.variety = variety;
    }

    public Crop() {
        throw new IllegalArgumentException("Crops need to have crop_name, and Variety");
    }

    public void setSeed_source(String seed_source) {
        if(seed_source == null || seed_source.equals("")) {
            this.seed_source = null;
        }
        else {
            if(seed_source.length() > 256) {
                throw new IllegalArgumentException("Seed source must be less than 256 characters");
            }
            this.seed_source = seed_source;
        }

    }


    public void setNum_seeds(Integer num_seeds) {
        if(num_seeds < 0) {
            throw new IllegalArgumentException("Number of seeds must be positive");
        }
        this.num_seeds = num_seeds;
    }


    public void setPart_num(String part_num) {
        if(part_num == null || part_num.equals("")) {
            this.part_num = null;
        }
        else {
            if(part_num.length() > 256) {
                throw new IllegalArgumentException("Part Numbers have a limit of 256 characters.");
            }
            this.part_num = part_num;
        }
    }


    public void setPackType(String packType) {
        if(packType == null || packType.equals("")) {
            this.packType = null;
        }else {
            this.packType = packType;
        }
    }


    public void setNotes(String notes) {
        if(notes == null || notes.equals("")) {
            this.notes = null;
            return;
        }
        if(notes.length() > 512) {
            throw new IllegalArgumentException("Notes must be less than 512 characters");
        }
        this.notes = notes;
    }

    @Override
    public String getTypes() {
        return "Crop Name, Variety, Seed Source, Number of Seeds, Proj. Germination Yield, " +
                "Actual Germination Yield, Feet Between Plants, Part Num, Cost, " +
                "Quantity, Pack Type, Notes";
    }

    @Override
    public ArrayList getValues() {
        ArrayList stuff = new ArrayList();
        stuff.add(cropName);
        stuff.add(variety);
        stuff.add(seed_source);
        stuff.add(num_seeds);
        stuff.add(germination_yield_proj);
        stuff.add(germination_yield_act);
        stuff.add(feet_between_plants);
        stuff.add(part_num);
        stuff.add(cost);
        stuff.add(qty);
        stuff.add(packType);
        stuff.add(notes);
        return stuff;
    }

    public String getCropName() {
        return cropName;
    }


    public String getVariety() {
        return variety;
    }

    public String getSeed_source() {
        return seed_source;
    }

    public Integer getNum_seeds() {
        return num_seeds;
    }

    public Double getGermination_yield_proj() {
        return germination_yield_proj;
    }

    public void setGermination_yield_proj(Double germination_yield_proj) {
        if(germination_yield_proj < 0) {
            throw new IllegalArgumentException("Germination yield must be greater than or equal to zero");
        }
        this.germination_yield_proj = germination_yield_proj;
    }

    public Double getGermination_yield_act() {
        return germination_yield_act;
    }

    public void setGermination_yield_act(Double germination_yield_act) {
        if(germination_yield_act < 0) {
            throw new IllegalArgumentException("Germination yield must be greater than or equal to zero");
        }
        this.germination_yield_act = germination_yield_act;
    }

    public Double getFeet_between_plants() {
        return feet_between_plants;
    }

    public void setFeet_between_plants(Double feet_between_plants) {
        if(feet_between_plants < 0) {
            throw new IllegalArgumentException("Feet between plants must be greater than zero");
        }
        this.feet_between_plants = feet_between_plants;
    }

    public String getPart_num() {
        return part_num;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getPackType() {
        return packType;
    }

    public String getNotes() {
        return notes;
    }
}
