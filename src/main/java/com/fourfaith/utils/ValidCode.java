//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidCode extends HttpServlet {
    private static final long serialVersionUID = 7984643570657748110L;
    private static final String generationStrategy = "1";

    public ValidCode() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ImageIO.write(this.getImage(request), "JPEG", response.getOutputStream());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    private BufferedImage getImage(HttpServletRequest request) {
        int width = 80;
        int height = 39;
        BufferedImage image = new BufferedImage(width, height, 1);
        Graphics g = image.getGraphics();
        g.setColor(new Color(14474460));
        g.fillRect(0, 0, width, height);
        g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height - 1);
        String rand = StringUtils.getRandomStr(4, true);
        g.setColor(Color.black);
        g.setFont(new Font("Atlantic Inline", 0, 18));
        String Str = rand.substring(0, 1);
        g.drawString(Str, 8, 27);
        Str = rand.substring(1, 2);
        g.drawString(Str, 25, 27);
        Str = rand.substring(2, 3);
        g.drawString(Str, 45, 27);
        Str = rand.substring(3, 4);
        g.drawString(Str, 65, 27);
        Random random = new Random();

        for(int i = 0; i < 150; ++i) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            g.drawOval(x, y, 0, 0);
        }

        g.dispose();
        request.getSession().setAttribute("validCode", rand);
        return image;
    }
}
