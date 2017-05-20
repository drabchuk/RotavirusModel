package sample;

import javafx.fxml.FXML;
import model.Parameters;
import model.ParametersSingleton;

import java.awt.*;

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
    TextField field_etta;
    @FXML
    TextField field_betta;
    @FXML
    TextField field_S_e_RDT;
    @FXML
    TextField field_C_RDT;
    @FXML
    TextField field_C_PCR;

    @FXML
    Button button_save;


    @FXML
    public void initialize() {
        Parameters parameters = ParametersSingleton.getInstance().getParameters();
        field_a.setText(Double.toString(parameters.a));
        field_b.setText(Double.toString(parameters.b));
        field_N_max.setText(Double.toString(parameters.N_max));
        field_delta2.setText(Double.toString(parameters.delta2));
        field_delta3.setText(Double.toString(parameters.delta3));
        field_etta.setText(Double.toString(parameters.etta));
        field_betta.setText(Double.toString(parameters.beta));
        field_S_e_RDT.setText(Double.toString(parameters.S_e_RDT));
        field_C_RDT.setText(Double.toString(parameters.C_RDT));
        field_C_PCR.setText(Double.toString(parameters.C_PCR));

    }



}
