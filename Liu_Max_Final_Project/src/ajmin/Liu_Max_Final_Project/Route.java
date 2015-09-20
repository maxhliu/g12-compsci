package ajmin.Liu_Max_Final_Project;

/**
 * Max Liu
 * May 23, 2014
 * Liu_Max_Final_Project
 * ICS4U1-1
 * Mister Lim
 */

//this route class stores the tag and title of a bus route
public class Route {
    private String tag;
    private String title;

    //it has a constructor as well as getters and setters for its variables
    Route(String tag, String title) {
        this.setTag(tag);
        this.setTitle(title);
    }

    @Override
    public String toString() {
        return title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}