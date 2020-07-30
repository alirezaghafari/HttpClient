package httpClientFront.gui;


import httpClientBack.HttpRequest;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * a class to preview image in response panel
 * @version 7.30.20
 * @author Alireza Ghafari
 */
public class Preview extends JPanel {

    private BufferedImage image;


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public void previewImage(){
        try {
            image = ImageIO.read(new File(HttpRequest.st));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}