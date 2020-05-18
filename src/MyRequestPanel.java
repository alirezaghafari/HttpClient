import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/**
 * this is the first panel of the mainFrame and keeps a list of requests
 * @author Alireza Ghafari
 * @version 1.0
 */
public class MyRequestPanel extends JPanel {
    private GridBagConstraints gbc;
    private GridBagLayout gridBagLayout;
    private JComboBox comboBox;
    private ArrayList<String> requestList;


    public MyRequestPanel() {
        requestList = new ArrayList<>();
        requestList.add("Request 1");
        requestList.add("Request 2");

        setBackground(Color.darkGray);

        gridBagLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        setLayout(gridBagLayout);


        addComponents();


    }

    /**
     * add button to crates new request
     */
    public void addComponents() {
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;


        JLabel label = new JLabel("new: ");
        label.setFont(new Font("Arial", 10, 15));
        gridBagLayout.setConstraints(label, gbc);
        label.setForeground(Color.WHITE);
        label.setSize(10, 10);
        add(label);


        gbc.gridx = 1;
        gbc.gridy = 0;
        String[] st = {"", "Request", "Folder"};
        comboBox = new JComboBox(st);
        gridBagLayout.setConstraints(comboBox, gbc);
        comboBox.setPreferredSize(new Dimension(100, 20));
        add(comboBox);

        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));
        gbc.gridx = 1;
        gbc.gridy = 1;
        String[] RList = new String[requestList.size()];
        for (int i = 0; i < requestList.size(); i++)
            RList[i] = requestList.get(i);
        JList<String> list = new JList<String>(RList);
        list.setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));
        list.setFont(new Font("Arial", 10, 15));
        list.setForeground(Color.gray);
        list.setPreferredSize(new Dimension(200, 400));

        JScrollPane scrollPane = new JScrollPane(list);
        gridBagLayout.setConstraints(scrollPane, gbc);
        scrollPane.setPreferredSize(new Dimension(200, 400));
        panel2.add(scrollPane);

        add(panel2);


    }


}
