import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;


/**
 * this is the first panel of the mainFrame and keeps a list of requests
 *
 * @author Alireza Ghafari
 * @version 2.0
 */
public class MyRequestPanel extends JPanel {
    private JComboBox comboBox;
    private JList<File> requestList;
    private JPanel topPanel;
    private JLabel label;
    private static final Color defaultThemeColor = Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f);

    public MyRequestPanel() {

        setLayout(new BorderLayout(0,0));

        addTopPanel();
        initDirectoryList();
        setTheme("dark");

    }

    private void initDirectoryList() {
        File[] files = FileUtils.getFilesInDirectory();
        requestList = new JList<>(files);


        requestList.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        requestList.setFixedCellHeight(25);
        requestList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        requestList.setVisibleRowCount(2);
        requestList.setMinimumSize(new Dimension(130, 100));
        requestList.setMaximumSize(new Dimension(130, 100));
        requestList.setFixedCellWidth(150);
        requestList.setCellRenderer(new MyCellRenderer());


        add(new JScrollPane(requestList), BorderLayout.CENTER);
    }


    /**
     * add button to crates new request
     */
    public void addTopPanel() {

        topPanel = new JPanel();
        label = new JLabel("new: ");
        label.setFont(new Font("Arial", 10, 15));
        label.setForeground(Color.WHITE);
        label.setSize(10, 10);
        topPanel.add(label, BorderLayout.PAGE_START);


        String[] st = {"", "Request", "Folder"};
        comboBox = new JComboBox(st);
        comboBox.setPreferredSize(new Dimension(100, 20));
        topPanel.add(comboBox);

        topPanel.setSize(200, 50);
        topPanel.setBackground(defaultThemeColor);
        add(topPanel, BorderLayout.NORTH);



    }

    private class MyCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object object, int index, boolean isSelected, boolean cellHasFocus) {
            if (object instanceof File) {
                File file = (File) object;
                setText(file.getName());
                setIcon(FileSystemView.getFileSystemView().getSystemIcon(file));
                if (isSelected) {
                    setBackground(list.getSelectionBackground());
                    setForeground(list.getSelectionForeground());
                } else {
                    setBackground(list.getBackground());
                    setForeground(list.getForeground());
                }
                setEnabled(list.isEnabled());
            }
            return this;
        }
    }
    public void setTheme(String st){
        if(st.equalsIgnoreCase("light")){
            requestList.setBackground(new Color(133,125,202));
            requestList.setForeground(Color.white);
            topPanel.setBackground(new Color(133,125,202));
            label.setForeground(Color.white);
            setBackground(Color.white);
        }else{
            requestList.setBackground(new Color(50,51,48));
            requestList.setForeground(Color.white);
            topPanel.setBackground(new Color(50,51,48));
            label.setForeground(Color.WHITE);
            setBackground(defaultThemeColor);

        }

    }

}
