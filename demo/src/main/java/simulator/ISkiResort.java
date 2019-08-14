package simulator;

import java.util.List;

public interface ISkiResort {
    static List<Skier> generateSkiers(SkiCondition cond) {
        return new SkiResort(cond).getSkiers();
    }

}
