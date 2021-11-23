package sudokusolver;

/**
 * @author jordan.amberg
 * @version 11.21.2021
 * This is a sudoku solver that will work on any size grid, given it's solvable.
 * the algorithm is quite simple and will brute force every square until it finds a number that is
 *  legal in that square, if the number gets to the maximum value for the square (9 on a standard board)
 * and is still invalid, then it will go back to the previous square and increment it until it finds the
 * next number that is valid. It will repeat this for every square until it finds the solution
 */

public class Solver {
    
     private final Board board;
    
    public Solver(Board board) {
        this.board = board;
    }
    //this function will solve the sudoku
    public void solve() {
        System.out.println(board.toString());
        boolean reversing = false;
        for(int i = 0; i < board.getLength(); i++){
            if(board.getTile(i).given){
                if(reversing)
                    i -= 2;  //goes back 2 since i will be incremented by 1 after iteration
                continue;
            }
            Tile tile = board.getTile(i);
            while(tile.getValue() < board.sectionSize){
               tile.setValue(tile.getValue() + 1); 
               if(isLegalValue(tile)){
                   reversing = false;
                   break;
               }
               if(tile.getValue() == board.sectionSize){
                   reversing = true;
               }
            }
            if(reversing){
                i -= 2; //goes back 2 since i will be incremented by 1 after iteration
                tile.setValue(0);
            }
        }
        System.out.println("SOLVED"); 
        System.out.println(board.toString()); 
    }
    
    public boolean isLegalValue(Tile tile){
        return(isLegalByRow(tile) && isLegalByColumn(tile) && isLegalBySubsection(tile)); //must be legal everywhere
    }
    //checks if the current row already contains the value being tested
    public boolean isLegalByRow(Tile tile){
        for(int positionInRow = 0; positionInRow < board.sectionSize; positionInRow++){
            if(positionInRow == tile.column){continue;} //doesn't add the value that is being tested into the String
            Tile currentTile = board.getTile(positionInRow + tile.row * board.sectionSize); //get the current tile in the array
            if(currentTile.getValue() == tile.getValue())
                return false;
        }
        return true;
    }
    //checks if the current column already contains the value being tested
    public boolean isLegalByColumn(Tile tile){
        for(int positionInColumn = 0; positionInColumn < board.sectionSize; positionInColumn++){
            if(positionInColumn == tile.row){continue;} //doesn't add the value that is being tested into the String
            Tile currentTile = board.getTile(positionInColumn * board.sectionSize + tile.column); //get the current tile in the array
            if(currentTile.getValue() == tile.getValue()){
                return false;
            }
        }
        return true;
    }
    //checks if the current subsection already contains the value being tested
    public boolean isLegalBySubsection(Tile tile){
        int currentSubsection = tile.subsection; 
        for(int i = 0; i < board.getLength(); i++){
            Tile t = board.getTile(i);
            boolean sameTile = t.row == tile.row && t.column == tile.column;
            if(sameTile){continue;} //skip the tile that is being checked
            if(t.subsection == currentSubsection && t.getValue() == tile.getValue()){
                return false;
            }
        }
        return true;
    }
}
