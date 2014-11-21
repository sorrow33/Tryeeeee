package com.controller;

        import com.view.TaquinGUI;

        import javax.swing.*;
        import java.awt.*;

public class Taquin {

    public static void main(String[] args) {
        JFrame window = new JFrame("Taquin");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new TaquinGUI());
        window.pack();
        window.setVisible(true);
        window.setResizable(false);
    }
}


