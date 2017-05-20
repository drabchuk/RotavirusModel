package model;

public class ParametersSingleton {

    private static ParametersSingleton instance;
    private Parameters parameters;

    private ParametersSingleton(Parameters p) {
        this.parameters = p;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public static ParametersSingleton getInstance() {
        if (instance == null) {
            instance =  new ParametersSingleton(ParametersFactory.getConstParameters());
        }
        return instance;
    }

}
