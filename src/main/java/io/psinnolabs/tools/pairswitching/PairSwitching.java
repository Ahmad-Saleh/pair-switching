package io.psinnolabs.tools.pairswitching;

import org.javatuples.Pair;

import java.util.*;

public class PairSwitching {

    private Set<Engineer> engineers;
    private List<Pair<Engineer, Engineer>> currentPairs;

    public PairSwitching(Set<Engineer> engineers) {
        this.engineers = engineers;
        validateEngineers();
    }

    private void validateEngineers() {
        Objects.requireNonNull(engineers);
        if(engineers.isEmpty()){
            throw new IllegalArgumentException("engineers cannot be empty");
        }
        if(engineers.size() < 2){
            throw new IllegalArgumentException("pairing requires at least two engineers");
        }
    }

    public void generateNewPairs() {
        Iterator<Engineer> engineerIterator = engineers.iterator();
        currentPairs = Collections.singletonList(new Pair<>(engineerIterator.next(), engineerIterator.next()));
    }

    public List<Pair<Engineer, Engineer>> getCurrentPairs() {
        if(currentPairs == null){
            throw new IllegalStateException("pairs are not generated yet");
        }
        return currentPairs;
    }
}
