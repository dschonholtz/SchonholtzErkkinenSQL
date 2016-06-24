import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Douglas Schonholtz on 6/16/2016.
 * This shows the buttons and tables inherent with the view.
 * Included buttons being:
 *  -Get/insert/delete Blocks
 *  -Get/insert/delete Beds
 *  -insert/delete Crops
 *  -insert/delete Crop Locations
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

    /**
     * Default constructor to the view. This builds the model that contains data and the concrete view
     * that paints things individually.
     * Also, sets up the JFrame to close properly
     */
    public View() {
        model = new Model();
        this.concreteView = new ConcreteView(model);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().add(concreteView);
        this.pack();
        buttonsDisplayed = false;
    }

    /**
     * This starts everything. After the object has been initialized this will connect to the database,
     * and start drawing.
     */
    public void run() {
        this.repaint();

        JLabel statusLabel = new JLabel();
        JLabel namelabel = new JLabel("User ID: ", JLabel.RIGHT);
        JLabel passwordLabel = new JLabel("Password: ", JLabel.CENTER);
        final JTextField userText = new JTextField(6);
        final JPasswordField passwordText = new JPasswordField(6);
        JButton loginButton = new JButton("Login");

        concreteView.add(namelabel);
        concreteView.add(userText);
        concreteView.add(passwordLabel);
        concreteView.add(passwordText);
        concreteView.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = userText.getText();
                String pass = new String(passwordText.getPassword());
                try {
                    model.connect(user, pass);
                    statusLabel.setText("");
                    concreteView.remove(namelabel);
                    concreteView.remove(userText);
                    concreteView.remove(passwordLabel);
                    concreteView.remove(passwordText);
                    concreteView.remove(loginButton);
                    model.getBlocks();
                    concreteView.setLoggedIn(true);
                    concreteView.revalidate();
                    concreteView.repaint();
                    showButtonButton();
                } catch (Exception exception) {
                    exception.printStackTrace();
                    statusLabel.setText("Invalid password or username please try again");
                }
            }
        });
        concreteView.add(statusLabel);
        this.setVisible(true);
    }

    /**
     * This initializes the button that allows the user to show or hide buttons.
     * It also sets the layout.
     */
    private void showButtonButton() {
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

    /**
     * This adds or removes the buttons on the screen depending on whether or not they're supposed to be visible.
     */
    private void showButtons() {
        if(buttonsDisplayed) {

            int buttonX = 50;
            int buttonY = concreteView.getHeight() / 3;
            int buttonWidth = 150;
            int buttonHeight = 20;
            insertOrUpdateBlock(buttonX, buttonY, buttonWidth, buttonHeight);

            buttonX += buttonWidth + 20;
            insertOrUpdateBed(buttonX, buttonY, buttonWidth, buttonHeight);

            buttonX += buttonWidth + 20;
            insertOrUpdateCrop(buttonX, buttonY, buttonWidth, buttonHeight);

            buttonX += buttonWidth + 20;
            insertOrUpdateCropLocation(buttonX, buttonY, buttonWidth, buttonHeight);

            buttonX += buttonWidth + 20;
            insertOrUpdateTrayLocation(buttonX, buttonY, buttonWidth, buttonHeight);

            buttonX += buttonWidth + 20;
            deleteBlock(buttonX, buttonY, buttonWidth, buttonHeight);

            buttonX += buttonWidth + 20;
            deleteBed(buttonX, buttonY, buttonWidth, buttonHeight);

            buttonX += buttonWidth + 20;
            deleteCrop(buttonX, buttonY, buttonWidth, buttonHeight);

            buttonX += buttonWidth + 20;
            deleteCropOrTrayLocation(buttonX, buttonY, buttonWidth, buttonHeight);

            buttonX -= buttonWidth * 3 + 60;
            buttonY = concreteView.getHeight() / 2;
            getHarvestable(buttonX, buttonY, buttonWidth, buttonHeight);

            buttonX += buttonWidth + 20;
            getValidBedFeet(buttonX, buttonY, buttonWidth, buttonHeight);

            buttonX += buttonWidth + 20;
            getValidBedPlants(buttonX, buttonY, buttonWidth, buttonHeight);

            buttonX -= buttonWidth * 2 + 40;
            buttonY = concreteView.getHeight() / 4 * 3;
            getBlocks(buttonX, buttonY, buttonWidth, buttonHeight);

            buttonX += buttonWidth + 20;
            getBedsInBlock(buttonX, buttonY, buttonWidth, buttonHeight);

            buttonX += buttonWidth + 20;
            getCropsInBed(buttonX, buttonY, buttonWidth, buttonHeight);

            revalidate();
            repaint();
        }
        else {
            concreteView.removeAll();
            showButtonButton();
            revalidate();
            repaint();
        }

    }

    /**
     * Gets all of the blocks
     * @param buttonX - The x starting location of buttons
     * @param buttonY - The y starting location of buttons
     * @param buttonWidth - The width of any given button
     * @param buttonHeight - The height of any given button
     */
    private void getBlocks(int buttonX, int buttonY, int buttonWidth, int buttonHeight) {
        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.red);
        errorLabel.setBounds(buttonX, buttonY + buttonHeight * 5, buttonWidth, buttonHeight);
        concreteView.add(errorLabel);

        JButton addBlockButton = new JButton("Get Blocks");
        addBlockButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        concreteView.add(addBlockButton);
        addBlockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    model.getBlocks();
                    errorLabel.setText("");
                    repaint();
                    revalidate();
                } catch (SQLException e1) {
                    errorLabel.setText("Invalid field");
                }
            }
        });
    }

    /**
     * Inserts or updates a block depending on whether or not the block with the enclosed fields already exists
     * @param buttonX - The x starting location of buttons
     * @param buttonY - The y starting location of buttons
     * @param buttonWidth - The width of any given button
     * @param buttonHeight - The height of any given button
     */
    private void insertOrUpdateBlock(int buttonX, int buttonY, int buttonWidth, int buttonHeight) {
        JLabel blockIDLabel = new JLabel("Block ID");
        blockIDLabel.setForeground(Color.white);
        blockIDLabel.setBounds(buttonX, buttonY + buttonHeight, buttonWidth, buttonHeight);
        concreteView.add(blockIDLabel);

        JTextField blockID = new JTextField(6);
        blockID.setBounds(buttonX, buttonY + buttonHeight * 2, buttonWidth, buttonHeight);
        concreteView.add(blockID);

        JLabel blockNoteLabel = new JLabel("Block Notes");
        blockNoteLabel.setForeground(Color.white);
        blockNoteLabel.setBounds(buttonX, buttonY + buttonHeight * 3, buttonWidth, buttonHeight);
        concreteView.add(blockNoteLabel);

        JTextField notes = new JTextField(6);
        notes.setBounds(buttonX, buttonY + buttonHeight * 4, buttonWidth, buttonHeight);
        concreteView.add(notes);

        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.red);
        errorLabel.setBounds(buttonX, buttonY + buttonHeight * 5, buttonWidth, buttonHeight);
        concreteView.add(errorLabel);

        JButton addBlockButton = new JButton("Add/Update Block");
        addBlockButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        concreteView.add(addBlockButton);
        addBlockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Block block = new Block(blockID.getText());
                    block.setNotes(notes.getText());
                    errorLabel.setText("");
                    model.insertOrUpdateBlock(block);
                    repaint();
                    revalidate();
                } catch(Exception se) {
                    errorLabel.setText("Invalid Field");
                }
            }
        });
    }

    /**
     * Removes a block with the primary key gotten from the enclosed fields
     * @param buttonX - The x starting location of buttons
     * @param buttonY - The y starting location of buttons
     * @param buttonWidth - The width of any given button
     * @param buttonHeight - The height of any given button
     */
    private void deleteBlock(int buttonX, int buttonY, int buttonWidth, int buttonHeight) {

        JLabel blockIDLabel = new JLabel("Block ID");
        blockIDLabel.setForeground(Color.white);
        blockIDLabel.setBounds(buttonX, buttonY + buttonHeight, buttonWidth, buttonHeight);
        concreteView.add(blockIDLabel);

        JTextField blockID = new JTextField(6);
        blockID.setBounds(buttonX, buttonY + buttonHeight * 2, buttonWidth, buttonHeight);
        concreteView.add(blockID);

        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.red);
        errorLabel.setBounds(buttonX, buttonY + buttonHeight * 3, buttonWidth, buttonHeight);
        concreteView.add(errorLabel);

        JButton deleteBlockButton = new JButton("Delete Block");
        deleteBlockButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        concreteView.add(deleteBlockButton);
        deleteBlockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Block block = new Block(blockID.getText());
                    errorLabel.setText("");
                    model.deleteBlock(block);
                    repaint();
                    revalidate();
                } catch(Exception se) {
                    errorLabel.setText("Invalid Field");
                }
            }
        });
    }

    /**
     * Gets all of the beds in the block that has the provided blockid
     * @param buttonX - The x starting location of buttons
     * @param buttonY - The y starting location of buttons
     * @param buttonWidth - The width of any given button
     * @param buttonHeight - The height of any given button
     */
    private void getBedsInBlock(int buttonX, int buttonY, int buttonWidth, int buttonHeight) {

        JLabel blockIDLabel = new JLabel("Block ID");
        blockIDLabel.setForeground(Color.white);
        blockIDLabel.setBounds(buttonX, buttonY + buttonHeight, buttonWidth, buttonHeight);
        concreteView.add(blockIDLabel);

        JTextField blockID = new JTextField(6);
        blockID.setBounds(buttonX, buttonY + buttonHeight * 2, buttonWidth, buttonHeight);
        concreteView.add(blockID);

        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.red);
        errorLabel.setBounds(buttonX, buttonY + buttonHeight * 3, buttonWidth, buttonHeight);
        concreteView.add(errorLabel);

        JButton deleteBlockButton = new JButton("Get Beds in Block");
        deleteBlockButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        concreteView.add(deleteBlockButton);
        deleteBlockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Block block = new Block(blockID.getText());
                    model.getBedsInBlock(block);
                    errorLabel.setText("");
                    repaint();
                    revalidate();
                } catch (Exception e1) {
                    errorLabel.setText("Invalid Field");
                }
            }
        });
    }

    /**
     * Inserts or updates a bed depending on if the bed with the provided primary key already exists or not
     * @param buttonX - The x starting location of buttons
     * @param buttonY - The y starting location of buttons
     * @param buttonWidth - The width of any given button
     * @param buttonHeight - The height of any given button
     */
    private void insertOrUpdateBed(int buttonX, int buttonY, int buttonWidth, int buttonHeight) {
        JLabel blockIDLabel = new JLabel("Block ID");
        blockIDLabel.setForeground(Color.white);
        blockIDLabel.setBounds(buttonX, buttonY + buttonHeight, buttonWidth, buttonHeight);
        concreteView.add(blockIDLabel);

        JTextField blockID = new JTextField(6);
        blockID.setBounds(buttonX, buttonY + buttonHeight * 2, buttonWidth, buttonHeight);
        concreteView.add(blockID);

        JLabel bedIDLabel = new JLabel("Bed ID");
        bedIDLabel.setForeground(Color.white);
        bedIDLabel.setBounds(buttonX, buttonY + buttonHeight * 3, buttonWidth, buttonHeight);
        concreteView.add( bedIDLabel);

        JTextField bedID = new JTextField(6);
        bedID.setBounds(buttonX, buttonY + buttonHeight * 4, buttonWidth, buttonHeight);
        concreteView.add(bedID);

        JLabel bedNoteLabel = new JLabel("Bed Notes");
        bedNoteLabel.setForeground(Color.white);
        bedNoteLabel.setBounds(buttonX, buttonY + buttonHeight * 5, buttonWidth, buttonHeight);
        concreteView.add(bedNoteLabel);

        JTextField notes = new JTextField(6);
        notes.setBounds(buttonX, buttonY + buttonHeight * 6, buttonWidth, buttonHeight);
        concreteView.add(notes);

        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.red);
        errorLabel.setBounds(buttonX, buttonY + buttonHeight * 7, buttonWidth, buttonHeight);
        concreteView.add(errorLabel);

        JButton insertBedButton = new JButton("Add or Update Bed");
        insertBedButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        concreteView.add(insertBedButton);
        insertBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Bed bed = new Bed(blockID.getText(), bedID.getText());
                    bed.setNotes(notes.getText());
                    model.insertOrUpdateBed(bed);
                    errorLabel.setText("");
                    repaint();
                    revalidate();
                } catch (Exception e1) {
                    errorLabel.setText("Invalid Field");
                }
            }
        });
    }

    /**
     * Deletes a bed with the given primary key
     * @param buttonX - The x starting location of buttons
     * @param buttonY - The y starting location of buttons
     * @param buttonWidth - The width of any given button
     * @param buttonHeight - The height of any given button
     */
    private void deleteBed(int buttonX, int buttonY, int buttonWidth, int buttonHeight) {
        JLabel blockIDLabel = new JLabel("Block ID");
        blockIDLabel.setForeground(Color.white);
        blockIDLabel.setBounds(buttonX, buttonY + buttonHeight, buttonWidth, buttonHeight);
        concreteView.add(blockIDLabel);

        JTextField blockID = new JTextField(6);
        blockID.setBounds(buttonX, buttonY + buttonHeight * 2, buttonWidth, buttonHeight);
        concreteView.add(blockID);

        JLabel bedIDLabel = new JLabel("Bed ID");
        bedIDLabel.setForeground(Color.white);
        bedIDLabel.setBounds(buttonX, buttonY + buttonHeight * 3, buttonWidth, buttonHeight);
        concreteView.add( bedIDLabel);

        JTextField bedID = new JTextField(6);
        bedID.setBounds(buttonX, buttonY + buttonHeight * 4, buttonWidth, buttonHeight);
        concreteView.add(bedID);

        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.red);
        errorLabel.setBounds(buttonX, buttonY + buttonHeight * 5, buttonWidth, buttonHeight);
        concreteView.add(errorLabel);

        JButton deleteBedButton = new JButton("Delete Bed");
        deleteBedButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        concreteView.add(deleteBedButton);
        deleteBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Bed bed = new Bed(blockID.getText(), bedID.getText());
                    model.deleteBed(bed);
                    errorLabel.setText("");
                    repaint();
                    revalidate();
                } catch (Exception elephant) {
                    errorLabel.setText("Invalid Field");
                }
            }
        });
    }

    /**
     * Gets all of the crops in the bed with the given primary key
     * @param buttonX - The x starting location of buttons
     * @param buttonY - The y starting location of buttons
     * @param buttonWidth - The width of any given button
     * @param buttonHeight - The height of any given button
     */
    private void getCropsInBed(int buttonX, int buttonY, int buttonWidth, int buttonHeight) {
        JLabel blockIDLabel = new JLabel("Block ID");
        blockIDLabel.setForeground(Color.white);
        blockIDLabel.setBounds(buttonX, buttonY + buttonHeight, buttonWidth, buttonHeight);
        concreteView.add(blockIDLabel);

        JTextField blockID = new JTextField(6);
        blockID.setBounds(buttonX, buttonY + buttonHeight * 2, buttonWidth, buttonHeight);
        concreteView.add(blockID);

        JLabel bedIDLabel = new JLabel("Bed ID");
        bedIDLabel.setForeground(Color.white);
        bedIDLabel.setBounds(buttonX, buttonY + buttonHeight * 3, buttonWidth, buttonHeight);
        concreteView.add( bedIDLabel);

        JTextField bedID = new JTextField(6);
        bedID.setBounds(buttonX, buttonY + buttonHeight * 4, buttonWidth, buttonHeight);
        concreteView.add(bedID);

        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.red);
        errorLabel.setBounds(buttonX, buttonY + buttonHeight * 5, buttonWidth, buttonHeight);
        concreteView.add(errorLabel);

        JButton deleteBedButton = new JButton("Get Crops In Bed");
        deleteBedButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        concreteView.add(deleteBedButton);
        deleteBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Bed bed = new Bed(blockID.getText(), bedID.getText());
                    model.getCropsInBed(bed);
                    errorLabel.setText("");
                    repaint();
                    revalidate();
                } catch (Exception e1) {
                    errorLabel.setText("Invalid Field");
                }
            }
        });
    }

    /**
     * Inserts or updates a crop depending on whether or not the provided fields already match a primary key in
     * the database.
     * @param buttonX - The x starting location of buttons
     * @param buttonY - The y starting location of buttons
     * @param buttonWidth - The width of any given button
     * @param buttonHeight - The height of any given button
     */
    private void insertOrUpdateCrop(int buttonX, int buttonY, int buttonWidth, int buttonHeight) {
        JLabel cropNameLabel = new JLabel("Crop Name");
        cropNameLabel.setForeground(Color.white);
        cropNameLabel.setBounds(buttonX, buttonY + buttonHeight, buttonWidth, buttonHeight);
        concreteView.add(cropNameLabel);

        JTextField cropName = new JTextField(6);
        cropName.setBounds(buttonX, buttonY + buttonHeight * 2, buttonWidth, buttonHeight);
        concreteView.add(cropName);


        JLabel varietyLabel = new JLabel("Variety");
        varietyLabel.setForeground(Color.white);
        varietyLabel.setBounds(buttonX, buttonY + buttonHeight * 3, buttonWidth, buttonHeight);
        concreteView.add(varietyLabel);

        JTextField varietyField = new JTextField(6);
        varietyField.setBounds(buttonX, buttonY + buttonHeight * 4, buttonWidth, buttonHeight);
        concreteView.add(varietyField);


        JLabel seedSourceLabel = new JLabel("Seed Source");
        seedSourceLabel.setForeground(Color.white);
        seedSourceLabel.setBounds(buttonX, buttonY + buttonHeight * 5, buttonWidth, buttonHeight);
        concreteView.add(seedSourceLabel);

        JTextField seedSource = new JTextField(6);
        seedSource.setBounds(buttonX, buttonY + buttonHeight * 6, buttonWidth, buttonHeight);
        concreteView.add(seedSource);


        JLabel numSeedsLabel = new JLabel("Number of Seeds (INT)");
        numSeedsLabel.setForeground(Color.white);
        numSeedsLabel.setBounds(buttonX, buttonY + buttonHeight * 7, buttonWidth, buttonHeight);
        concreteView.add(numSeedsLabel);

        JTextField numSeeds = new JTextField(6);
        numSeeds.setBounds(buttonX, buttonY + buttonHeight * 8, buttonWidth, buttonHeight);
        concreteView.add(numSeeds);


        JLabel germinationYieldProjLabel = new JLabel("Proj. Germination Yield(Decimal)");
        germinationYieldProjLabel.setForeground(Color.white);
        germinationYieldProjLabel.setBounds(buttonX, buttonY + buttonHeight * 9, buttonWidth, buttonHeight);
        concreteView.add(germinationYieldProjLabel);

        JTextField germinationProj = new JTextField(6);
        germinationProj.setBounds(buttonX, buttonY + buttonHeight * 10, buttonWidth, buttonHeight);
        concreteView.add(germinationProj);


        JLabel germinationYieldActLabel = new JLabel("Act. Germination Yield(Decimal)");
        germinationYieldActLabel.setForeground(Color.white);
        germinationYieldActLabel.setBounds(buttonX, buttonY + buttonHeight * 11, buttonWidth, buttonHeight);
        concreteView.add(germinationYieldActLabel);

        JTextField germinationAct = new JTextField(6);
        germinationAct.setBounds(buttonX, buttonY + buttonHeight * 12, buttonWidth, buttonHeight);
        concreteView.add(germinationAct);


        JLabel feetBetweenPlantsLabel = new JLabel("Feet Between Plants(Double)");
        feetBetweenPlantsLabel.setForeground(Color.white);
        feetBetweenPlantsLabel.setBounds(buttonX, buttonY + buttonHeight * 13, buttonWidth, buttonHeight);
        concreteView.add(feetBetweenPlantsLabel);

        JTextField feetBetweenPlants = new JTextField(6);
        feetBetweenPlants.setBounds(buttonX, buttonY + buttonHeight * 14, buttonWidth, buttonHeight);
        concreteView.add(feetBetweenPlants);


        JLabel partNumLabel = new JLabel("Part Number");
        partNumLabel.setForeground(Color.white);
        partNumLabel.setBounds(buttonX, buttonY + buttonHeight * 15, buttonWidth, buttonHeight);
        concreteView.add(partNumLabel);

        JTextField partNum = new JTextField(6);
        partNum.setBounds(buttonX, buttonY + buttonHeight * 16, buttonWidth, buttonHeight);
        concreteView.add(partNum);


        JLabel costLabel = new JLabel("Cost(DOUBLE)");
        costLabel.setForeground(Color.white);
        costLabel.setBounds(buttonX, buttonY + buttonHeight * 17, buttonWidth, buttonHeight);
        concreteView.add(costLabel);

        JTextField cost = new JTextField(6);
        cost.setBounds(buttonX, buttonY + buttonHeight * 18, buttonWidth, buttonHeight);
        concreteView.add(cost);


        JLabel qtyLabel = new JLabel("Quantity integer");
        qtyLabel.setForeground(Color.white);
        qtyLabel.setBounds(buttonX, buttonY + buttonHeight * 19, buttonWidth, buttonHeight);
        concreteView.add(qtyLabel);

        JTextField qty = new JTextField(6);
        qty.setBounds(buttonX, buttonY + buttonHeight * 20, buttonWidth, buttonHeight);
        concreteView.add(qty);


        JLabel packTypeLabel = new JLabel("Pack Type");
        packTypeLabel.setForeground(Color.white);
        packTypeLabel.setBounds(buttonX, buttonY + buttonHeight * 21, buttonWidth, buttonHeight);
        concreteView.add(packTypeLabel);

        JTextField packType = new JTextField(6);
        packType.setBounds(buttonX, buttonY + buttonHeight * 22, buttonWidth, buttonHeight);
        concreteView.add(packType);

        JLabel cropNoteLabel = new JLabel("Crop Notes");
        cropNoteLabel.setForeground(Color.white);
        cropNoteLabel.setBounds(buttonX, buttonY + buttonHeight * 23, buttonWidth, buttonHeight);
        concreteView.add(cropNoteLabel);

        JTextField cropNotes = new JTextField(6);
        cropNotes.setBounds(buttonX, buttonY + buttonHeight * 24, buttonWidth, buttonHeight);
        concreteView.add(cropNotes);

        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.red);
        errorLabel.setBounds(buttonX, buttonY + buttonHeight * 25, buttonWidth, buttonHeight);
        concreteView.add(errorLabel);

        JButton insertOrUpdateCropButton = new JButton("Add/Update Crop");
        insertOrUpdateCropButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        concreteView.add(insertOrUpdateCropButton);
        insertOrUpdateCropButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Crop crop = new Crop(cropName.getText(), varietyField.getText(), seedSource.getText(),
                            numSeeds.getText(), germinationProj.getText(), germinationAct.getText(),
                            feetBetweenPlants.getText(), partNum.getText(), cost.getText(), qty.getText(),
                            packType.getText(), cropNotes.getText());
                    model.insertOrUpdateCrop(crop);
                    errorLabel.setText("");
                    repaint();
                    revalidate();
                } catch (Exception e1) {
                    e1.printStackTrace();
                    errorLabel.setText("Invalid field");
                }
            }
        });
    }

    /**
     * Deletes a crop of the given key
     * @param buttonX - The x starting location of buttons
     * @param buttonY - The y starting location of buttons
     * @param buttonWidth - The width of any given button
     * @param buttonHeight - The height of any given button
     */
    private void deleteCrop(int buttonX, int buttonY, int buttonWidth, int buttonHeight) {
        JLabel cropNameLabel = new JLabel("Crop Name");
        cropNameLabel.setForeground(Color.white);
        cropNameLabel.setBounds(buttonX, buttonY + buttonHeight, buttonWidth, buttonHeight);
        concreteView.add(cropNameLabel);

        JTextField cropName = new JTextField(6);
        cropName.setBounds(buttonX, buttonY + buttonHeight * 2, buttonWidth, buttonHeight);
        concreteView.add(cropName);


        JLabel varietyLabel = new JLabel("Variety");
        varietyLabel.setForeground(Color.white);
        varietyLabel.setBounds(buttonX, buttonY + buttonHeight * 3, buttonWidth, buttonHeight);
        concreteView.add(varietyLabel);

        JTextField varietyField = new JTextField(6);
        varietyField.setBounds(buttonX, buttonY + buttonHeight * 4, buttonWidth, buttonHeight);
        concreteView.add(varietyField);

        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.red);
        errorLabel.setBounds(buttonX, buttonY + buttonHeight * 5, buttonWidth, buttonHeight);
        concreteView.add(errorLabel);

        JButton deleteCropButton = new JButton("Delete Crop");
        deleteCropButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        concreteView.add(deleteCropButton);
        deleteCropButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Crop crop = new Crop(cropName.getText(), varietyField.getText());
                    model.deleteCrop(crop);
                    errorLabel.setText("");
                    repaint();
                    revalidate();
                } catch (Exception e1) {
                    errorLabel.setText("Invalid Field");
                }
            }
        });
    }

    /**
     * Inserts or updates a crop location depending on whether or not a crop with the given primary key already exists
     * @param buttonX - The x starting location of buttons
     * @param buttonY - The y starting location of buttons
     * @param buttonWidth - The width of any given button
     * @param buttonHeight - The height of any given button
     */
    private void insertOrUpdateCropLocation(int buttonX, int buttonY, int buttonWidth, int buttonHeight) {
        JLabel blockIDLabel = new JLabel("Block ID");
        blockIDLabel.setForeground(Color.white);
        blockIDLabel.setBounds(buttonX, buttonY + buttonHeight, buttonWidth, buttonHeight);
        concreteView.add(blockIDLabel);

        JTextField blockID = new JTextField(6);
        blockID.setBounds(buttonX, buttonY + buttonHeight * 2, buttonWidth, buttonHeight);
        concreteView.add(blockID);

        JLabel bedIDLabel = new JLabel("Bed ID");
        bedIDLabel.setForeground(Color.white);
        bedIDLabel.setBounds(buttonX, buttonY + buttonHeight * 3, buttonWidth, buttonHeight);
        concreteView.add( bedIDLabel);

        JTextField bedID = new JTextField(6);
        bedID.setBounds(buttonX, buttonY + buttonHeight * 4, buttonWidth, buttonHeight);
        concreteView.add(bedID);


        JLabel cropNameLabel = new JLabel("Crop Name");
        cropNameLabel.setForeground(Color.white);
        cropNameLabel.setBounds(buttonX, buttonY + buttonHeight * 5, buttonWidth, buttonHeight);
        concreteView.add(cropNameLabel);

        JTextField cropName = new JTextField(6);
        cropName.setBounds(buttonX, buttonY + buttonHeight * 6, buttonWidth, buttonHeight);
        concreteView.add(cropName);


        JLabel varietyLabel = new JLabel("Variety");
        varietyLabel.setForeground(Color.white);
        varietyLabel.setBounds(buttonX, buttonY + buttonHeight * 7, buttonWidth, buttonHeight);
        concreteView.add(varietyLabel);

        JTextField varietyField = new JTextField(6);
        varietyField.setBounds(buttonX, buttonY + buttonHeight * 8, buttonWidth, buttonHeight);
        concreteView.add(varietyField);


        JLabel numPlantsLabel = new JLabel("Number of Plants");
        numPlantsLabel.setForeground(Color.white);
        numPlantsLabel.setBounds(buttonX, buttonY + buttonHeight * 9, buttonWidth, buttonHeight);
        concreteView.add(numPlantsLabel);

        JTextField numPlants = new JTextField(6);
        numPlants.setBounds(buttonX, buttonY + buttonHeight * 10, buttonWidth, buttonHeight);
        concreteView.add(numPlants);


        JLabel projectedHarvestLabel = new JLabel("Projected Harvest");
        projectedHarvestLabel.setForeground(Color.white);
        projectedHarvestLabel.setBounds(buttonX, buttonY + buttonHeight * 11, buttonWidth, buttonHeight);
        concreteView.add(projectedHarvestLabel);

        JLabel projectedHarvestDayLabel = new JLabel("DD");
        projectedHarvestDayLabel.setForeground(Color.white);
        projectedHarvestDayLabel.setBounds(buttonX, buttonY + buttonHeight * 12, buttonWidth / 3 - 5, buttonHeight);
        concreteView.add(projectedHarvestDayLabel);

        JTextField projectedHarvestDayField = new JTextField(6);
        projectedHarvestDayField.setBounds(buttonX, buttonY + buttonHeight * 13, buttonWidth / 3 - 5, buttonHeight);
        concreteView.add(projectedHarvestDayField);

        JLabel projectedHarvestMonthLabel = new JLabel("MM");
        projectedHarvestMonthLabel.setForeground(Color.white);
        projectedHarvestMonthLabel.setBounds(buttonX + buttonWidth / 3, buttonY + buttonHeight * 12,
                buttonWidth / 3 - 5, buttonHeight);
        concreteView.add(projectedHarvestMonthLabel);

        JTextField projectedHarvestMonthField = new JTextField(6);
        projectedHarvestMonthField.setBounds(buttonX + buttonWidth / 3, buttonY + buttonHeight * 13,
                buttonWidth / 3 - 5, buttonHeight);
        concreteView.add(projectedHarvestMonthField);

        JLabel projectedHarvestYearLabel = new JLabel("YYYY");
        projectedHarvestYearLabel.setForeground(Color.white);
        projectedHarvestYearLabel.setBounds(buttonX + buttonWidth / 3 * 2, buttonY + buttonHeight * 12,
                buttonWidth / 2, buttonHeight);
        concreteView.add(projectedHarvestYearLabel);

        JTextField projectedHarvestYearField = new JTextField(6);
        projectedHarvestYearField.setBounds(buttonX + buttonWidth / 3 * 2, buttonY + buttonHeight * 13,
                buttonWidth / 2 - 20, buttonHeight);
        concreteView.add(projectedHarvestYearField);


        JLabel actualHarvestLabel = new JLabel("Actual Harvest");
        actualHarvestLabel.setForeground(Color.white);
        actualHarvestLabel.setBounds(buttonX, buttonY + buttonHeight * 14, buttonWidth, buttonHeight);
        concreteView.add(actualHarvestLabel);

        JLabel actualHarvestDayLabel = new JLabel("DD");
        actualHarvestDayLabel.setForeground(Color.white);
        actualHarvestDayLabel.setBounds(buttonX, buttonY + buttonHeight * 15, buttonWidth / 3 - 5, buttonHeight);
        concreteView.add(actualHarvestDayLabel);

        JTextField actualHarvestDayField = new JTextField(6);
        actualHarvestDayField.setBounds(buttonX, buttonY + buttonHeight * 16, buttonWidth / 3 - 5, buttonHeight);
        concreteView.add( actualHarvestDayField);

        JLabel actualHarvestMonthLabel = new JLabel("MM");
        actualHarvestMonthLabel.setForeground(Color.white);
        actualHarvestMonthLabel.setBounds(buttonX + buttonWidth / 3, buttonY + buttonHeight * 15,
                buttonWidth / 3 - 5, buttonHeight);
        concreteView.add( actualHarvestMonthLabel);

        JTextField actualHarvestMonthField = new JTextField(6);
        actualHarvestMonthField.setBounds(buttonX + buttonWidth / 3, buttonY + buttonHeight * 16,
                buttonWidth / 3 - 5, buttonHeight);
        concreteView.add(actualHarvestMonthField);

        JLabel actualHarvestYearLabel = new JLabel("YYYY");
        actualHarvestYearLabel.setForeground(Color.white);
        actualHarvestYearLabel.setBounds(buttonX + buttonWidth / 3 * 2, buttonY + buttonHeight * 15,
                buttonWidth / 2, buttonHeight);
        concreteView.add(actualHarvestYearLabel);

        JTextField actualHarvestYearField = new JTextField(6);
        actualHarvestYearField.setBounds(buttonX + buttonWidth / 3 * 2, buttonY + buttonHeight * 16,
                buttonWidth / 2 - 20, buttonHeight);
        concreteView.add(actualHarvestYearField);


        JLabel cropLocationNoteLabel = new JLabel("Crop Location Notes");
        cropLocationNoteLabel.setForeground(Color.white);
        cropLocationNoteLabel.setBounds(buttonX, buttonY + buttonHeight * 17, buttonWidth, buttonHeight);
        concreteView.add(cropLocationNoteLabel);

        JTextField cropLocationNotes = new JTextField(6);
        cropLocationNotes.setBounds(buttonX, buttonY + buttonHeight * 18, buttonWidth, buttonHeight);
        concreteView.add(cropLocationNotes);

        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.red);
        errorLabel.setBounds(buttonX, buttonY + buttonHeight * 19, buttonWidth, buttonHeight);
        concreteView.add(errorLabel);

        JButton insertOrUpdateCropLocationButton = new JButton("A/U Crop Location");
        insertOrUpdateCropLocationButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        concreteView.add(insertOrUpdateCropLocationButton);
        insertOrUpdateCropLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CropLocation cropLocation = new CropLocation(blockID.getText(), bedID.getText(),
                            cropName.getText(), varietyField.getText(), numPlants.getText(),
                            projectedHarvestYearField.getText(), projectedHarvestMonthField.getText(),
                            projectedHarvestDayField.getText(), actualHarvestYearField.getText(),
                            actualHarvestMonthField.getText(), actualHarvestDayField.getText(),
                            cropLocationNotes.getText());

                    model.insertOrUpdateCropLocation(cropLocation);
                    repaint();
                    revalidate();
                    errorLabel.setText("");
                } catch (Exception e1) {
                    errorLabel.setText("Invalid Field");
                }
            }
        });
    }

    /**
     * Removes a crop or tray location with the provided primary key
     * @param buttonX - The x starting location of buttons
     * @param buttonY - The y starting location of buttons
     * @param buttonWidth - The width of any given button
     * @param buttonHeight - The height of any given button
     */
    private void deleteCropOrTrayLocation(int buttonX, int buttonY, int buttonWidth, int buttonHeight) {
        JLabel blockIDLabel = new JLabel("Block ID");
        blockIDLabel.setForeground(Color.white);
        blockIDLabel.setBounds(buttonX, buttonY + buttonHeight, buttonWidth, buttonHeight);
        concreteView.add(blockIDLabel);

        JTextField blockID = new JTextField(6);
        blockID.setBounds(buttonX, buttonY + buttonHeight * 2, buttonWidth, buttonHeight);
        concreteView.add(blockID);

        JLabel bedIDLabel = new JLabel("Bed ID");
        bedIDLabel.setForeground(Color.white);
        bedIDLabel.setBounds(buttonX, buttonY + buttonHeight * 3, buttonWidth, buttonHeight);
        concreteView.add( bedIDLabel);

        JTextField bedID = new JTextField(6);
        bedID.setBounds(buttonX, buttonY + buttonHeight * 4, buttonWidth, buttonHeight);
        concreteView.add(bedID);


        JLabel cropNameLabel = new JLabel("Crop Name");
        cropNameLabel.setForeground(Color.white);
        cropNameLabel.setBounds(buttonX, buttonY + buttonHeight * 5, buttonWidth, buttonHeight);
        concreteView.add(cropNameLabel);

        JTextField cropName = new JTextField(6);
        cropName.setBounds(buttonX, buttonY + buttonHeight * 6, buttonWidth, buttonHeight);
        concreteView.add(cropName);


        JLabel varietyLabel = new JLabel("Variety");
        varietyLabel.setForeground(Color.white);
        varietyLabel.setBounds(buttonX, buttonY + buttonHeight * 7, buttonWidth, buttonHeight);
        concreteView.add(varietyLabel);

        JTextField varietyField = new JTextField(6);
        varietyField.setBounds(buttonX, buttonY + buttonHeight * 8, buttonWidth, buttonHeight);
        concreteView.add(varietyField);


        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.red);
        errorLabel.setBounds(buttonX, buttonY + buttonHeight * 9, buttonWidth, buttonHeight);
        concreteView.add(errorLabel);


        JButton deleteCropLocationButton = new JButton("Delete Crop Location");
        deleteCropLocationButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        concreteView.add(deleteCropLocationButton);
        deleteCropLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CropLocation cropLocation = new CropLocation(blockID.getText(), bedID.getText(),
                            cropName.getText(), varietyField.getText());
                    model.deleteCropLocationTrayLocation(cropLocation);
                    errorLabel.setText("");
                } catch (Exception e1) {
                    errorLabel.setText("Invalid Field");
                }
                repaint();
                revalidate();
            }
        });
    }

    /**
     * inserts or Updates a tray location depending on whether or not a crop or tray location exists with the
     * given primary key
     * @param buttonX - The x starting location of buttons
     * @param buttonY - The y starting location of buttons
     * @param buttonWidth - The width of any given button
     * @param buttonHeight - The height of any given button
     */
    private void insertOrUpdateTrayLocation(int buttonX, int buttonY, int buttonWidth, int buttonHeight) {

        JLabel blockIDLabel = new JLabel("Block ID");
        blockIDLabel.setForeground(Color.white);
        blockIDLabel.setBounds(buttonX, buttonY + buttonHeight, buttonWidth, buttonHeight);
        concreteView.add(blockIDLabel);

        JTextField blockID = new JTextField(6);
        blockID.setBounds(buttonX, buttonY + buttonHeight * 2, buttonWidth, buttonHeight);
        concreteView.add(blockID);

        JLabel bedIDLabel = new JLabel("Bed ID");
        bedIDLabel.setForeground(Color.white);
        bedIDLabel.setBounds(buttonX, buttonY + buttonHeight * 3, buttonWidth, buttonHeight);
        concreteView.add( bedIDLabel);

        JTextField bedID = new JTextField(6);
        bedID.setBounds(buttonX, buttonY + buttonHeight * 4, buttonWidth, buttonHeight);
        concreteView.add(bedID);


        JLabel cropNameLabel = new JLabel("Crop Name");
        cropNameLabel.setForeground(Color.white);
        cropNameLabel.setBounds(buttonX, buttonY + buttonHeight * 5, buttonWidth, buttonHeight);
        concreteView.add(cropNameLabel);

        JTextField cropName = new JTextField(6);
        cropName.setBounds(buttonX, buttonY + buttonHeight * 6, buttonWidth, buttonHeight);
        concreteView.add(cropName);


        JLabel varietyLabel = new JLabel("Variety");
        varietyLabel.setForeground(Color.white);
        varietyLabel.setBounds(buttonX, buttonY + buttonHeight * 7, buttonWidth, buttonHeight);
        concreteView.add(varietyLabel);

        JTextField varietyField = new JTextField(6);
        varietyField.setBounds(buttonX, buttonY + buttonHeight * 8, buttonWidth, buttonHeight);
        concreteView.add(varietyField);

        JLabel numTraysLabel = new JLabel("Number of Trays (Decimal)");
        numTraysLabel.setForeground(Color.white);
        numTraysLabel.setBounds(buttonX, buttonY + buttonHeight * 9, buttonWidth, buttonHeight);
        concreteView.add(numTraysLabel);

        JTextField numTrays = new JTextField(6);
        numTrays.setBounds(buttonX, buttonY + buttonHeight * 10, buttonWidth, buttonHeight);
        concreteView.add(numTrays);

        JLabel trayTypeLabel = new JLabel("Tray Type (Integer)");
        trayTypeLabel.setForeground(Color.white);
        trayTypeLabel.setBounds(buttonX, buttonY + buttonHeight * 11, buttonWidth, buttonHeight);
        concreteView.add(trayTypeLabel);

        JTextField trayType = new JTextField(6);
        trayType.setBounds(buttonX, buttonY + buttonHeight * 12, buttonWidth, buttonHeight);
        concreteView.add(trayType);


        JLabel soilTypeLabel = new JLabel("Soil Type (Integer)");
        soilTypeLabel.setForeground(Color.white);
        soilTypeLabel.setBounds(buttonX, buttonY + buttonHeight * 13, buttonWidth, buttonHeight);
        concreteView.add(soilTypeLabel);

        JTextField soilType = new JTextField(6);
        soilType.setBounds(buttonX, buttonY + buttonHeight * 14, buttonWidth, buttonHeight);
        concreteView.add(soilType);


        JLabel seedsPerCellLabel = new JLabel("Seeds Per Cell (Integer)");
        seedsPerCellLabel.setForeground(Color.white);
        seedsPerCellLabel.setBounds(buttonX, buttonY + buttonHeight * 15, buttonWidth, buttonHeight);
        concreteView.add(seedsPerCellLabel);

        JTextField seedsPerCell = new JTextField(6);
        seedsPerCell.setBounds(buttonX, buttonY + buttonHeight * 16, buttonWidth, buttonHeight);
        concreteView.add(seedsPerCell);


        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.red);
        errorLabel.setBounds(buttonX, buttonY + buttonHeight * 17, buttonWidth, buttonHeight);
        concreteView.add(errorLabel);


        JButton insertOrUpdateTrayLocationButton = new JButton("A/U Tray Location");
        insertOrUpdateTrayLocationButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        concreteView.add(insertOrUpdateTrayLocationButton);
        insertOrUpdateTrayLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TrayLocation trayLocation = new TrayLocation(blockID.getText(), bedID.getText(),
                            cropName.getText(), varietyField.getText(), numTrays.getText(),
                            trayType.getText(), soilType.getText(),
                            seedsPerCell.getText());
                    model.insertOrUpdateTrayLocation(trayLocation);
                    errorLabel.setText("");
                    repaint();
                    revalidate();
                } catch (Exception e1) {
                    errorLabel.setText("Invalid field");
                }
            }
        });
    }

    /**
     * Gets all of the crops that are "Ready to harvest" - All of the crops with a date for harvesting that is before
     * today
     * @param buttonX - The x starting location of buttons
     * @param buttonY - The y starting location of buttons
     * @param buttonWidth - The width of any given button
     * @param buttonHeight - The height of any given button
     */
    private void getHarvestable(int buttonX, int buttonY, int buttonWidth, int buttonHeight) {
        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.red);
        errorLabel.setBounds(buttonX, buttonY + buttonHeight * 5, buttonWidth, buttonHeight);
        concreteView.add(errorLabel);

        JButton getHarvestableButton = new JButton("Get Harvestable Crops");
        getHarvestableButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        concreteView.add(getHarvestableButton);
        getHarvestableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    model.getHarvestable();
                    errorLabel.setText("");
                    repaint();
                    revalidate();
                } catch (Exception e1) {
                    errorLabel.setText("Something went wrong: Contact Douglas");
                }
            }
        });
    }

    /**
     * This finds all beds with at least the fields feet left space in them
     * @param buttonX - The x starting location of buttons
     * @param buttonY - The y starting location of buttons
     * @param buttonWidth - The width of any given button
     * @param buttonHeight - The height of any given button
     */
    private void getValidBedFeet(int buttonX, int buttonY, int buttonWidth, int buttonHeight) {
        JLabel feetLabel = new JLabel("Feet Left");
        feetLabel.setForeground(Color.white);
        feetLabel.setBounds(buttonX, buttonY + buttonHeight, buttonWidth, buttonHeight);
        concreteView.add(feetLabel);

        JTextField feet = new JTextField(6);
        feet.setBounds(buttonX, buttonY + buttonHeight * 2, buttonWidth, buttonHeight);
        concreteView.add(feet);


        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.red);
        errorLabel.setBounds(buttonX, buttonY + buttonHeight * 3, buttonWidth, buttonHeight);
        concreteView.add(errorLabel);


        JButton validBedButton = new JButton("Get Valid Bed");
        validBedButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        concreteView.add(validBedButton);
        validBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer feetFinal;
                    try {
                        feetFinal = Integer.parseInt(feet.getText());
                    } catch (NumberFormatException ne) {
                        throw new SQLException("Field must be int");
                    }

                    model.getValidBed(feetFinal);
                    errorLabel.setText("");
                    repaint();
                    revalidate();
                } catch (Exception e1) {
                    errorLabel.setText("Field must be an Integer");
                }
            }
        });
    }

    /**
     * This finds all of the beds that can fit the given number of the given type of plant into the bed
     * @param buttonX - The x starting location of buttons
     * @param buttonY - The y starting location of buttons
     * @param buttonWidth - The width of any given button
     * @param buttonHeight - The height of any given button
     */
    private void getValidBedPlants(int buttonX, int buttonY, int buttonWidth, int buttonHeight) {
        JLabel cropNameLabel = new JLabel("Crop Name");
        cropNameLabel.setForeground(Color.white);
        cropNameLabel.setBounds(buttonX, buttonY + buttonHeight, buttonWidth, buttonHeight);
        concreteView.add(cropNameLabel);

        JTextField cropName = new JTextField(6);
        cropName.setBounds(buttonX, buttonY + buttonHeight * 2, buttonWidth, buttonHeight);
        concreteView.add(cropName);


        JLabel varietyLabel = new JLabel("Variety");
        varietyLabel.setForeground(Color.white);
        varietyLabel.setBounds(buttonX, buttonY + buttonHeight * 3, buttonWidth, buttonHeight);
        concreteView.add(varietyLabel);

        JTextField varietyField = new JTextField(6);
        varietyField.setBounds(buttonX, buttonY + buttonHeight * 4, buttonWidth, buttonHeight);
        concreteView.add(varietyField);


        JLabel numPlantsLabel = new JLabel("Number Of Plants");
        numPlantsLabel.setForeground(Color.white);
        numPlantsLabel.setBounds(buttonX, buttonY + buttonHeight * 5, buttonWidth, buttonHeight);
        concreteView.add(numPlantsLabel);

        JTextField numPlantsField = new JTextField(6);
        numPlantsField.setBounds(buttonX, buttonY + buttonHeight * 6, buttonWidth, buttonHeight);
        concreteView.add(numPlantsField);


        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.red);
        errorLabel.setBounds(buttonX, buttonY + buttonHeight * 7, buttonWidth, buttonHeight);
        concreteView.add(errorLabel);


        JButton insertOrUpdateTrayLocationButton = new JButton("Get Valid Beds");
        insertOrUpdateTrayLocationButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        concreteView.add(insertOrUpdateTrayLocationButton);
        insertOrUpdateTrayLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Crop crop = new Crop(cropName.getText(), varietyField.getText());
                    model.getValidBed(crop, Integer.parseInt(numPlantsField.getText()));
                    errorLabel.setText("");
                    repaint();
                    revalidate();
                } catch (Exception e1) {
                    errorLabel.setText("Invalid Field");
                }
            }
        });
    }
}
