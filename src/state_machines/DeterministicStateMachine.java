package state_machines;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Collection;
import java.util.Set;

public abstract class DeterministicStateMachine<K,
        S extends DeterministicState<K, T>,
        T extends DeterministicTransition<K, S>,
        I extends DeterministicIterator<K, S, T>>
        extends BaseStateMachine<K, S, T> {
    
    protected ObjectProperty<S> initialState;
    
    protected DeterministicStateMachine() {
        super();
        initialState = new SimpleObjectProperty<>(this, "initialState", null);
    }
    
    protected abstract I iterator(Collection<K> input);
    
    protected static abstract class DeterministicStateMachineBuilder<K,
            S extends DeterministicState<K, T>,
            T extends DeterministicTransition<K, S>,
            E extends DeterministicStateMachine,
            R extends DeterministicStateMachineBuilder<K, S, T, E, R>>
            extends BaseStateMachineBuilder<K, S, T, E, R> {
        
        public static String MULTIPLE_TRANSITIONS_ON_SAME_KEY_ERROR_MSG = "Attempted to add a transition on the same " +
                "key as a pre-existing transition. Deterministic state machines can only have one transition per key";
        
        protected S initialState;
        
        protected DeterministicStateMachineBuilder() {
            super();
            initialState = null;
        }
        
        protected DeterministicStateMachineBuilder(Set<S> states, S initialState) {
            super(states);
            this.initialState = initialState;
        }
        
        protected abstract R copyOf();
        
        @Override
        public R withStartState(S state) {
            initialState = state;
            if (!states.contains(state)) {
                states.add(state);
            }
            return copyOf();
        }
        
        @Override
        public R withTransition(final S startState, final T transition) {
            if (startState.transitions.get(transition.getTransitionKey()) != null) {
                throw new IllegalArgumentException(MULTIPLE_TRANSITIONS_ON_SAME_KEY_ERROR_MSG);
            }
            startState.transitions.put(transition.getTransitionKey(), transition);
            return copyOf();
        }

        @Override
        public R withTransitions(final S startState, final Collection<T> transitions) {
            //Separate this out so no data is modified if an error occurs
            for(T transition : transitions) {
                if (startState.transitions.get(transition.getTransitionKey()) != null) {
                    throw new IllegalArgumentException(MULTIPLE_TRANSITIONS_ON_SAME_KEY_ERROR_MSG);
                }
            }
            for (T transition : transitions) {
                startState.transitions.put(transition.getTransitionKey(), transition);
            }
            return copyOf();
        }
        
        public abstract E build();
    
        @Override
        protected String validate(final boolean silent) {
            StringBuilder errors = new StringBuilder();
        
            if (initialState == null) {
                errors.append("Initial state must be set.\n");
            }
        
            boolean hasAccept = false;
            for(S state : states) {
                if (state.isAccept()) {
                    hasAccept = true;
                    break;
                }
            }
            if (!hasAccept) {
                errors.append("Must have at least one accept state.\n");
            }
        
            return errors.toString();
        }
    }
}
