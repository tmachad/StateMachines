package state_machines;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Collection;
import java.util.Hashtable;

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

        protected DeterministicStateMachineBuilder(Hashtable<String, S> states, S initialState) {
            super(states);
            this.initialState = initialState;
        }

        protected abstract R copyOf();
        
        public abstract E build();

        @Override
        protected String validate() {
            StringBuilder errors = new StringBuilder();

            if (initialState == null) {
                errors.append("Initial state must be set.\n");
            }

            boolean hasAccept = false;
            for(S state : states.values()) {
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
