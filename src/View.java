import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Created by Douglas Schonholtz on 6/16/2016.
 * This shows the buttons and tables inherent with the view.
 * Included buttons being:
 *  -Get/insert/delete Blocks
 *  -Get/insert/delete Beds
 *  -Get/insert/delete Crops
 *  -Get/insert/delete Crop Locations
 *  -Get/insert/delete BedsInBlock
 *  -Get/insert/delete Crops in Bed
 *  -Show Harvest ready plants
 *  -getValidBed(String Crop_name, String Variety)
 *  -getValidBed(int feet)
 *
 */
public class View extends JFrame {
    private Model model;
    final private ConcreteView concreteView;
    private String username;
    private String password;
    private String databaseURL;

    public View() {
        this.concreteView = new ConcreteView();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().add(concreteView);
        this.pack();
    }

    public View(Model model) {
        Objects.requireNonNull(model);
        this.concreteView = new ConcreteView(model);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().add(concreteView);
        this.pack();
    }

    public void run() {
        this.repaint();
        this.setVisible(true);
    }

    /**
     * PSEUDOCODE
     *
     *
     */


}
