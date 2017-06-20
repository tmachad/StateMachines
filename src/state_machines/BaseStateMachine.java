package state_machines;

import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.collections.FXCollections;

import java.util.HashSet;
import java.util.Set;

public abstract class BaseStateMachine <K, S extends BaseState<K, ? extends BaseTransition<K, S>>,
        T extends BaseTransition<K, ? extends BaseState<K, T>>> {
    
    protected SetProperty<S> states;
    
    public BaseStateMachine() {
        states = new SimpleSetProperty<>(this, "states", FXCollections.observableSet(new HashSet<>()));
    }
    
    protected static abstract class BaseStateMachineBuilder<K,
            S extends BaseState<K, ? extends BaseTransition<K, S>>,
            T extends BaseTransition<K, ? extends BaseState<K, T>>,
            E extends BaseStateMachine, R extends BaseStateMachineBuilder<K, S, T, E, R>> {
        
        protected Set<S> states;
        
        protected BaseStateMachineBuilder() {
            this.states = new HashSet<>();
        }
        
        protected BaseStateMachineBuilder(Set<S> states) {
            this.states = states;
        }
    
        /**
         * Creates and returns a copy of this builder instance.
         * @return A copy of this builder.
         */
        protected abstract R copyOf();
    
        /**
         * Adds a non-starting state to this state machine.
         * If the state was set as a starting state, it will
         * be changed to be a non-starting state.
         * @param state The state to be added.
         * @return A copy of this builder.
         */
        public R withState(S state) {
            state.start.set(false);
            this.states.add(state);
            return copyOf();
        }
    
        /**
         * Builds a state machine from this builder.
         * @return A state machine.
         */
        public abstract E build();
    }
}
