package UI;

import java.util.Scanner;

public class IO {
    private static Scanner scanner = new Scanner(System.in);

    public static int getInt(){
        while (!scanner.hasNextInt()) { // <-- 'peeks' at, doesn't remove, the next token
            System.out.println("Please enter a number (INT)!");
            scanner.next(); // <-- skips over an invalid token
        }

        return scanner.nextInt();
    }

    public static double getDouble(){
        while (!scanner.hasNextDouble()) { // <-- 'peeks' at, doesn't remove, the next token
            System.out.println("Please enter a number!");
            scanner.next(); // <-- skips over an invalid token
        }

        return scanner.nextDouble();
    }

}
