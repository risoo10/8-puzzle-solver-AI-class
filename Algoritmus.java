package sample;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;

public class Algoritmus {

    private Stav pociatok;
    private Stav ciel;
    private Heurestika heurestika;
    private Stav result;

    // Min halda ktora porovnava prvky v halde podla priority
    // priorita je ciselne ohodnotenie stavu, ktore vyhodnoti heurestika
    private Comparator<Stav> cmprtr = new PriorityComparator();
    private PriorityQueue<Stav> fronta = new PriorityQueue<>(cmprtr);

    // Hash mapa generovanych stavov aby sme nezvazovali duplicitne stavy
    private HashMap<String, Integer> hashMap = new LinkedHashMap<>();




    public Algoritmus(Heurestika heurestika, Stav pociatok, Stav ciel){
        this.pociatok = pociatok;
        this.ciel = ciel;
        this.heurestika = heurestika;
    }


    public void hladajRiesenie(){

        // Pridame pociatocny stav do fronty
        pociatok.setPriorita(heurestika.vyhodnotStav(pociatok, ciel));
        pociatok.setPredchodca(null);
        fronta.add(pociatok);

        while(!fronta.isEmpty()) {
            Stav stav = fronta.poll();

            // Pomocne vypisy
            System.out.println("fronta : "+ fronta.size());

            // Ak je finalny stav
            if (stav.getPriorita() == 0) {
                result = stav;
                return;
            }

            // Vygeneruj vsetky nasledujuce stavy
            Stav[] nasledovnici = stav.nasledovnici();

            // Kazdy stav ohodnot heurestikou a pridaj do fronty, a zahashuj pre test originality
            for (Stav s : nasledovnici) {
                if (s != null) {

                    // Zahasuje novy stav alebo preskoci stav, ktory bol prehladavany
                    if (hashMap.containsKey(s.unikatnyHash())) {
                        continue;
                    } else {
                        hashMap.put(s.unikatnyHash(), 9);
                    }

                    // Nastavi prioritu stavu podla heurestiky a predchadzajuci stav
                    s.setPriorita(heurestika.vyhodnotStav(s, ciel));
                    s.setPredchodca(stav);
                    fronta.add(s);
                }
            }
        }
    }


    public Stav getResult() {
        return result;
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