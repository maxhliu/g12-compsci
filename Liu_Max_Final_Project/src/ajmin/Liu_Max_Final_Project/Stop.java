package ajmin.Liu_Max_Final_Project;

/**
 * Max Liu
 * May 23, 2014
 * Liu_Max_Final_Project
 * ICS4U1-1
 * Mister Lim
 */
//this route class stores the tag and title of a bus route
public class Stop {
    private String tag;
    private String name;
    private Route route;

    //it has a constructor as well as getters and setters for its variables
    //it includes a Route field so that a Stop object knows which Route it belongs to
    Stop(String tag, String name, Route route) {
        this.setTag(tag);
        this.setName(name);
        this.setRoute(route);
    }

    public String toString() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
