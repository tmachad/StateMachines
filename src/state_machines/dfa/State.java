package state_machines.dfa;

import java.util.Collection;

/**
 * Created by Tom Machado on 11/04/2017.
 */
public class State {
    private String name;
    private Collection<Transition> transitions;
    private boolean accept, start;

    public State(String name) {
        this.name = name;
        this.transitions = null;
    }

    public State(String name, Collection<Transition> transitions) {
        this.name = name;
        this.transitions = transitions;
    }

    public State(String name, Collection<Transition> transitions, boolean accept, boolean start) {
        this.name = name;
        this.transitions = transitions;
        this.accept = accept;
        this.start = start;
    }
}
