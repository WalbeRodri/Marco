package base;

/**
 * Created by Matheus on 30/10/2016.
 */

import org.parceler.Parcel;

@Parcel
public class Local  {

    String name;
    String description;
    double price;
    String schedule;
    String timeSpend;
    double latitude;
    double longitude;
    String type;

    public Local(){}

    public Local(String name, String description, double price, String schedule, String timeSpend, double latitude, double longitude, String type) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.schedule = schedule;
        this.timeSpend = timeSpend;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getTimeSpend() {
        return timeSpend;
    }

    public void setTimeSpend(String timeSpend) {
        this.timeSpend = timeSpend;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getlongitude() {
        return longitude;
    }

    public void setlongitude(double longitude) {
        this.longitude = longitude;
    }
}
