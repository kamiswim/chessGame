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
public abstract class AbstractPiece implements Pieces{
    
    private Couleur couleur;
    private Coord coord;
    private boolean moved;

    
    public AbstractPiece(Couleur couleur, Coord coord) {
        this.couleur = couleur;
        this.coord = coord;
        this.moved = false;
    }
    
    public boolean hasMoved(){
        return this.moved;
    }

    
    @Override
    public int getX(){
        return this.coord.x;
    }
    
    @Override
    public int getY(){
        return this.coord.y;
    }
    
    @Override
    public Couleur getCouleur(){
        return this.couleur;
    }
    
    @Override
    public String toString(){
        return this.getName()+" en ("+this.getX()+","+this.getY()+")";
    }
    
    @Override
    public boolean move(int xFinal, int yFinal){
        this.coord.x = xFinal;
        this.coord.y = yFinal;
        
        return true;
    }
    
    @Override
    public boolean capture(){
        this.coord.x = -1;
        this.coord.y = -1;
        
        return true;
    }
    
    @Override
    public abstract boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible);
    
    public static void main(String args[]){
        Pion pion = new Pion(Couleur.BLANC, new Coord(4,5));
        System.out.println(pion.isMoveOk(4, 4, true, true));
        System.out.println(pion.isMoveOk(4, 3, true, true)); // not ok (2 forward)
        System.out.println(pion.isMoveOk(4, 6, true, true)); // not ok (going back)
        System.out.println(pion.isMoveOk(5, 5, true, true)); // not ok (side move)

        Pion pion2 = new Pion(Couleur.NOIR, new Coord(4,5));
        System.out.println(pion2.isMoveOk(4, 6, true, true));
        System.out.println(pion2.isMoveOk(4, 4, true, true)); // not ok (going back)
        System.out.println(pion2.isMoveOk(4, 5, true, true)); // not ok (sur place)

        Fou fou = new Fou(Couleur.BLANC, new Coord(4,5));
        System.out.println(fou.isMoveOk(6, 3, true, true));
        System.out.println(fou.isMoveOk(6, 7, true, true));
        System.out.println(fou.isMoveOk(2, 7, true, true));
        System.out.println(fou.isMoveOk(2, 3, true, true));
        System.out.println(fou.isMoveOk(5, 5, true, true)); // not ok
        System.out.println(fou.isMoveOk(4, 5, true, true)); // sur place
        
        Tour tour = new Tour(Couleur.BLANC, new Coord(4,5));
        System.out.println(tour.isMoveOk(4, 3, true, true));
        System.out.println(tour.isMoveOk(3, 5, true, true));
        System.out.println(tour.isMoveOk(3, 3, true, true)); // not ok
        System.out.println(tour.isMoveOk(4, 5, true, true)); // sur place
        
        Cavalier cavalier = new Cavalier(Couleur.BLANC, new Coord(4,5));
        System.out.println(cavalier.isMoveOk(3, 3, true, true));
        System.out.println(cavalier.isMoveOk(5, 3, true, true));
        System.out.println(cavalier.isMoveOk(2, 4, true, true));
        System.out.println(cavalier.isMoveOk(6, 4, true, true));
        System.out.println(cavalier.isMoveOk(6, 6, true, true));
        System.out.println(cavalier.isMoveOk(2, 6, true, true));
        System.out.println(cavalier.isMoveOk(3, 7, true, true));
        System.out.println(cavalier.isMoveOk(5, 7, true, true));
        System.out.println(cavalier.isMoveOk(7, 5, true, true)); // not ok (ligne droite)
        System.out.println(cavalier.isMoveOk(4, 5, true, true)); // sur place
        
        Pieces roi = new Roi(Couleur.BLANC, new Coord(4,5));
        System.out.println(roi.isMoveOk(3, 4, true, true));
        System.out.println(roi.isMoveOk(4, 4, true, true));
        System.out.println(roi.isMoveOk(5, 4, true, true));
        System.out.println(roi.isMoveOk(3, 5, true, true));
        System.out.println(roi.isMoveOk(5, 5, true, true));
        System.out.println(roi.isMoveOk(3, 6, true, true));
        System.out.println(roi.isMoveOk(4, 6, true, true));
        System.out.println(roi.isMoveOk(5, 6, true, true));
        System.out.println(roi.isMoveOk(4, 5, true, true)); // not ok (sur place)
        
        Pieces reine = new Reine(Couleur.BLANC, new Coord(4,5));
        System.out.println(reine.isMoveOk(2, 5, true, true));
        System.out.println(reine.isMoveOk(2, 3, true, true));
        System.out.println(reine.isMoveOk(9, 5, true, true)); // not ok (outside of board)
        System.out.println(reine.isMoveOk(4, 5, true, true)); // not ok (sur place)
    }
}
