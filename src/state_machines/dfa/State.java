package state_machines.dfa;

import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import state_machines.BaseState;

public class State extends BaseState<Character, Transition> {
    private final MapProperty<Character, Transition> transitions;
    
    public State() {
        super();
        transitions = new SimpleMapProperty<>(this, "transitions", FXCollections.observableHashMap());
    }
    
    
    @Override
    public void addTransition(final Transition transition) {
        transitions.put(transition.getTransitionKey(), transition);
    }
    
    @Override
    public boolean hasTransition(final Character transitionChar) {
        return false;
    }
}
