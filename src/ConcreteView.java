import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Douglas Schonholtz on 6/17/2016.
 */
public class ConcreteView extends JPanel {
    private Model model;
    public boolean buttonsDisplayed;
    private boolean loggedIn;

    public ConcreteView(Model model) {
        super();
        this.model = model;
        this.loggedIn = false;
    }

    public ConcreteView() {
        super();
        this.model = new Model();
        this.loggedIn = false;
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

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    /**
     * This paints the data types on the top of the program
     */
    public void paintDataTypes(Graphics g) {
        ArrayList<FarmOBJ> data = (ArrayList<FarmOBJ>) model.getData();
        String dataTypes = data.get(0).getTypes();
        int xPos = 0;
        int yPos = 10;
        int count = 0;
        while(dataTypes.length() > count) {
            if(dataTypes.charAt(count) ==  ',') {
                g.setColor(Color.black);
                g.drawString(dataTypes.substring(0,count), xPos, yPos);
                dataTypes = dataTypes.substring(count + 1);
                count = -1;
                xPos += 200;
            }
            count++;
        }
        g.drawString(dataTypes, xPos, yPos);
        xPos = 0;
        yPos += 20;
        for(FarmOBJ fo : data) {
            ArrayList foVals = fo.getValues();
            for(Object f : foVals) {
                if(f != null) {
                    g.drawString((String) f, xPos, yPos);
                }
                else {
                    g.drawString("null", xPos, yPos);
                }
                xPos += 200;
            }
            xPos = 0;
            yPos += 20;
        }
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

}
