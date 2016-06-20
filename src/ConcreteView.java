import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Douglas Schonholtz on 6/17/2016.
 */
public class ConcreteView extends JPanel {
    private Model model;
    public boolean buttonsDisplayed;

    public ConcreteView(Model model) {
        super();
        this.model = model;
    }

    public ConcreteView() {
        super();
        this.model = new Model();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(buttonsDisplayed) {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        return new Dimension(width, height);
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
/**
     * harvest button
     * valid bed
     *  - button
     *  - 3 fields
     * Insert/update
     *  -Block 2 fields
     *  -Bed 3 fields
     *  -Crop 12 fields
     *  -CropLocation - 8 fields
     *  -TrayLocation - 8 fields
     *
     *  Total:
     *      - 7 Buttons
     */
}
