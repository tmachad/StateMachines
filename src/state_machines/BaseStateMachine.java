package state_machines;

import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.collections.FXCollections;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class BaseStateMachine <K,
        S extends BaseState<K, T>,
        T extends BaseTransition<K, S>> {
    
    public static String ILLEGAL_BODY_STATE_MSG = "Body states cannot be set as start states. " +
            "Make sure that the 'start' property is false for the state(s) you provided";
    public static String ILLEGAL_START_STATE_MSG = "Start states must be set as start states. " +
            "Make sure that the 'start' property is true for the state(s) you provided";
    
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
         * Adds the given state to the state machine.
         * @param state The state to be added.
         * @return A copy of this builder.
         */
        public R withState(S state) {
            this.states.add(state);
            return copyOf();
        }
    
        /**
         * Adds the given states to the state machine.
         * @param states The states to be added.
         * @return A copy of this builder.
         */
        public R withStates(Collection<S> states) {
            this.states.addAll(states);
            return copyOf();
        }
    
        /**
         * Adds the given states to the state machine.
         * @param states The states to be added.
         * @return A copy of this builder.
         */
        public R withStates(S... states) {
            return withStates(Arrays.asList(states));
        }
    
        /**
         * Adds the given state as the state any iterators on this state
         * machine will start in. If the state is not already part of the
         * state machine, it will be added.
         * @param state The state to start in.
         * @return A copy of this builder.
         */
        public abstract R withStartState(S state);
    
        /**
         * Adds the given transition to the state machine starting at
         * the given state. The destination state should already be part
         * of the transition object.
         * @param startState The state the transition starts at.
         * @param transition The transition being added.
         * @return A copy of this builder.
         */
        public abstract R withTransition(S startState, T transition);
    
        /**
         * Adds the given transitions to the state machine starting at
         * the given state. The destination states should already be part
         * of the transition objects.
         * @param startState The state the transitions start at.
         * @param transitions The transitions being added.
         * @return A copy of this builder.
         */
        public abstract R withTransitions(S startState, Collection<T> transitions);
    
        /**
         * Adds the given transitions to the state machine starting at
         * the given state. The destination states should already be part
         * of the transition objects.
         * @param startState The state the transitions start at.
         * @param transitions The transitions being added.
         * @return A copy of this builder.
         */
        public R withTransitions(S startState, T... transitions) {
            return withTransitions(startState, Arrays.asList(transitions));
        }
    
        /**
         * Builds a state machine from this builder.
         * @return A state machine.
         */
        public abstract E build();
    
        /**
         * Validates the configuration of the builder.
         * Should crash the program by throwing
         * {@link IllegalStateException}. Fail hard,
         * fail fast.
         * @param silent If true, no errors will be thrown,
         *               regardless of whether there were any
         * @return All of the error messages that occurred
         */
        protected abstract String validate(boolean silent);
    }
}
