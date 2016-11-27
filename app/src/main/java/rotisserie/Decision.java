package rotisserie;

import java.util.ArrayList;
import java.util.List;

import base.Local;

public class Decision {
    private List<String> teste;
    //private Local[] array;
    private ArrayList<Local> arrayLocais;


    //receber tempo inicial e a duração
    public Decision(ArrayList<Local> arrayLocaisr, List<String> preferencias) {
        this.teste = preferencias;
        this.arrayLocais = arrayLocaisr;
    }

    public double horaToDouble(String hora){
        double retorno = Double.parseDouble(hora.substring(0, 1));
        return retorno;
    }
    public static double timeToDouble(String tempo){
        String[] teste = tempo.split(":");
        double hora = Double.parseDouble(teste[0]);
        //double minuto = Double.parseDouble(teste[1]);
        return hora;
    }

    public ArrayList<Local> packing(ArrayList<Local> listaUsuario, String horaInicio, double tempoPorDia, double budget) {
        Local[] arrayLocais = new Local[listaUsuario.size()];
        listaUsuario.toArray(arrayLocais);

        ArrayList<Local> packed = new ArrayList<Local>();
        double aux = 0;
        double auxMoney = 0;
        double horaAtual = timeToDouble(horaInicio);
//    System.out.println(horaAtual);
        for (int i = 0; i < arrayLocais.length; i++) {
            if((arrayLocais[i].getTimespend() + aux <= tempoPorDia)
                    && ((arrayLocais[i].getPrice() + auxMoney) <= budget)
                    && (timeToDouble(arrayLocais[i].getSchedule()) < timeToDouble(horaInicio))
                    && (!arrayLocais[i].getType().equals("Comida"))
                    && (horaAtual < 12) )
            {
                arrayLocais[i].setHorario(Double.toString(horaAtual));
                packed.add(arrayLocais[i]);
                aux += arrayLocais[i].getTimespend();
                auxMoney += arrayLocais[i].getPrice();
                horaAtual += arrayLocais[i].getTimespend();

            }
        }

        if(timeToDouble(horaInicio)+tempoPorDia >= 12 && horaAtual < 13){
            for(int i=0;i<arrayLocais.length;i++){
                if((arrayLocais[i].getPrice()+ auxMoney) <= budget && (arrayLocais[i].getType().equalsIgnoreCase("comida"))){
                    arrayLocais[i].setHorario(Double.toString(horaAtual));
                    packed.add(arrayLocais[i]);
                    horaAtual += arrayLocais[i].getTimespend();
                    break;
                }
            }
        }

        if(timeToDouble(horaInicio)+tempoPorDia>13){
            for(int i=0; i<arrayLocais.length;i++){
                if((aux+arrayLocais[i].getTimespend()<= tempoPorDia)
                        && ((arrayLocais[i].getPrice() + auxMoney) <= budget)

                        && (!arrayLocais[i].getType().equalsIgnoreCase("comida"))){
                    arrayLocais[i].setHorario(Double.toString(horaAtual));
                    packed.add(arrayLocais[i]);
                    aux +=arrayLocais[i].getTimespend();
                    auxMoney += arrayLocais[i].getPrice();
                    horaAtual += arrayLocais[i].getTimespend();
                }
            }

        }


        return packed;

    }



    public ArrayList<Local> choice(){
        return packing(escolha(this.teste, this.arrayLocais), "12:00", 5, 800);
    }

    private ArrayList<Local> escolha(List<String> preferencias, ArrayList<Local> locals){
        ArrayList<Local> escolhidos = new ArrayList<Local>();

        Local[] locais = new Local[locals.size()];
        locals.toArray(locais);

        String[] auxiliar = new String[preferencias.size()];
        preferencias.toArray(auxiliar);

        for(int i=0; i<locais.length;i++){
            for(int j=0; j<auxiliar.length;j++){
                if(locais[i].getType().contains(auxiliar[j])){
                    escolhidos.add(locais[i]);
                }

            }
        }

        return escolhidos;
    }
}