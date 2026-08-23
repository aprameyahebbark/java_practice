import java.util.Scanner;

public class RockPaper {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int[] dp = new int[4];
        System.out.println("1. Stone");
        System.out.println("2. Paper");
        System.out.println("3. Scissors");
        System.out.print("Enter your choice: ");
        int user = sc.nextInt();
        int computer = (int)(Math.random() * 3) + 1;
        System.out.println("Computer chose: " + computer);
        if (user == computer) {
            System.out.println("Draw!");
            dp[0] = 1;
        }
        else if ((user == 1 && computer == 3) ||
                (user == 2 && computer == 1) ||
                (user == 3 && computer == 2)) {
            System.out.println("You win!");
            dp[1] = 1;
        }
        else {
            System.out.println("Computer wins!");
            dp[2] = 1;
        }
        System.out.println("Result stored in DP array:");
        System.out.println("Draw: " + dp[0]);
        System.out.println("Player Win: " + dp[1]);
        System.out.println("Computer Win: " + dp[2]);
        sc.close();
    }
}