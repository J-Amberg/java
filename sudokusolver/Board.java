/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver;

/**
 *
 * @author jordan.amberg
 */
public class Board {
    
    private final Tile[] tiles;
    public final int sectionSize;
    public final int subsectionSize;
    
    public Board(int[][] array){
        //this will convert the given 2d int array into a single dimensional tile array
        sectionSize = array.length;
        subsectionSize = (int)Math.sqrt((double)sectionSize);
        Tile[] originalBoard = new Tile[sectionSize * sectionSize];
        for(int i = 0; i < originalBoard.length; i++){
            int row = i / sectionSize, column = i % sectionSize;
            boolean given = false; 
            if(array[row][column] != 0){ //check if the current tile has a starting value or is empty
                given = true;
            }
            originalBoard[i] = new Tile(row, column, array[row][column], given, subsectionSize);
        }
        tiles = originalBoard;
    }
    
    public int getLength(){
        return tiles.length;
    }
    public Tile getTile(int i){
        return tiles[i];
    }
    public void setTile(Tile tile){
        tiles[tile.position] = tile;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Tile t : tiles){
            if(t.getValue() == 0)
                sb.append("." + " "); //prints . if tile is empty
            else 
                sb.append(t.getValue()).append(" "); 
            if(t.column == sectionSize - 1 && t.row != sectionSize - 1)
                sb.append("\n");
        }
        return sb.toString();
    }
}
