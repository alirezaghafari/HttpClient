package httpClientFore.gui;


import httpClientBack.HttpRequest;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Preview extends JPanel {

    private BufferedImage image;


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public void previewImage(){
        try {
            image = ImageIO.read(new File(HttpRequest.st+".jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}