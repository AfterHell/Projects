package tam.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * This class represents a Teaching Assistant for the table of TAs.
 * 
 * @author Richard McKenna
 */
public class TeachingAssistant<E extends Comparable<E>> implements Comparable<E>  {
    // THE TABLE WILL STORE TA NAMES AND EMAILS
    private final StringProperty name;
    private final StringProperty email;
    private final BooleanProperty isUndergraduated;

    /**
     * Constructor initializes both the TA name and email.
     */
    public TeachingAssistant(String initName, String initEmail, boolean isUndergraduated) 
    {
        name = new SimpleStringProperty(initName);
        email = new SimpleStringProperty(initEmail);
        this.isUndergraduated = new SimpleBooleanProperty(isUndergraduated);
    }

    // ACCESSORS AND MUTATORS FOR THE PROPERTIES

    public String getName() {
        return name.get();
    }

    public void setName(String initName) {
        name.set(initName);
    }

    
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String initEmail) {
        email.set(initEmail);
    }
    
    
    public Boolean getIsUndergraduated() {
        return isUndergraduated.get();
    }

    public void setIsUndergraduated(boolean isUndergraduated) {
        this.isUndergraduated.set(isUndergraduated);
    }

    @Override
    public int compareTo(E otherTA) {
        return getName().compareTo(((TeachingAssistant)otherTA).getName());
    }
    
    @Override
    public String toString() 
    {
        return name.getValue()+","+email.getValue()+","+isUndergraduated.getValue();
    }
    
}