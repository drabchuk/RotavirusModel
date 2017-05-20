package model;

public class ModelWithParams extends ModelWithoutParams{

    public boolean[] d1;
    public boolean[] d2;
    public boolean[] d3;
    public boolean[] d4;
    public double[] K;

    public ModelWithParams(int from, int to) {
        super(from, to);
        d1 = new boolean[size];
        d2 = new boolean[size];
        d3 = new boolean[size];
        d4 = new boolean[size];
        for (int i = 0; i < size; i++) {
            d1[i] = true;
            d2[i] = true;
        }
        K = new double[size];
    }

    @Override
    public double calculate() {
        S[0] = (1.0 - p.a) * B[0];
        I[0] = p.a * p.b * B[0] * (1.0 - p.S_e_RDT);
        Ia[0] = p.a * (1.0 - p.b) * B[0] * (1.0 - p.S_e_RDT);
        K[0] = p.a * B[0] * p.S_e_RDT;
        N[0] = B[0];

        double trans;
        double cured;
        double curedA;
        double curedS;
        double sum = 0.0;
        trans = beta[0] * (I[0] + Ia[0]) * S[0] / N[0];
        S[1] = S[0] * (1  - p.delta2 - trans) + (1 - p.a) * B[0];
        Ia[1] = Ia[0] + (1 - p.etta) * trans * S[0] - p.delta2 * Ia[0];
        if (d1[0]) {
            double infected = B[0] * p.a * (1.0 - p.b);
            if (d3[0]) infected *= (1.0 - p.S_e_RDT);
            else if (d4[0]) infected *= 0;
            Ia[1] += infected;
        }
        I[1] = I[0] + p.etta * trans - p.delta3 * I[0];
        if (d1[0]) {
            double infected = B[0] * p.a * p.b;
            if (d3[0]) {
                infected *= (1 - p.S_e_RDT);
            } else if (d4[0]) {
                infected -= 0;
            }
        }
        K[1] = K[0] - p.delta4 * K[0];
        if (d1[0]) {
            if (d2[0]) {
                K[1] += B[0] * p.a * p.S_e_RDT;
            } else if (d3[0]) {
            }
        }


        for (int t = 1; t < size - 1; t++) {
            trans = beta[t] * (I[t] + Ia[t]) * S[t] / N[t];
            S[t + 1] = S[t] * (1 - p.delta2 - trans)  + (1 - p.a) * B[t];
            Ia[t + 1] = Ia[t] + (1 - p.etta) * trans * S[t] - p.delta2 * Ia[t];
            if (d1[t]) {
                double infected = B[t] * p.a * (1.0 - p.b);
                if (d3[t]) infected *= (1.0 - p.S_e_RDT);
                else if (d4[t]) infected = p.a * (1 - p.b) * B[t - 1] * p.S_e_PCR;
                Ia[t + 1] += infected;
            }
            I[t + 1] = I[t] + p.etta * trans - p.delta3 * I[t];
            if (d1[t]) {
                double infected = B[t] * p.a * p.b;
                if (d3[t]) {
                    infected *= (1 - p.S_e_RDT);
                } else if (d4[t]) {
                    infected -= p.a * p.b * B[t - 1] * p.S_e_PCR;
                }
            }

            K[t + 1] = K[t] - p.delta4 * K[t];
            if (d1[0]) {
                if (d2[0]) {
                    K[t + 1] += B[t] * p.a * p.S_e_RDT;
                } else if (d3[0]) {
                    K[t + 1] += p.a * B[t - 1] * p.S_e_PCR;
                }
            }

            //N[t + 1] = N[t] + B[t + 1] - cured - curedA - curedS;

            sum += p.etta * trans;

        }

        return sum;
    }
}
