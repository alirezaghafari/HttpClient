import javax.swing.*;
import java.awt.*;

/**
 * this is Main class to run main method and create mainframe
 * @author Alireza Ghafari
 * @version 2.0
 */
public class Run {
    public static void main(String[] args) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }

        MainFrame mainFrame = new MainFrame("Insomnia");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLayout(new GridLayout(1, 3, 1, 0));
        mainFrame.setSize(1400, 500);
        mainFrame.setMinimumSize(new Dimension(1400, 480));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);


    }
}
