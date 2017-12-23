package state_machines.dfa;

import state_machines.DeterministicIterator;

import java.util.Collection;

public class Iterator extends DeterministicIterator<Character, State, Transition> {
    
    public Iterator() {
        this(0, null, null);
    }
    
    public Iterator(int head, Collection<Character> input, State initialState) {
        super(head, input, initialState);
    }
}
