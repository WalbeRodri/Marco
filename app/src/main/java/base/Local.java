package base;

/**
 * Created by Matheus on 30/10/2016.
 */

import android.os.Parcelable;

import android.os.Parcel;

public class Local  implements Parcelable {

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

    public Local(Parcel in) {
        name = in.readString();
        description = in.readString();
        this.price = in.readDouble();
        this.schedule = in.readString();
        this.timeSpend = in.readDouble();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
        this.type = in.readString();
        this.image = in.readString();
        this.horario = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeDouble(price);
        dest.writeString(schedule);
        dest.writeDouble(timeSpend);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(type);
        dest.writeString(image);
        dest.writeString(horario);
    }

    public static final Parcelable.Creator<Local> CREATOR = new Parcelable.Creator<Local>()
    {
        @Override
        public Local createFromParcel(android.os.Parcel source) {
            return null;
        }

        public Local[] newArray(int size)
        {
            return new Local[size];
        }
    };

}
