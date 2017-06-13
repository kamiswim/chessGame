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
public class Pion extends AbstractPiece{
    
    public Pion(Couleur couleur, Coord coord) {
        super(couleur, coord);
    }
    
    @Override
    public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible) {
        boolean deplacement = (this.getCouleur() == Couleur.BLANC) && (xFinal == this.getX() && (yFinal - this.getY() == -1)) ||
                              !this.hasMoved() && (this.getCouleur() == Couleur.BLANC) && (xFinal == this.getX() && (yFinal - this.getY() == -2)) ||
                              (this.getCouleur() == Couleur.NOIR)  && (xFinal == this.getX() && (yFinal - this.getY() == 1)) ||
                              !this.hasMoved() && (this.getCouleur() == Couleur.NOIR)  && (xFinal == this.getX() && (yFinal - this.getY() == 2));
        return Coord.coordonnees_valides(xFinal, yFinal) && deplacement;
    }

    @Override
    public String getName() {
        return "Pion";
    }
    
}
