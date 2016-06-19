import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Douglas Schonholtz on 6/17/2016.
 */
public class ConcreteView extends JPanel {
    Model model;
    private boolean loggedIn;
    private boolean buttonsDisplayed; //TODO needs to be in concreteView

    public ConcreteView(Model model) {
        super();
        this.model = model;
        this.loggedIn = false;
        this.buttonsDisplayed = false;
    }

    public ConcreteView() {
        super();
        this.model = new Model();
        this.loggedIn = false;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(loggedIn) {
            JButton showButtonsButton = new JButton("Show Buttons");//TODO needs to be in concreteView
            showButtonsButton.setBounds(getWidth() - 100, 0, 100, 20);
            this.add(showButtonsButton);
            showButtonsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //buttonsDisplayed = !buttonsDisplayed;
                }
            });
            //paintData(g);
            //if(buttonsDisplayed) {
                paintButtons(g);
            //}
        }
    }

    @Override
    public Dimension getPreferredSize() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        return new Dimension(width, height);
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    private void paintButtons(Graphics g) {//TODO needs to be in concreteView
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,getHeight()*4/5,getWidth(),getHeight()); //rectangle that all buttons will be in
        int buttonx = 50;
        int buttony = getHeight()*4/5;
        int buttonWidth = 150;
        int buttonHeight = 20;
        addBlock(g, buttonx,buttony, buttonWidth,buttonHeight);
        buttonx += buttonWidth + 20;
        addBed(g, buttonx,buttony, buttonWidth,buttonHeight);

    }

    private void addBlock(Graphics g, int buttonx, int buttony, int buttonWidth, int buttonHeight) {//TODO needs to be in concreteView
        JButton addBlockButton = new JButton("Add/Update Block");
        addBlockButton.setBounds(buttonx, buttony, buttonWidth, buttonHeight);
        this.add(addBlockButton);
        addBlockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JLabel blockIDLabel = new JLabel("Block ID");
        blockIDLabel.setForeground(Color.white);
        blockIDLabel.setBounds(buttonx, buttony + buttonHeight, buttonWidth, buttonHeight);
        this.add(blockIDLabel);

        JTextField blockID = new JTextField(6);
        blockID.setBounds(buttonx, buttony + buttonHeight * 2, buttonWidth, buttonHeight);
        this.add(blockID);

        JLabel blockNoteLabel = new JLabel("Block Notes");
        blockNoteLabel.setForeground(Color.white);
        blockNoteLabel.setBounds(buttonx, buttony + buttonHeight * 3, buttonWidth, buttonHeight);
        this.add(blockNoteLabel);

        JTextField notes = new JTextField(6);
        notes.setBounds(buttonx, buttony + buttonHeight * 4, buttonWidth, buttonHeight);
        this.add(notes);
    }

    private void addBed(Graphics g, int buttonx, int buttony, int buttonWidth, int buttonHeight) {
        JButton addBedButton = new JButton("Add/Update Bed");
        addBedButton.setBounds(buttonx, buttony, buttonWidth, buttonHeight);
        this.add(addBedButton);
        addBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JLabel blockIDLabel = new JLabel("Block ID");
        blockIDLabel.setForeground(Color.white);
        blockIDLabel.setBounds(buttonx, buttony + buttonHeight, buttonWidth, buttonHeight);
        this.add(blockIDLabel);

        JTextField blockID = new JTextField(6);
        blockID.setBounds(buttonx, buttony + buttonHeight * 2, buttonWidth, buttonHeight);
        this.add(blockID);

        JLabel blockNoteLabel = new JLabel("Bed Notes");
        blockNoteLabel.setForeground(Color.white);
        blockNoteLabel.setBounds(buttonx, buttony + buttonHeight * 3, buttonWidth, buttonHeight);
        this.add(blockNoteLabel);

        JTextField notes = new JTextField(6);
        notes.setBounds(buttonx, buttony + buttonHeight * 4, buttonWidth, buttonHeight);
        this.add(notes);
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
