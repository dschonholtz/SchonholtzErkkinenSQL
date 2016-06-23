import java.util.ArrayList;

/**
 * Created by dschonholtz on 6/16/2016.
 */
public class Block implements FarmOBJ{
    private String blockID;
    private String notes;

    public Block(String blockID) {
        if(blockID.length() > 2 || blockID.equals("")) {
            throw new IllegalArgumentException("BlockID's are constrained to 2 characters");
        }
        this.blockID = blockID;
        this.notes = null;
    }

    @Override
    public String getTypes() {
        return "Block ID, Notes";
    }

    @Override
    public ArrayList getValues() {
        ArrayList values = new ArrayList();
        values.add(blockID);
        values.add(notes);
        return values;
    }

    public String getBlockID() {
        return blockID;
    }

    public String getNotes() {
        return notes;
    }

    /**
     *
     * @param notes
     */
    public void setNotes(String notes) {
        if (notes == null || notes.equals("")) {
            this.notes = null;
            return;
        }
        if(notes.length() > 512) {
            throw new IllegalArgumentException("Notes must be less than 512 characters");
        }
        this.notes = notes;
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
}
