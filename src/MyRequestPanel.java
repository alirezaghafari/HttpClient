import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/**
 * this is the first panel of the mainFrame and keeps a list of requests
 * @author Alireza Ghafari
 * @version 1.0
 */
public class MyRequestPanel extends JPanel {
    private JComboBox comboBox;
    private ArrayList<String> requestList;


    public MyRequestPanel() {
        requestList = new ArrayList<>();
        requestList.add("Request 1");
        requestList.add("Request 2");

        setBackground(Color.darkGray);
        setLayout(new BorderLayout());

        addComponents();
    }


    /**
     * add button to crates new request
     */
    public void addComponents() {

        JPanel panel1=new JPanel();
        JLabel label = new JLabel("new: ");
        label.setFont(new Font("Arial", 10, 15));
        label.setForeground(Color.WHITE);
        label.setSize(10, 10);
        panel1.add(label,BorderLayout.PAGE_START);


        String[] st = {"", "Request", "Folder"};
        comboBox = new JComboBox(st);
        comboBox.setPreferredSize(new Dimension(100, 20));
        panel1.add(comboBox);

        panel1.setSize(200,50);
        panel1.setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));
        add(panel1,BorderLayout.NORTH);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));

        String[] RList = new String[requestList.size()];
        for (int i = 0; i < requestList.size(); i++)
            RList[i] = requestList.get(i);
        JList<String> list = new JList<String>(RList);
        list.setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));
        list.setFont(new Font("Arial", 10, 15));
        list.setForeground(Color.gray);
        list.setPreferredSize(new Dimension(200, 400));

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(200, 400));
        panel2.add(scrollPane, BorderLayout.CENTER);

        add(panel2, BorderLayout.CENTER);

    }
}
