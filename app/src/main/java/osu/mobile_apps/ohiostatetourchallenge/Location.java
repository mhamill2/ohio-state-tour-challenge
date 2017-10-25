package osu.mobile_apps.ohiostatetourchallenge;

import java.util.Comparator;
import java.util.List;

public class Location {
    private String name;
    private String description;
    private Double lattitude;
    private Double longitude;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLattitude(Double lattitude) {
        this.lattitude = lattitude;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLattitude() {
        return lattitude;
    }

    public static Comparator<Location> LocationNameComparator = new Comparator<Location>() {
        @Override
        public int compare(Location loc1, Location loc2) {
            return loc1.getName().compareTo(loc2.getName());
        }
    };
}
