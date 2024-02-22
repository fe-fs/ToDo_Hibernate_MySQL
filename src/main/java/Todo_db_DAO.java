import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/*This class will contain methods for database operations like Add item, delete, view.*/
public class Todo_db_DAO {
    private SessionFactory sessionFactory;

    public Todo_db_DAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

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

    public void todoViewItem() {
        try (Session session = sessionFactory.openSession()) {
            List<Todo_db_Entity> todoItems = session.createQuery("FROM Todo_db_Entity", Todo_db_Entity.class).list();
            for (Todo_db_Entity todoItem : todoItems) {
                System.out.println("ID: " + todoItem.getId());
                System.out.println("Description: " + todoItem.getDescription());
                System.out.println("Is Done: " + (todoItem.getIs_done() == 1 ? "Yes" : "No"));
                System.out.println("------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
