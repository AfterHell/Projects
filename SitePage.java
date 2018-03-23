package tam.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author chenj
 */
public class SitePage <E extends Comparable<E>> implements Comparable<E>
{
    private final BooleanProperty isUsed;
    private final StringProperty navbarTitle;
    private final StringProperty fileName;
    private final StringProperty script;
    

    public SitePage(boolean isUsed, String navbarTitle, String fileName, String script) 
    {
        this.isUsed = new SimpleBooleanProperty(isUsed);
        this.navbarTitle = new SimpleStringProperty(navbarTitle);
        this.fileName = new SimpleStringProperty(fileName);
        this.script = new SimpleStringProperty(script);
    }

    // ACCESSORS AND MUTATORS FOR THE PROPERTIES
    public boolean getIsUsed() {return isUsed.get();}
    public void setIsUsed(boolean isUsed) {this.isUsed.set(isUsed);}
    
    public String getNavbarTitle() {return navbarTitle.get();}
    public void setNavbarTitle(String navbarTitle) {this.navbarTitle.set(navbarTitle);}

    public String getFileName() {return fileName.get();}
    public void setFileName(String fileName) {this.fileName.set(fileName);}

    public String getScript() {return script.get();}
    public void setScript(String script) {this.script.set(script);}

    @Override
    public int compareTo(E otherSitePage) {return getNavbarTitle().compareTo(((SitePage)otherSitePage).getNavbarTitle());}
    
    @Override
    public String toString() 
    {
        return this.isUsed.getValue()+","+this.navbarTitle.getValue()+","+this.fileName.getValue()+","+this.script.getValue();
    }

    public boolean equals(SitePage otherSitePage)
    {
        return  this.fileName.equals(otherSitePage.fileName)&&
                this.isUsed.equals(otherSitePage.isUsed)&&
                this.navbarTitle.equals(otherSitePage.navbarTitle)&&
                this.script.equals(otherSitePage.script)
                    ;
    }
}
