package sudokusolver;

/**
 * @author jordan.amberg
 * @version 11.21.2021
 */
public class Tile {
    
    public final int row;
    public final int column;
    private int value;
    public final boolean given; //whether or not the tile is on the original board
    public final int subsection;
    public final int position;
    
    public Tile(int row, int column, int value, boolean given, int subsectionSize) {
        this.row = row;
        this.column = column;
        this.value = value;
        this.given = given;
        position = row * (subsectionSize * subsectionSize) + column;
        this.subsection = determineSubsection(subsectionSize);
    }
    //getters
    public int getValue(){
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    //finds the subsection (0-8) of the current tile
    private int determineSubsection(int subsectionSize){
        int subsectionRow = row / subsectionSize, subsectionColumn = column / subsectionSize;
        return subsectionRow * subsectionSize + subsectionColumn;
    }
}
