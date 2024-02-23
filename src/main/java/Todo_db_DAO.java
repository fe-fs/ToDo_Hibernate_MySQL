/*Modulo 05 - Assignment
 * Java Todo List Application with Hibernate and MySQL database
 * Name: Fernanda Frederico Ribeiro da Silva
 * Class: Software Development II CEN-4025C-24671
 * Professor: Walauskis
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*This class will contain methods for database operations like Add item, delete, view.*/
public class Todo_db_DAO {
    private SessionFactory sessionFactory;

//SessionFactory is responsible for reading the hibernate configuration parameters and connecting to the database.
// Once it’s created, it should be kept for later use. You would need one SessionFactory object per database using
// a separate configuration file.
    public Todo_db_DAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /** Adds a new Todo_db_Entity item to the database.
     *
     * @param item The description of the new Todo_db_Entity item to be added.
     *
     * This method begins a new transaction, then creates a new Todo_db_Entity item with the provided description.
     * The new item is then saved to the database.*/
    public void todoAddItem(String item) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Todo_db_Entity todoItem = new Todo_db_Entity();
            todoItem.setDescription(item);  // Set the description field
            session.save(todoItem);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

     /** Deletes a Todo_db_Entity item from the database.
     *
     * @param id The ID of the Todo_db_Entity item to be deleted.
     *
     * This method begins a new transaction, then tries to find a Todo_db_Entity item in the database with the provided ID.
     * If such an item is found, it is deleted from the database.
     */
    public void todoDelItem(int id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Todo_db_Entity todoItem = (Todo_db_Entity) session.createQuery("FROM Todo_db_Entity T WHERE T.id = :id")
                    .setParameter("id", id)
                    .uniqueResult();
            if (todoItem != null) {
                session.delete(todoItem);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**todoView method
     * returns a Map<Integer, Todo_db_Entity> where the keys are the new sequence numbers starting from 1,
     *  and the values are the corresponding todo items.
     *  The todo items are fetched once, sorted by their IDs, and then printed along with their new sequence numbers.
     *  The orderedTodos map is returned at the end of the method. */
    public Map<Integer, Todo_db_Entity> todoViewItem() {
        Map<Integer, Todo_db_Entity> orderedTodos = new LinkedHashMap<>();
        try (Session session = sessionFactory.openSession()) {
            List<Todo_db_Entity> todoItems = session.createQuery("FROM Todo_db_Entity", Todo_db_Entity.class).list();
            todoItems.sort(Comparator.comparing(Todo_db_Entity::getId));
            for (int i = 0; i < todoItems.size(); i++) {
                orderedTodos.put(i + 1, todoItems.get(i));
            }
            for (Map.Entry<Integer, Todo_db_Entity> entry : orderedTodos.entrySet()) {
                System.out.println("ID: " + entry.getKey());
                System.out.println("Description: " + entry.getValue().getDescription());
                System.out.println("Is Done: " + (entry.getValue().getIs_done() == 1 ? "Yes" : "No"));
                System.out.println("------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderedTodos;
    }
}
