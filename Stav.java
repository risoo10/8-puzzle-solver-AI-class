package sample;

import com.sun.xml.internal.fastinfoset.util.CharArray;

public class Stav {



    private int[][] hlavolam = new int[3][3];


    private Stav predchodca;
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

    public Stav(int[][] hlavolam, Stav predchodca){
        this.hlavolam = hlavolam;
        this.predchodca = predchodca;
    }

    public Stav[] nasledovnici(){
        Stav[] result = new Stav[4];

        // Najdi suradnice medzery
        int[] suradniceMedzera = suradnicePreCislovHlavolame(0);

        // posun dole
        if(suradniceMedzera[1] != 0){
            result[0] = new Stav(klonujVymen(
                    suradniceMedzera[0],
                    suradniceMedzera[1],
                    suradniceMedzera[0],
                    suradniceMedzera[1] - 1
            ), this);
        } else {
            result[0] = null;
        }

        // posun hore
        if(suradniceMedzera[1] != 2){
            result[1] = new Stav(klonujVymen(
                    suradniceMedzera[0],
                    suradniceMedzera[1],
                    suradniceMedzera[0],
                    suradniceMedzera[1] + 1
            ), this);
        } else {
            result[1] = null;
        }

        // posun doprava
        if(suradniceMedzera[0] != 0){
            result[2] = new Stav(klonujVymen(
                    suradniceMedzera[0],
                    suradniceMedzera[1],
                    suradniceMedzera[0] - 1,
                    suradniceMedzera[1]
            ), this);
        } else {
            result[2] = null;
        }

        // posun dolava
        if(suradniceMedzera[0] != 2){
            result[3] = new Stav(klonujVymen(
                    suradniceMedzera[0],
                    suradniceMedzera[1],
                    suradniceMedzera[0] + 1,
                    suradniceMedzera[1]
            ), this);
        } else {
            result[3] = null;
        }

        return result;
    }

    private int[] suradnicePreCislovHlavolame(int cislo){
        int[] suradnice = new int[2];
        for(int y=0; y<3; y++){
            for(int x=0; x<3; x++){
                if(hlavolam[y][x] == cislo){
                    suradnice[0] = x;
                    suradnice[1] = y;
                    return suradnice;
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
