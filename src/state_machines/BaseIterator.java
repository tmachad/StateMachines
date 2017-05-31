package state_machines;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;

import java.util.Collection;

public abstract class BaseIterator<K, S extends BaseState<K, ? extends BaseTransition<K, S>>, T extends BaseTransition<K, ? extends BaseState<K, T>>> {
    private final ListProperty<K> input;
    private int head;
    private final ObjectProperty<S> currentState;
    
    public BaseIterator() {
        this(0, null, null);
    }
    
    public BaseIterator(int head, Collection<K> input, S initialState) {
        this.head = head;
        this.input = new SimpleListProperty<>(this, "input", FXCollections.observableArrayList(input));
        this.currentState = new SimpleObjectProperty<>(this, "currentState", initialState);
    }
    
    public abstract boolean hasNext();
    
    public abstract boolean hasNext(K key);
    
    public abstract boolean next();
}
