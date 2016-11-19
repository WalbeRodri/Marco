package rotisserie;

import java.util.ArrayList;
import java.util.List;

import base.Local;


public class Decision {
    private List<String> teste;
    private List<Local> arrayLocais;
    public Decision(List<Local> Locais, List<String> preferences){
        arrayLocais = new ArrayList<Local>();
        teste = new ArrayList<String>();
        arrayLocais = Locais;
        teste  = preferences;
    }

    public ArrayList choice(){
        return escolha(teste, arrayLocais);
    }

    public ArrayList escolha(List<String> preferencias, List<Local> locals){
        ArrayList escolhidos = new ArrayList();

        Local[] locais = new Local[locals.size()];
        locals.toArray(locais);

        String[] auxiliar = new String[preferencias.size()];
        preferencias.toArray(auxiliar);

        for(int i=0; i<locais.length;i++){
            for(int j=0; j<auxiliar.length;j++){
                if(locais[i].getDescription().contains(auxiliar[j])){
                    escolhidos.add(locais[i]);
                }

            }
        }

        return escolhidos;
    }


}