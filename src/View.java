import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private boolean buttonsDisplayed;


    public View() {
        this.concreteView = new ConcreteView();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().add(concreteView);
        this.pack();
        buttonsDisplayed = false;
    }

    public void run() {
        this.repaint();

        JLabel headerLabel = new JLabel("Stone Beech Farm Database \nBy Douglas Schonholtz and Brian Erkkinen");
        JLabel statusLabel = new JLabel();

        JLabel namelabel = new JLabel("User ID: ", JLabel.RIGHT);
        JLabel passwordLabel = new JLabel("Password: ", JLabel.CENTER);
        final JTextField userText = new JTextField(6);
        final JPasswordField passwordText = new JPasswordField(6);
        JButton loginButton = new JButton("Login");

        concreteView.add(namelabel);
        concreteView.add(userText);
        concreteView.add(headerLabel);
        concreteView.add(passwordLabel);
        concreteView.add(passwordText);
        concreteView.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = userText.getText().toString();
                String pass = new String(passwordText.getPassword());
                Model model = new Model();
                try {
                    model.connect(user, pass);
                    statusLabel.setText("Credentials accepted");

                    concreteView.remove(namelabel);
                    concreteView.remove(userText);
                    concreteView.remove(passwordLabel);
                    concreteView.remove(passwordText);
                    concreteView.remove(loginButton);
                    concreteView.remove(headerLabel);
                    concreteView.revalidate();
                    concreteView.repaint();
                    begin();
                } catch (Exception exception) {
                    statusLabel.setText("Invalid password or username please try again");
                }
            }
        });
        concreteView.add(statusLabel);


        //paintData(g);

        this.setVisible(true);
    }

    private void begin() {
        concreteView.setLayout(null);
        JButton showButtonsButton = new JButton("Show Buttons");
        showButtonsButton.setBounds(getWidth() - 150, 0, 150, 20);
        concreteView.add(showButtonsButton);
        showButtonsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonsDisplayed = !buttonsDisplayed;
                concreteView.buttonsDisplayed = !concreteView.buttonsDisplayed;
                showButtons();
            }
        });
    }

    private void showButtons() {
        if(buttonsDisplayed) {

            int buttonx = 50;
            int buttony = concreteView.getHeight() / 2;
            int buttonWidth = 150;
            int buttonHeight = 20;
            insertOrUpdateBlock(buttonx, buttony, buttonWidth, buttonHeight);

            buttonx += buttonWidth + 20;
            insertOrUpdateBed(buttonx, buttony, buttonWidth, buttonHeight);

            buttonx += buttonWidth + 20;
            inserOrUpdateCrop(buttonx, buttony, buttonWidth, buttonHeight);


            revalidate();
            repaint();
        }
        else {
            concreteView.removeAll();
            begin();
            revalidate();
            repaint();
        }

    }

    private void insertOrUpdateBlock(int buttonx, int buttony, int buttonWidth, int buttonHeight) {
        JButton addBlockButton = new JButton("Add/Update Block");
        addBlockButton.setBounds(buttonx, buttony, buttonWidth, buttonHeight);
        concreteView.add(addBlockButton);
        addBlockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JLabel blockIDLabel = new JLabel("Block ID");
        blockIDLabel.setForeground(Color.white);
        blockIDLabel.setBounds(buttonx, buttony + buttonHeight, buttonWidth, buttonHeight);
        concreteView.add(blockIDLabel);

        JTextField blockID = new JTextField(6);
        blockID.setBounds(buttonx, buttony + buttonHeight * 2, buttonWidth, buttonHeight);
        concreteView.add(blockID);

        JLabel blockNoteLabel = new JLabel("Block Notes");
        blockNoteLabel.setForeground(Color.white);
        blockNoteLabel.setBounds(buttonx, buttony + buttonHeight * 3, buttonWidth, buttonHeight);
        concreteView.add(blockNoteLabel);

        JTextField notes = new JTextField(6);
        notes.setBounds(buttonx, buttony + buttonHeight * 4, buttonWidth, buttonHeight);
        concreteView.add(notes);
    }

    private void insertOrUpdateBed(int buttonx, int buttony, int buttonWidth, int buttonHeight) {
        JButton addBedButton = new JButton("Add/Update Bed");
        addBedButton.setBounds(buttonx, buttony, buttonWidth, buttonHeight);
        concreteView.add(addBedButton);
        addBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JLabel blockIDLabel = new JLabel("Block ID");
        blockIDLabel.setForeground(Color.white);
        blockIDLabel.setBounds(buttonx, buttony + buttonHeight, buttonWidth, buttonHeight);
        concreteView.add(blockIDLabel);

        JTextField blockID = new JTextField(6);
        blockID.setBounds(buttonx, buttony + buttonHeight * 2, buttonWidth, buttonHeight);
        concreteView.add(blockID);

        JLabel bedIDLabel = new JLabel("Bed ID");
        bedIDLabel.setForeground(Color.white);
        bedIDLabel.setBounds(buttonx, buttony + buttonHeight * 3, buttonWidth, buttonHeight);
        concreteView.add( bedIDLabel);

        JTextField bedID = new JTextField(6);
        bedID.setBounds(buttonx, buttony + buttonHeight * 4, buttonWidth, buttonHeight);
        concreteView.add(bedID);

        JLabel blockNoteLabel = new JLabel("Bed Notes");
        blockNoteLabel.setForeground(Color.white);
        blockNoteLabel.setBounds(buttonx, buttony + buttonHeight * 5, buttonWidth, buttonHeight);
        concreteView.add(blockNoteLabel);

        JTextField notes = new JTextField(6);
        notes.setBounds(buttonx, buttony + buttonHeight * 6, buttonWidth, buttonHeight);
        concreteView.add(notes);
    }

    private void inserOrUpdateCrop(int buttonx, int buttony, int buttonWidth, int buttonHeight) {
        JLabel cropNameLabel = new JLabel("Crop Name");
        cropNameLabel.setForeground(Color.white);
        cropNameLabel.setBounds(buttonx, buttony + buttonHeight, buttonWidth, buttonHeight);
        concreteView.add(cropNameLabel);

        JTextField varietyField = new JTextField(6);
        varietyField.setBounds(buttonx, buttony + buttonHeight * 2, buttonWidth, buttonHeight);
        concreteView.add(varietyField);


        JLabel seedSourceLabel = new JLabel("Seed Source");
        seedSourceLabel.setForeground(Color.white);
        seedSourceLabel.setBounds(buttonx, buttony + buttonHeight * 3, buttonWidth, buttonHeight);
        concreteView.add(seedSourceLabel);

        JTextField seedSource = new JTextField(6);
        seedSource.setBounds(buttonx, buttony + buttonHeight * 4, buttonWidth, buttonHeight);
        concreteView.add(seedSource);


        JLabel numSeedsLabel = new JLabel("Number of Seeds (INT)");
        numSeedsLabel.setForeground(Color.white);
        numSeedsLabel.setBounds(buttonx, buttony + buttonHeight * 5, buttonWidth, buttonHeight);
        concreteView.add(numSeedsLabel);

        JTextField numSeeds = new JTextField(6);
        numSeeds.setBounds(buttonx, buttony + buttonHeight * 6, buttonWidth, buttonHeight);
        concreteView.add(numSeeds);


        JLabel germinationYieldProjLabel = new JLabel("Projected Germination Yield(Double)");
        germinationYieldProjLabel.setForeground(Color.white);
        germinationYieldProjLabel.setBounds(buttonx, buttony + buttonHeight * 7, buttonWidth, buttonHeight);
        concreteView.add(germinationYieldProjLabel);

        JTextField germinationProj = new JTextField(6);
        germinationProj.setBounds(buttonx, buttony + buttonHeight * 8, buttonWidth, buttonHeight);
        concreteView.add(germinationProj);


        JLabel germinationYieldActLabel = new JLabel("Actual Germination Yield(Double)");
        germinationYieldActLabel.setForeground(Color.white);
        germinationYieldActLabel.setBounds(buttonx, buttony + buttonHeight * 9, buttonWidth, buttonHeight);
        concreteView.add(germinationYieldActLabel);

        JTextField germinationAct = new JTextField(6);
        germinationAct.setBounds(buttonx, buttony + buttonHeight * 10, buttonWidth, buttonHeight);
        concreteView.add(germinationAct);
        // Double feet_between_plants, String part_num, Double cost, Integer qty,
        // String packType, String notes

        JLabel feetBetweenPlantsLabel = new JLabel("Feet Between Plants(Double)");
        feetBetweenPlantsLabel.setForeground(Color.white);
        feetBetweenPlantsLabel.setBounds(buttonx, buttony + buttonHeight * 11, buttonWidth, buttonHeight);
        concreteView.add(feetBetweenPlantsLabel);

        JTextField feetBetweenPlants = new JTextField(6);
        feetBetweenPlants.setBounds(buttonx, buttony + buttonHeight * 12, buttonWidth, buttonHeight);
        concreteView.add(feetBetweenPlants);

        JButton insertOrUpdateCropButton = new JButton("Add/Update Crop");
        insertOrUpdateCropButton.setBounds(buttonx, buttony, buttonWidth, buttonHeight);
        concreteView.add(insertOrUpdateCropButton);
        insertOrUpdateCropButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}
