package state_machines.dfa;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Tom Machado on 11/04/2017.
 */
public class DFA {
    private Collection<State> states;
    private Iterator iterator;

    public DFA() {
        states = new ArrayList<>();
        iterator = new Iterator();
    }

    public DFA(Collection<State> states) {
        this.states = states;
        this.iterator = new Iterator();
    }
}
