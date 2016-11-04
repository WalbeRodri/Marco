package com.example.marco;

/**
 * Class copiada do tutorail de ExpandableList
 * http://www.journaldev.com/9942/android-expandablelistview-example-tutorial
 */

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static LinkedHashMap<String, List<String>> getData() {
        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<String, List<String>>();

        List<String> atraction1 = new ArrayList<String>();
        atraction1.add("O Cais do Sertão, é um local de convivência, diversão e conhecimento, pólo gerador de novas ideias e experiências. Abrigando e reverenciando a obra de Luiz Gonzaga, o grande homenageado do espaço, o Cais traz para a beira-mar da capital do Estado um pouco do solo rico e generoso da cultura popular do sertão.");
        atraction1.add("Terça a Sexta: 9h - 18h");
        atraction1.add("Sábado e Domingo: 13h - 19h");
        atraction1.add("Entrada: R$10,00 (aceita meia entrada)");

        List<String> nullAtraction = new ArrayList<String>();
        nullAtraction.add("---");

        expandableListDetail.put("Museu - Cais do Sertão", atraction1);
        expandableListDetail.put("Almoço - Seu Boteco", nullAtraction);
        expandableListDetail.put("Museu - Paço do Frevo", nullAtraction);
        expandableListDetail.put("Museu - Parque de Esculturas Francisco Brennand", nullAtraction);
        expandableListDetail.put("Jantar - Rock & Ribs", nullAtraction);

        return expandableListDetail;
    }
}