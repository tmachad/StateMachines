package state_machines;

import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.collections.FXCollections;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public abstract class BaseStateMachine <K,
        S extends BaseState<K, T>,
        T extends BaseTransition<K, S>> {
    
    protected SetProperty<S> states;
    
    public BaseStateMachine() {
        states = new SimpleSetProperty<>(this, "states", FXCollections.observableSet(new HashSet<>()));
    }
    
    private BaseStateMachine(Set<S> states) {
        states = new SimpleSetProperty<>(this, "states", FXCollections.observableSet(states));
    }
    
    protected static BaseStateMachine Parse() {
        throw new UnsupportedOperationException();
    }
    
    protected static abstract class BaseStateMachineParser<K,
            S extends BaseState<K, ?  extends BaseTransition<K, S>>,
            T extends BaseTransition<K, ? extends BaseState<K, T>>,
            E extends BaseStateMachine> {
        
    }
    
    protected static abstract class BaseStateMachineBuilder<K,
            S extends BaseState<K, ? extends BaseTransition<K, S>>,
            T extends BaseTransition<K, ? extends BaseState<K, T>>,
            E extends BaseStateMachine, R extends BaseStateMachineBuilder<K, S, T, E, R>> {

        protected Hashtable<String, S> states;

        protected BaseStateMachineBuilder() {
            this.states = new Hashtable<>();
        }

        protected BaseStateMachineBuilder(Hashtable<String, S> states) {
            this.states = states;
        }

        /**
         * Creates and returns a copy of this builder instance.
         * @return A copy of this builder.
         */
        protected abstract R copyOf();
    
        /**
         * Creates a new state with the given properties and name. State names must be unique.
         * If the given named state already exists, it's properties are overwritten with the
         * given properties.
         * @param stateName The name of the state.
         * @param start Whether or not the state is a start state.
         * @param accept Whether or not the state is a final state.
         * @return A copy of this builder.
         */
        protected abstract R withState(String stateName, boolean start, boolean accept);
    
        /**
         * Creates a transition from the given source state to the given destination state.
         * If either of the states doesn't exist it is created with default settings. If the
         * states do exist, they are left unchanged. The transition is created with the given
         * transition key, but otherwise default settings.
         * @param sourceStateName The state the transition begins in.
         * @param transitionKey The key the transition occurs on.
         * @param destinationStateName The state the transition ends in.
         * @return A copy of this builder.
         */
        protected abstract R withTransition(String sourceStateName, K transitionKey, String destinationStateName);
        
        /**
         * Builds a state machine from this builder.
         * @return A state machine.
         */
        public abstract E build();

        /**
         * Validates the configuration of the builder.
         * Should crash the program by throwing
         * {@link IllegalStateException}.
         * @return All of the error messages that occurred
         */
        protected abstract String validate();
    }
}
