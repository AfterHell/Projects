package tam.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author chenj
 */
public class Student <E extends Comparable<E>> implements Comparable<E>
{
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty team;
    private final StringProperty role;
    
    public Student(String firstName, String lastName, String team, String role) 
    {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.team = new SimpleStringProperty(team);
        this.role = new SimpleStringProperty(role);
    }

    // ACCESSORS AND MUTATORS FOR THE PROPERTIES
    public String getFirstName() {return firstName.get();}
    public void setFirstName(String firstName) {this.firstName.set(firstName);}
    
    public String getLastName() {return lastName.get();}
    public void setLastName(String lastName) {this.lastName.set(lastName);}

    public String getTeam() {return team.get();}
    public void setTeam(String team) {this.team.set(team);}

    public String getRole() {return role.get();}
    public void setRole(String role) {this.role.set(role);}

    @Override
    public int compareTo(E otherStudent) {return getFirstName().compareTo(((Student)otherStudent).getFirstName());}
    
    @Override
    public String toString() 
    {
        return this.firstName.getValue()+","+this.lastName.getValue()+","+this.team.getValue()+","+this.role.getValue();
    }
}
