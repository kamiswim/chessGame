/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static java.lang.Math.abs;

/**
 *
 * @author JeanClode
 */
public class Reine extends AbstractPiece{

    public Reine(Couleur couleur, Coord coord) {
        super(couleur, coord);
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible) {
        boolean deplacement = (abs(xFinal - this.getX()) == abs(yFinal - this.getY()) &&
                               abs(xFinal - this.getX()) != 0 && abs(yFinal - this.getY()) != 0) ||
                              ((xFinal == this.getX() && yFinal != this.getY()) ||
                               (xFinal != this.getX() && yFinal == this.getY()));
        return Coord.coordonnees_valides(xFinal, yFinal) && deplacement;
    }

    @Override
    public String getName() {
        return "Reine";
    }
    
}
