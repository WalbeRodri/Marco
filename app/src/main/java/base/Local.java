package base;

/**
 * Created by Matheus on 30/10/2016.
 */

import org.parceler.Parcel;

import java.io.Serializable;

@Parcel
public class Local implements Serializable{

    String name;
    String description;
    double price;
    String schedule;
    double timeSpend;
    double latitude;
    double longitude;
    String type;
    String image;
     String horario;
    public Local(){}

    public Local(String name, String description, double price, String schedule, double timeSpend, double latitude, double longitude, String type,String image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.schedule = schedule;
        this.timeSpend = timeSpend;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.image = image;
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

    public double getTimeSpend() {
        return timeSpend;
    }

    public void setTimeSpend(double timeSpend) {
        this.timeSpend = timeSpend;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

}
