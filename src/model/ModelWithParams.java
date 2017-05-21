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
        double b;
        double s = 0.0;
        double i = 0.0;
        double ia = 0.0;
        double k = 0.0;
        double n = 0.0;
        double curedS;
        double curedI;
        double curedIa;
        double curedK;
        double pcrGroupI = 0.0;
        double pcrGroupIa = 0.0;
        double pcrGroupIFlow;
        double pcrGroupIaFlow;
        double infectedNode;
        double negativeRDTNode;
        double insideInfectedNode = 0.0;
        double transmissive = 0.0;
        double newI;
        double newInsideI;

        double sum = 0.0;

        for (int t = 0; t < size; t++) {
            //inside infected
            insideInfectedNode = transmissive * s;

            // estimation function
            sum += insideInfectedNode;

            newInsideI = p.etta * s;

            //cured patients
            curedS = p.delta2 * s;
            curedI = p.delta3 * i;
            curedIa = p.delta2 * ia;
            curedK = p.delta4 * k;
            s -= curedS;
            i -= curedI;
            ia -= curedIa;
            k -= curedK;

            //inside infected moves to I and Ia
            s -= insideInfectedNode;
            i += newInsideI;
            ia += insideInfectedNode - newInsideI;

            //new patients
            b = B[t];

            infectedNode = b * p.a;
            s += b - infectedNode;// s += b * (1 - p.a);
            if (d3[t]) {//if RDT test
                negativeRDTNode = (1 - p.S_e_RDT) * infectedNode;
                k += infectedNode - negativeRDTNode;
            } else {
                negativeRDTNode = infectedNode;
            }
            newI = negativeRDTNode * p.b;
            i += newI;
            ia += negativeRDTNode - newI;
            //new patients distributed

            //yesterday PCR group
            pcrGroupIFlow = pcrGroupI * p.S_e_PCR;
            pcrGroupIaFlow = pcrGroupIa * p.S_e_PCR;
            i -= pcrGroupIFlow;
            ia -= pcrGroupIaFlow;
            k += pcrGroupIaFlow + pcrGroupIFlow;
            pcrGroupI = 0.0;
            pcrGroupIa = 0.0;

            if (d4[t]) {//if PCR test
                pcrGroupI = newI;
                pcrGroupIa = negativeRDTNode - newI;
            }

            n += b - curedS - curedI - curedIa - curedK;
            transmissive = beta[t] * (i + ia) / n;

            S[t] = s;
            I[t] = i;
            Ia[t] = ia;
            K[t] = k;
            N[t] = n;

        }

        return sum;

    }

    /*@Override
    public double calculate() {
        if (d2[0]) {
            S[0] = (1.0 - p.a) * B[0];
            I[0] = p.a * p.b * B[0];
            Ia[0] = p.a * (1.0 - p.b) * B[0];
            K[0] = 0;
            N[0] = B[0];
        } else if (d3[0]) {
            S[0] = (1.0 - p.a) * B[0];
            I[0] = p.a * p.b * B[0] * (1.0 - p.S_e_RDT);
            Ia[0] = p.a * (1.0 - p.b) * B[0] * (1.0 - p.S_e_RDT);
            K[0] = p.a * B[0] * p.S_e_RDT;
            N[0] = B[0];
        } else if (d4[0]) {
            S[0] = (1.0 - p.a) * B[0];
            I[0] = p.a * p.b * B[0];
            Ia[0] = p.a * (1.0 - p.b) * B[0];
            K[0] = 0;
            N[0] = B[0];
        }

        double trans;
        double curedS;
        double curedIa;
        double curedI;
        double curedK;
        double newInfectedS;
        double newInfectedIa;
        double newInfectedI;
        double newInfectedK;
        double pcrFromIa;
        double pcrFromI;
        double sum = 0.0;
        curedS = p.delta2 * S[0];
        curedIa = p.delta2 * Ia[0];
        curedI = p.delta3 * I[0];
        curedK = p.delta4 * K[0];
        pcrFromI = 0.0;
        pcrFromIa = 0.0;

        trans = beta[0] * (I[0] + Ia[0]) * S[0] / N[0];

        newInfectedS = (1 - p.a) * B[1];
        newInfectedIa = (1 - p.etta) * trans;
        if (d1[1]) {
            double infected = B[1] * p.a * (1.0 - p.b);
            if (d3[1]) infected *= (1.0 - p.S_e_RDT);
            newInfectedIa += infected;
        }
        newInfectedI = p.etta * trans;
        if (d1[1]) {
            double infected = B[1] * p.a * p.b;
            if (d3[1]) {
                infected *= (1 - p.S_e_RDT);
            }
            newInfectedI += infected;
        }

        newInfectedK = 0.0;
        if (d1[1]) {
            if (d3[1]) {
                newInfectedK = B[0] * p.a * p.S_e_RDT;
            } else if (d4[0]){
                newInfectedK = B[1] * p.a * p.S_e_PCR;
            }
            newInfectedK += newInfectedK;
        }

        S[1] = S[0] - trans + newInfectedS - curedS;
        Ia[1] = Ia[0] + newInfectedIa - curedIa;
        I[1] = I[0] + newInfectedI - curedI;
        K[1] = K[0] + newInfectedK - curedK;
        N[1] = N[0] + B[1] - curedI - curedIa - curedK - curedS;


        for (int t = 1; t < size - 1; t++) {

            curedS = p.delta2 * S[t];
            curedIa = p.delta2 * Ia[t];
            curedI = p.delta3 * I[t];
            curedK = p.delta4 * K[t];

            newInfectedS = 0;
            newInfectedIa = 0;
            newInfectedI = 0;

            pcrFromI = 0;
            pcrFromIa = 0;

            trans = beta[t] * (I[t] + Ia[t]) * S[t] / N[t];

            newInfectedS = (1 - p.a) * B[t];

            if (d1[t + 1]) {
                if (d2[t + 1]) {

                } else if (d3[t + 1]) {

                }
                if (d4[t]) {

                }
            }

            /*
            S[t + 1] = S[t] - trans + newInfectedS - curedS;
            Ia[t + 1] = Ia[t] + (1 - p.etta) * trans - curedIa;
            if (d1[t]) {
                double infected = B[t] * p.a * (1.0 - p.b);
                if (d3[t]) infected *= (1.0 - p.S_e_RDT);
                else if (d4[t]) infected = p.a * (1 - p.b) * B[t - 1] * p.S_e_PCR;
                Ia[t + 1] += infected;
            }
            I[t + 1] = I[t] + p.etta * trans - curedI;
            if (d1[t]) {
                double infected = B[t] * p.a * p.b;
                if (d3[t]) {
                    infected *= (1 - p.S_e_RDT);
                } else if (d4[t]) {
                    infected -= p.a * p.b * B[t - 1] * p.S_e_PCR;
                }
                I[t + 1] += infected;
            }

            K[t + 1] = K[t] - p.delta4 * K[t];
            if (d1[0]) {
                if (d2[0]) {
                    K[t + 1] += B[t] * p.a * p.S_e_RDT;
                } else if (d3[0]) {
                    K[t + 1] += p.a * B[t - 1] * p.S_e_PCR;
                }
            }*/

        /*N[t + 1] = N[t] + B[t + 1] - curedI - curedIa - curedK - curedS;

            sum += p.etta * trans;

        }

        return sum;
    }*/
}
