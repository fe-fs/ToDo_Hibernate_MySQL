import org.hibernate.SessionFactory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        // Create a SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Pass the SessionFactory when creating a new Todo_db_DAO
        Todo_db_DAO todo = new Todo_db_DAO(sessionFactory);
        Scanner scanner = new Scanner(System.in);

        //menu
        while (true) {
            System.out.println("1. Add a to-do item");
            System.out.println("2. Delete a to-do item");
            System.out.println("3. View to-do items");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the item to add: ");
                    String addItem = scanner.nextLine();
                    todo.todoAddItem(addItem);  // Use 'todo' instead of 'todoListFacade'
                    break;
                case 2:
                    System.out.print("Enter the id of the item to delete: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();  // consume newline
                    todo.todoDelItem(id);
                case 3:
                    System.out.println("To-do items:");
                    todo.todoViewItem();  // Use 'todo' instead of 'todoListFacade'
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
