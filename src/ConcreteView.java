import javax.swing.*;
import java.awt.*;

/**
 * Created by Douglas Schonholtz on 6/17/2016.
 */
public class ConcreteView extends JPanel {
    Model model;

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
    }

    @Override
    public Dimension getPreferredSize() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        return new Dimension(width, height);
    }
}
