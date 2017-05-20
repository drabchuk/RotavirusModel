package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Parameters;
import model.ParametersSingleton;

public class ParametersController {

    @FXML
    TextField field_a;
    @FXML
    TextField field_b;
    @FXML
    TextField field_N_max;
    @FXML
    TextField field_delta2;
    @FXML
    TextField field_delta3;
    @FXML
    TextField field_delta4;
    @FXML
    TextField field_etta;
    @FXML
    TextField field_S_e_PCR;
    @FXML
    TextField field_S_e_RDT;
    @FXML
    TextField field_C_RDT;
    @FXML
    TextField field_C_PCR;

    @FXML
    Button button_save;

    @FXML
    Label label_status;

    @FXML
    public void initialize() {
        Parameters parameters = ParametersSingleton.getInstance().getParameters();
        field_a.setText(Double.toString(parameters.a));
        field_b.setText(Double.toString(parameters.b));
        field_N_max.setText(Double.toString(parameters.N_max));
        field_delta2.setText(Double.toString(parameters.delta2));
        field_delta3.setText(Double.toString(parameters.delta3));
        field_delta4.setText(Double.toString(parameters.delta4));
        field_etta.setText(Double.toString(parameters.etta));
        field_S_e_RDT.setText(Double.toString(parameters.S_e_RDT));
        field_S_e_PCR.setText(Double.toString(parameters.S_e_PCR));
        field_C_RDT.setText(Double.toString(parameters.C_RDT));
        field_C_PCR.setText(Double.toString(parameters.C_PCR));

        button_save.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    parameters.a = Double.valueOf(field_a.getText());
                    parameters.b = Double.valueOf(field_b.getText());
                    parameters.N_max = Double.valueOf(field_N_max.getText());
                    parameters.delta2 = Double.valueOf(field_delta2.getText());
                    parameters.delta3 = Double.valueOf(field_delta3.getText());
                    parameters.delta4 = Double.valueOf(field_delta4.getText());
                    parameters.etta = Double.valueOf(field_etta.getText());
                    parameters.S_e_RDT = Double.valueOf(field_S_e_RDT.getText());
                    parameters.S_e_PCR = Double.valueOf(field_S_e_PCR.getText());
                    parameters.C_RDT = Double.valueOf(field_C_RDT.getText());
                    parameters.C_PCR = Double.valueOf(field_C_PCR.getText());
                    label_status.setText("saved");
                } catch (NumberFormatException e) {
                    label_status.setText("Number format incorrect");
                }
            }
        });

    }



}
