/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author JeanClode
 */
public class Tour extends AbstractPiece{

    public Tour(Couleur couleur, Coord coord) {
        super(couleur, coord);
    }
    
    @Override
    public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible){
        boolean deplacement = (xFinal == this.getX() && yFinal != this.getY()) || (xFinal != this.getX() && yFinal == this.getY());
        return Coord.coordonnees_valides(xFinal, yFinal) && deplacement;
    }

    @Override
    public String getName() {
        return "Tour";
    }
}
