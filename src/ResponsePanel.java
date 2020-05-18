import javax.swing.*;
import java.awt.*;
/**
 * this is the third panel of the mainFrame to show the result of requests
 * @author Alireza Ghafari
 * @version 1.0
 */
public class ResponsePanel extends JPanel {
    private JPanel barPanel;
    private JTextField statusCode;
    private JTextField timeStatus;
    JTabbedPane tabbedPane;
    private GridBagConstraints gbc;
    private GridBagLayout gridBagLayout;
    private JPanel tabPanel;


    public ResponsePanel() {

        setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));
        addComponents();
    }

    public void addComponents() {
        addBarPanel();
        addTabbedPane();
        addCopyToClipBoard();
    }

    /**
     * add time and code status at the top of panel
     */
    public void addBarPanel() {
        barPanel = new JPanel();
        barPanel.setPreferredSize(new Dimension(500, 40));
        statusCode = new JTextField();
        timeStatus = new JTextField();
        statusCode.setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));
        statusCode.setForeground(Color.white);
        timeStatus.setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));
        timeStatus.setForeground(Color.white);
        statusCode.setEditable(false);
        timeStatus.setEditable(false);
        statusCode.setPreferredSize(new Dimension(60, 30));
        timeStatus.setPreferredSize(new Dimension(60, 30));
        barPanel.add(statusCode);
        barPanel.add(timeStatus);
        add(barPanel);
    }

    /**
     * a method to add tabbed pane to panel
     */
    public void addTabbedPane() {
        gbc = new GridBagConstraints();
        gridBagLayout = new GridBagLayout();
        tabPanel = new JPanel();
        tabPanel.setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));
        tabPanel.setLayout(gridBagLayout);

        gbc.gridx = 0;
        gbc.gridy = 1;
        tabbedPane = new JTabbedPane();
        gridBagLayout.setConstraints(tabbedPane, gbc);

        String[] st2 = {"Raw", "Preview"};
        JComboBox bodyComboBox = new JComboBox(st2);
        JPanel bodyPanel = new JPanel();
        bodyPanel.setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));
        bodyPanel.add(bodyComboBox);
        JTextArea textArea = new JTextArea();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(300, 300));
        scrollPane.add(textArea);

        bodyPanel.add(scrollPane);
        tabbedPane.add("Body", bodyPanel);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));

        addKeyAndValue(headerPanel);


        tabbedPane.add("Header", headerPanel);
        tabPanel.add(tabbedPane);
        add(tabPanel);
    }

    /**
     * add text box to panel
     * @param headerPanel the panel which keeps headers
     */
    public void addKeyAndValue(JPanel headerPanel) {


        JPanel panel = new JPanel();
        panel.setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));


        GridBagLayout gridBagLayout2 = new GridBagLayout();
        GridBagConstraints gbc2 = new GridBagConstraints();
        headerPanel.setLayout(gridBagLayout2);
        JTextField keyField = new JTextField();
        JTextField valueField = new JTextField();
        keyField.setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));
        valueField.setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));
        keyField.setForeground(Color.white);
        valueField.setForeground(Color.white);


        keyField.setEditable(false);
        valueField.setEditable(false);
        JScrollPane scrollPane2 = new JScrollPane(keyField);
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        scrollPane2.setPreferredSize(new Dimension(220, 40));
        scrollPane2.setLocation(50, 20);
        gridBagLayout.setConstraints(scrollPane2, gbc2);
        panel.add(scrollPane2);
        JScrollPane scrollPane3 = new JScrollPane(valueField);
        gbc2.gridx = 1;
        gbc2.gridy = 0;
        scrollPane3.setPreferredSize(new Dimension(220, 40));
        scrollPane3.setLocation(50, 20);
        gridBagLayout.setConstraints(scrollPane3, gbc2);
        panel.add(scrollPane3);


        headerPanel.add(panel);

    }

    public void addCopyToClipBoard() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));
        JButton copy = new JButton("Copy To Clipboard");
        panel.add(copy);
        add(panel);
    }
}
