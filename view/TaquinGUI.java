package com.view;

import com.model.TaquinModel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// class TaquinGUI
// Interface GUI
public class TaquinGUI extends JPanel {
    private final int NB_MAX=10;
    private final int NB_MIN=2;
    private GraphicsPanel _taquinGraphic;
    private TaquinModel _taquinModel = new TaquinModel();

    // constructeur
    public TaquinGUI() {

        // Crée un bouton et un écouteur

        JButton newGameButton = new JButton("Nouveau jeu");
        JButton addCol = new JButton("+ colonne");
        JButton delCol = new JButton("- colonne");
        JButton addLi = new JButton("+ ligne");
        JButton delLi = new JButton("- ligne");


        newGameButton.addActionListener(new NouveauJeuAction());
        addCol.addActionListener(new ajoutColAction());
        delCol.addActionListener(new delColAction());
        addLi.addActionListener(new ajoutLiAction());
        delLi.addActionListener(new delLiAction());

        // Crée un controlPanel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(newGameButton);
        controlPanel.add(addCol);
        controlPanel.add(delCol);
        controlPanel.add(addLi);
        controlPanel.add(delLi);

        // Crée un graphicsPanel
        _taquinGraphic = new GraphicsPanel();

        // Positionnement
        this.setLayout(new BorderLayout());
        this.add(controlPanel, BorderLayout.NORTH);
        this.add(_taquinGraphic, BorderLayout.CENTER);
    }

    class GraphicsPanel extends JPanel implements MouseListener {

        private int rows;
        private int cols;
        private int CASE_SIZE = 40;
        private Font _biggerFont;

        // constructeur
        public GraphicsPanel() {
            this.rows =_taquinModel.getRows();
            this.cols =_taquinModel.getCols();
            _biggerFont = new Font("SansSerif", Font.CENTER_BASELINE, CASE_SIZE / 4);
            this.setPreferredSize(
                    new Dimension(CASE_SIZE * cols, CASE_SIZE * rows)); // dimensions
            this.setBackground(Color.black); // arrière plan
            this.addMouseListener(this);  // Mouvements souris
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            cols=_taquinModel.getCols();
            rows=_taquinModel.getRows();
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    int x = c * CASE_SIZE;
                    int y = r * CASE_SIZE;
                    String text = _taquinModel.getValeur(r, c);
                    if (text != null) {
                        g.setColor(Color.gray);
                        g.fillRect(x + 2, y + 2, CASE_SIZE - 4, CASE_SIZE - 4);
                        g.setColor(Color.black);
                        g.setFont(_biggerFont);
                        g.drawString(text, x + 20, y + (3 * CASE_SIZE) / 4);
                    }
                }
            }
        }

        // Ecouteur souris bouton
        public void mousePressed(MouseEvent e) {

            int col = e.getX() / CASE_SIZE;
            int row = e.getY() / CASE_SIZE;

            if (!_taquinModel.bougerJeton(row, col)) {
                // bougerJeton déplace le jeton si vrai.
                Toolkit.getDefaultToolkit().beep();
            }

            this.repaint();  // Met a jour la vue
        }

        //Autres évènements ???.dlzdaoz,dazfa
        public void mouseClicked(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }
        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    // class NouveauJeuAction
    public class NouveauJeuAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            _taquinModel.reset();
            _taquinGraphic.repaint();
        }
    }

    private class ajoutColAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(_taquinModel.getCols()<NB_MAX) {
                _taquinModel.colPlusUn();
                _taquinModel = new TaquinModel(_taquinModel.getRows(), _taquinModel.getCols());
                _taquinModel.reset();
                _taquinGraphic.repaint();
            }
            else Toolkit.getDefaultToolkit().beep();
        }
}

    private class delColAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(_taquinModel.getCols()>NB_MIN) {
                _taquinModel.colMoinsUn();
                _taquinModel = new TaquinModel(_taquinModel.getRows(), _taquinModel.getCols());
                _taquinModel.reset();
                _taquinGraphic.repaint();
            }
            else Toolkit.getDefaultToolkit().beep();
        }
    }
    private class ajoutLiAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(_taquinModel.getRows()<NB_MAX) {
            _taquinModel.liPlusUn();
            _taquinModel = new TaquinModel(_taquinModel.getRows(),_taquinModel.getCols());
            _taquinModel.reset();
            _taquinGraphic.repaint();
            }
            else Toolkit.getDefaultToolkit().beep();
        }
    }

    private class delLiAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(_taquinModel.getRows()>NB_MIN) {
                _taquinModel.liMoinsUn();
                _taquinModel = new TaquinModel(_taquinModel.getRows(), _taquinModel.getCols());
                _taquinModel.reset();
                _taquinGraphic.repaint();
            }
            else Toolkit.getDefaultToolkit().beep();
        }
    }
}