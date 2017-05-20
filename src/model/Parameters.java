package model;

import java.util.List;

public class Parameters {

    public double a;
    public double b;
    public double N_max;
    public double delta2;
    public double delta3;
    public double etta;
    public double beta;
    public double S_e_RDT;
    public double C_RDT;
    public double C_PCR;

    public Parameters(double a, double b, double n_max, double delta2, double delta3, double etta, double beta, double s_e_RDT, double c_RDT, double c_PCR) {
        this.a = a;
        this.b = b;
        N_max = n_max;
        this.delta2 = delta2;
        this.delta3 = delta3;
        this.etta = etta;
        this.beta = beta;
        S_e_RDT = s_e_RDT;
        C_RDT = c_RDT;
        C_PCR = c_PCR;
    }

}
