package httpClientFore.gui;
import httpClientBack.utils.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class SettingPanel {
    static JFrame optionFrame=new JFrame("Insomnia Preferences");
    private static String[] st = {"Dark","Light"};
    private static Checkbox exitOnClose=new Checkbox();
    private static JComboBox themeBox=new JComboBox(st);
    private static Checkbox followRedirect=new Checkbox();
    private static JLabel label1=new JLabel("             Follow redirect: ");;
    private static JLabel label2=new JLabel("             Exit on close: ");;
    private static JLabel label3=new JLabel("             Theme: ");;
    private static JPanel optionMainPanel=new JPanel();
    private static MainFrame mainFrame;

    public static void setMainFrame(MainFrame myMainFrame){
        mainFrame=myMainFrame;
    }

    public static void initializeSetting(){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("./documentations/setting/Options.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner inputScanner=new Scanner(fileReader);
        followRedirect.setState(Boolean.parseBoolean(inputScanner.next()));
        exitOnClose.setState((Boolean.parseBoolean(inputScanner.next())));
        themeBox.setSelectedIndex(Integer.parseInt(inputScanner.next()));

        init();
    }
    public static void init(){
        //TODO: follow redirect

        //initialize exit on close checkbox
        if(exitOnClose.getState())
            mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        else
            mainFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        //initialize theme
        if(themeBox.getSelectedIndex()==0) {
            MainFrame.theme= Theme.dark;
            MainFrame.myRequestPanel.setTheme("Dark");
            MainFrame.composeRequestPanel.setTheme("Dark");
            MainFrame.responsePanel.setTheme("Dark");
            optionFrame.setBackground(new Color(213,216,222));
            label1.setForeground(Color.black);
            label2.setForeground(Color.black);
            label3.setForeground(Color.black);
            optionMainPanel.setBackground(new Color(213,216,222));
            mainFrame.setBackground(Color.black);
        }
        else {
            MainFrame.theme= Theme.light;
            MainFrame.myRequestPanel.setTheme("Light");
            MainFrame.composeRequestPanel.setTheme("Light");
            MainFrame.responsePanel.setTheme("Light");
            optionFrame.setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));
            label1.setForeground(Color.white);
            label2.setForeground(Color.white);
            label3.setForeground(Color.white);
            optionMainPanel.setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));
            mainFrame.setBackground(new Color(133,125,202));
        }
        mainFrame.revalidate();
        mainFrame.repaint();

    }
    public static void settingHandler(){
        optionFrame=new JFrame("Insomnia Preferences");

        exitOnClose.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                FileUtils.settingWriter(""+SettingPanel.getFollowRedirect().getState()+"\n"+SettingPanel.getExitOnClose().getState()+"\n"+SettingPanel.getThemeBox().getSelectedIndex());
                if(exitOnClose.getState())
                    mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                else
                    mainFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            }
        });

        optionMainPanel=new JPanel();
        optionMainPanel.setLayout(new GridLayout(3, 2, 50, 30));
        optionFrame.setSize(450, 200);
        optionFrame.setMinimumSize(new Dimension(450, 200));




        label1.setFont(new Font("Arial", 10, 15));
        optionMainPanel.add(label1);
        followRedirect.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                FileUtils.settingWriter(""+SettingPanel.getFollowRedirect().getState()+"\n"+SettingPanel.getExitOnClose().getState()+"\n"+SettingPanel.getThemeBox().getSelectedIndex());
            }
        });
        optionMainPanel.add(followRedirect);

        label2.setFont(new Font("Arial", 10, 15));
        optionMainPanel.add(label2);
        optionMainPanel.add(exitOnClose);

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
                FileUtils.settingWriter(""+SettingPanel.getFollowRedirect().getState()+"\n"+SettingPanel.getExitOnClose().getState()+"\n"+SettingPanel.getThemeBox().getSelectedIndex());
                if(themeBox.getSelectedIndex()==0) {
                    MainFrame.theme= Theme.dark;
                    MainFrame.myRequestPanel.setTheme("Dark");
                    MainFrame.composeRequestPanel.setTheme("Dark");
                    MainFrame.responsePanel.setTheme("Dark");
                    optionFrame.setBackground(new Color(213,216,222));
                    label1.setForeground(Color.black);
                    label2.setForeground(Color.black);
                    label3.setForeground(Color.black);
                    optionMainPanel.setBackground(new Color(213,216,222));
                    mainFrame.setBackground(Color.black);
                }
                else {
                    MainFrame.theme= Theme.light;
                    MainFrame.myRequestPanel.setTheme("Light");
                    MainFrame.composeRequestPanel.setTheme("Light");
                    MainFrame.responsePanel.setTheme("Light");
                    optionFrame.setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));
                    label1.setForeground(Color.white);
                    label2.setForeground(Color.white);
                    label3.setForeground(Color.white);
                    optionMainPanel.setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));
                    mainFrame.setBackground(new Color(133,125,202));
                }
                mainFrame.revalidate();
                mainFrame.repaint();
            }
        });

    }

    public static Checkbox getExitOnClose() {
        return exitOnClose;
    }

    public static JComboBox getThemeBox() {
        return themeBox;
    }

    public static Checkbox getFollowRedirect() {
        return followRedirect;
    }

}
