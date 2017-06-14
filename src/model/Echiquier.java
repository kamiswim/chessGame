/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import static java.lang.Math.abs;

/**
 *
 * @author JeanClode
 */
public class Echiquier implements BoardGames{
    
    private Jeu jeu_noir;
    private Jeu jeu_blanc;
    private Jeu[] jeux;
    private int jeu_courrant;
    private String message;

    public Echiquier() {
        this.jeu_blanc = new Jeu(Couleur.BLANC);
        this.jeu_noir = new Jeu(Couleur.NOIR);

        jeux = new Jeu[2];
        jeux[0] = jeu_blanc;
        jeux[1] = jeu_noir;
                
        jeu_courrant = 0;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Echiquier{" + "jeu_noir=" + jeu_noir + "\n, jeu_blanc=" + jeu_blanc + '}';
    }
    
    public void switchJoueur(){
        this.jeu_courrant ++;
        this.jeu_courrant %= 2;
    }
    
    // ne prend pas en compte la case de destination
    private boolean collision(int xInit, int xFinal, int yInit, int yFinal){
        if(abs(xInit - xFinal) <= 1 && abs(yInit - yFinal) <= 1)
            return false;
        // Si le deplacement est valide on regarde si il n'y a pas de pièce intermédiaire
        // Et si la pièce n'est pas un cavalier (pas besoin de gestion de collision)
        if(!"Cavalier".equals(jeux[jeu_courrant].getPieceName(xInit, yInit))){
            if(abs(xInit - xFinal) > abs(yInit - yFinal)){
                // deplacement horizontal
                if((xInit - xFinal) < 0){
                    // droite
                    for(int x = xInit + 1; x < xFinal; x++){
                        if(jeu_blanc.isPieceHere(x, yFinal) || jeu_noir.isPieceHere(x, yFinal))
                            return true;
                    }
                } else {
                    // gauche
                    for(int x = xInit - 1; x > xFinal; x--){
                        if(jeu_blanc.isPieceHere(x, yFinal) || jeu_noir.isPieceHere(x, yFinal))
                            return true;
                    }
                }
            } else if (abs(xInit - xFinal) < abs(yInit - yFinal)) {
                // deplacement vertical
                if((yInit - yFinal) < 0){
                    // bas
                    for(int y = yInit + 1; y < yFinal; y++){
                        if(jeu_blanc.isPieceHere(xFinal, y) || jeu_noir.isPieceHere(xFinal, y))
                            return true;
                    }
                } else {
                    // haut
                    for(int y = yInit - 1; y > yFinal; y--){
                        if(jeu_blanc.isPieceHere(xFinal, y) || jeu_noir.isPieceHere(xFinal, y))
                            return true;
                    }
                }
            } else {
                // deplacement diagonal
                if((xInit - xFinal) < 0 && (yInit - yFinal) > 0){
                    // diagonal haut droite
                    int y = yInit - 1;
                    for(int x = xInit + 1; x < xFinal; x++){
                        if(jeu_blanc.isPieceHere(x, y) || jeu_noir.isPieceHere(x, y))
                            return true;
                        y --;
                    }
                }
                if((xInit - xFinal) < 0 && (yInit - yFinal) < 0){
                    // diagonal bas droite
                    int y = yInit + 1;
                    for(int x = xInit + 1; x < xFinal; x++){
                        if(jeu_blanc.isPieceHere(x, y) || jeu_noir.isPieceHere(x, y))
                            return true;
                        y ++;
                    }
                }
                if((xInit - xFinal) > 0 && (yInit - yFinal) > 0){
                    // diagonal haut gauche
                    int y = yInit - 1;
                    for(int x = xInit - 1; x > xFinal; x--){
                        if(jeu_blanc.isPieceHere(x, y) || jeu_noir.isPieceHere(x, y))
                            return true;
                        y --;
                    }
                }
                if((xInit - xFinal) > 0 && (yInit - yFinal) < 0){
                    // diagonal bas gauche
                    int y = yInit + 1;
                    for(int x = xInit - 1; x > xFinal; x--){
                        if(jeu_blanc.isPieceHere(x, y) || jeu_noir.isPieceHere(x, y))
                            return true;
                        y ++;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal){
        boolean fin = Coord.coordonnees_valides(xFinal, yFinal) && (xInit != xFinal || yInit != yFinal);
        boolean deplacement = jeux[jeu_courrant].isMoveOk(xInit, yInit, xFinal, yFinal, false, false);
        
        return fin && deplacement && !this.collision(xInit, xFinal, yInit, yFinal);
    }

    @Override
    public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
        if(this.isMoveOk(xInit, yInit, xFinal, yFinal)){
            jeux[jeu_courrant].move(xInit, yInit, xFinal, yFinal);
            return true;
        }
        return false;
    }
    
    public List<PieceIHMs> getPiecesIHM(){
        List<PieceIHMs> list_ihm = jeu_blanc.getPiecesIHM();
        list_ihm.addAll(jeu_noir.getPiecesIHM());
        return list_ihm;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Couleur getColorCurrentPlayer() {
        return jeux[jeu_courrant].getCouleur();
    }

    @Override
    public Couleur getPieceColor(int x, int y) {
        return jeux[jeu_courrant].getPieceColor(x, y);
    }

    public static void main(String args[]){
        Echiquier e = new Echiquier();
        System.out.println(e);
        System.out.println(e.isMoveOk(0, 6, 0, 5));
    }
}
