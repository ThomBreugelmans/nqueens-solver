package NQueensSolver;

import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Welcome~!");
        Scanner input = new Scanner(System.in);

        boolean quit = false;
        while (!quit) {
            System.out.print("What size of the NQueens problem would you like to solve?: ");
            int nqueensSize = input.nextInt();
            // start timer
            long startTime = System.nanoTime();
            // call solver
            int numberOfSolutions = NQueens.getNQueensSolution(nqueensSize);
            // end timer
            long timeElapsed = System.nanoTime() - startTime;
            // print solution and time
            System.out.println("We have found " + numberOfSolutions + " of solutions to the NQueens problem of size " + nqueensSize);
            System.out.println("This took " + timeElapsed + "μs or " + (timeElapsed/1000.0d) + "ms or " + (timeElapsed/10000000.d) + "s\n");

            System.out.print("Would you like to solve another NQueens problem? (y/N");
            String choice = input.nextLine();
            if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
                continue;
            } else {
                quit = true;
            }
        }

        input.close();
    }
}
