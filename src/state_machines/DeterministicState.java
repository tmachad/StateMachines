package state_machines;


import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.HashMap;
import java.util.Map;

public abstract class DeterministicState<K, T extends DeterministicTransition<K, ? extends DeterministicState<K, T>>> extends BaseState<K, T>{
    protected final MapProperty<K, T> transitions;
    
    protected DeterministicState() {
        this("", false, false, new HashMap<>());
    }
    
    protected DeterministicState(String name, boolean start, boolean accept) {
        super(name, start, accept);
        this.transitions = new SimpleMapProperty<>(this, "transitions", FXCollections.observableHashMap());
    }
    
    protected DeterministicState(String name, boolean start, boolean accept, Map<K, T> transitions) {
        super(name, start, accept);
        this.transitions = new SimpleMapProperty<>(this, "transitions", FXCollections.observableMap(transitions));
    }
    
    @SuppressWarnings("unchecked")
    public boolean addTransition(T transition) {
        if (transitions.get(transition.getTransitionKey()) != null) {
            transitions.put(transition.getTransitionKey(), transition);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean hasTransition(K key) {
        return transitions.get(key) != null;
    }
    
    public ObservableMap<K, T> getTransitions() {
        return transitions.get();
    }
    
    public MapProperty<K, T> transitionsProperty() {
        return transitions;
    }
}
