/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.LinkedList;
import java.util.List;
import tools.ChessPiecesFactory;

/**
 *
 * @author JeanClode
 */
public class Jeu implements Game{
    
    private Couleur couleur;
    private List<Pieces> pieces;

    public Jeu(Couleur couleur){
        this.pieces = ChessPiecesFactory.newPieces(couleur);
        this.couleur = couleur;
    }
    
    private Pieces findPiece(int x, int y){
        for(Pieces piece : pieces){
            if(piece.getX() == x && piece.getY() == y){
                return piece;
            }
        }
        return null;
    }

    public Couleur getCouleur() {
        return couleur;
    }
    
    public Couleur getPieceColor(int x, int y){
        Pieces tmp;
        if((tmp = this.findPiece(x, y)) != null)
            return tmp.getCouleur();
        return Couleur.NOIRBLANC;
    }
    
    public String getPieceName(int x, int y){
        Pieces tmp;
        if((tmp = this.findPiece(x, y)) != null)
                return tmp.getName();
        return "None";
    }

    @Override
    public boolean isPieceHere(int x, int y) {
        Pieces tmp;
        if((tmp = this.findPiece(x, y)) != null)
                return true;
        return false;
    }
    
    /**
     * @return une version réduite de la liste des pièces en cours
     * ne donnant que des accès en lecture sur des PiècesIHMs
     * (type piece + couleur + coordonnées)
     */
    public List<PieceIHMs> getPiecesIHM(){
        PieceIHMs newPieceIHM = null;
        List<PieceIHMs> list = new LinkedList<PieceIHMs>();
        
        for(Pieces piece : pieces){
            // si pièce toujours en jeu
            if(piece.getX() != -1){
                newPieceIHM = new PieceIHM(piece);
                list.add(newPieceIHM);
            }
        }
        return list;
    }

    @Override
    public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible) {
        return this.findPiece(xInit, yInit).isMoveOk(xFinal, yFinal, isCatchOk, isCastlingPossible);
    }

    @Override
    public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
        return this.findPiece(xInit, yInit).move(xFinal, yFinal);
    }
    
    @Override
    public String toString() {
        String ret = "Jeu{pieces=";
        for(Pieces piece : pieces){
            ret+=piece.toString();
        }
        return  ret+'}';
    }

    @Override
    public boolean capture(int xCatch, int yCatch) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String args[]){
        Jeu jeu = new Jeu(Couleur.BLANC);
        System.out.println(jeu.getPieceColor(0, 7));
        System.out.println(jeu.getPieceName(0, 7));
        System.out.println(jeu.isPieceHere(0, 7));
        System.out.println(jeu);
    }
}
