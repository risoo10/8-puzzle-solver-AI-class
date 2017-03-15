package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class Controller {


    @FXML
    TextField vstupnytext;

    @FXML
    TextField vystupnytext;

    @FXML
    Label outputLabel;


    public void zacniVypocet(){

        outputLabel.setText("Prebieha vypocet ...");

        Algoritmus algo = new Algoritmus(
                new RozdielnePozicieHeurestika(),
                new Stav(vstupnytext.getText()),
                new Stav(vystupnytext.getText())
        );



        algo.hladajRiesenie();

        if(algo.getResult() != null){
            outputLabel.setText("Nasli sme riesenie.");
        } else {
            outputLabel.setText("Preskumali sme vsetky stavy. \n Riesenie neexistuje.");
        }


    }




}
