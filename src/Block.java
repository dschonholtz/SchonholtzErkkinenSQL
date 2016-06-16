/**
 * Created by dschonholtz on 6/16/2016.
 */
public class Block {
    String blockID;
    String notes;
    public Block(String blockID) {
        if(blockID.length() > 2) {
            throw new IllegalArgumentException("BlockID's are constrained to 2 characters");
        }
        this.blockID = blockID;
        this.notes = null;
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
     *
     * @param notes
     */
    public void setNotes(String notes) {
        if(notes.length() > 512) {
            throw new IllegalArgumentException("Notes must be less than 512 characters");
        }
        this.notes = notes;
    }
}
