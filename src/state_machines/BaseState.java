package state_machines;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class BaseState<K, T extends BaseTransition<K, ? extends BaseState<K, T>>> {
    protected final StringProperty name;
    protected final BooleanProperty accept;
    
    protected BaseState() {
        this("", false, false);
    }
    
    protected BaseState(String name, boolean accept, boolean start) {
        this.name = new SimpleStringProperty(this, "name", name);
        this.accept = new SimpleBooleanProperty(this, "accept", accept);
    }
    
    /**
     * Adds the given transition to the collection of transitions
     * from this state.
     * @param transition The new transition.
     */
    public abstract boolean addTransition (T transition);
    
    /**
     * Returns whether or not this state has <em>at least one</em>
     * transition on the given key.
     * @param transitionKey The key to transition on.
     * @return Whether or not this state has at least one transition
     * on the given character.
     */
    public abstract boolean hasTransition (K transitionKey);
    
    /**
     * Returns whether or not this state is an accept state.
     * @return Whether or not this state is an accept state.
     */
    public boolean isAccept() {
        return accept.get();
    }
    
    /**
     * Return the accept property of this state.
     * @return The accept property of this state.
     */
    public BooleanProperty acceptProperty() {
        return accept;
    }
    
    /**
     * Sets the value contained in the accept property
     * to the given value. This value determines whether
     * or not this state is an accept state.
     * @param accept The new value of the accept property.
     */
    public void setAccept(final boolean accept) {
        this.accept.set(accept);
    }

    public String getName() {
        return name.get();
    }
    
    public StringProperty nameProperty() {
        return name;
    }
}
