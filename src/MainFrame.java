import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
/**
 * this is a class which extends JFrame class and it has containers
 * @author Alireza Ghafari
 * @version 1.0
 */
public class MainFrame extends JFrame {
    private MyRequestPanel myRequestPanel;
    private ComposeRequestPanel composeRequestPanel;
    private ResponsePanel responsePanel;
    private JMenuBar menuBar;


    public MainFrame(String title) {
        super(title);


        myRequestPanel = new MyRequestPanel();
        composeRequestPanel = new ComposeRequestPanel();
        responsePanel = new ResponsePanel();
        addPanels();


        menuBar = new JMenuBar();
        menuBar.setBackground(Color.darkGray);
        addMenu();
        setJMenuBar(menuBar);

    }

    private void addPanels() {
        add(myRequestPanel);
        add(composeRequestPanel);
        add(responsePanel);

    }

    public void addMenu() {
        //add view menu
        JMenu viewMenu = new JMenu("View");

        JMenuItem toggleFullscreen = new JMenuItem("Toggle Full Screen");
        toggleFullscreen.setToolTipText("Enter Full Screen");
        toggleFullscreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.ALT_DOWN_MASK));
        viewMenu.add(toggleFullscreen);

        JMenuItem toggleSlideBar = new JMenuItem("Toggle Sidebar");
        toggleSlideBar.setToolTipText("Hide Request List");
        toggleSlideBar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK));
        viewMenu.add(toggleSlideBar);

        viewMenu.setMnemonic(KeyEvent.VK_V);


        //add help menu
        JMenu helpMenu = new JMenu("Help");

        JMenuItem helpItem = new JMenuItem("Help");
        helpItem.setToolTipText("Need any help?");
        helpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.ALT_DOWN_MASK));
        helpMenu.add(helpItem);

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.setToolTipText("About Us");
        aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_DOWN_MASK));
        helpMenu.add(aboutItem);

        helpMenu.setMnemonic(KeyEvent.VK_H);

        JMenu application = new JMenu("Application");

        JMenuItem exit = new JMenuItem("Exit");
        exit.setToolTipText("Do you want to exit?");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
        application.add(exit);

        JMenuItem option = new JMenuItem("Option");
        option.setToolTipText("Need Options?");
        option.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_DOWN_MASK));
        application.add(option);

        application.setMnemonic(KeyEvent.VK_A);


        menuBar.add(application);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);


    }
}
