package state_machines;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public abstract class BaseTransition <K, T extends BaseState<K, ? extends BaseTransition<K, T>>> {
    protected final ObjectProperty<K> transitionKey;
    protected final ObjectProperty<T> destination;
    
    protected BaseTransition() {
        this(null, null);
    }
    
    protected BaseTransition(K transitionKey, T destination) {
        this.transitionKey = new SimpleObjectProperty<>(transitionKey);
        this.destination = new SimpleObjectProperty<>(destination);
    }
    
    public K getTransitionKey() {
        return transitionKey.get();
    }
    
    public ObjectProperty<K> transitionCharProperty() {
        return transitionKey;
    }
    
    public BaseState getDestination() {
        return destination.get();
    }
    
    public ObjectProperty<T> destinationProperty() {
        return destination;
    }
}
