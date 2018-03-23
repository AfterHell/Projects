package tam.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author chenj
 */
public class Team <E extends Comparable<E>> implements Comparable<E>
{
    private final StringProperty teamName;
    private final StringProperty teamColor;
    private final StringProperty teamTextColor;
    private final StringProperty teamLink;
    private int red;
    private int green;
    private int blue;
    
    public Team(String name, String color, String textColor, String link) 
    {
        this.teamName = new SimpleStringProperty(name);
        this.teamColor = new SimpleStringProperty(color);
        this.teamTextColor = new SimpleStringProperty(textColor);
        this.teamLink = new SimpleStringProperty(link);
        
        this.red = Integer.valueOf(color.substring( 1, 3 ), 16 );
        this.green = Integer.valueOf(color.substring( 3, 5 ), 16 );
        this.blue = Integer.valueOf(color.substring( 5, 7 ), 16 );
    }

    // ACCESSORS AND MUTATORS FOR THE PROPERTIES
    public String getTeamName() {return teamName.get();}
    public void setTeamName(String name) {this.teamName.set(name);}
    
    public String getTeamColor() {return teamColor.get();}
    public void setTeamColor(String color) {this.teamColor.set(color);}

    public String getTeamTextColor() {return teamTextColor.get();}
    public void setTeamTextColor(String textColor) {this.teamTextColor.set(textColor);}

    public String getTeamLink() {return teamLink.get();}
    public void setTeamLink(String link) {this.teamLink.set(link);}

    public int getTeamRed() {return red;}
    public void setTeamRed(int red) {this.red = red;}
    
    public int getTeamGreen() {return green;}
    public void setTeamGreen(int green) {this.green = green;}
    
    public int getTeamBlue() {return blue;}
    public void setTeamBlue(int blue) {this.blue = blue;}
    
    @Override
    public int compareTo(E otherTeam) {return getTeamName().compareTo(((Team)otherTeam).getTeamName());}
    
    @Override
    public String toString() 
    {
        return  this.teamName.getValue()+","+this.teamColor.getValue()+","+this.teamTextColor.getValue()+","+this.teamLink.getValue();
    }
}
