package sample;

import java.awt.*;

/**
 * Created by Riso on 3/12/2017.
 */
public class ManhattanskaVzdialenostHeurestika implements Heurestika {


    @Override
    public int vyhodnotStav(Stav stav, Stav ciel) {

        int suma = 0;

        // Pre kazde cislo v hlavolame vyhodnot vzdialenost do cieloveho stavu
        for(int i=1; i<=8; i++){

            Point aktPoz = stav.suradnicePreCislovHlavolame(i);
            Point cielPoz = ciel.suradnicePreCislovHlavolame(i);

            // Manhattansku vzdialenost vypocitame ako rozdiel x-ovych a y-vych suradnic pozicii v absolutnej hodnote
            suma += Math.abs(aktPoz.x - cielPoz.x) - Math.abs(aktPoz.y - cielPoz.y);

        }

        return suma;
    }

}

