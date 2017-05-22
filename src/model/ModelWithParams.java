package model;

public class ModelWithParams {

    public Parameters p;
    public double[] B;
    public double[] S;
    public double[] I;
    public double[] Ia;
    public double[] N;
    public double[] K;
    public double[] beta;
    int size;

    public boolean[] d1;
    public boolean[] d2;
    public boolean[] d3;
    public boolean[] d4;

    public boolean[] md2;
    public boolean[] md3;
    public boolean[] md4;



    public double criteria;
    public double cost;
    public double d2Criteria;

    private int iterator;

    public ModelWithParams(int from, int to) {
        B = PatirntsFlowImitator.getPatientsFlow(from, to);
        beta = PatirntsFlowImitator.getTransmissions(from, to);
        this.p = ParametersSingleton.getInstance().getParameters();
        this.size = B.length;
        this.S = new double[size];
        this.I = new double[size];
        this.Ia = new double[size];
        this.N = new double[size];
        d1 = new boolean[size];
        d2 = new boolean[size];
        d3 = new boolean[size];
        d4 = new boolean[size];
        md2 = new boolean[size];
        md3 = new boolean[size];
        md4 = new boolean[size];
        /*for (int i = 0; i < size; i++) {
            d1[i] = true;
            d2[i] = true;
        }*/
        for (int i = 0; i < size; i++) {
            int testI = (int) (Math.random() * 3.0);
            if (testI == 0) {
                d2[i] = true;
            } else if (testI == 1) {
                d3[i] = true;
            } else if (testI == 2) {
                d4[i] = true;
            }
        }
        K = new double[size];
        criteria = 0.0;
        d2Criteria = 0.0;
        cost = 0.0;
        calculateD2();

        iterator = size - 1;
    }

    public double calculate() {

        double b;
        /*double s = S[0];
        double i = I[0];
        double ia = Ia[0];
        double k = K[0];
        double n = N[0];*/
        double s = 0;
        double i = 0;
        double ia = 0;
        double k = 0;
        double n = 0;
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

        for (int t = 0; t < size; t++) {
            //inside infected
            insideInfectedNode = transmissive * s;

            // estimation function
            criteria += insideInfectedNode;

            newInsideI = p.etta * insideInfectedNode;

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
                cost += p.C_RDT * b;
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
                cost += p.C_PCR * b;
            }

            n += b - curedS - curedI - curedIa - curedK;
            transmissive = beta[t] * (i + ia) / n;

            S[t] = s;
            I[t] = i;
            Ia[t] = ia;
            K[t] = k;
            N[t] = n;

        }

        return cost / (criteria - d2Criteria);

    }

    public void calculateD2() {

        double b;
        /*double s = S[0];
        double i = I[0];
        double ia = Ia[0];
        double k = K[0];
        double n = N[0];*/
        double s = 0;
        double i = 0;
        double ia = 0;
        double k = 0;
        double n = 0;
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

        for (int t = 0; t < size; t++) {
            //inside infected
            insideInfectedNode = transmissive * s;

            // estimation function
            d2Criteria += insideInfectedNode;

            newInsideI = p.etta * insideInfectedNode;

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

            negativeRDTNode = infectedNode;
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

            n += b - curedS - curedI - curedIa - curedK;
            transmissive = beta[t] * (i + ia) / n;

            S[t] = s;
            I[t] = i;
            Ia[t] = ia;
            K[t] = k;
            N[t] = n;

        }
        /*S[0] = 0;
        I[0] = 0;
        Ia[0] = 0;
        K[0] = 0;
        N[0] = 0;*/
    }

    public void remParam() {
        System.arraycopy(d2, 0, md2, 0, size);
        System.arraycopy(d3, 0, md3, 0, size);
        System.arraycopy(d4, 0, md4, 0, size);
    }

    public void setMinParam() {
        System.arraycopy(md2, 0, d2, 0, size);
        System.arraycopy(md3, 0, d3, 0, size);
        System.arraycopy(md4, 0, d4, 0, size);
    }

    public boolean next() {
        if (d2[iterator]) {
            d2[iterator] = false;
            d3[iterator] = true;
        } else if (d3[iterator]) {
            d3[iterator] = false;
            d4[iterator] = true;
        } else if (d4[iterator]) {
            int pos = iterator;
            do {
                pos--;
            } while (pos >= 0 && d4[pos]);
            if (pos < 0) return false;
            if (d2[pos]) {
                d2[pos] = false;
                d3[pos] = true;
            } else {
                d3[pos] = false;
                d4[pos] = true;
            }
            for (int i = pos + 1; i < size; i++) {
                d2[i] = true;
                d3[i] = false;
                d4[i] = false;
            }
            iterator = size - 1;
        }
        return true;
    }

}
