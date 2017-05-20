package model;

public class ModelWithoutParams {

    public Parameters p;
    public double[] B;
    public double[] S;
    public double[] I;
    public double[] Ia;
    public double[] N;
    public double[] beta;
    int size;

    public ModelWithoutParams(int from, int to) {
        B = PatirntsFlowImitator.getPatientsFlow(from, to);
        beta = PatirntsFlowImitator.getTransmissions(from, to);
        this.p = ParametersSingleton.getInstance().getParameters();
        this.size = B.length;
        this.S = new double[size];
        this.I = new double[size];
        this.Ia = new double[size];
        this.N = new double[size];
    }

    public double calculate() {
        S[0] = (1.0 - p.a) * B[0];
        I[0] = p.a * p.b * B[0];
        Ia[0] = p.a * (1.0 - p.b) * B[0];
        N[0] = B[0];

        double trans;
        double cured;
        double curedA;
        double curedS;
        double sum = 0.0;
        for (int t = 0; t < size - 1; t++) {
            trans = beta[t] * (I[t] + Ia[t]) * S[t] / N[t];
            cured = p.delta3 * I[t];
            curedA = p.delta2 * Ia[t];
            curedS = p.delta2 * S[t];
            S[t + 1] = S[t] + (1 - p.a) * B[t + 1] - curedS - trans;
            Ia[t + 1] = Ia[t] + (1 - p.etta) * trans + B[t + 1] * p.a * (1 - p.b) - curedA;
            I[t + 1] = I[t] + p.etta * trans + B[t + 1] * p.a * p.b - cured;
            N[t + 1] = N[t] + B[t + 1] - cured - curedA - curedS;

            sum += p.etta * trans;

        }

        return sum;

    }

}
