package com.model;

public class TaquinModel {

    private int rows;
    private int cols;
    private Jeton[][] _contenu;  //Les jetons

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    private Jeton _videJeton; // Le jeton vide

    // constructeur
    public TaquinModel() {
        this.rows=6;
        this.cols=6;
        _contenu = new Jeton[rows][cols];
        reset();
    }
    public TaquinModel(int rows,int cols) {
        this.rows=getRows();
        this.cols=getCols();
        _contenu = new Jeton[rows][cols];
        reset();
    }


    // Modifie le nombre de lignes
    public void setCols(int cols){
        this.cols = cols;
    }

    // Modifie le nombre de lignes
    public void setRows(int rows){
        this.rows = rows;
    }

    // getValeur
    // Retourne la valeur du jeton dans une string.
    public String getValeur(int row, int col) {
        return _contenu[row][col].getValeur();
    }

    // reset
    // Initialise et mélange les jetons
    public void reset() {
        this.rows=getRows();
        this.cols=getCols();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) _contenu[r][c] = new Jeton(r, c, "" + (r * cols + c + 1));
        }
        _videJeton = _contenu[rows - 1][cols - 1];
        _videJeton.setValeur(null);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++)
                echangeJeton(r, c, (int) (Math.random() * rows), (int) (Math.random() * cols)); // mélange
        }
    }

    // bougerJeton
    // Déplace le jeton à la case vide si possible.
    // Renvoie vraie s'il y a eu déplacement, faux sinon.
    public boolean bougerJeton(int r, int c) {
        if (estLibre(r, c, -1, 0)) return true;
        if (estLibre(r, c, 1, 0)) return true;
        if (estLibre(r, c, 0, -1)) return true;
        if (estLibre(r, c, 0, 1)) return true;
        return false;
    }

    // estLibre
    // Verifie s'il y a un jeton de libre à côté
    // Retourne vrai et échange si possible, sinon retourne faux.
    private boolean estLibre(int r, int c, int rdelta, int cdelta) {
        int rVoisin = r + rdelta;
        int cVoisin = c + cdelta;
        if (estLegalRC(rVoisin, cVoisin)
                && (_contenu[rVoisin][cVoisin] == _videJeton)) {
            echangeJeton(r, c, rVoisin, cVoisin);
            return true;
        }
        return false;
    }

    // estLegalRC
    // Vérifie si une colonne / ligne est bonne.
    public boolean estLegalRC(int r, int c) {
        return (r >= 0) && (r < rows) && (c >= 0) && (c < cols);
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