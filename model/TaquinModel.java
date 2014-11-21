package com.model;

public class TaquinModel {

    private static final int ROWS = 4;
    private static final int COLS = 4;
    private Jeton[][] _contenu;  //Les jetons
    private Jeton _videJeton; // Le jeton vide

    // constructeur
    public TaquinModel() {
        _contenu = new Jeton[ROWS][COLS];
        reset();
    }

    // getValeur
    // Retourne la valeure du jeton dans une string.
    public String getValeur(int row, int col) {
        return _contenu[row][col].getValeur();
    }

    // reset
    // Initialise et mélange les jetons
    public void reset() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                _contenu[r][c] = new Jeton(r, c, "" + (r * COLS + c + 1));
            }
        }
        _videJeton = _contenu[ROWS - 1][COLS - 1];
        _videJeton.setValeur(null);

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                echangeJeton(r, c, (int) (Math.random() * ROWS), (int) (Math.random() * COLS)); // mélange
            }
        }
    }

    // bougerJeton
    // Déplace le jeton à la case vide si possible.
    // Renvoie vraie s'il y a eu déplacement, faux sinon.
    public boolean bougerJeton(int r, int c) {
        return estLibre(r, c, -1, 0) || estLibre(r, c, 1, 0)
                || estLibre(r, c, 0, -1) || estLibre(r, c, 0, 1);
    }

    // estLibre
    // Verifie s'il y a un jeton de libre à côté
    // Retourne vrai et échange si possible, sinon retourne faux.
    private boolean estLibre(int r, int c, int rdelta, int cdelta) {
        int rVoisin = r + rdelta;
        int cVoisin = c + cdelta;
        if (estLegalRC(rVoisin, cVoisin)
                && _contenu[rVoisin][cVoisin] == _videJeton) {
            echangeJeton(r, c, rVoisin, cVoisin);
            return true;
        }
        return false;
    }

    // estLegalRC
    // Vérifie si une colonne / ligne est bonne.
    public boolean estLegalRC(int r, int c) {
        return r >= 0 && r < ROWS && c >= 0 && c < COLS;
    }

    // echangeJeton
    // Echange les deux jetons.
    private void echangeJeton(int r1, int c1, int r2, int c2) {
        Jeton temp = _contenu[r1][c1];
        _contenu[r1][c1] = _contenu[r2][c2];
        _contenu[r2][c2] = temp;
    }
}

// class Jeton
class Jeton {

    private int _row;
    private int _col;
    private String _valeur;

    // constructeur
    public Jeton(int row, int col, String valeur) {
        _row = row;
        _col = col;
        _valeur = valeur;
    }

    // setValeur
    public void setValeur(String newVal) {
        _valeur = newVal;
    }

    // getValeur
    public String getValeur() {
        return _valeur;
    }

}