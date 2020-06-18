package httpClientFore.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/**
 * this is the third panel of the mainFrame to show the result of requests
 *
 * @author Alireza Ghafari
 * @version 2.0
 */
public class ResponsePanel extends JPanel {
    private JPanel barPanel;
    private JTextField codeStatus;
    private JTextField timeStatus;
    private JTextField sizeStatus;
    private JTabbedPane tabbedPane;
    private JPanel tabPanel;
    private JTextField valueField;
    public  Preview previewPanel;
    private JTextField keyField;
    private JPanel panel;
    private JPanel headerPanel;
    private JScrollPane scrollPane;
    private JTextArea textArea;
    private JPanel bodyPanel;
    private JButton copyButton;
    private JTabbedPane bodyTabbedPane;
    private JPanel copyPanel;
    private JPanel mainHeaderPanel;
    private JButton bodyCopyButton;
    private static final Color defaultDarkThemeColor = new Color(45, 48, 55);
    private static final Color defaultLightThemeColor = new Color(251, 246, 227);


    public ResponsePanel() {
        setLayout(new BorderLayout());
        addComponents();
        if (MainFrame.theme == Theme.dark)
            setTheme("dark");
        else
            setTheme("light");
    }

    public void addComponents() {
        addBarPanel();
        addTabbedPane();
    }

    /**
     * add time and code status at the top of panel
     */
    public void addBarPanel() {
        barPanel = new JPanel();
        barPanel.setPreferredSize(new Dimension(500, 40));
        codeStatus = new JTextField();
        timeStatus = new JTextField();
        sizeStatus = new JTextField();

        codeStatus.setEditable(false);
        timeStatus.setEditable(false);
        sizeStatus.setEditable(false);
        codeStatus.setPreferredSize(new Dimension(100, 30));
        timeStatus.setPreferredSize(new Dimension(100, 30));
        sizeStatus.setPreferredSize(new Dimension(100, 30));
        barPanel.add(codeStatus);
        barPanel.add(timeStatus);
        barPanel.add(sizeStatus);
        add(barPanel, BorderLayout.NORTH);
    }

    /**
     * a method to add tabbed pane to panel
     */
    public void addTabbedPane() {
        tabPanel = new JPanel();
        tabbedPane = new JTabbedPane();

        tabbedPane.setPreferredSize(new Dimension(460, 1020));

        bodyTabbedPane=new JTabbedPane();
        bodyTabbedPane.setPreferredSize(new Dimension(460, 1020));

        bodyPanel = new JPanel();
        bodyPanel.setPreferredSize(new Dimension(400, 900));
        bodyPanel.setLayout(new BorderLayout(0, 0));
        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", 10, 13));
        scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        bodyPanel.add(scrollPane);
        bodyPanel.add(scrollPane);
        bodyCopyButton=new JButton("Copy To Clipboard");
        bodyCopyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bodyContent=textArea.getText();
                StringSelection stringSelection = new StringSelection(bodyContent);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection,null);
            }
        });
        bodyPanel.add(bodyCopyButton,BorderLayout.SOUTH);
        bodyTabbedPane.add("Raw",bodyPanel);

        previewPanel=new Preview();

        bodyTabbedPane.add("Preview",previewPanel);


        tabbedPane.add("Body", bodyTabbedPane);


        mainHeaderPanel=new JPanel(new BorderLayout());

        copyPanel=new JPanel();
        copyButton = new JButton("Copy To Clipboard");
        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String headersContent="";
                for(int i=0;i<headerPanel.getComponentCount();i++) {
                    JPanel tempPanel = (JPanel) MainFrame.responsePanel.getHeaderPanel().getComponent(i);
                    JTextField key = (JTextField) (((JScrollPane) tempPanel.getComponent(0)).getViewport()).getView();
                    JTextField value = (JTextField) (((JScrollPane) tempPanel.getComponent(1)).getViewport()).getView();
                    headersContent+=key.getText()+": "+value.getText()+"\n";
                }
                StringSelection stringSelection = new StringSelection(headersContent);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection,null);
            }
        });
        copyPanel.add(copyButton);
        mainHeaderPanel.add(copyButton,BorderLayout.NORTH);


        headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(20, 1));
        mainHeaderPanel.add( headerPanel);

        tabbedPane.add("Headers",mainHeaderPanel);
        tabPanel.add(tabbedPane);

        add(tabPanel);
    }

    /**
     * add text box to panel
     */
    public void addKeyAndValue() {

        panel = new JPanel();

        keyField = new JTextField();
        keyField.setEditable(false);
        JScrollPane scrollPane2 = new JScrollPane(keyField);
        scrollPane2.setPreferredSize(new Dimension(220, 40));
        scrollPane2.setLocation(50, 20);
        panel.add(scrollPane2);

        valueField = new JTextField();
        valueField.setEditable(false);
        JScrollPane scrollPane3 = new JScrollPane(valueField);
        scrollPane3.setPreferredSize(new Dimension(220, 40));
        scrollPane3.setLocation(50, 20);
        panel.add(scrollPane3);


        headerPanel.add(panel);

        if ((MainFrame.theme == Theme.dark)) {
            setTheme("dark");
        } else {
            setTheme("light");
        }
    }


    /**
     * set the theme of panel
     *
     * @param st to determine that theme should be dark or light
     */
    public void setTheme(String st) {
        if (st.equalsIgnoreCase("light")) {
            setBackground(defaultLightThemeColor);
            barPanel.setBackground(defaultLightThemeColor);
            headerPanel.setBackground(defaultLightThemeColor);
            bodyPanel.setBackground(defaultLightThemeColor);
            codeStatus.setBackground(Color.gray);
            timeStatus.setBackground(Color.gray);
            sizeStatus.setBackground(Color.gray);
            tabPanel.setBackground(defaultLightThemeColor);
            textArea.setBackground(defaultLightThemeColor);
            copyButton.setBackground(Color.white);
            bodyCopyButton.setBackground(Color.white);
            previewPanel.setBackground(defaultLightThemeColor);
            mainHeaderPanel.setBackground(defaultLightThemeColor);
            copyPanel.setBackground(defaultLightThemeColor);
            setBackground(Color.gray);

            codeStatus.setForeground(Color.white);
            timeStatus.setForeground(Color.white);
            sizeStatus.setForeground(Color.white);
            textArea.setForeground(Color.darkGray);
            copyButton.setForeground(Color.darkGray);
            bodyCopyButton.setForeground(Color.darkGray);

            for (Component component : headerPanel.getComponents()) {
                JPanel tempPanel = (JPanel) component;
                ((JScrollPane) tempPanel.getComponent(0)).getViewport().getView().setBackground(defaultLightThemeColor);
                ((JScrollPane) tempPanel.getComponent(1)).getViewport().getView().setBackground(defaultLightThemeColor);
                tempPanel.setBackground(defaultLightThemeColor);
                ((JScrollPane) tempPanel.getComponent(0)).getViewport().getView().setForeground(Color.darkGray);
                ((JScrollPane) tempPanel.getComponent(1)).getViewport().getView().setForeground(Color.darkGray);
            }


        } else {
            setBackground(defaultDarkThemeColor);
            barPanel.setBackground(defaultDarkThemeColor);
            headerPanel.setBackground(defaultDarkThemeColor);
            bodyPanel.setBackground(defaultDarkThemeColor);
            codeStatus.setBackground(Color.gray);
            timeStatus.setBackground(Color.gray);
            sizeStatus.setBackground(Color.gray);
            tabPanel.setBackground(defaultDarkThemeColor);
            textArea.setBackground(defaultDarkThemeColor);
            copyButton.setBackground(defaultDarkThemeColor);
            bodyCopyButton.setBackground(defaultDarkThemeColor);
            mainHeaderPanel.setBackground(defaultDarkThemeColor);
            copyPanel.setBackground(defaultDarkThemeColor);

            previewPanel.setBackground(defaultDarkThemeColor);

            codeStatus.setForeground(Color.white);
            timeStatus.setForeground(Color.white);
            sizeStatus.setForeground(Color.white);
            textArea.setForeground(Color.white);
            copyButton.setForeground(Color.white);
            bodyCopyButton.setForeground(Color.white);

            for (Component component : headerPanel.getComponents()) {
                JPanel tempPanel = (JPanel) component;
                ((JScrollPane) tempPanel.getComponent(0)).getViewport().getView().setBackground(defaultDarkThemeColor);
                ((JScrollPane) tempPanel.getComponent(1)).getViewport().getView().setBackground(defaultDarkThemeColor);
                tempPanel.setBackground(defaultDarkThemeColor);
                ((JScrollPane) tempPanel.getComponent(0)).getViewport().getView().setForeground(Color.white);
                ((JScrollPane) tempPanel.getComponent(1)).getViewport().getView().setForeground(Color.white);
            }
        }
        revalidate();
        repaint();

    }

    public JTextField getCodeStatus() {
        return codeStatus;
    }

    public JPanel getHeaderPanel() {
        return headerPanel;
    }

    public JTextField getSizeStatus() {
        return sizeStatus;
    }

    public JTextField getTimeStatus() {
        return timeStatus;
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}
