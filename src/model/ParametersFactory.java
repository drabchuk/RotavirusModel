package model;

public class ParametersFactory {

    public static Parameters getConstParameters() {
        return new Parameters(
                ParamerersConstants.a,
                ParamerersConstants.b,
                ParamerersConstants.N_max,
                ParamerersConstants.delta2,
                ParamerersConstants.delta3,
                ParamerersConstants.delta4,
                ParamerersConstants.etta,
                ParamerersConstants.S_e_RDT,
                ParamerersConstants.S_e_PCR,
                ParamerersConstants.C_RDT,
                ParamerersConstants.C_PCR
        );
    }

}
