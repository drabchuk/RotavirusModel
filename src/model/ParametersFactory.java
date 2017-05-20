package model;

public class ParametersFactory {

    public static Parameters getConstParameters() {
        return new Parameters(
                ParamerersConstants.a,
                ParamerersConstants.b,
                ParamerersConstants.N_max,
                ParamerersConstants.delta2,
                ParamerersConstants.delta3,
                ParamerersConstants.etta,
                ParamerersConstants.beta,
                ParamerersConstants.S_e_RDT,
                ParamerersConstants.C_RDT,
                ParamerersConstants.C_PCR
        );
    }

}
