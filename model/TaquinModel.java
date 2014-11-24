package com.model;

import java.util.Observable;

public class TaquinModel extends Observable {

    private int _rows;
    private int _cols;
    private Jeton[][] _contenu;  //Les jetons
    private Jeton _videJeton; // Le jeton vide

    public TaquinModel(int _rows, int _cols) {
        this._rows = _rows;
        this._cols = _cols;
        _contenu = new Jeton[_rows][_cols];
        reset();
    }

    // constructeur
    public TaquinModel() {
        this._rows=6;
        this._cols=6;
        _contenu = new Jeton[_rows][_cols];
        reset();

    }
    public int get_rows() {
        return _rows;
    }

    public int get_cols() {
        return _cols;
    }
    // Modifie le nombre de lignes
    public void set_cols(int cols){
        _cols = cols;
    }

    // Modifie le nombre de lignes
    public void set_rows(int rows){
        _rows = rows;
    }

    // getValeur
    // Retourne la valeur du jeton dans une string.
    public String getValeur(int row, int col) {
        return _contenu[row][col].getValeur();
    }

    // reset
    // Initialise et mélange les jetons
    public void reset() {
        int r;
        int c;
        for ( r = 0; r < _rows; r++) {
            for ( c = 0; c < _cols; c++) {
                _contenu[r][c] = new Jeton(r, c, "" + (r * _cols + c + 1));
            }
        }
        _videJeton = _contenu[_rows - 1][_cols - 1];
        _videJeton.setValeur(null);

        for ( r = 0; r < _rows; r++) {
            for ( c = 0; c < _cols; c++)
                echangeJeton(r, c, (int) (Math.random() * _rows), (int) (Math.random() * _cols)); // mélange
        }
    }

    public int ajoutCol()
    {
        return _cols++;
    }

    // bougerJeton
    // Déplace le jeton à la case vide si possible.
    // Renvoie vraie s'il y a eu déplacement, faux sinon.
    public boolean bougerJeton(int r, int c) {
        if (estLibre(r, c, -1, 0)) return true;
        if (estLibre(r, c, 1, 0)) return true;
        if (estLibre(r, c, 0, -1)) return true;
        return estLibre(r, c, 0, 1);
    }

    // estLibre
    // Verifie s'il y a un jeton de libre à côté
    // Retourne vrai et échange si possible, sinon retourne faux.
    private boolean estLibre(int r, int c, int rdelta, int cdelta) {
        int rVoisin = r + rdelta;
        int cVoisin = c + cdelta;
        if ((_contenu[rVoisin][cVoisin] == _videJeton))
            if (estLegalRC(rVoisin, cVoisin)) {
                echangeJeton(r, c, rVoisin, cVoisin);
                return true;
            }
        return false;
    }

    // estLegalRC
    // Vérifie si une colonne / ligne est bonne.
    public boolean estLegalRC(int r, int c) {
        if ((r < _rows))
            return (r >= 0) && (c >= 0) && (c < _cols);
        else {
            return false;
        }
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