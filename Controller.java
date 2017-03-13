package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class Controller {


    @FXML
    TextField vstupnytext;

    @FXML
    TextField vystupnytext;


    public void zacniVypocet(){
        Algoritmus algo = new Algoritmus(
                new RozdielnePozicieHeurestika(),
                new Stav(vstupnytext.getText()),
                new Stav(vystupnytext.getText())
        );

        algo.hladajRiesenie();
    }




}
