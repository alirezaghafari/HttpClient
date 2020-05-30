import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Optional;

/**
 * this is a class which extends JFrame class and it has containers
 * @author Alireza Ghafari
 * @version 1.0
 */
public class MainFrame extends JFrame  {
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

        JFrame optionFrame=new JFrame("Insomnia Preferences");
        Checkbox followRedirect=new Checkbox();
        Checkbox exitOnClose=new Checkbox();
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
        String[] st = {"Light","Dark"};
        JComboBox theme=new JComboBox(st);
        theme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(theme.getSelectedIndex()==0) {
                    myRequestPanel.setTheme(Color.gray);
                }
                else {
                    myRequestPanel.setTheme(Color.darkGray);
                }
                MainFrame.this.revalidate();
                MainFrame.this.repaint();
            }
        });

        option.addActionListener(e -> {
            optionFrame.setLayout(new GridLayout(3, 2, 50, 30));
            optionFrame.setBackground(Color.darkGray);
            optionFrame.setSize(450, 200);
            optionFrame.setMinimumSize(new Dimension(450, 200));
            optionFrame.setLocationRelativeTo(null);
            optionFrame.setResizable(false);
            optionFrame.setVisible(true);

            optionFrame.add(new JLabel("             Follow redirect: "));
            optionFrame.add(followRedirect);
            optionFrame.add(new JLabel("             Exit on close: "));
            optionFrame.add(exitOnClose);
            optionFrame.add(new JLabel("             Theme: "));
            optionFrame.add(theme);



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
