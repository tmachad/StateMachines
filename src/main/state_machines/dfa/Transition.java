package state_machines.dfa;

import state_machines.DeterministicTransition;

public class Transition extends DeterministicTransition<Character, State> {

    public Transition(Character transitionKey, State destination) {
        super(transitionKey, destination);
    }
}
