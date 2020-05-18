import javax.swing.*;
import java.awt.*;
/**
 * this is the second panel of the mainFrame to compose and send new request
 * @author Alireza Ghafari
 * @version 1.0
 */
public class ComposeRequestPanel extends JPanel
{
    private GridBagConstraints gbc;
    private GridBagLayout gridBagLayout;
    private JTextField textField;
    private JScrollPane scrollPane;
    private JButton sendButton;
    private JComboBox comboBox;
    private JTabbedPane tabbedPane;
    private JPanel tabPanel;
    private JPanel urlPanel;

    public ComposeRequestPanel(){


        setBackground(Color.getHSBColor(0.16666667f,0.06666667f,0.1764706f));
        addComponents();
    }

    /**
     * add URL text field and send button
     */
    public void addComponents(){
        gridBagLayout=new GridBagLayout();
        gbc=new GridBagConstraints();
        urlPanel=new JPanel();
        urlPanel.setBackground(Color.getHSBColor(0.16666667f,0.06666667f,0.1764706f));
        gbc.anchor=GridBagConstraints.NORTH;
        gbc.weightx=0;
        gbc.weighty=1;
        gbc.gridx=0;
        gbc.gridy=0;
        String[] st={"GET","PUT","POST","DELETE","PATCH"};
        comboBox=new JComboBox(st);
        gridBagLayout.setConstraints(comboBox,gbc);
        urlPanel.add(comboBox);

        gbc.gridx=1;
        gbc.gridy=0;
        textField=new JTextField();
        textField.setBackground(Color.getHSBColor(0.16666667f,0.06666667f,0.1764706f));
        textField.setForeground(Color.white);
        textField.setEditable(true);
        scrollPane = new JScrollPane(textField);
        scrollPane.setPreferredSize(new Dimension(215, 50));
        scrollPane.setLocation(50,20);
        gridBagLayout.setConstraints(scrollPane,gbc);
        urlPanel.add(scrollPane);

        gbc.gridx=2;
        gbc.gridy=0;
        sendButton=new JButton("Send");
        gridBagLayout.setConstraints(sendButton,gbc);
        urlPanel.add(sendButton);

        add(urlPanel);
        addTabbedPane();
        JTextArea textArea = new JTextArea();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(300, 300));
        textArea.setEditable(true);
        scrollPane.add(textArea);
        add(scrollPane);
        JButton saveButton=new JButton("Save") ;
        add(saveButton);

    }

    /**
     * add tabbed pane to panel
     */
    public void addTabbedPane(){
        GridBagConstraints gbc3=new GridBagConstraints();
        GridBagLayout gridBagLayout=new GridBagLayout();
        tabPanel=new JPanel();
        tabPanel.setBackground(Color.getHSBColor(0.16666667f,0.06666667f,0.1764706f));

        tabPanel.setLayout(gridBagLayout);

        gbc3.gridx=0;
        gbc3.gridy=1;
        tabbedPane=new JTabbedPane();
        gridBagLayout.setConstraints(tabbedPane,gbc3);
        String[] st2={"","From Data","JSON","Binary Data"};
        JComboBox bodyComboBox= new JComboBox(st2);
        JPanel bodyPanel=new JPanel();
        bodyPanel.setBackground(Color.getHSBColor(0.16666667f,0.06666667f,0.1764706f));
        bodyPanel.add(bodyComboBox);
        tabbedPane.add("Body",bodyPanel);

        JPanel headerPanel=new JPanel();
        headerPanel.setBackground(Color.getHSBColor(0.16666667f,0.06666667f,0.1764706f));

        addKeyAndValue(headerPanel);


        tabbedPane.add("Header",headerPanel);
        tabPanel.add(tabbedPane);
        add(tabPanel);
    }
    /**
     * add text box to panel
     * @param headerPanel the panel which keeps headers
     */
    public void addKeyAndValue(JPanel headerPanel){

        JPanel panel=new JPanel();
        panel.setBackground(Color.getHSBColor(0.16666667f,0.06666667f,0.1764706f));


        GridBagLayout gridBagLayout2=new GridBagLayout();
        GridBagConstraints gbc2=new GridBagConstraints();
        headerPanel.setLayout(gridBagLayout2);
        JTextField keyField=new JTextField();
        JTextField valueField=new JTextField();
        keyField.setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));
        valueField.setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));
        keyField.setForeground(Color.white);
        valueField.setForeground(Color.white);


        keyField.setEditable(true);
        valueField.setEditable(true);
        JScrollPane scrollPane2 = new JScrollPane(keyField);
        gbc2.gridx=0;
        gbc2.gridy=0;
        scrollPane2.setPreferredSize(new Dimension(180, 40));
        scrollPane2.setLocation(50,20);
        gridBagLayout.setConstraints(scrollPane2,gbc2);
        panel.add(scrollPane2);
        JScrollPane scrollPane3 = new JScrollPane(valueField);
        gbc2.gridx=1;
        gbc2.gridy=0;
        scrollPane3.setPreferredSize(new Dimension(180, 40));
        scrollPane3.setLocation(50,20);
        gridBagLayout.setConstraints(scrollPane3,gbc2);
        panel.add(scrollPane3);
        JButton removeButton =new JButton("\u2573");
        removeButton.setToolTipText("remove");
        gbc2.gridx=2;
        gbc2.gridy=0;
        gridBagLayout.setConstraints(removeButton,gbc2);
        removeButton.setBackground(Color.getHSBColor(0.16666667f,0.06666667f,0.1764706f));
        panel.add(removeButton);

        JCheckBox checkBox =new JCheckBox();
        gbc2.gridx=3;
        gbc2.gridy=0;
        gridBagLayout.setConstraints(checkBox,gbc2);
        checkBox.setBackground(Color.getHSBColor(0.16666667f,0.06666667f,0.1764706f));
        panel.add(checkBox);

        headerPanel.add(panel);



    }


}
