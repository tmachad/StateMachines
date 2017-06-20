package state_machines.dfa;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.collections.FXCollections;
import state_machines.DeterministicStateMachine;

import java.util.Collection;
import java.util.Set;

public class DFA extends DeterministicStateMachine<Character, State, Transition, Iterator>{

    public DFA() {
    }
    
    public DFA(Set<State> states) {
    
    }
    
    public DFA(Set<State> states, State initialState) {
        this.states = new SimpleSetProperty<>(this, "states", FXCollections.observableSet(states));
        this.initialState = new SimpleObjectProperty<>(this, "initialState", initialState);
    }
    
    @Override
    protected Iterator iterator(final Collection<Character> input) {
        return new Iterator(0, input, this.initialState.get());
    }
    
    protected DFABuilder getBuilder() {
        return new DFABuilder();
    }
    
    private static class DFABuilder extends DeterministicStateMachineBuilder<Character, State, Transition, DFA, DFABuilder> {
    
        private DFABuilder() {
            super();
        }
        
        private DFABuilder(Set<State> states, State initialState) {
            super(states, initialState);
        }
        
        @Override
        protected DFABuilder copyOf() {
            return new DFABuilder();
        }
    
        @Override
        public DFA build() {
            return new DFA(this.states, this.initialState);
        }
    }
}
