package httpClientFore.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Preview extends JPanel {

    private BufferedImage image;

    public Preview() {
//        try {
//            image = ImageIO.read(new File(HttpRequest.st));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
    }

}