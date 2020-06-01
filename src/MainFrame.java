
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
enum Theme{
    dark,light
}
/**
 * this is a class which extends JFrame class and it has containers
 * @author Alireza Ghafari
 * @version 2.0
 */
public class MainFrame extends JFrame  {
    private MyRequestPanel myRequestPanel;
    private ComposeRequestPanel composeRequestPanel;
    private ResponsePanel responsePanel;
    private JMenuBar menuBar;
    private JFrame optionFrame;
    private Checkbox exitOnClose;
    private JComboBox themeBox;
    private Checkbox followRedirect;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JPanel optionMainPanel;

    static Theme theme=Theme.dark;



    public MainFrame(String title) {
        super(title);
        setBackground(Color.black);

        myRequestPanel = new MyRequestPanel();
        composeRequestPanel = new ComposeRequestPanel();
        responsePanel = new ResponsePanel();
        addPanels();


        menuBar = new JMenuBar();
        menuBar.setBackground(Color.darkGray);
        manageMenu();
        setJMenuBar(menuBar);

    }

    private void addPanels() {
        add(myRequestPanel);
        add(composeRequestPanel);
        add(responsePanel);

    }

    public void manageMenu() {
        addApplicationMenu();
        addViewMenu();
        addHelpMenu();
    }

    public void addApplicationMenu(){
        JMenu application = new JMenu("Application");

        //add exit item
        JMenuItem exit = new JMenuItem("Exit");
        exit.setToolTipText("Do you want to exit?");
        exit.addActionListener(e -> System.exit(0));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
        application.add(exit);

        //add option item
        JMenuItem option = new JMenuItem("Option");

        optionFrame=new JFrame("Insomnia Preferences");
        followRedirect=new Checkbox();
        exitOnClose=new Checkbox();
        exitOnClose.setState(true);
        exitOnClose.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(exitOnClose.getState())
                    MainFrame.this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                else
                    MainFrame.this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            }
        });
        String[] st = {"Dark","Light"};
        themeBox=new JComboBox(st);
        optionMainPanel=new JPanel();
        optionMainPanel.setLayout(new GridLayout(3, 2, 50, 30));
        optionFrame.setSize(450, 200);
        optionFrame.setMinimumSize(new Dimension(450, 200));



        label1=new JLabel("             Follow redirect: ");
        label1.setFont(new Font("Arial", 10, 15));
        optionMainPanel.add(label1);
        optionMainPanel.add(followRedirect);
        label2=new JLabel("             Exit on close: ");
        label2.setFont(new Font("Arial", 10, 15));
        optionMainPanel.add(label2);
        optionMainPanel.add(exitOnClose);
        label3=new JLabel("             Theme: ");
        label3.setFont(new Font("Arial", 10, 15));
        optionMainPanel.add(label3);
        optionMainPanel.add(themeBox);


        optionFrame.setResizable(false);
        optionFrame.setLocationRelativeTo(null);
        optionFrame.setVisible(true);

        optionFrame.add(optionMainPanel);

        themeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(themeBox.getSelectedIndex()==0) {
                    theme=Theme.dark;
                    myRequestPanel.setTheme("Dark");
                    composeRequestPanel.setTheme("Dark");
                    responsePanel.setTheme("Dark");
                    optionFrame.setBackground(new Color(213,216,222));
                    label1.setForeground(Color.black);
                    label2.setForeground(Color.black);
                    label3.setForeground(Color.black);
                    optionMainPanel.setBackground(new Color(213,216,222));
                    MainFrame.this.setBackground(Color.black);
                }
                else {
                    theme=Theme.light;
                    myRequestPanel.setTheme("Light");
                    composeRequestPanel.setTheme("Light");
                    responsePanel.setTheme("Light");
                    optionFrame.setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));
                    label1.setForeground(Color.white);
                    label2.setForeground(Color.white);
                    label3.setForeground(Color.white);
                    optionMainPanel.setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));
                    MainFrame.this.setBackground(new Color(133,125,202));
                }
                MainFrame.this.revalidate();
                MainFrame.this.repaint();
            }
        });

        option.addActionListener(e -> {
            optionFrame.show();
        });
        option.setToolTipText("Need Options?");
        option.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_DOWN_MASK));
        application.add(option);


        application.setMnemonic(KeyEvent.VK_A);
        menuBar.add(application);
    }

    public void addViewMenu(){
        JMenu viewMenu = new JMenu("View");

        //add toggle fullScreen item
        JMenuItem toggleFullscreen = new JMenuItem("Toggle Full Screen");
        toggleFullscreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(MainFrame.this.getExtendedState()==0)
                    MainFrame.this.setExtendedState(Frame.MAXIMIZED_BOTH);
                else
                    MainFrame.this.setExtendedState(Frame.NORMAL);
            }
        });
        toggleFullscreen.setToolTipText("Full Screen");
        toggleFullscreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.ALT_DOWN_MASK));
        viewMenu.add(toggleFullscreen);


        //add toggle slideBar item
        JMenuItem toggleSlideBar = new JMenuItem("Toggle Sidebar");
        toggleSlideBar.addActionListener(new ActionListener() {
            boolean isFrameOnSlideBar = true;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isFrameOnSlideBar) {
                    MainFrame.this.remove(myRequestPanel);
                    isFrameOnSlideBar = false;
                }
                else {
                    MainFrame.this.add(myRequestPanel,0);
                    isFrameOnSlideBar = true;
                }
                MainFrame.this.revalidate();
                MainFrame.this.repaint();

            }
        });
        toggleSlideBar.setToolTipText("Hide Request List");
        toggleSlideBar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK));
        viewMenu.add(toggleSlideBar);



        viewMenu.setMnemonic(KeyEvent.VK_V);
        menuBar.add(viewMenu);

    }
    public void addHelpMenu(){
        JMenu helpMenu = new JMenu("Help");

        //add Help item
        JMenuItem helpItem = new JMenuItem("Help");
        helpItem.setToolTipText("Need any help?");
        helpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.ALT_DOWN_MASK));
        helpMenu.add(helpItem);

        //add About item
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.setToolTipText("About Us");
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Author: Alireza Ghafari\nID: 9831115\nContact: areza.ghafari01@gmail.com");
            }
        });
        aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_DOWN_MASK));
        helpMenu.add(aboutItem);



        helpMenu.setMnemonic(KeyEvent.VK_H);
        menuBar.add(helpMenu);

    }


}
