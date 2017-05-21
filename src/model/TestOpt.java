package model;

public class TestOpt {

    public static void main(String[] args) {
        int from = 0;
        int to = 15;
        ModelWithParams model = new ModelWithParams(from, to);
        double minEstimation = Double.POSITIVE_INFINITY;
        do {
            double estimation = model.calculate();
            //System.out.println(estimation);
            if (minEstimation > estimation) {
                minEstimation = estimation;
                model.remParam();
            }
        } while (model.next());
        model.setMinParam();
        for (int i = 0; i < to; i++) {
            System.out.printf("%b %b %b\n", model.d2[i], model.d3[i], model.d4[i]);
        }
    }

}
