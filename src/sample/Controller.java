package sample;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.AnnualOpt;
import model.TestEnum;

import java.util.LinkedList;
import java.util.List;

public class Controller {

    @FXML
    TableView<Controller.Row> tableView = new TableView<>();

    @FXML
    TableColumn<Row, String> day = new TableColumn<>("day");
    @FXML
    TableColumn<Row, String> test = new TableColumn<>("test");
    @FXML
    TableColumn<Row, String> S = new TableColumn<>("S");
    @FXML
    TableColumn<Row, String> I = new TableColumn<>("I");
    @FXML
    TableColumn<Row, String> Ia = new TableColumn<>("Ia");
    @FXML
    TableColumn<Row, String> K = new TableColumn<>("K");
    @FXML
    TableColumn<Row, String> B = new TableColumn<>("B");
    @FXML
    Button buttonOptimize;
    @FXML
    TextField fieldFrom;
    @FXML
    TextField fieldTo;
    @FXML
    Label labelCost;
    @FXML
    Label labelPrevented;
    @FXML
    Label labelEstimation;


    private final ObservableList<Controller.Row> data =
            FXCollections.observableArrayList(
                    new Controller.Row("1", "we", "2", "2", "3", "3", "af"),
                    new Controller.Row("2", "we", "2", "2", "3", "3", "af")
            );

    public Controller() {
        tableView.setEditable(true);
        day.setCellValueFactory(new PropertyValueFactory<Row, String>("day"));
        test.setCellValueFactory(new PropertyValueFactory<Row, String>("test"));
        S.setCellValueFactory(new PropertyValueFactory<Row, String>("S"));
        I.setCellValueFactory(new PropertyValueFactory<Row, String>("I"));
        Ia.setCellValueFactory(new PropertyValueFactory<Row, String>("Ia"));
        K.setCellValueFactory(new PropertyValueFactory<Row, String>("K"));
        B.setCellValueFactory(new PropertyValueFactory<Row, String>("B"));
        //tableView.getColumns().addAll(day, test, S, I, Ia, K, B);
        tableView.setItems(data);
        System.out.println("debug");
    }

    public void initialize() {
        tableView.setEditable(true);
        day.setCellValueFactory(new PropertyValueFactory<Row, String>("day"));
        test.setCellValueFactory(new PropertyValueFactory<Row, String>("test"));
        S.setCellValueFactory(new PropertyValueFactory<Row, String>("S"));
        I.setCellValueFactory(new PropertyValueFactory<Row, String>("I"));
        Ia.setCellValueFactory(new PropertyValueFactory<Row, String>("Ia"));
        K.setCellValueFactory(new PropertyValueFactory<Row, String>("K"));
        B.setCellValueFactory(new PropertyValueFactory<Row, String>("B"));
        //tableView.getColumns().addAll(day, test, S, I, Ia, K, B);
        //tableView.setItems(data);
        //System.out.println("debug");
        buttonOptimize.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int from = Integer.valueOf(fieldFrom.getText());
                int to = Integer.valueOf(fieldTo.getText());
                AnnualOpt ao = new AnnualOpt(from, to);
                //ObservableList<Controller.Row> data =
                //        FXCollections.observableArrayList();
                List<Row> list = new LinkedList<>();

                for (int i = 0; i < ao.size; i++) {
                    list.add(new Row(
                                    Integer.toString(from + i),
                                    ao.tests[i],
                                    Double.toString(Math.round(ao.S[i])),
                                    Double.toString(Math.round(ao.I[i])),
                                    Double.toString(Math.round(ao.Ia[i])),
                                    Double.toString(Math.round(ao.K[i])),
                                    Double.toString(Math.round(ao.B[i]))
                            )
                    );
                }
                ObservableList<Controller.Row> data = new ObservableListWrapper<Row>(list);
                tableView.setItems(data);
                labelCost.setText(Double.toString(ao.cost));
                labelPrevented.setText(Double.toString(ao.prevented * 10));
                labelEstimation.setText(Double.toString(ao.cost / (ao.prevented * 10)));
            }
        });

    }

    public static class Row {

        private final SimpleStringProperty day;
        private final SimpleStringProperty test;
        private final SimpleStringProperty S;
        private final SimpleStringProperty I;
        private final SimpleStringProperty Ia;
        private final SimpleStringProperty K;
        private final SimpleStringProperty B;

        private Row(String day,
                    String test,
                    String s,
                    String i,
                    String ia,
                    String k,
                    String b) {
            this.day = new SimpleStringProperty(day);
            this.test = new SimpleStringProperty(test);
            S = new SimpleStringProperty(s);
            I = new SimpleStringProperty(i);
            Ia = new SimpleStringProperty(ia);
            K = new SimpleStringProperty(k);
            B = new SimpleStringProperty(b);
        }

        public String getDay() {
            return day.get();
        }

        public SimpleStringProperty dayProperty() {
            return day;
        }

        public void setDay(String day) {
            this.day.set(day);
        }

        public String getTest() {
            return test.get();
        }

        public SimpleStringProperty testProperty() {
            return test;
        }

        public void setTest(String test) {
            this.test.set(test);
        }

        public String getS() {
            return S.get();
        }

        public SimpleStringProperty sProperty() {
            return S;
        }

        public void setS(String s) {
            this.S.set(s);
        }

        public String getI() {
            return I.get();
        }

        public SimpleStringProperty iProperty() {
            return I;
        }

        public void setI(String i) {
            this.I.set(i);
        }

        public String getIa() {
            return Ia.get();
        }

        public SimpleStringProperty iaProperty() {
            return Ia;
        }

        public void setIa(String ia) {
            this.Ia.set(ia);
        }

        public String getK() {
            return K.get();
        }

        public SimpleStringProperty kProperty() {
            return K;
        }

        public void setK(String k) {
            this.K.set(k);
        }

        public String getB() {
            return B.get();
        }

        public SimpleStringProperty bProperty() {
            return B;
        }

        public void setB(String b) {
            this.B.set(b);
        }
    }

}
