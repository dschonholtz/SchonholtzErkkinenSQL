import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

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
            int buttony = concreteView.getHeight() / 3;
            int buttonWidth = 150;
            int buttonHeight = 20;
            insertOrUpdateBlock(buttonx, buttony, buttonWidth, buttonHeight);

            buttonx += buttonWidth + 20;
            insertOrUpdateBed(buttonx, buttony, buttonWidth, buttonHeight);

            buttonx += buttonWidth + 20;
            insertOrUpdateCrop(buttonx, buttony, buttonWidth, buttonHeight);

            buttonx += buttonWidth + 20;
            insertOrUpdateCropLocation(buttonx, buttony, buttonWidth, buttonHeight);

            buttonx += buttonWidth + 20;
            insertOrUpdateTrayLocation(buttonx, buttony, buttonWidth, buttonHeight);

            buttonx += buttonWidth + 20;
            deleteBlock(buttonx, buttony, buttonWidth, buttonHeight);

            buttonx += buttonWidth + 20;
            deleteBed(buttonx, buttony, buttonWidth, buttonHeight);

            buttonx += buttonWidth + 20;
            deleteCrop(buttonx, buttony, buttonWidth, buttonHeight);

            buttonx += buttonWidth + 20;
            deleteCropOrTrayLocation(buttonx, buttony, buttonWidth, buttonHeight);

            buttonx -= buttonWidth * 3 + 60;
            buttony = concreteView.getHeight() / 3 * 2;
            getHarvestable(buttonx, buttony, buttonWidth, buttonHeight);

            buttonx += buttonWidth + 20;
            getValidBedFeet(buttonx, buttony, buttonWidth, buttonHeight);

            buttonx += buttonWidth + 20;
            getValidBedPlants(buttonx, buttony, buttonWidth, buttonHeight);

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

        JButton addBlockButton = new JButton("Add/Update Block");
        addBlockButton.setBounds(buttonx, buttony, buttonWidth, buttonHeight);
        concreteView.add(addBlockButton);
        addBlockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void deleteBlock(int buttonx, int buttony, int buttonWidth, int buttonHeight) {

        JLabel blockIDLabel = new JLabel("Block ID");
        blockIDLabel.setForeground(Color.white);
        blockIDLabel.setBounds(buttonx, buttony + buttonHeight, buttonWidth, buttonHeight);
        concreteView.add(blockIDLabel);

        JTextField blockID = new JTextField(6);
        blockID.setBounds(buttonx, buttony + buttonHeight * 2, buttonWidth, buttonHeight);
        concreteView.add(blockID);

        JButton deleteBlockButton = new JButton("Delete Block");
        deleteBlockButton.setBounds(buttonx, buttony, buttonWidth, buttonHeight);
        concreteView.add(deleteBlockButton);
        deleteBlockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void insertOrUpdateBed(int buttonx, int buttony, int buttonWidth, int buttonHeight) {
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

        JLabel bedNoteLabel = new JLabel("Bed Notes");
        bedNoteLabel.setForeground(Color.white);
        bedNoteLabel.setBounds(buttonx, buttony + buttonHeight * 3, buttonWidth, buttonHeight);
        concreteView.add(bedNoteLabel);

        JTextField notes = new JTextField(6);
        notes.setBounds(buttonx, buttony + buttonHeight * 4, buttonWidth, buttonHeight);
        concreteView.add(notes);


        JButton insertBedButton = new JButton("Add or Update Bed");
        insertBedButton.setBounds(buttonx, buttony, buttonWidth, buttonHeight);
        concreteView.add(insertBedButton);
        insertBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void deleteBed(int buttonx, int buttony, int buttonWidth, int buttonHeight) {
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


        JButton deleteBedButton = new JButton("Delete Bed");
        deleteBedButton.setBounds(buttonx, buttony, buttonWidth, buttonHeight);
        concreteView.add(deleteBedButton);
        deleteBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void insertOrUpdateCrop(int buttonx, int buttony, int buttonWidth, int buttonHeight) {
        JLabel cropNameLabel = new JLabel("Crop Name");
        cropNameLabel.setForeground(Color.white);
        cropNameLabel.setBounds(buttonx, buttony + buttonHeight, buttonWidth, buttonHeight);
        concreteView.add(cropNameLabel);

        JTextField cropName = new JTextField(6);
        cropName.setBounds(buttonx, buttony + buttonHeight * 2, buttonWidth, buttonHeight);
        concreteView.add(cropName);


        JLabel varietyLabel = new JLabel("Variety");
        varietyLabel.setForeground(Color.white);
        varietyLabel.setBounds(buttonx, buttony + buttonHeight * 3, buttonWidth, buttonHeight);
        concreteView.add(varietyLabel);

        JTextField varietyField = new JTextField(6);
        varietyField.setBounds(buttonx, buttony + buttonHeight * 4, buttonWidth, buttonHeight);
        concreteView.add(varietyField);


        JLabel seedSourceLabel = new JLabel("Seed Source");
        seedSourceLabel.setForeground(Color.white);
        seedSourceLabel.setBounds(buttonx, buttony + buttonHeight * 5, buttonWidth, buttonHeight);
        concreteView.add(seedSourceLabel);

        JTextField seedSource = new JTextField(6);
        seedSource.setBounds(buttonx, buttony + buttonHeight * 6, buttonWidth, buttonHeight);
        concreteView.add(seedSource);


        JLabel numSeedsLabel = new JLabel("Number of Seeds (INT)");
        numSeedsLabel.setForeground(Color.white);
        numSeedsLabel.setBounds(buttonx, buttony + buttonHeight * 7, buttonWidth, buttonHeight);
        concreteView.add(numSeedsLabel);

        JTextField numSeeds = new JTextField(6);
        numSeeds.setBounds(buttonx, buttony + buttonHeight * 8, buttonWidth, buttonHeight);
        concreteView.add(numSeeds);


        JLabel germinationYieldProjLabel = new JLabel("Projected Germination Yield(Double)");
        germinationYieldProjLabel.setForeground(Color.white);
        germinationYieldProjLabel.setBounds(buttonx, buttony + buttonHeight * 9, buttonWidth, buttonHeight);
        concreteView.add(germinationYieldProjLabel);

        JTextField germinationProj = new JTextField(6);
        germinationProj.setBounds(buttonx, buttony + buttonHeight * 10, buttonWidth, buttonHeight);
        concreteView.add(germinationProj);


        JLabel germinationYieldActLabel = new JLabel("Actual Germination Yield(Double)");
        germinationYieldActLabel.setForeground(Color.white);
        germinationYieldActLabel.setBounds(buttonx, buttony + buttonHeight * 11, buttonWidth, buttonHeight);
        concreteView.add(germinationYieldActLabel);

        JTextField germinationAct = new JTextField(6);
        germinationAct.setBounds(buttonx, buttony + buttonHeight * 12, buttonWidth, buttonHeight);
        concreteView.add(germinationAct);


        JLabel feetBetweenPlantsLabel = new JLabel("Feet Between Plants(Double)");
        feetBetweenPlantsLabel.setForeground(Color.white);
        feetBetweenPlantsLabel.setBounds(buttonx, buttony + buttonHeight * 13, buttonWidth, buttonHeight);
        concreteView.add(feetBetweenPlantsLabel);

        JTextField feetBetweenPlants = new JTextField(6);
        feetBetweenPlants.setBounds(buttonx, buttony + buttonHeight * 14, buttonWidth, buttonHeight);
        concreteView.add(feetBetweenPlants);


        JLabel partNumLabel = new JLabel("Part Number");
        partNumLabel.setForeground(Color.white);
        partNumLabel.setBounds(buttonx, buttony + buttonHeight * 15, buttonWidth, buttonHeight);
        concreteView.add(partNumLabel);

        JTextField partNum = new JTextField(6);
        partNum.setBounds(buttonx, buttony + buttonHeight * 16, buttonWidth, buttonHeight);
        concreteView.add(partNum);


        JLabel costLabel = new JLabel("Cost");
        costLabel.setForeground(Color.white);
        costLabel.setBounds(buttonx, buttony + buttonHeight * 17, buttonWidth, buttonHeight);
        concreteView.add(costLabel);

        JTextField cost = new JTextField(6);
        cost.setBounds(buttonx, buttony + buttonHeight * 18, buttonWidth, buttonHeight);
        concreteView.add(cost);


        JLabel qtyLabel = new JLabel("Part Number");
        qtyLabel.setForeground(Color.white);
        qtyLabel.setBounds(buttonx, buttony + buttonHeight * 19, buttonWidth, buttonHeight);
        concreteView.add(qtyLabel);

        JTextField qty = new JTextField(6);
        qty.setBounds(buttonx, buttony + buttonHeight * 20, buttonWidth, buttonHeight);
        concreteView.add(qty);


        JLabel packTypeLabel = new JLabel("Part Number");
        packTypeLabel.setForeground(Color.white);
        packTypeLabel.setBounds(buttonx, buttony + buttonHeight * 21, buttonWidth, buttonHeight);
        concreteView.add(packTypeLabel);

        JTextField packType = new JTextField(6);
        packType.setBounds(buttonx, buttony + buttonHeight * 22, buttonWidth, buttonHeight);
        concreteView.add(packType);

        JLabel cropNoteLabel = new JLabel("crop Notes");
        cropNoteLabel.setForeground(Color.white);
        cropNoteLabel.setBounds(buttonx, buttony + buttonHeight * 23, buttonWidth, buttonHeight);
        concreteView.add(cropNoteLabel);

        JTextField cropNotes = new JTextField(6);
        cropNotes.setBounds(buttonx, buttony + buttonHeight * 24, buttonWidth, buttonHeight);
        concreteView.add(cropNotes);

        JButton insertOrUpdateCropButton = new JButton("Add/Update Crop");
        insertOrUpdateCropButton.setBounds(buttonx, buttony, buttonWidth, buttonHeight);
        concreteView.add(insertOrUpdateCropButton);
        insertOrUpdateCropButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void deleteCrop(int buttonx, int buttony, int buttonWidth, int buttonHeight) {
        JLabel cropNameLabel = new JLabel("Crop Name");
        cropNameLabel.setForeground(Color.white);
        cropNameLabel.setBounds(buttonx, buttony + buttonHeight, buttonWidth, buttonHeight);
        concreteView.add(cropNameLabel);

        JTextField cropName = new JTextField(6);
        cropName.setBounds(buttonx, buttony + buttonHeight * 2, buttonWidth, buttonHeight);
        concreteView.add(cropName);


        JLabel varietyLabel = new JLabel("Variety");
        varietyLabel.setForeground(Color.white);
        varietyLabel.setBounds(buttonx, buttony + buttonHeight * 3, buttonWidth, buttonHeight);
        concreteView.add(varietyLabel);

        JTextField varietyField = new JTextField(6);
        varietyField.setBounds(buttonx, buttony + buttonHeight * 4, buttonWidth, buttonHeight);
        concreteView.add(varietyField);

        JButton deleteCropButton = new JButton("Delete Crop");
        deleteCropButton.setBounds(buttonx, buttony, buttonWidth, buttonHeight);
        concreteView.add(deleteCropButton);
        deleteCropButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void insertOrUpdateCropLocation(int buttonx, int buttony, int buttonWidth, int buttonHeight) {
//        String blockID, String bedID,
//                String cropName, String variety,
//                Integer numPlants, Date projectedHarvest,
//                Date actualHarvest, String notes
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


        JLabel cropNameLabel = new JLabel("Crop Name");
        cropNameLabel.setForeground(Color.white);
        cropNameLabel.setBounds(buttonx, buttony + buttonHeight * 5, buttonWidth, buttonHeight);
        concreteView.add(cropNameLabel);

        JTextField cropName = new JTextField(6);
        cropName.setBounds(buttonx, buttony + buttonHeight * 6, buttonWidth, buttonHeight);
        concreteView.add(cropName);


        JLabel varietyLabel = new JLabel("Variety");
        varietyLabel.setForeground(Color.white);
        varietyLabel.setBounds(buttonx, buttony + buttonHeight * 7, buttonWidth, buttonHeight);
        concreteView.add(varietyLabel);

        JTextField varietyField = new JTextField(6);
        varietyField.setBounds(buttonx, buttony + buttonHeight * 8, buttonWidth, buttonHeight);
        concreteView.add(varietyField);


        JLabel numPlantsLabel = new JLabel("Number of Plants");
        numPlantsLabel.setForeground(Color.white);
        numPlantsLabel.setBounds(buttonx, buttony + buttonHeight * 9, buttonWidth, buttonHeight);
        concreteView.add(numPlantsLabel);

        JTextField numPlants = new JTextField(6);
        numPlants.setBounds(buttonx, buttony + buttonHeight * 10, buttonWidth, buttonHeight);
        concreteView.add(numPlants);


        JLabel projectedHarvestLabel = new JLabel("Projected Harvest");
        projectedHarvestLabel.setForeground(Color.white);
        projectedHarvestLabel.setBounds(buttonx, buttony + buttonHeight * 11, buttonWidth, buttonHeight);
        concreteView.add(projectedHarvestLabel);

        JLabel projectedHarvestDayLabel = new JLabel("DD");
        projectedHarvestDayLabel.setForeground(Color.white);
        projectedHarvestDayLabel.setBounds(buttonx, buttony + buttonHeight * 12, buttonWidth / 3 - 5, buttonHeight);
        concreteView.add(projectedHarvestDayLabel);

        JTextField projectedHarvestDayField = new JTextField(6);
        projectedHarvestDayField.setBounds(buttonx, buttony + buttonHeight * 13, buttonWidth / 3 - 5, buttonHeight);
        concreteView.add(projectedHarvestDayField);

        JLabel projectedHarvestMonthLabel = new JLabel("MM");
        projectedHarvestMonthLabel.setForeground(Color.white);
        projectedHarvestMonthLabel.setBounds(buttonx + buttonWidth / 3, buttony + buttonHeight * 12,
                buttonWidth / 3 - 5, buttonHeight);
        concreteView.add(projectedHarvestMonthLabel);

        JTextField projectedHarvestMonthField = new JTextField(6);
        projectedHarvestMonthField.setBounds(buttonx + buttonWidth / 3, buttony + buttonHeight * 13,
                buttonWidth / 3 - 5, buttonHeight);
        concreteView.add(projectedHarvestMonthField);

        JLabel projectedHarvestYearLabel = new JLabel("YYYY");
        projectedHarvestYearLabel.setForeground(Color.white);
        projectedHarvestYearLabel.setBounds(buttonx + buttonWidth / 3 * 2, buttony + buttonHeight * 12,
                buttonWidth / 2, buttonHeight);
        concreteView.add(projectedHarvestYearLabel);

        JTextField projectedHarvestYearField = new JTextField(6);
        projectedHarvestYearField.setBounds(buttonx + buttonWidth / 3 * 2, buttony + buttonHeight * 13, // TODO FIELD NOT BEING DRAWN FOR UNKNOWN REASON
                buttonWidth / 2, buttonHeight);
        concreteView.add(projectedHarvestYearField);


        JLabel actualHarvestLabel = new JLabel("Actual Harvest");
        actualHarvestLabel.setForeground(Color.white);
        actualHarvestLabel.setBounds(buttonx, buttony + buttonHeight * 14, buttonWidth, buttonHeight);
        concreteView.add(actualHarvestLabel);

        JLabel actualHarvestDayLabel = new JLabel("DD");
        actualHarvestDayLabel.setForeground(Color.white);
        actualHarvestDayLabel.setBounds(buttonx, buttony + buttonHeight * 15, buttonWidth / 3 - 5, buttonHeight);
        concreteView.add(actualHarvestDayLabel);

        JTextField actualHarvestDayField = new JTextField(6);
        actualHarvestDayField.setBounds(buttonx, buttony + buttonHeight * 16, buttonWidth / 3 - 5, buttonHeight);
        concreteView.add( actualHarvestDayField);

        JLabel actualHarvestMonthLabel = new JLabel("MM");
        actualHarvestMonthLabel.setForeground(Color.white);
        actualHarvestMonthLabel.setBounds(buttonx + buttonWidth / 3, buttony + buttonHeight * 15,
                buttonWidth / 3 - 5, buttonHeight);
        concreteView.add( actualHarvestMonthLabel);

        JTextField actualHarvestMonthField = new JTextField(6);
        actualHarvestMonthField.setBounds(buttonx + buttonWidth / 3, buttony + buttonHeight * 16,
                buttonWidth / 3 - 5, buttonHeight);
        concreteView.add(actualHarvestMonthField);

        JLabel actualHarvestYearLabel = new JLabel("YYYY");
        actualHarvestYearLabel.setForeground(Color.white);
        actualHarvestYearLabel.setBounds(buttonx + buttonWidth / 3 * 2, buttony + buttonHeight * 15,
                buttonWidth / 2, buttonHeight);
        concreteView.add(actualHarvestYearLabel);

        JTextField actualHarvestYearField = new JTextField(6);
        projectedHarvestYearField.setBounds(buttonx + buttonWidth / 3 * 2, buttony + buttonHeight * 16,
                buttonWidth / 2, buttonHeight);
        concreteView.add(projectedHarvestYearField);


        JLabel cropLocationNoteLabel = new JLabel("Crop Location Notes");
        cropLocationNoteLabel.setForeground(Color.white);
        cropLocationNoteLabel.setBounds(buttonx, buttony + buttonHeight * 17, buttonWidth, buttonHeight);
        concreteView.add(cropLocationNoteLabel);

        JTextField cropLocationNotes = new JTextField(6);
        cropLocationNotes.setBounds(buttonx, buttony + buttonHeight * 18, buttonWidth, buttonHeight);
        concreteView.add(cropLocationNotes);

        JButton insertOrUpdateCropLocationButton = new JButton("A/U Crop Location");
        insertOrUpdateCropLocationButton.setBounds(buttonx, buttony, buttonWidth, buttonHeight);
        concreteView.add(insertOrUpdateCropLocationButton);
        insertOrUpdateCropLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void deleteCropOrTrayLocation(int buttonx, int buttony, int buttonWidth, int buttonHeight) {
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


        JLabel cropNameLabel = new JLabel("Crop Name");
        cropNameLabel.setForeground(Color.white);
        cropNameLabel.setBounds(buttonx, buttony + buttonHeight * 5, buttonWidth, buttonHeight);
        concreteView.add(cropNameLabel);

        JTextField cropName = new JTextField(6);
        cropName.setBounds(buttonx, buttony + buttonHeight * 6, buttonWidth, buttonHeight);
        concreteView.add(cropName);


        JLabel varietyLabel = new JLabel("Variety");
        varietyLabel.setForeground(Color.white);
        varietyLabel.setBounds(buttonx, buttony + buttonHeight * 7, buttonWidth, buttonHeight);
        concreteView.add(varietyLabel);

        JTextField varietyField = new JTextField(6);
        varietyField.setBounds(buttonx, buttony + buttonHeight * 8, buttonWidth, buttonHeight);
        concreteView.add(varietyField);


        JButton deleteCropLocationButton = new JButton("Delete Crop Location");
        deleteCropLocationButton.setBounds(buttonx, buttony, buttonWidth, buttonHeight);
        concreteView.add(deleteCropLocationButton);
        deleteCropLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void insertOrUpdateTrayLocation(int buttonx, int buttony, int buttonWidth, int buttonHeight) {

//              String blockID, String bedID,
//                String cropName, String variety,
//                Double numTrays, Integer trayType,
//                String soilType, Integer seedsPerCell

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


        JLabel cropNameLabel = new JLabel("Crop Name");
        cropNameLabel.setForeground(Color.white);
        cropNameLabel.setBounds(buttonx, buttony + buttonHeight * 5, buttonWidth, buttonHeight);
        concreteView.add(cropNameLabel);

        JTextField cropName = new JTextField(6);
        cropName.setBounds(buttonx, buttony + buttonHeight * 6, buttonWidth, buttonHeight);
        concreteView.add(cropName);


        JLabel varietyLabel = new JLabel("Variety");
        varietyLabel.setForeground(Color.white);
        varietyLabel.setBounds(buttonx, buttony + buttonHeight * 7, buttonWidth, buttonHeight);
        concreteView.add(varietyLabel);

        JTextField varietyField = new JTextField(6);
        varietyField.setBounds(buttonx, buttony + buttonHeight * 8, buttonWidth, buttonHeight);
        concreteView.add(varietyField);

        JLabel numTraysLabel = new JLabel("Number of Trays (Double)");
        numTraysLabel.setForeground(Color.white);
        numTraysLabel.setBounds(buttonx, buttony + buttonHeight * 9, buttonWidth, buttonHeight);
        concreteView.add(numTraysLabel);

        JTextField numTrays = new JTextField(6);
        numTrays.setBounds(buttonx, buttony + buttonHeight * 10, buttonWidth, buttonHeight);
        concreteView.add(numTrays);

        JLabel trayTypeLabel = new JLabel("Tray Type (Integer)");
        trayTypeLabel.setForeground(Color.white);
        trayTypeLabel.setBounds(buttonx, buttony + buttonHeight * 11, buttonWidth, buttonHeight);
        concreteView.add(trayTypeLabel);

        JTextField trayType = new JTextField(6);
        trayType.setBounds(buttonx, buttony + buttonHeight * 12, buttonWidth, buttonHeight);
        concreteView.add(trayType);


        JLabel soilTypeLabel = new JLabel("Tray Type (Integer)");
        soilTypeLabel.setForeground(Color.white);
        soilTypeLabel.setBounds(buttonx, buttony + buttonHeight * 13, buttonWidth, buttonHeight);
        concreteView.add(soilTypeLabel);

        JTextField soilType = new JTextField(6);
        soilType.setBounds(buttonx, buttony + buttonHeight * 14, buttonWidth, buttonHeight);
        concreteView.add(soilType);


        JLabel SeedsPerCellLabel = new JLabel("Seeds Per Cell (Integer)");
        SeedsPerCellLabel.setForeground(Color.white);
        SeedsPerCellLabel.setBounds(buttonx, buttony + buttonHeight * 15, buttonWidth, buttonHeight);
        concreteView.add(SeedsPerCellLabel);

        JTextField SeedsPerCell = new JTextField(6);
        SeedsPerCell.setBounds(buttonx, buttony + buttonHeight * 16, buttonWidth, buttonHeight);
        concreteView.add(SeedsPerCell);

        JButton insertOrUpdateTrayLocationButton = new JButton("A/U Tray Location");
        insertOrUpdateTrayLocationButton.setBounds(buttonx, buttony, buttonWidth, buttonHeight);
        concreteView.add(insertOrUpdateTrayLocationButton);
        insertOrUpdateTrayLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void getHarvestable(int buttonx, int buttony, int buttonWidth, int buttonHeight) {
        JButton getHarvestableButton = new JButton("Get Harvestable Crops");
        getHarvestableButton.setBounds(buttonx, buttony, buttonWidth, buttonHeight);
        concreteView.add(getHarvestableButton);
        getHarvestableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void getValidBedFeet(int buttonx, int buttony, int buttonWidth, int buttonHeight) {
        JLabel feetLabel = new JLabel("Feet Left");
        feetLabel.setForeground(Color.white);
        feetLabel.setBounds(buttonx, buttony + buttonHeight * 1, buttonWidth, buttonHeight);
        concreteView.add(feetLabel);

        JTextField feet = new JTextField(6);
        feet.setBounds(buttonx, buttony + buttonHeight * 2, buttonWidth, buttonHeight);
        concreteView.add(feet);

        JButton validBedButton = new JButton("Get Valid Bed");
        validBedButton.setBounds(buttonx, buttony, buttonWidth, buttonHeight);
        concreteView.add(validBedButton);
        validBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void getValidBedPlants(int buttonx, int buttony, int buttonWidth, int buttonHeight) {
        JLabel cropNameLabel = new JLabel("Crop Name");
        cropNameLabel.setForeground(Color.white);
        cropNameLabel.setBounds(buttonx, buttony + buttonHeight * 1, buttonWidth, buttonHeight);
        concreteView.add(cropNameLabel);

        JTextField cropName = new JTextField(6);
        cropName.setBounds(buttonx, buttony + buttonHeight * 2, buttonWidth, buttonHeight);
        concreteView.add(cropName);


        JLabel varietyLabel = new JLabel("Variety");
        varietyLabel.setForeground(Color.white);
        varietyLabel.setBounds(buttonx, buttony + buttonHeight * 3, buttonWidth, buttonHeight);
        concreteView.add(varietyLabel);

        JTextField varietyField = new JTextField(6);
        varietyField.setBounds(buttonx, buttony + buttonHeight * 4, buttonWidth, buttonHeight);
        concreteView.add(varietyField);


        JLabel numPlantsLabel = new JLabel("Number Of Plants");
        numPlantsLabel.setForeground(Color.white);
        numPlantsLabel.setBounds(buttonx, buttony + buttonHeight * 5, buttonWidth, buttonHeight);
        concreteView.add(numPlantsLabel);

        JTextField numPlantsField = new JTextField(6);
        numPlantsField.setBounds(buttonx, buttony + buttonHeight * 6, buttonWidth, buttonHeight);
        concreteView.add(numPlantsField);

        JButton insertOrUpdateTrayLocationButton = new JButton("Get Valid Beds");
        insertOrUpdateTrayLocationButton.setBounds(buttonx, buttony, buttonWidth, buttonHeight);
        concreteView.add(insertOrUpdateTrayLocationButton);
        insertOrUpdateTrayLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}
