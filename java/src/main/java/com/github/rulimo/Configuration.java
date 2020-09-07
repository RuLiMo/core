package com.github.rulimo;

import static java.lang.Float.parseFloat;

public class Configuration {

    public static String getMorphStateMachinePath(){
        return System.getProperty("rulimo.morph.state_machine_path", "../../resources/MorphStateMachine");
    }

    /**
     * Измеряется от 0 до 1
     * Чем больше, тем меньше памяти, минимально 110мб.
     * Чем меньше тем больше скорость работы.
     */
    public static float getMorphMappingLoadFactor(){
        return parseFloat(System.getProperty("rulimo.morph.mapping_load_factor", "0.5"));

    }
}
