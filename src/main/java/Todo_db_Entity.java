/*Modulo 05 - Assignment
 * Java Todo List Application with Hibernate and MySQL database
 * Name: Fernanda Frederico Ribeiro da Silva
 * Class: Software Development II CEN-4025C-24671
 * Professor: Walauskis
 */

import jakarta.persistence.*;

/*An entity class is a simple Java POJO (Plain Old Java Object) that represents a table in your database.
 Each instance of the entity class represents a row in the table.*/

@Entity //This annotation specifies that the class is an entity.
@Table(name = "todo_list") //This annotation specifies the table in the database with which this entity is mapped.
public class Todo_db_Entity {

    @Id //primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This annotation specifies the generation strategies for the values of primary keys.
    @Column(name = "id") //The @Column annotation is used to specify the mapping between a basic entity attribute and the database table column.

    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "is_done")
    private int is_done;

    public Todo_db_Entity(){

    }

    public Todo_db_Entity(String description, int is_done){
        this.description = description;
        this.is_done = is_done;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIs_done() {
        return is_done;
    }

    public void setIs_done(int is_done) {
        this.is_done = is_done;
    }

    public boolean getTask() {
        return false;
    }


    public void setTask(String item) {
    }

    @Override
    public String toString() {
        return "To do: \n" + id +"." + description + "\n Is done? " + is_done;
    }

}
