package state_machines;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;

import java.util.Collection;

public abstract class BaseIterator<K, S extends BaseState<K, ? extends BaseTransition<K, S>>, T extends BaseTransition<K, ? extends BaseState<K, T>>> {
    protected final ListProperty<K> input;
    protected int head;
    protected final ObjectProperty<S> currentState;
    protected final ObjectProperty<RunningState> runningState;
    
    public enum RunningState {
        Run, Accept, Reject
    }
    
    protected BaseIterator() {
        this(0, null, null);
    }
    
    protected BaseIterator(int head, Collection<K> input, S initialState) {
        this.head = head;
        this.input = new SimpleListProperty<>(this, "input", FXCollections.observableArrayList(input));
        this.currentState = new SimpleObjectProperty<>(this, "currentState", initialState);
        this.runningState = new SimpleObjectProperty<>(this, "runningState", RunningState.Run);
    }
    
    protected abstract boolean hasNext();
    
    protected abstract boolean hasNext(K key);
}
