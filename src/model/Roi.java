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
public class Roi extends AbstractPiece{

    public Roi(Couleur couleur, Coord coord) {
        super(couleur, coord);
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible) {
        boolean deplacement = abs(yFinal - this.getY()) == 1 && abs(xFinal - this.getX()) == 0 ||
                              abs(xFinal - this.getX()) == 1 && abs(yFinal - this.getY()) == 0 ||
                              abs(xFinal - this.getX()) == 1 && abs(yFinal - this.getY()) == 1;
        return Coord.coordonnees_valides(xFinal, yFinal) && deplacement;
    }

    @Override
    public String getName() {
        return "Roi";
    }
    
}
