package state_machines;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Collection;
import java.util.Set;

public abstract class DeterministicStateMachine<K,
        S extends DeterministicState<K, ? extends DeterministicTransition<K, S>>,
        T extends DeterministicTransition<K, ? extends DeterministicState<K, T>>,
        I extends DeterministicIterator<K, S, T>>
        extends BaseStateMachine<K, S, T> {
    
    protected ObjectProperty<S> initialState;
    
    protected DeterministicStateMachine() {
        super();
        initialState = new SimpleObjectProperty<>(this, "initialState", null);
    }
    
    protected abstract I iterator(Collection<K> input);
    
    protected static abstract class DeterministicStateMachineBuilder<K,
            S extends DeterministicState<K, ? extends DeterministicTransition<K, S>>,
            T extends DeterministicTransition<K, ? extends DeterministicState<K, T>>,
            E extends DeterministicStateMachine,
            R extends DeterministicStateMachineBuilder<K, S, T, E, R>>
            extends BaseStateMachineBuilder<K, S, T, E, R> {
        
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
        
        public R withInitialState(S state) {
            state.start.set(true);
            this.initialState = state;
            return copyOf();
        }
        
        public abstract E build();
    }
}
