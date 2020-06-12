package insomniaFore.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * this is the second panel of the mainFrame to compose and send new request
 * @author Alireza Ghafari
 * @version 2.0
 */
public class ComposeRequestPanel extends JPanel
{
    private JTextField textField;
    private JScrollPane scrollPane;
    private JButton sendButton;
    private JComboBox comboBox;
    private JTabbedPane tabbedPane;
    private JPanel tabPanel;
    private JPanel urlPanel;
    private JPanel headerPanel;
    private JButton saveButton;
    private JComboBox bodyComboBox;
    private JPanel bodyPanel;
    private JTextArea textArea;
    private JPanel panel;
    private JButton removeButton;
    private JTextField valueField;
    private JTextField keyField;
    private JCheckBox checkBox;
    private boolean followRedirects=false;



    private static final Color defaultDarkThemeColor = new Color(45,48,55);
    private static final Color defaultLightThemeColor = new Color(251,246,227);


    public ComposeRequestPanel(){

        setLayout(new BorderLayout(0,0));
        setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        addUrlPanel();
        addTabbedPane();
        if(MainFrame.theme==Theme.dark)
            setTheme("dark");
        else
            setTheme("light");

    }

    /**
     * add URL text field and send button
     */
    public void addUrlPanel(){

        urlPanel=new JPanel();
        urlPanel.setLayout(new BorderLayout(0,0));
        urlPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        String[] st={"GET","PUT","POST","DELETE","PATCH"};
        comboBox=new JComboBox(st);
        urlPanel.add(comboBox,BorderLayout.WEST);

        textField=new HintTextField("https://api.myproduct.com/v1/users");
        textField.setForeground(Color.gray);
        textField.setFont(new Font("Arial", 10, 15));
        textField.setEditable(true);
        scrollPane = new JScrollPane(textField);
        scrollPane.setPreferredSize(new Dimension(215, 50));
        scrollPane.setLocation(50,20);
        urlPanel.add(scrollPane,BorderLayout.CENTER);


        sendButton=new JButton("Send");
        urlPanel.add(sendButton,BorderLayout.EAST);

        saveButton=new JButton("Save request") ;
        urlPanel.add(saveButton,BorderLayout.SOUTH);
        add(urlPanel,BorderLayout.NORTH);

    }

    /**
     * add tabbed pane to panel
     */
    public void addTabbedPane(){
        tabPanel=new JPanel();


        tabbedPane=new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(470,990));

        String[] st2={"","From Data","JSON","Binary Data"};
        bodyComboBox= new JComboBox(st2);

        bodyPanel=new JPanel();
        bodyPanel.setPreferredSize(new Dimension(400,950));
        bodyPanel.setLayout(new BorderLayout(0,0));
        bodyPanel.add(bodyComboBox,BorderLayout.NORTH);
        textArea = new HintTextArea("request body...");
        textArea.setFont(new Font("Arial", 10, 13));
        textArea.setEditable(true);
        textArea.setForeground(Color.gray);
        scrollPane = new JScrollPane(textArea);
        bodyPanel.add(scrollPane);
        tabbedPane.add("Body",bodyPanel);

        headerPanel=new JPanel();
        headerPanel.setLayout(new GridLayout(20,1));
        addKeyAndValue();
        addKeyAndValue();
        addKeyAndValue();



        JScrollPane headersScroller= new JScrollPane(headerPanel);
        tabbedPane.add("Header",headersScroller);
        tabPanel.add(tabbedPane);
        add(tabPanel);
    }
    /**
     * add text box to panel
     */
    public void addKeyAndValue(){

        panel=new JPanel();

        keyField=new HintTextField("new header");
        keyField.setForeground(Color.gray);
        keyField.setEditable(true);
        JScrollPane scrollPane2 = new JScrollPane(keyField);
        scrollPane2.setPreferredSize(new Dimension(180, 40));
        scrollPane2.setLocation(50,20);
        panel.add(scrollPane2);

        valueField=new HintTextField("new value");
        valueField.setForeground(Color.gray);
        valueField.setEditable(true);
        JScrollPane scrollPane3 = new JScrollPane(valueField);
        scrollPane3.setPreferredSize(new Dimension(180, 40));
        scrollPane3.setLocation(50,20);
        panel.add(scrollPane3);

        removeButton =new JButton("\u2573");
        removeButton.setToolTipText("remove");
        panel.add(removeButton);

        checkBox =new JCheckBox();

        panel.add(checkBox);

        headerPanel.add(panel);

        if ((MainFrame.theme == Theme.dark)) {
            setTheme("dark");
        } else {
            setTheme("light");
        }


        addHandler(keyField,valueField,removeButton);
    }
    /**
     * an inner class to put hint on text fields
     */
    class HintTextField extends JTextField implements FocusListener {

        private final String hint;
        private boolean showingHint;

        public HintTextField(final String hint) {
            super(hint);
            this.hint = hint;
            this.showingHint = true;
            super.addFocusListener(this);
        }

        @Override
        public void focusGained(FocusEvent e) {
            if(this.getText().isEmpty()) {
                if(MainFrame.theme==Theme.dark)
                    super.setForeground(Color.white);
                else
                    super.setForeground(Color.darkGray);
                super.setText("");
                showingHint = false;
            }
        }
        @Override
        public void focusLost(FocusEvent e) {
            if(this.getText().isEmpty()) {
                super.setForeground(Color.gray);
                super.setText(hint);
                showingHint = true;
            }
        }

        @Override
        public String getText() {
            return showingHint ? "" : super.getText();
        }
    }

    /**
     * an inner class to put hint on text areas
     */
    class HintTextArea extends JTextArea implements FocusListener {

        private final String hint;
        private boolean showingHint;

        public HintTextArea(final String hint) {
            super(hint);
            this.hint = hint;
            this.showingHint = true;
            super.addFocusListener(this);
        }

        @Override
        public void focusGained(FocusEvent e) {
            if(this.getText().isEmpty()) {
                if(MainFrame.theme==Theme.dark)
                    super.setForeground(Color.white);
                else
                    super.setForeground(Color.darkGray);
                super.setText("");
                showingHint = false;
            }
        }
        @Override
        public void focusLost(FocusEvent e) {
            if(this.getText().isEmpty()) {
                super.setForeground(Color.gray);
                super.setText(hint);
                showingHint = true;
            }
        }

        @Override
        public String getText() {
            return showingHint ? "" : super.getText();
        }
    }

    /**
     * set the theme of panel
     * @param st to determine that theme should be dark or light
     */
    public void setTheme(String st){
        if(st.equalsIgnoreCase("light")){
            setBackground(defaultLightThemeColor);
            urlPanel.setBackground(defaultLightThemeColor);
            textField.setBackground(defaultLightThemeColor);
            saveButton.setBackground(defaultLightThemeColor);
            tabPanel.setBackground(defaultLightThemeColor);
            bodyPanel.setBackground(defaultLightThemeColor);
            textArea.setBackground(defaultLightThemeColor);
            headerPanel.setBackground(defaultLightThemeColor);
            checkBox.setBackground(defaultLightThemeColor);
            saveButton.setForeground(Color.darkGray);
            sendButton.setBackground(defaultLightThemeColor);
            sendButton.setForeground(Color.darkGray);
            comboBox.setBackground(Color.gray);
            bodyComboBox.setBackground(Color.gray);
            setBackground(Color.gray);

            for(Component component:headerPanel.getComponents()){
                JPanel tempPanel = (JPanel) component;
                tempPanel.getComponent(2).setBackground(defaultLightThemeColor);
                ((JScrollPane) tempPanel.getComponent(0)).getViewport().getView().setBackground(defaultLightThemeColor);
                ((JScrollPane) tempPanel.getComponent(1)).getViewport().getView().setBackground(defaultLightThemeColor);
                tempPanel.setBackground(defaultLightThemeColor);
            }
        }else{
            setBackground(defaultDarkThemeColor);
            urlPanel.setBackground(defaultDarkThemeColor);
            textField.setBackground(defaultDarkThemeColor);
            saveButton.setBackground(defaultDarkThemeColor);
            sendButton.setBackground(defaultDarkThemeColor);
            sendButton.setForeground(Color.white);
            checkBox.setBackground(defaultDarkThemeColor);
            saveButton.setForeground(Color.white);
            comboBox.setBackground(defaultDarkThemeColor);

            tabPanel.setBackground(defaultDarkThemeColor);
            bodyPanel.setBackground(defaultDarkThemeColor);
            textArea.setBackground(defaultDarkThemeColor);

            headerPanel.setBackground(defaultDarkThemeColor);

            bodyComboBox.setBackground(defaultDarkThemeColor);
            for(Component component:headerPanel.getComponents()){
                JPanel tempPanel = (JPanel) component;
                tempPanel.getComponent(2).setBackground(defaultDarkThemeColor);
                ((JScrollPane) tempPanel.getComponent(0)).getViewport().getView().setBackground(defaultDarkThemeColor);
                ((JScrollPane) tempPanel.getComponent(1)).getViewport().getView().setBackground(defaultDarkThemeColor);
                tempPanel.setBackground(defaultDarkThemeColor);

            }
        }
        revalidate();
        repaint();

    }
    public void addHandler(JTextField keyField, JTextField valueField, JButton removeButton){
        MouseAdapter addField =new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Component c =(Component)e.getSource();
                if(c.getParent().getParent().getParent()==headerPanel.getComponent(headerPanel.getComponentCount()-1))
                    addKeyAndValue();
                revalidate();
                repaint();
            }
        };
        keyField.addMouseListener(addField);
        valueField.addMouseListener(addField);

        removeButton.addActionListener(e -> {
            Component c =(Component)e.getSource();
            if(e.getSource() instanceof JButton)
                headerPanel.remove(c.getParent());
            ComposeRequestPanel.this.revalidate();
            ComposeRequestPanel.this.repaint();
        });
    }


    public void setFollowRedirects(boolean followRedirects) {
        this.followRedirects = followRedirects;
    }

    public boolean getFollowRedirects() {
        return followRedirects;
    }

}