package base;


import java.util.Date;

/**
 * Created by Matheus on 30/10/2016.
 */

public class Perfil {

    String username;
    String gmail;
    String gender;
    Date data_born;
    Preference preferences;
//    private String id;

    public Perfil(){}

    public Perfil(String username, String gmail, String gender, Date data_born, Preference preferences) {
        this.username = username;
        this.gmail = gmail;
        this.gender = gender;
        this.data_born = data_born;
        this.preferences = preferences;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getData_born() {
        return data_born;
    }

    public void setData_born(Date data_born) {
        this.data_born = data_born;
    }

    public Preference getPreferences() {
        return preferences;
    }

    public void setPreferences(Preference preferences) {
        this.preferences = preferences;
    }

    /*public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
*/
}
