package base;
import java.util.Date;

/**
 * Created by Matheus on 30/10/2016.
 */



public class Trip {

  private   String name;
   private   Double budget;
   private  String destiny;
    private  Date start_date;
    private   Date end_date;
    private String adress;
    private  Agenda agenda;
    private String timeStar;



    public Trip(){}


    public Trip( String name,Double budget, String destiny,
                Date start_date, Date end_date, String adress, Agenda agenda, String timeStar) {
        this.budget = budget;
        this.name = name;
        this.destiny = destiny;
        this.start_date = start_date;
        this.end_date = end_date;
        this.adress = adress;
        this.agenda = agenda;
        this.timeStar = timeStar;

    }



    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTimeStar() {
        return timeStar;
    }

    public void setTimeStar(String timeStar) {
        this.timeStar = timeStar;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }


}
