import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This paints everything onto the JPanel itself
 * Created by Douglas Schonholtz on 6/17/2016.
 */
public class ConcreteView extends JPanel {
    private Model model; // How all data is accessed, modified and stored
    public boolean buttonsDisplayed;
    private boolean loggedIn;

    public ConcreteView(Model model) {
        super();
        this.model = model;
        this.loggedIn = false;
    }

    public ConcreteView() {
        throw new IllegalArgumentException("Concrete View must take a model or it will not work");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(buttonsDisplayed) {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, this.getHeight() / 3, this.getWidth(), this.getHeight() / 3 * 2);
        }
        if(loggedIn) {
            paintDataTypes(g);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        return new Dimension(width, height);
    }

    /**
     * This paints the data types on the top of the program
     */
    public void paintDataTypes(Graphics g) {
        ArrayList<FarmOBJ> data = (ArrayList<FarmOBJ>) model.getData();

        int xPos = 0;
        int yPos = 10;
        if(data.size() > 0) {
            String dataTypes = data.get(0).getTypes();
            int count = 0;
            while (dataTypes.length() > count) {
                if (dataTypes.charAt(count) == ',') {
                    g.setColor(Color.black);
                    g.drawString(dataTypes.substring(0, count), xPos, yPos);
                    dataTypes = dataTypes.substring(count + 1);
                    count = -1;
                    xPos += 150;
                }
                count++;
            }
            g.drawString(dataTypes, xPos, yPos);
            xPos = 0;
            yPos += 20;
            for (FarmOBJ fo : data) {
                ArrayList foVals = fo.getValues();
                for (Object f : foVals) {
                    if (f != null) {
                        g.drawString(f.toString(), xPos, yPos);
                    } else {
                        g.drawString("null", xPos, yPos);
                    }
                    xPos += 150;
                }
                xPos = 0;
                yPos += 20;
            }
        }
        else {
            g.drawString("There is no Data to display", xPos, yPos);
        }
    }

    /**
     * Sets the loggedIn parameter
     * @param loggedIn - Shows when connected from the database so when its ok to start trying to draw data.
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

}
