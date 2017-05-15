package state_machines;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

public abstract class BaseState {
    private StringProperty name;
    private BooleanProperty accept, start;
    
    /**
     * Adds the given transition to the collection of transitions
     * from this state.
     * @param transition The new transition.
     */
    public abstract void addTransition (BaseTransition transition);
    
    /**
     * Returns whether or not this state has a transition on the
     * given character.
     * @param transitionChar The character to transition on.
     * @return Whether or not this state has at least one transition
     * on the given character.
     */
    public abstract boolean hasTransition (Character transitionChar);
}
