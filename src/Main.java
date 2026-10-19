import com.bello.rpg.model.Room;
import com.bello.rpg.model.Warrior;
import com.bello.rpg.model.Mage;
import com.bello.rpg.model.Character;
import com.bello.rpg.service.BattleService;
import com.bello.rpg.service.SaveServices;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            System.out.println("=============================================");
            System.out.println("WELCOME TO TERMINAL RPG");
            System.out.println("=============================================");
            System.out.println("[1] New Game");
            System.out.println("[2] Load Game");
            System.out.print("Select choice: ");
            int startChoice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            Character player = null;
            int startingRoomIndex = 0;

            if (startChoice == 2) {
                player = SaveServices.loadGame();
                if (player != null) {
                    startingRoomIndex = SaveServices.loadedRoomIndex;
                } else {
                    System.out.println("No valid save found. Starting a new game instead...\n");
                }
            }

            // If new game or load failed, run character creation
            if (player == null) {
                System.out.println("Enter your Hero's name!");
                String name = scanner.nextLine();
                System.out.println("Choose your class");
                System.out.println("[1] Warrior, Higher HP!");
                System.out.println("[2] Mage, Spell Attack!");
                int choice = scanner.nextInt();

                if (choice == 1) {
                    player = new Warrior(name, 100, 20);
                } else {
                    player = new Mage(name, 80, 15);
                }
            }

            Character enemy = new Warrior("Goblin", 70, 25);
            Character enemy1 = new Warrior("Dax", 100, 25);
            BattleService battleService = new BattleService();

            Room dungeon = new Room(1, "Dark labyrinth", enemy);
            Room coven = new Room(2, "Destructive chasm", enemy1);

            Room[] arena = {dungeon, coven};

            // Loop starting from saved index instead of always index 0
            for (int i = startingRoomIndex; i < arena.length; i++) {
                Room c = arena[i];
                c.enterRoom(player, battleService);

                if (!player.isAlive()) {
                    System.out.println("You have fallen in battle....GAME OVER!!!");
                    break;
                } else {
                    System.out.println("\n--- Room Cleared! ---");
                    System.out.println("[1] Proceed to next room");
                    System.out.println("[2] Save Game & Continue");
                    System.out.println("[3] Save Game & Quit");
                    System.out.print("Choose option: ");
                    int postRoomChoice = scanner.nextInt();

                    if (postRoomChoice == 2) {
                        SaveServices.saveGame(player, i + 1); // Save progress to next room
                    } else if (postRoomChoice == 3) {
                        SaveServices.saveGame(player, i + 1);
                        System.out.println("Thanks for playing! See you next time.");
                        return; // Exit game loop entirely
                    }
                }
            }

            System.out.println("\nWould you like to restart??");
            System.out.println("[1] Yes!");
            System.out.println("[2] No!");
            int choose = scanner.nextInt();

            if (choose == 2) {
                playAgain = false;
            }
        }
    }
}