import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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

    private JLabel headerLabel;
    private JLabel statusLabel;

    boolean loggedIn;

    public View() {
        loggedIn = false;
        this.concreteView = new ConcreteView();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().add(concreteView);
        this.pack();
        prepareGui();

    }

    public void run() {
        this.repaint();
        headerLabel.setText("Control in action: JTextField");

        JLabel  namelabel= new JLabel("User ID: ", JLabel.RIGHT);
        JLabel  passwordLabel = new JLabel("Password: ", JLabel.CENTER);
        final JTextField userText = new JTextField(6);
        final JPasswordField passwordText = new JPasswordField(6);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = userText.getText().toString();
                String pass = new String(passwordText.getPassword());
                Model model = new Model();
                    try {
                        model.connect(user, pass);
                        statusLabel.setText("Credentials accepted");
                    } catch (Exception exception) {
                        statusLabel.setText("Invalid password or username please try again");
                    }
            }
        });

        concreteView.add(namelabel);
        concreteView.add(userText);
        concreteView.add(passwordLabel);
        concreteView.add(passwordText);
        concreteView.add(loginButton);
        this.setVisible(true);

    }

    private void prepareGui() {
        this.setLayout(new GridLayout(3, 1));
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("",JLabel.CENTER);

        statusLabel.setSize(350,100);
//        ConcreteView.setLayout(new FlowLayout());

        this.add(headerLabel);
        this.add(statusLabel);
    }
}
