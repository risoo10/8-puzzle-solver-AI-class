package sample;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;

/**
 * Created by Riso on 3/12/2017.
 */
public class Algoritmus {

    private Stav pociatok;
    private Stav ciel;
    private Heurestika heurestika;

    // Min halda ktora porovnava prvky v halde podla priority
    // priorita je ciselne ohodnotenie stavu, ktore vyhodnoti heurestika
    private Comparator<Stav> cmprtr = new PriorityComparator();
    private PriorityQueue<Stav> fronta = new PriorityQueue<>(cmprtr);



    public Algoritmus(Heurestika heurestika, Stav pociatok, Stav ciel){
        this.pociatok = pociatok;
        this.ciel = ciel;
        this.heurestika = heurestika;
    }


    public void hladajRiesenie(){
        // Vygeneruj vsetky nasledujuce stavy
        Stav[] nasledovnici = pociatok.nasledovnici();
        // Kazdy stav ohodnot heurestikou a pridaj do fronty, a zahashuj pre test originality
        for(Stav s : nasledovnici){
            if(s != null){
                
                s.setPriorita(heurestika.vyhodnotStav(s, ciel));
                fronta.add(s);
            }
        }
        while(!fronta.isEmpty()){
            Stav s = fronta.poll();

        }
    }


}

class PriorityComparator implements Comparator<Stav> {

    @Override
    public int compare(Stav o1, Stav o2) {
        if(o1.getPriorita() < o2.getPriorita()){
            return -1;
        }
        if(o1.getPriorita() > o2.getPriorita()){
            return 1;
        }
        return 0;
    }
}