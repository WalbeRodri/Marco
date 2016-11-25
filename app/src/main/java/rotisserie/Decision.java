package rotisserie;

import java.util.ArrayList;
import java.util.List;

import base.Local;

public class Decision {
    private List<String> teste;
    //private Local[] array;
    private List<Local> arrayLocais;

   /* public Decision() {
        teste = new ArrayList<String>();
        teste.add("Bar");
        teste.add("Museu");
        arrayLocais = new ArrayList<Local>();
        arrayLocais.add(new Local("Rock in Ribs", "Bar", 0, "08:00", "3", -8.06405238, -34.87158716,"Bar"));
        arrayLocais.add(new Local("Bistro Buteco", "Bar", 0, "08:00", "3", -8.06259175, -34.87066984,"Bar"));
        arrayLocais.add(new Local("Caixa Cultura Recife", "Museu", 15, "09:00", "4", -8.062577639207683, -34.87145226901435,"Bar"));
        arrayLocais.add(new Local("Sinagoga", "Museu", 0, "08:00", "3", -8.06189065, -34.87138867,"Bar"));
        arrayLocais.add(new Local("Torre Malakoff", "Diversao", 150, "10:00", "7", -8.060745474745637, -34.87037133878135,"Bar"));
        arrayLocais.add(new Local("Centro Artesanato de Pernambuco", "Museu", 5, "07:00", "1", -8.062282856771736, -34.871192094739904,"Bar"));
        arrayLocais.add(new Local("Downtown Pier", "Bar", 0, "21:00", "1", -8.06473755, -34.87182319,"Bar"));
    }
*/
    //receber tempo inicial e a duração
    public Decision(List<Local> arrayLocaisr, List<String> preferencias)

    {
        this.teste = preferencias;
        this.arrayLocais = arrayLocaisr;
    }

    public ArrayList<Local> packing(List<Local> listaUsuario, int dias, int tempoPorDia, double budget){
        Local[] arrayLocais = new Local[listaUsuario.size()];
        listaUsuario.toArray(arrayLocais);

        ArrayList<Local> packed = new ArrayList<Local>();
        int aux = 0;
        double auxMoney = 0;
        for(int i=0; i<arrayLocais.length;i++){
            if(((arrayLocais[i].getTimeSpend() + aux <= tempoPorDia) && (arrayLocais[i].getPrice() + auxMoney) <= budget )){
                packed.add(arrayLocais[i]);
                aux +=arrayLocais[i].getTimeSpend();
                auxMoney += arrayLocais[i].getPrice();
            }
        }
        return packed;
    }

    public ArrayList<Local> choice(){
        return packing(escolha(teste, arrayLocais), 1, 10, 1000);
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