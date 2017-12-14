package state_machines.dfa;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.collections.FXCollections;
import state_machines.DeterministicStateMachine;

import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class DFA extends DeterministicStateMachine<Character, State, Transition, Iterator> {
    
    private DFA(Set<State> states, State initialState) {
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

    private static class DFABuilder
            extends DeterministicStateMachineBuilder<Character, State, Transition, DFA, DFABuilder> {

        private DFABuilder() {
            super();
        }

        private DFABuilder(Hashtable<String, State> states, State initialState) {
            super(states, initialState);
        }

        @Override
        protected DFABuilder copyOf() {
            return new DFABuilder(states, initialState);
        }

        @Override
        public DFABuilder withState(String stateName, boolean start, boolean accept) {
            State state;
            if (this.states.containsKey(stateName)) {
                state = this.states.get(stateName);
                state.setStart(start);
                state.setAccept(accept);
            } else {
                state = new State(stateName, start, accept);
                this.states.put(stateName, state);
            }
            
            if (start) {
                if (initialState != null) {
                    initialState.setStart(false);
                }
                initialState = state;
            }
            return copyOf();
        }
        
        public DFABuilder withTransition(String sourceStateName, Character transitionKey, String destinationStateName) {
            State sourceState, destinationState;
            
            if (this.states.containsKey(sourceStateName)) {
                sourceState = this.states.get(sourceStateName);
            } else {
                sourceState = new State(sourceStateName, false, false);
                this.states.put(sourceStateName, sourceState);
            }
            
            if (this.states.containsKey(destinationStateName)) {
                destinationState = this.states.get(destinationStateName);
            } else {
                destinationState = new State(destinationStateName, false, false);
                this.states.put(destinationStateName, destinationState);
            }
            
            sourceState.addTransition(new Transition(transitionKey, destinationState));

            return copyOf();
        }
        
        @Override
        public DFA build() {
            String errors = validate();
            if (errors.length() > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("Errors in DFA configuration:\n");
                sb.append(errors);
                throw new IllegalStateException(sb.toString());
            }

            return new DFA(new HashSet<State>(this.states.values()), this.initialState);
        }
    }
}
