package tracker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Utils {
    public static final Scanner input = new Scanner(System.in);
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String getTodayDate() {
        return LocalDate.now().format(DATE_FORMAT);
    }

    public static void printHeader(String title) {
        System.out.println("\n====== " + title + " ======");
    }

    public static void pressEnter() {
        System.out.print("\n<Press Enter to continue>");
        input.nextLine();
    }
}
