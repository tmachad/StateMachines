package state_machines.dfa;

import state_machines.DeterministicState;

public class State extends DeterministicState<Character, Transition> {
    public State() {
        super();
    }
    
    public State(String name, boolean start, boolean accept) {
        super(name, start, accept);
    }
}
