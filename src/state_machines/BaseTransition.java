package state_machines;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public abstract class BaseTransition {
    
    private ObjectProperty<Character> transitionChar;
    private ObjectProperty<BaseState> destination;
    
    public BaseTransition(Character transitionChar, BaseState destination) {
        this.transitionChar = new SimpleObjectProperty<>(transitionChar);
        this.destination = new SimpleObjectProperty<>(destination);
    }
    
    public Character getTransitionChar() {
        return transitionChar.get();
    }
    
    public ObjectProperty<Character> transitionCharProperty() {
        return transitionChar;
    }
    
    public BaseState getDestination() {
        return destination.get();
    }
    
    public ObjectProperty<BaseState> destinationProperty() {
        return destination;
    }
}
