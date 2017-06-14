package vue;

import controller.ChessGameControlers;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import model.Coord;
import model.Couleur;
import model.PieceIHM;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static tools.ChessImageProvider.getImageFile;
 
public class ChessGameGUI extends JFrame implements MouseListener, MouseMotionListener, Observer{
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
    private Coord origin;
    private ChessGameControlers chessGameControler;
    private Dimension boardSize;
 
    public ChessGameGUI(String name, ChessGameControlers chessGameControler, Dimension boardSize){
        //  Use a Layered Pane for this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        this.boardSize = boardSize;
        this.chessGameControler = chessGameControler;
        
        //Add a chess board to the Layered Pane 
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0, 0, boardSize.width-1, boardSize.height-26);
        
        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel(new BorderLayout());
            chessBoard.add(square);

            int row = (i / 8) % 2;

            if (row == 0)
                square.setBackground( i % 2 == 0 ? Color.black : Color.white );
            else
                square.setBackground( i % 2 == 0 ? Color.white : Color.black );
        }
    }
    
    // Helper convertisant les coord d'un évènement en postion sur l'échiquier
    private Coord transformCoord(Coord coord){
        int pasX = chessBoard.getWidth() / 8;
        int pasY = chessBoard.getHeight() / 8;
        
        return new Coord(coord.x / pasX, coord.y / pasY);
    }

    @Override
    public void mousePressed(MouseEvent e){      
        chessPiece = null;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());

        if (c instanceof JPanel) 
            return;
        
        Coord co = transformCoord(new Coord(e.getX(), e.getY()));
        if(chessGameControler.isPlayerOK(co)){
            Point parentLocation = c.getParent().getLocation();
            xAdjustment = parentLocation.x - e.getX();
            yAdjustment = parentLocation.y - e.getY();
            chessPiece = (JLabel)c;
            chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
            chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
            
            origin = new Coord(e.getX() + xAdjustment, e.getY() + yAdjustment);
            
            layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
        } else {
            System.out.println("Not your turn");
        }
    }

    //Move the chess piece around
    @Override
    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null)
            return;
        chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
    }

    //Drop the chess piece back onto the chess board
    @Override
    public void mouseReleased(MouseEvent e) {
        if(chessPiece == null) return;
        
        Coord coord = transformCoord(new Coord(e.getX(), e.getY()));
        if(chessGameControler.move(transformCoord(origin), coord)){
            chessPiece.setVisible(false);
            Component c =  chessBoard.findComponentAt(e.getX(), e.getY());

            if (c instanceof JLabel){
                Container parent = c.getParent();
                System.out.println("parent"+parent);
                parent.remove(0);
                parent.add(chessPiece);
            } else {
                Container parent = (Container)c;
                parent.add(chessPiece);
            }

            chessPiece.setVisible(true);
        } else {
            chessPiece.setLocation(origin.x, origin.y);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mouseMoved(MouseEvent e) {
   }
    @Override
    public void mouseEntered(MouseEvent e){

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
    
    private void clear(){
        JPanel pan;
        for(Component c : chessBoard.getComponents()){
            pan = (JPanel) c;
            pan.removeAll();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        clear();
        
        List<PieceIHM> piecesIHM = (List<PieceIHM>) arg;
        JLabel piece;
        JPanel panel;

        //Add a few pieces to the board
        for(PieceIHM pieceIHM : piecesIHM) {
            String type = (pieceIHM.getNamePiece());
            Couleur couleur = pieceIHM.getCouleur();

            piece = new JLabel(new ImageIcon(getImageFile(type, couleur)));
            panel = (JPanel) chessBoard.getComponent(pieceIHM.getX() + (8 * pieceIHM.getY()));
            panel.add(piece);
        }
    }
}
