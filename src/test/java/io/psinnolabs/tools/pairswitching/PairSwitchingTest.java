package io.psinnolabs.tools.pairswitching;

import org.javatuples.Pair;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class PairSwitchingTest {

    @Test(expected = NullPointerException.class)
    public void givenNullEngineersSet_whenCreate_thenThrowException() {
        new PairSwitching(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenEmptyEngineersSet_whenCreate_thenThrowException() {
        new PairSwitching(Collections.emptySet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenEngineersSetWithOneEngineer_whenCreate_thenThrowException() {
        new PairSwitching(Collections.singleton(new Engineer()));
    }

    @Test(expected = IllegalStateException.class)
    public void givenTwoEngineers_whenGetCurrentPairsWithoutGenerating_thenThrowException() {
        new PairSwitching(getEngineersAsSet("Ahmad", "Hazem")).getCurrentPairs();
    }

    @Test
    public void givenTwoPairsAndGeneratedCalled_whenGetCurrentPair_thenReturnPairWithBothEngineers() {
        PairSwitching pairSwitching = new PairSwitching(getEngineersAsSet("Ahmad", "Hazem"));
        pairSwitching.generateNewPairs();

        List<Pair<Engineer, Engineer>> currentPairs = pairSwitching.getCurrentPairs();

        assertOnlyPairExists(currentPairs, "Hazem", "Ahmad");
    }

    @Test
    public void givenTwoPairsAndGeneratedMultipleTimes_whenGetCurrentPair_thenAlwaysReturnPairWithBothEngineers() {
        PairSwitching pairSwitching = new PairSwitching(getEngineersAsSet("Ahmad", "Hazem"));

        pairSwitching.generateNewPairs();
        assertOnlyPairExists(pairSwitching.getCurrentPairs(), "Hazem", "Ahmad");

        pairSwitching.generateNewPairs();
        assertOnlyPairExists(pairSwitching.getCurrentPairs(), "Hazem", "Ahmad");

        pairSwitching.generateNewPairs();
        assertOnlyPairExists(pairSwitching.getCurrentPairs(), "Hazem", "Ahmad");

        pairSwitching.generateNewPairs();
        assertOnlyPairExists(pairSwitching.getCurrentPairs(), "Hazem", "Ahmad");
    }

    private void assertOnlyPairExists(List<Pair<Engineer, Engineer>> currentPairs, String firstName, String secondName) {
        assertThat(currentPairs).hasSize(1)
                .flatExtracting(p -> p.getValue0().getName(), p -> p.getValue1().getName())
                .containsExactlyInAnyOrder(firstName, secondName);
    }

    private Set<Engineer> getEngineersAsSet(String... engineerNames) {
        Set<Engineer> engineers = new HashSet<>();
        Arrays.stream(engineerNames).forEach(n -> engineers.add(new Engineer(n)));
        return engineers;
    }
}
