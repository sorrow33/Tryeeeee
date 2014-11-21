package com.controller;

        import com.view.TaquinGUI;
        import javax.swing.JFrame;

public class Taquin {

    public static void main(String[] args) {

        JFrame window = new JFrame("Taquin");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new TaquinGUI());
        window.pack();
        window.setResizable(true);
        window.setVisible(true);

        /*
        JFrame f = new JFrame("Test du compte à rebours");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(new TaquinGUI());
        f.add(new util.Chrono(60));
        f.pack();
        f.setResizable(true);
        f.setVisible(true);
        */
    }
}


