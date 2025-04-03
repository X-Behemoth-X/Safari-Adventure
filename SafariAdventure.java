import java.util.Random;
import java.util.Scanner;

public class SafariAdventure {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int totalPoints = 0;
        String[] areas = {"Jungle", "River", "Desert", "Mountains"};
        String[] events = {
            "You found an old campsite (no points)", "You gathered fresh water (+20 points)",
            "You found edible berries (+15 points)", "You spotted a bird (no points)",
            "You found a hidden food stash (+25 points)", "You discovered ancient ruins (+20 points)",
            "You encountered a lion (danger!)", "You were hit by a sandstorm (-5 points)",
            "A heavy storm begins (-10 points)", "You fished a rare species (+25 points)",
            "You saw a beautiful sunrise (no points)", "A crocodile attacked you (danger!)"
        };

        System.out.println("\nWelcome to Safari Adventure!\n");

        for (int day = 1; day <= 5; day++) {
            System.out.println("Day " + day + ":");
            String choice;

            do {
                System.out.print("Where would you like to go? (Jungle, Desert, River, Mountains): ");
                choice = scanner.nextLine().trim();
            } while (!isValidArea(choice, areas));

            System.out.println("You chose: " + choice);
            System.out.println("Exploring " + choice + "...\n");

            int eventsToday = 0;
            while (eventsToday < 3) {
                String event = events[random.nextInt(events.length)];
                System.out.println("Event " + (eventsToday + 1) + ": " + event);

                if (event.contains("bird") || event.contains("sunrise")) {
                    System.out.println("(Too small to track. Moving on.)\n");
                    eventsToday++;
                    continue;
                }

                if (event.contains("lion") || event.contains("crocodile")) {
                    System.out.print("Type 'run' to escape: ");
                    String action = scanner.nextLine().trim();
                    if (action.equalsIgnoreCase("run")) {
                        System.out.println("You escaped safely, ending the day early.\n");
                        break;
                    }
                }

                totalPoints += extractPoints(event);
                eventsToday++;
            }
            System.out.println("Day summary: " + totalPoints + " points earned so far.");
            System.out.println("-----------------------------------\n");
        }

        System.out.println("Safari Finished! You collected " + totalPoints + " points!");
        if (totalPoints >= 100) {
            System.out.println("You survived and completed the adventure!");
        } else {
            System.out.println("You didn't collect enough resources to survive. Try again!");
        }

        scanner.close();
    }

    public static boolean isValidArea(String choice, String[] areas) {
        for (String area : areas) {
            if (area.equalsIgnoreCase(choice)) {
                return true;
            }
        }
        System.out.println("Invalid area. Please choose again.\n");
        return false;
    }

    public static int extractPoints(String event) {
        if (event.contains("+")) {
            return Integer.parseInt(event.replaceAll("[^0-9]", ""));
        } else if (event.contains("-")) {
            return -Integer.parseInt(event.replaceAll("[^0-9]", ""));
        }
        return 0;
    }
}