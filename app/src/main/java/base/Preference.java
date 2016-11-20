package base;
import java.util.ArrayList;
import java.util.List;

public class Preference {

    private List<String> preferences;

    public Preference(){}

    public Preference(List<String> preferences) {
        this.preferences = new ArrayList<>();
        this.preferences = preferences;
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }
}
