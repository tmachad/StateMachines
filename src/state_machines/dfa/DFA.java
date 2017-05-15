package state_machines.dfa;

import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.collections.FXCollections;

import java.util.Set;

public class DFA {
    private SetProperty<State> states;
    private Iterator iterator;

    public DFA() {
        states = new SimpleSetProperty<>(FXCollections.observableSet());
        iterator = new Iterator();
    }

    public DFA(Set<State> states) {
        this.states = new SimpleSetProperty<>(FXCollections.observableSet(states));
        this.iterator = new Iterator();
    }
    
    public DFA(Set<State> states, State initialState, String input) {
        this.states = new SimpleSetProperty<>(FXCollections.observableSet(states));
        this.iterator = new Iterator(initialState, input);
    }
}
