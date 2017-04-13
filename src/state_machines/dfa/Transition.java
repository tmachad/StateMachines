package state_machines.dfa;

/**
 * Created by Tom Machado on 11/04/2017.
 */
public class Transition {
    private Character transitionChar;
    private State destination;

    public Transition(Character transitionChar, State destination) {
        this.transitionChar = transitionChar;
        this.destination = destination;
    }

    public State MakeTransition(Character input) {
        if (transitionChar == input) {
            return destination;
        } else {
            return null;
        }
    }
}
