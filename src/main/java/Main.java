/*Modulo 05 - Assignment
 * Java Todo List Application with Hibernate and MySQL database
 * Name: Fernanda Frederico Ribeiro da Silva
 * Class: Software Development II CEN-4025C-24671
 * Professor: Walauskis
 */
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
            //view todo do list
            todo.todoViewItem();  // Use 'todo' instead of 'todoListFacade'

            //menu
            System.out.println("\n1. Add a to-do item");
            System.out.println("2. Delete a to-do item");
            System.out.println("3. Check or Uncheck item");
            System.out.println("4. Exit");
            System.out.println("5. RESET LIST");
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
                    todo.resetIdIfEmpty();
                    break;
                case 3:
                    System.out.print("Enter the id of the item to check: ");
                    id = scanner.nextInt();
                    todo.toggleTodoItem(id);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                case 5:
                    System.out.print("\nAre you sure you want to delete all list?\n           IT WILL ERASE ALL DATA! \n   Type 1 for Yes: ");
                    int decision = scanner.nextInt();
                    if(decision == 1){
                        todo.resetDatabase();
                        todo.resetIdIfEmpty();
                    }
                    else{
                        break;
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
