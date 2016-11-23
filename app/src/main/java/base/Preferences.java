package base;
import java.util.ArrayList;
import java.util.List;

public class Preferences {

    private List<String> preferences;

    public Preferences(){
        this.preferences = new ArrayList<>();
    }

    public Preferences(List<String> preferences2) {
        this.preferences = new ArrayList<>();
        this.preferences = preferences2;
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }

    public void addPreferences(String Gosto) {
        this.preferences.add(Gosto);
    }
}
