package connectedfishproblem;

/**
 *
 * @author jordan.amberg
 * @version 11.15.2021
 */
public class Client {
    
    public static void main(String[] args) {
        ConnectedFishProblem calculate = new ConnectedFishProblem(4,7); //width x height
        calculate.calculateProblem(1000000, false); //number of tests and whether or not it prints successful inventories
    }
}
