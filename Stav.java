package sample;

import com.sun.xml.internal.fastinfoset.util.CharArray;

import java.awt.*;
import java.awt.geom.Point2D;

public class Stav {



    private int[][] hlavolam = new int[3][3];


    private Stav predchodca;
    private Operacia predOperacia;
    private int priorita;

    // Vstup je v tvare "(1 2 3 4 5 6 7 0 9)"
    // Nula predstavuje prazdne policko v hlavolame
    // Retazec rozdelime podla medzier a napasujeme do 2-rozmerneho pola
    public Stav(String vstup){
        String[] znaky = vstup.split(" ");
        for(int i=0; i<znaky.length; i++){
            hlavolam[i/3][i%3] = Integer.parseInt(znaky[i]);
        }
    }

    public Stav(int[][] hlavolam, Stav predchodca, Operacia op){
        this.hlavolam = hlavolam;
        this.predchodca = predchodca;
        this.predOperacia = op;
    }

    public Stav[] nasledovnici(){
        Stav[] result = new Stav[4];

        // Najdi suradnice medzery
        Point suradniceMedzera = suradnicePreCislovHlavolame(0);

        // posun dole
        if(suradniceMedzera.y != 0){
            result[0] = new Stav(klonujVymen(
                    suradniceMedzera.x,
                    suradniceMedzera.y,
                    suradniceMedzera.x,
                    suradniceMedzera.y - 1
            ), this, Operacia.DOLE);
        } else {
            result[0] = null;
        }

        // posun hore
        if(suradniceMedzera.y != 2){
            result[1] = new Stav(klonujVymen(
                    suradniceMedzera.x,
                    suradniceMedzera.y,
                    suradniceMedzera.x,
                    suradniceMedzera.y + 1
            ), this, Operacia.HORE);
        } else {
            result[1] = null;
        }

        // posun doprava
        if(suradniceMedzera.x != 0){
            result[2] = new Stav(klonujVymen(
                    suradniceMedzera.x,
                    suradniceMedzera.y,
                    suradniceMedzera.x - 1,
                    suradniceMedzera.y
            ), this, Operacia.VPRAVO);
        } else {
            result[2] = null;
        }

        // posun dolava
        if(suradniceMedzera.x != 2){
            result[3] = new Stav(klonujVymen(
                    suradniceMedzera.x,
                    suradniceMedzera.y,
                    suradniceMedzera.x + 1,
                    suradniceMedzera.y
            ), this, Operacia.VLAVO);
        } else {
            result[3] = null;
        }

        return result;
    }

    public Point suradnicePreCislovHlavolame(int cislo){
        for(int y=0; y<3; y++){
            for(int x=0; x<3; x++){
                if(hlavolam[y][x] == cislo){
                    return new Point(x,y);
                }
            }
        }
        return null;
    }

    public void printHlavolam(){
        String result = "";
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                result += hlavolam[i][j];
            }
            result += '\n';
        }
        System.out.print(result);
    }

    public String unikatnyHash(){
        String result = "";
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++) {
                result += hlavolam[i][j];
            }
        }
        return result;
    }



    public int[][] getHlavolam() {
        return hlavolam;
    }
    public int getPriorita() {
        return priorita;
    }
    public void setPriorita(int priorita) { this.priorita = priorita; }

    public void setPredchodca(Stav predchodca) {
        this.predchodca = predchodca;
    }

    public Stav getPredchodca() {
        return predchodca;
    }

    public String getPredOperacia() {
        return predOperacia != null ? predOperacia.name() : " ";
    }

    public int[][] klonujVymen(int medzeraX, int medzeraY, int x2, int y2){
        int[][] novyHlavolam = new int[3][3];

        // Naklonovanie pola po riadkoch
        for(int i=0; i< this.getHlavolam().length; i++){
            novyHlavolam[i] = this.getHlavolam()[i].clone();
        }

        // Posun policko
        int temp = novyHlavolam[y2][x2];
        novyHlavolam[y2][x2] = 0; // vymenena medzera
        novyHlavolam[medzeraY][medzeraX] = temp; //vymenene cislo

        return novyHlavolam;

    }

}
