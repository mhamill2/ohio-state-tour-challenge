package osu.mobile_apps.ohiostatetourchallenge;

import java.util.Comparator;
import java.util.List;
import java.io.Serializable;


public class Location implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private Double latitude;
    private Double longitude;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
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

    public Double getLatitude() {
        return latitude;
    }

    public static Comparator<Location> LocationNameComparator = new Comparator<Location>() {
        @Override
        public int compare(Location loc1, Location loc2) {
            return loc1.getName().compareTo(loc2.getName());
        }
    };
}
