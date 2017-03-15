package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class Controller {

    private ObservableList<String> moznosti =
            FXCollections.observableArrayList(
                    "Manhattanska vzdialenosť",
                    "Počet rozdielnych políčok"
            );
    @FXML
    private TextField vstupnytext;

    @FXML
    private TextField vystupnytext;

    @FXML
    private Label outputLabel;

    @FXML
    private ComboBox<String> heurestikaBox;


    public void zacniVypocet(){

        outputLabel.setText("Prebieha vypocet ...");

        Algoritmus algo = new Algoritmus(
                new RozdielnePozicieHeurestika(),
                new Stav(vstupnytext.getText()),
                new Stav(vystupnytext.getText())
        );



        algo.hladajRiesenie();



        if(algo.getResult() != null){
            outputLabel.setText("Naslo sa riesenie. \nBolo prehladanych " + algo.pocetPrehladanychStavov() + " stavov. \nPoužitá heurestika : " + heurestikaBox.getValue());
        } else {
            outputLabel.setText("Riesenie neexistuje. \nBolo prehladanych " + algo.pocetPrehladanychStavov() + " stavov.");
        }


    }

    @FXML
    public void initialize(){

        // Inicializuj combo BOX
        heurestikaBox.getItems().addAll(moznosti);
        heurestikaBox.getSelectionModel().selectFirst();

    }




}
