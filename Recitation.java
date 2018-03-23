package tam.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author chenj
 */
public class Recitation <E extends Comparable<E>> implements Comparable<E>
{
    private final StringProperty section;
    private final StringProperty instructor;
    private final StringProperty dayOrTime;
    private final StringProperty location;
    private final StringProperty TA1;
    private final StringProperty TA2;
    

    public Recitation(String section, String instructor, String dayOrTime, String location, String TA1, String TA2) 
    {
        this.section = new SimpleStringProperty(section);
        this.instructor = new SimpleStringProperty(instructor);
        this.dayOrTime = new SimpleStringProperty(dayOrTime);
        this.location = new SimpleStringProperty(location);
        this.TA1 = new SimpleStringProperty(TA1);
        this.TA2 = new SimpleStringProperty(TA2);
    }

    // ACCESSORS AND MUTATORS FOR THE PROPERTIES
    public String getSection() {return section.get();}
    public void setSection(String section) {this.section.set(section);}
    
    public String getInstructor() {return instructor.get();}
    public void setInstructor(String instructor) {this.instructor.set(instructor);}

    public String getDayOrTime() {return dayOrTime.get();}
    public void setDayOrTime(String dayOrTime) {this.dayOrTime.set(dayOrTime);}

    public String getLocation() {return location.get();}
    public void setLocation(String location) {this.location.set(location);}

    public String getTA1() {return TA1.get();}
    public void setTA1(String TA1) {this.TA1.set(TA1);}

    public String getTA2() {return TA2.get();}
    public void setTA2(String TA2) {this.TA2.set(TA2);}

    @Override
    public int compareTo(E otherRecitation) {return getInstructor().compareTo(((Recitation)otherRecitation).getInstructor());}
    
    @Override
    public String toString() 
    {
        return  this.section.getValue()+","+this.instructor.getValue()+","+this.dayOrTime.getValue()+","+this.location.getValue()+","+
                this.TA1.getValue()+","+this.TA2.getValue();
    }

    
}
