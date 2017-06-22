package base;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public class Trip {

    private String name;
    private Double budget;
    private String destiny;
    private Date start_date;
    private Date end_date;
    private String address;
    private ArrayList<Day> agenda;
    private String timeStart;
    private String timeEnd  ;


    public Trip() {
    }

    /**
     * Construtor sem Data Chegada e Endereço
     */
    public Trip(String name, Double budget, String destiny,
                Date start_date, ArrayList<Day> agenda, String timeStart, String timeEnd) {
        this.budget = budget;
        this.name = name;
        this.destiny = destiny;
        this.start_date = start_date;
        this.agenda = agenda;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }
   /* public Trip(String viagem){
        StringTokenizer tokeni = new StringTokenizer(viagem,",");
        //Log.d("Opa",viagem);
        ArrayList<String> parteTrip = new ArrayList<String>();
        while(tokeni.hasMoreTokens()){
            parteTrip.add(tokeni.nextToken());
        }

    }*/

    /**
     * Construtor com todos os campos
     */
    public Trip(String name, Double budget, String destiny,
                Date start_date, Date end_date, String address, ArrayList<Day> agenda, String timeStart, String timeEnd) {
        this.budget = budget;
        this.name = name;
        this.destiny = destiny;
        this.start_date = start_date;
        this.end_date = end_date;
        this.address = address;
        this.agenda = agenda;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public ArrayList<Day> getAgenda() {
        return agenda;
    }

    public void setAgenda(ArrayList<Day> agenda) {
        this.agenda = agenda;
    }

}
