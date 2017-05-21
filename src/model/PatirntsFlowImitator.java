package model;

import static java.lang.Math.*;

public class PatirntsFlowImitator {

    private final static double I0 = 20.486;
    private final static double I1 = 0.00027;
    private final static double I2 = 0.572;
    private final static double I3 = 10.743;
    //private final static double MEAN_TRANSMISSION_PARAMETER = 0.0027;
    //private final static double MEAN_TRANSMISSION_PARAMETER = 2.7; //10 days
    //private final static double MEAN_TRANSMISSION_PARAMETER = 3.3; // 15 days
    private final static double MEAN_TRANSMISSION_PARAMETER = 3.4; // 15 days

    public static double[] getPatientsFlow(int from, int to) {
        double[] mock = new double[to - from + 1];
        for (int i = from; i <= to; i++) {
            mock[i] = flow(i);
        }
        return mock;
    }

    public static double[] getTransmissions(int from, int to) {
        double[] mock = new double[to - from + 1];
        for (int i = from; i <= to; i++) {
            mock[i] = transmissive(i);
        }
        return mock;
    }

    public static double[] getPopulation(int from, int to) {
        double[] mock = new double[to - from + 1];
        for (int i = from; i <= to; i++) {
            mock[i] = population(i);
        }
        return mock;
    }

    public static double flow(double t) {
        return I0 * exp(I1 * t) * (1.0 - I2 * cos(2 * PI * t / 365 - I3));
    }

    public static double transmissive(double t) {
        return MEAN_TRANSMISSION_PARAMETER * exp(I1 * t) * (1.0 - I2 * cos(2 * PI * t / 365 - I3));
    }

    public static double population(double t) {
        if (t < 1095) { // 2012 - basic year. After 2015 population decreased
            return -711.726 * t + 4.606 * 10e7;
        } else {
            return -2251 * t + 4.54 * 10e7;
        }
    }

}
