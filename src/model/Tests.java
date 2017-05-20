package model;

public class Tests {
    public static void main(String[] args) {
        //ModelWithoutParams model = new ModelWithoutParams(0, 2*365);
        ModelWithParams model = new ModelWithParams(0, 2*365);
        double sum = model.calculate();
        System.out.println("SUM : " + sum);
        double[] S = model.S;
        double[] I = model.I;
        double[] Ia = model.Ia;
        double[] N = model.N;
        int size = S.length;
        /*for (int i = 0; i < size; i++) {
            System.out.printf("%.2f  %.2f  %.2f , sum: %.2f ; N = %.2f; dif: %.2f \n",
                    S[i], I[i], Ia[i], S[i] + I[i] + Ia[i], N[i], S[i] + I[i] + Ia[i] - N[i]);
        }*/
        double[] K = model.K;

        for (int i = 0; i < size; i++) {
            System.out.printf("%.2f  %.2f  %.2f %.2f, sum: %.2f ; N = %.2f; dif: %.2f \n",
                    S[i], I[i], Ia[i], K[i], S[i] + I[i] + Ia[i] + K[i], N[i], S[i] + I[i] + Ia[i] + K[i] - N[i]);
        }
    }
}
