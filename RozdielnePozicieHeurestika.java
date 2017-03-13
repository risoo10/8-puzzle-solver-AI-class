package sample;

/**
 * Created by Riso on 3/12/2017.
 */
public class RozdielnePozicieHeurestika implements Heurestika {

    @Override
    public int vyhodnotStav(Stav stav, Stav ciel) {
        int result = 0;
        for(int i=0; i<9; i++){
            if(stav.getHlavolam()[i/3][i%3] != ciel.getHlavolam()[i/3][i%3]){
                result++;
            }
        }
        return result;
    }
}
