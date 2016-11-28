package base;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Matheus on 30/10/2016.
 */

@org.parceler.Parcel
public class Local implements Parcelable {

    private String name;
    private String description;
    private double price;
    private String schedule;
    private double timeSpend;
    private double latitude;
    private double longitude;
    private String type;
    private String image;
    private String horario;
    private String general_category;
    public Local(){}

    public Local(String name, String description, double price, String schedule, double timeSpend,
                 double latitude, double longitude, String type,String image,String general_category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.schedule = schedule;
        this.timeSpend = timeSpend;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.image = image;
        this.general_category = general_category;
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

    public double getTimespend() {
        return timeSpend;
    }

    public void setTimespend(double timeSpend) {
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

    public String getGeneral_category() {
        return general_category;
    }

    private Local(Parcel in) {
        name = in.readString();
        description = in.readString();
        price = in.readDouble();
        schedule = in.readString();
        timeSpend = in.readDouble();
        latitude = in.readDouble();
        longitude = in.readDouble();
        type = in.readString();
        image = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest. writeString(name);
        dest.writeString(description);
        dest.writeDouble(price);
        dest.writeString(schedule);
        dest.writeDouble(timeSpend);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(type);
        dest.writeString(image);
    }

    public static final Parcelable.Creator<Local> CREATOR = new Parcelable.Creator<Local>()
    {
        public Local createFromParcel(Parcel in) {
            return new Local(in);
        }

        public Local[] newArray(int size)
        {
            return new Local[size];
        }
    };

}
