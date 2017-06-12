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
    
    public AbstractPiece(Couleur couleur, Coord coord) {
        this.couleur = couleur;
        this.coord = coord;
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
}
