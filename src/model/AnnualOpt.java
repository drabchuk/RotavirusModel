package model;

public class AnnualOpt {

    public int from;
    public int to;
    public int size;
    private ModelWithParams model;
    public int frame = 15;
    private boolean[] d2;
    private boolean[] d3;
    private boolean[] d4;
    public double[] S;
    public double[] I;
    public double[] Ia;
    public double[] K;
    public double[] B;
    public String[] tests;

    public double cost = 0;
    public double prevented = 0;
    public double estimation;

    public AnnualOpt(int from, int to) {
        this.from = from;
        this.to = to;
        int frames = (to - from) / frame;
        size = frames * frame;
        d2 = new boolean[size];
        d3 = new boolean[size];
        d4 = new boolean[size];
        S = new double[size];
        I = new double[size];
        Ia = new double[size];
        K = new double[size];
        B = new double[size];
        tests = new String[size];

        for (int f = 0; f < frames; f++) {
            model = new ModelWithParams(from + f * frame, from + (f + 1) * frame);
            model.calculate();
            cost += model.cost;
            prevented += - model.criteria + model.d2Criteria;
            //double minEstimation = Double.POSITIVE_INFINITY;
            /*do {
                double estimation = model.calculate();
                //System.out.println(estimation);
                if (minEstimation > estimation) {
                    minEstimation = estimation;
                    model.remParam();
                }
            } while (model.next());*/
            //model.setMinParam();
            System.arraycopy(model.d2, 0, d2, f * frame, frame);
            System.arraycopy(model.d3, 0, d3, f * frame, frame);
            System.arraycopy(model.d4, 0, d4, f * frame, frame);
            System.arraycopy(model.S, 0, S, f * frame, frame);
            System.arraycopy(model.I, 0, I, f * frame, frame);
            System.arraycopy(model.Ia, 0, Ia, f * frame, frame);
            System.arraycopy(model.K, 0, K, f * frame, frame);
            System.arraycopy(model.B, 0, B, f * frame, frame);
            for (int i = 0; i < size; i++) {
                if(d2[i]) {
                    tests[i] = "no test";
                } else if (d3[i]) {
                    tests[i] = "RDT";
                } else if (d4[i]) {
                    tests[i] = "PCR";
                }
            }
        }

        estimation = (double) cost / prevented;


    }
}
