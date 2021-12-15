package Pieces;

import Interfaces.Drawable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author jordan.amberg
 */
public class Board implements Drawable{
    
    private Tile[][] chessBoard;
    private ArrayList<Pawn> pawns = new ArrayList(16);
    private ArrayList<Rook> rooks = new ArrayList(4);
    private ArrayList<Knight> knights = new ArrayList(4);
    private Color pieceBlackColor = Color.BLACK; //piece
    private Color pieceWhiteColor = Color.WHITE; //piece
    private Color tileDarkColor = new Color(102, 51, 0); //light brown
    private Color tileLightColor = new Color(153, 102, 0); //brown
    
    public Board(){
        chessBoard = createEmptyBoard();
        setupChess();
    }
    
    public Tile[][] createEmptyBoard(){
        Tile[][] board = new Tile[8][8];
        for(int rank = 0; rank < 8; rank++){
            for(int file = 0; file < 8; file++){
                Color colorOfTile = determineTileColor(rank, file);
                board[rank][file] = new Tile(false, colorOfTile, file, rank);
            }
        }
        return board;
    }
    
    public void setupChess(){   
        setupPawns();
        setupRooks();
        setupKnights();
    }
    
    public Color determineTileColor(int rank, int file){
        Color c = tileLightColor;
        if((rank + file) % 2 == 0)
            return tileDarkColor;
        return c;
    }

    public void setupPawns(){
        for(int file = 0; file < 8; file++){
            Pawn p = new Pawn(chessBoard[1][file], pieceBlackColor);
            pawns.add(p);
        }
        for(int file = 0; file < 8; file++){
            Pawn p = new Pawn(chessBoard[6][file], pieceWhiteColor);
            pawns.add(p);
        }
    }
    
    public void setupRooks(){
        rooks.add(new Rook(chessBoard[0][0], pieceBlackColor));
        rooks.add(new Rook(chessBoard[0][7], pieceBlackColor));
        rooks.add(new Rook(chessBoard[7][0], pieceWhiteColor));
        rooks.add(new Rook(chessBoard[7][7], pieceWhiteColor)); 
    }
    
    public void setupKnights(){
        knights.add(new Knight(chessBoard[0][1], pieceBlackColor));
        knights.add(new Knight(chessBoard[0][6], pieceBlackColor));
        knights.add(new Knight(chessBoard[7][1], pieceWhiteColor));
        knights.add(new Knight(chessBoard[7][6], pieceWhiteColor));
    }
    
    @Override
    public void draw(Graphics g) {
        for(int rank = 0; rank < 8; rank++){
            for(int file = 0; file < 8; file++){
                chessBoard[rank][file].draw(g);
            }
        }
        drawPieces(g);
    }
    
    public void drawPieces(Graphics g){
        for(Pawn p: pawns)
            p.draw(g);
        for(Rook r: rooks)
            r.draw(g);
        for(Knight k: knights){
            k.draw(g);
        }
    }
}
