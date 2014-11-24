package com.view;

        import com.model.TaquinModel;
        import java.awt.*;
        import java.awt.event.*;
        import javax.swing.*;

// class TaquinGUI
// Interface GUI
public class TaquinGUI extends JPanel {

    private GraphicsPanel _taquinGraphic;
    private TaquinModel _taquinModel = new TaquinModel();

    // constructeur
    public TaquinGUI() {

        // Crée un bouton et un écouteur
        JLabel Name = new JLabel("Nom: ");
        JTextField nameField = new JFormattedTextField("Entre ton nom...");
        JLabel numberC = new JLabel("Nombre de colonnes et de lignes souhaitées : ");
        JTextField numberCase = new JFormattedTextField();
        JButton newGameButton = new JButton("Nouveau jeu");
        newGameButton.addActionListener(new NouveauJeuAction());

        // Crée un controlPanel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(newGameButton);
        controlPanel.add(Name);
        controlPanel.add(nameField);
        controlPanel.add(numberC);
        controlPanel.add(numberCase);

        // Crée un graphicsPanel
        _taquinGraphic = new GraphicsPanel();

        // Positionnement
        this.setLayout(new BorderLayout());
        this.add(controlPanel, BorderLayout.NORTH);
        this.add(_taquinGraphic, BorderLayout.CENTER);
    }

    class GraphicsPanel extends JPanel implements MouseListener {

        private int ROWS = 4;
        private int COLS = 4;
        private static final int CASE_SIZE = 80;
        private Font _biggerFont;

        // constructeur
        public GraphicsPanel() {
            _biggerFont = new Font("SansSerif", Font.BOLD, CASE_SIZE / 2);
            this.setPreferredSize(
                    new Dimension(CASE_SIZE * COLS, CASE_SIZE * ROWS)); // dimensions
            this.setBackground(Color.black); // arrière plan
            this.addMouseListener(this);  // Mouvements souris
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int r = 0; r < ROWS; r++) {
                for (int c = 0; c < COLS; c++) {
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
}