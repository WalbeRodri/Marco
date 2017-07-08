package base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Day {


   private  Date date_day;
    private List<Local> locais;

    public Day(){}

    public Day(Date date_day, List<Local> locais) {
        this.locais = new ArrayList<>();
        this.date_day = date_day;
        this.locais = locais;
    }

    public Date getDate_day() {
        return date_day;
    }

    public void setDate_day(Date date_day) {
        this.date_day = date_day;
    }

    public List<Local> getLocais() {
        return locais;
    }

    public void setLocais(List<Local> locais) {
        this.locais = locais;
    }


}
