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
public interface Pieces {
    
    boolean capture();
    
    Couleur getCouleur();
    
    String getName();
    
    int getX();
    
    int getY();
    
    boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible);
    
    boolean move(int xFinal, int yFinal);
}
