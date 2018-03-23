package tam.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
/**
 *
 * @author chenj
 */
public class Schedule <E extends Comparable<E>> implements Comparable<E>
{
    private String year;
    private String month;
    private String day;
    
    private final StringProperty type;
    private final StringProperty date;
    private final StringProperty title;
    private final StringProperty topic;
    
    private String time;
    private String link;
    private String criteria;
    
    public Schedule(String type, String year, String month, String day, String time, String title, String topic, String link, String criteria) 
    {
        this.year = year;
        this.month = month;
        this.day = day;
        
        this.type = new SimpleStringProperty(type);
        this.date = new SimpleStringProperty(month+"/"+day+"/"+year);
        this.title = new SimpleStringProperty(title);
        this.topic = new SimpleStringProperty(topic);
        
        this.time = time;
        this.link = link;
        this.criteria = criteria;
    }

    // ACCESSORS AND MUTATORS FOR THE PROPERTIES
    public String getYear() {return year;}
    public void setYear(String year) {this.year = year;}
    
    public String getMonth() {return month;}
    public void setMonth(String month) {this.month = month;}

    public String getDay() {return day;}
    public void setDay(String day) {this.day = day;}
    
    
    
    
    public String getType() {return type.get();}
    public void setType(String type) {this.type.set(type);}
    
    public String getDate() {return date.get();}
    public void setDate(String date) {this.date.set(date);}

    public String getTitle() {return title.get();}
    public void setTitle(String title) {this.title.set(title);}

    public String getTopic() {return topic.get();}
    public void setTopic(String topic) {this.topic.set(topic);}

    
    
    public String getTime() {return time;}
    public void setTime(String time) {this.time = time;}
    
    public String getLink() {return link;}
    public void setLink(String link) {this.link = link;}
    
    public String getCriteria() {return criteria;}
    public void setCriteria(String criteria) {this.criteria = criteria;}
    
    @Override
    public int compareTo(E otherSchedule) {return getType().compareTo(((Schedule)otherSchedule).getType());}
    
    @Override
    public String toString() 
    {
        return  this.type.getValue()+","+this.date.getValue()+","+this.title.getValue()+","+this.topic.getValue();
    }

    
}
