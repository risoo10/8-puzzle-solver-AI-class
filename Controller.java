package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Deque;


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

    @FXML
    private TextArea vysledky;



    public void zacniVypocet(){

        // Vycisti povodne riesenie a vypis ze zacina vypocet
        outputLabel.setText("Prebieha vypocet ...");
        vysledky.clear();

        // Nastav zvolenu heurestiku z comboboxu
        Heurestika heurestika;
        if(heurestikaBox.getValue().toString().equals("Manhattanska vzdialenosť")){
            heurestika = new ManhattanskaVzdialenostHeurestika();
        } else {
            heurestika = new RozdielnePozicieHeurestika();
        }

        Algoritmus algo = new Algoritmus(
                heurestika,
                new Stav(vstupnytext.getText()),
                new Stav(vystupnytext.getText())
        );


        algo.hladajRiesenie();

        // Overi vysledok

        if(algo.getVysledok().isEmpty()){
            outputLabel.setText("Nenašlo sa riešenie! " +
                    "Prehladalo sa všetkých " + algo.pocetPrehladanychStavov() + " stavov. " +
                    "Stavy ohodnotila heurestika" + heurestikaBox.getValue() + ".");
        } else {
            Deque<Stav> riesenie = algo.getVysledok();
            int kroky = riesenie.size();

            outputLabel.setText("Našlo sa riešenie! " +
                    "Na vyriešenie potrebujeme " + (algo.getVysledok().size() - 1) + " krokov " +
                    "a prehľadalo sa " + algo.pocetPrehladanychStavov() + " stavov. " +
                    "Stavy ohodnotila heurestika" + heurestikaBox.getValue() + ".");

            for(int i=1; i<=kroky; i++ ){
                Stav s = riesenie.removeFirst();
                vysledky.appendText(i + ".  " + s.getPredOperacia() + " -> " + s.printHlavolam() + "\n");
            }
        }


    }

    @FXML
    public void initialize(){

        // Inicializuj combo BOX
        heurestikaBox.getItems().addAll(moznosti);
        heurestikaBox.getSelectionModel().selectFirst();

    }




}
