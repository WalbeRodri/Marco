package rotisserie;

import java.util.ArrayList;
import java.util.List;

import base.Local;

public class Decision {
    private List<String> teste;
    //private Local[] array;
    private List<Local> arrayLocais;


    //receber tempo inicial e a duração
    public Decision(List<Local> arrayLocaisr, List<String> preferencias) {
        this.teste = preferencias;
        this.arrayLocais = arrayLocaisr;
    }

    public double horaToDouble(String hora){
        double retorno = Double.parseDouble(hora.substring(0, 1));
        return retorno;
    }
    public ArrayList packing(List<Local> listaUsuario, String horaInicio, int tempoPorDia, double budget) {
        Local[] arrayLocais = new Local[listaUsuario.size()];
        listaUsuario.toArray(arrayLocais);

        ArrayList<Local> packed = new ArrayList();
        double aux = 0;
        double auxMoney = 0;
        double horaAtual = horaToDouble(horaInicio);
        for (int i = 0; i < arrayLocais.length; i++) {
            if (((arrayLocais[i].getTimeSpend()) + aux <= tempoPorDia)
                    || (arrayLocais[i].getTimeSpend() + aux <= 12)
                    && (arrayLocais[i].getPrice() + auxMoney) <= budget
                    && horaToDouble(arrayLocais[i].getSchedule()) > horaToDouble(horaInicio)
                    && (!arrayLocais[i].getType().equalsIgnoreCase("comida"))
                    && horaAtual < 12 ) {
                packed.add(arrayLocais[i]);
                aux += arrayLocais[i].getTimeSpend();
                auxMoney += arrayLocais[i].getPrice();
                horaAtual += arrayLocais[i].getTimeSpend();
            }
        }

        if(horaToDouble(horaInicio)+tempoPorDia >= 12){
            for(int i=0;i<arrayLocais.length;i++){
                if((arrayLocais[i].getPrice()+ auxMoney) <= budget && (arrayLocais[i].getType().equalsIgnoreCase("comida"))){
                    packed.add(arrayLocais[i]);
                    horaAtual += arrayLocais[i].getTimeSpend();
                }
            }
        }

        if(horaToDouble(horaInicio)+tempoPorDia>13){
            for(int i=0; i<arrayLocais.length;i++){
                if(((arrayLocais[i].getTimeSpend() + aux <= tempoPorDia)  || (arrayLocais[i].getTimeSpend()) + aux <= 12)
                        && ((arrayLocais[i].getPrice() + auxMoney) <= budget)
                        && (horaToDouble(arrayLocais[i].getSchedule().substring(6, 7)) < horaToDouble(horaInicio)+horaAtual)
                        && (!arrayLocais[i].getType().equalsIgnoreCase("comida"))){

                }
                packed.add(arrayLocais[i]);
                aux +=arrayLocais[i].getTimeSpend();
                auxMoney += arrayLocais[i].getPrice();
                horaAtual += arrayLocais[i].getTimeSpend();
            }

        }


        return packed;
    }


    public ArrayList<Local> choice(){
        return packing(escolha(teste, arrayLocais), "12:00", 5, 800);
    }

    private ArrayList<Local> escolha(List<String> preferencias, List<Local> locals){
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