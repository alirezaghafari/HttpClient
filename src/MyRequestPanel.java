import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * this is the first panel of the mainFrame and keeps a list of requests
 *
 * @author Alireza Ghafari
 * @version 1.0
 */
public class MyRequestPanel extends JPanel {
    private JComboBox comboBox;
    private JList<File> requestList;


    public MyRequestPanel() {
        setBackground(Color.darkGray);
        setLayout(new BorderLayout());

        addComponents();
        initDirectoryList();
    }

    private void initDirectoryList() {
        File[] files = FileUtils.getFilesInDirectory();
        requestList = new JList<>(files);

        requestList.setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));

        requestList.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
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
    public void addComponents() {

        JPanel panel1 = new JPanel();
        JLabel label = new JLabel("new: ");
        label.setFont(new Font("Arial", 10, 15));
        label.setForeground(Color.WHITE);
        label.setSize(10, 10);
        panel1.add(label, BorderLayout.PAGE_START);


        String[] st = {"", "Request", "Folder"};
        comboBox = new JComboBox(st);
        comboBox.setPreferredSize(new Dimension(100, 20));
        panel1.add(comboBox);

        panel1.setSize(200, 50);
        panel1.setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));
        add(panel1, BorderLayout.NORTH);

//        JPanel panel2 = new JPanel();
//        panel2.setLayout(new BorderLayout());
//        panel2.setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));
//
//
//        list.setBackground(Color.getHSBColor(0.16666667f, 0.06666667f, 0.1764706f));
//        list.setFont(new Font("Arial", 10, 15));
//        list.setForeground(Color.gray);
//        list.setPreferredSize(new Dimension(200, 400));
//
//        JScrollPane scrollPane = new JScrollPane(list);
//        scrollPane.setPreferredSize(new Dimension(200, 400));
//        panel2.add(scrollPane, BorderLayout.CENTER);
//
//        add(panel2, BorderLayout.CENTER);

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
}
