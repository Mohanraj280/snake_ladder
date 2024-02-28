
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class snake_ladder {
    Random rand = new Random();
    int dice_faces = 6;

    public int rollDice() {
        return rand.nextInt(dice_faces) + 1;
    }

    public int movePlayer(int player, int diceValue, Map<Integer, Integer> snakes, Map<Integer, Integer> ladders) {
        int newPosition = player + diceValue;
        System.out.println("Move from " + player + " to " + newPosition);
        System.out.println();

        // Check for snake bite
        if (snakes.containsKey(newPosition)) {
            int oldPosition = newPosition;
            newPosition = snakes.get(newPosition);
            System.out.println("Oops! Snake bite! " + oldPosition + " -> " + newPosition);
            System.out.println();
        }

        // Check for ladder climb
        if (ladders.containsKey(newPosition)) {
            int oldPosition = newPosition;
            newPosition = ladders.get(newPosition);
            System.out.println("Wow! Climbed a ladder! " + oldPosition + " -> " + newPosition);
            System.out.println();
        }

        return newPosition;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String name1, name2;
        snake_ladder game = new snake_ladder();

        Map<Integer, Integer> snakes = new HashMap<>();
        snakes.put(17, 7);
        snakes.put(54, 34);
        snakes.put(62, 19);
        snakes.put(64, 60);
        snakes.put(87, 36);
        snakes.put(95, 75);
        snakes.put(93, 73);
        snakes.put(98, 79);

        Map<Integer, Integer> ladders = new HashMap<>();
        ladders.put(4, 14);
        ladders.put(9, 31);
        ladders.put(21, 42);
        ladders.put(28, 84);
        ladders.put(51, 67);
        ladders.put(80, 99);

        int WINNING_POSITION = 100;
        System.out.println("Welcome to Snake and Ladder Game");

        System.out.println("Enter user name1: ");
        name1 = scan.nextLine();

        System.out.println("Enter user name2: ");
        name2 = scan.nextLine();

        int position1 = 0;
        int position2 = 0;
        String currentPlayer = name1;

        while (true) {
            System.out.print(currentPlayer + " Press enter to roll the dice.");
            scan.nextLine();
            int diceValue = game.rollDice();
            System.out.println("Dice rolled: " + diceValue);

            if (currentPlayer.equals(name1)) {
                position1 = game.movePlayer(position1, diceValue, snakes, ladders);
                if (position1 >= WINNING_POSITION) {
                    System.out.println(name1 + " wins!");
                    System.out.println();
                    break;
                }
                currentPlayer = name2;
            } else {
                position2 = game.movePlayer(position2, diceValue, snakes, ladders);
                if (position2 >= WINNING_POSITION) {
                    System.out.println(name2 + " wins!");
                    System.out.println();
                    break;
                }
                currentPlayer = name1;
            }
        }
    }
}