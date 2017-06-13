/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

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
    
    public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal){
        boolean fin = Coord.coordonnees_valides(xFinal, yFinal) && (xInit != xFinal || yInit != yFinal);
        boolean deplacement = jeux[jeu_courrant].isMoveOk(xInit, yInit, xFinal, yFinal, false, false);
        
        return fin && deplacement;
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
