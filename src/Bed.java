/**
 * Created by dschonholtz on 6/16/2016.
 */
public class Bed {
    String blockID;
    String bedID;
    String notes;
    public Bed(String blockID, String bedID) {
        if(blockID.length() > 2) {
            throw new IllegalArgumentException("BlockID's are constrained to 2 characters");
        }
        if(bedID.length() > 32) {
            throw new IllegalArgumentException("BedID's are constrained to 32 characters");
        }
        this.blockID = blockID;
        this.bedID = bedID;
        this.notes = "";

    }

    /**
     * Use for printing in gui
     * @return returns the blockid
     */
    public String getBlockID() {
        return blockID;
    }

    /**
     * Use for printing in gui
     * @return returns the bedid
     */
    public String getBedID() {
        return bedID;
    }

    /**
     * Use for printing in gui
     * @return returns the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Used for updating
     * @param blockID - must be 2 characters in length max
     */
    public void setBlockID(String blockID) {
        if(blockID.length() > 2) {
            throw new IllegalArgumentException("BlockID's must be less than 2 characters");
        }
        this.blockID = blockID;
    }

    /**
     * Used for updating
     * @param bedID - must be 32 characters in length at a maximum
     */
    public void setBedID(String bedID) {
        if(bedID.length() > 32) {
            throw new IllegalArgumentException("BedID's are constrained to 32 characters");
        }
        this.bedID = bedID;
    }

    /**
     *
     * @param notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }
}
