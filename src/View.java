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


    public View() {
        this.concreteView = new ConcreteView();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().add(concreteView);
        this.pack();
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
                    concreteView.setLoggedIn(true);
                    concreteView.revalidate();
                    concreteView.repaint();
                } catch (Exception exception) {
                    statusLabel.setText("Invalid password or username please try again");
                }
            }
        });
        concreteView.add(statusLabel);
        this.setVisible(true);
    }
}
