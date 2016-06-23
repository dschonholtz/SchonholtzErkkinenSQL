import java.util.ArrayList;

/**
 * Created by Douglas Schonholt on 6/16/2016.
 * The file structure for Beds.
 */
public class Bed implements FarmOBJ{
    private String blockID;
    private String bedID;
    private String notes;
    public Bed(String blockID, String bedID) {
        if(blockID.length() > 2  || blockID.equals("")) {
            throw new IllegalArgumentException("BlockID's are constrained to 1 or 2 characters");
        }
        if(bedID.length() > 32) {
            throw new IllegalArgumentException("BedID's are constrained to 32 characters");
        }
        this.blockID = blockID;
        this.bedID = bedID;
        this.notes = null;
    }


    /**
     *
     * @param notes inserts notes into this and checks to make sure its valid
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


    @Override
    public String getTypes() {
        return "Block ID, Bed ID, Notes";
    }

    @Override
    public ArrayList getValues() {
        ArrayList stuff = new ArrayList();
        stuff.add(blockID);
        stuff.add(bedID);
        stuff.add(notes);
        return stuff;
    }

    public String getBlockID() {
        return blockID;
    }

    public String getBedID() {
        return bedID;
    }

    public String getNotes() {
        return notes;
    }

}
