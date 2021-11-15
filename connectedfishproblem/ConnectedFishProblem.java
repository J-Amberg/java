package connectedfishproblem;

import java.util.Random;
/** @author jordan.amberg
 *  @version 11.15.2021
 *  This is a random non-applicable program I made to check what the odds of 
 *  getting a connected inventory of trout and salmon when fishing in Runescape.
 *  Fish are caught randomly with Ratio of trout to salmon 58:42.
 *  I accomplished this using floodFill recursively.
 *  connected example: https://imgur.com/Sith3vG
 *  non-connected example: https://imgur.com/kxtO9lp
 */
public class ConnectedFishProblem {
    
    private int height;
    private int width;
    private int[][] inventory;
    private int fishConnected = 0;
    private int numConnectedInventories = 0;
    
    public ConnectedFishProblem(int width, int height){
        //height is numRows, width is numColumns
        this.height = height;
        this.width = width;
    }
    //parameters numInventories is number of tests and print is whether successful inventories are printed
    public double calculateProblem(int numInventories, boolean print){
        for(int i = 0; i < numInventories; i++){
            fishConnected = 0;
            generateInventory();
            int[][] copy = copyArray(inventory);
            String indexes = findFish(inventory); //finds indexes of both a trout and a salmon
            int troutCol, troutRow, salmonCol, salmonRow;
            if(indexes.length() < 3){
                numConnectedInventories++;
                if(print)
                    printRows(copy); 
                continue;
            } //gets trout and salmon indexes
            troutCol = Integer.parseInt(String.valueOf(indexes.charAt(0)));
            troutRow = Integer.parseInt(String.valueOf(indexes.charAt(1)));
            salmonCol = Integer.parseInt(String.valueOf(indexes.charAt(2)));
            salmonRow = Integer.parseInt(String.valueOf(indexes.charAt(3)));
            floodFill(troutRow, troutCol, 0);
            floodFill(salmonRow, salmonCol, 1);
            //checks if all fish are connected
            if(fishConnected == width * height){
                numConnectedInventories++;
                if(print)
                    printRows(copy);
            }
        }
        double percentChance = ((double)numConnectedInventories/(double)numInventories) * 100.00;
        System.out.println("Based on " + numInventories + " tests, there were " 
        + numConnectedInventories + " connected inventories" + " with a success "
        + "chance of " + (percentChance) + "%");
        return percentChance;
    }
    //floodFill recursive algorithm
    public void floodFill(int row, int column, int target){
        if(inventory[column][row] == target){
            fishConnected++;
            inventory[column][row] = 3;
            if(row != 6)
                floodFill( row + 1, column, target);
            if(row != 0)
                floodFill(row - 1, column, target);
            if(column != 3)
                floodFill(row, column + 1, target);
            if(column != 0)
                floodFill(row, column - 1, target);
        }
    }
    //simple function used to create a copy of an array
    public int[][] copyArray(int[][] original){
        int[][] copy = new int[4][7];
        for(int i = 0; i < original.length; i++){
            for(int j = 0; j < original[0].length; j++){
                copy[i][j] = original[i][j];
            }
        }
        return copy;
    }
    //finds and returns indexes of a trout and a salmon
    public String findFish(int[][] inventory){
        String indexes = "";
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(inventory[i][j] == 0){
                    indexes += i;
                    indexes += j;
                    i = 1000000; //exits loops
                    j = 1000000;
                }
            }
        }
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(inventory[i][j] == 1){
                    indexes += i;
                    indexes += j;
                    i = 1000000;
                    j = 1000000; //exits loops
                }
            }
        }
        return indexes;
    }
    //prints the inventory
    public void printRows(int[][] inventory){
        System.out.println("-------");
        for(int i = 0; i < height; i++){
            System.out.println(inventory[0][i] + " " + inventory[1][i] + " " + inventory[2][i] + " " + inventory[3][i]);
        }
    }
    //generates an inventory of fish based on height and width 
    public void generateInventory(){
        Random rand = new Random();
        inventory = new int[4][7];
         for(int i = 0; i < inventory.length; i++){
             for(int j = 0; j < inventory[0].length; j++){
                int fish = rand.nextInt((100 - 1) + 1) + 1;
                if(fish <= 58){
                    inventory[i][j] = 0; //0 for trout
                }
                else{
                    inventory[i][j] = 1; //1 for salmon
                }
            }
        }
    }
}
