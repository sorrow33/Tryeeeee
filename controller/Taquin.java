package com.controller;

        import com.view.TaquinGUI;

        import javax.swing.*;
        import java.awt.*;

public class Taquin {

    public static void main(String[] args) {

        JPanel chrono = new JPanel();
        chrono.add(new util.Chrono(120));

        JFrame game = new JFrame("Taquin");
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.getContentPane().setLayout(new GridLayout(1,2));
        //game.getContentPane().add(chrono);
        game.getContentPane().add(new TaquinGUI());
        game.setSize(400,600);
        game.setResizable(true);
        game.setVisible(true);

    }
}


