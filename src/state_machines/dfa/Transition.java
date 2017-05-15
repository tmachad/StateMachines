package state_machines.dfa;

public class Transition {
    private final Character transitionChar;
    private final State destination;

    public Transition(Character transitionChar, State destination) {
        this.transitionChar = transitionChar;
        this.destination = destination;
    }

    public State makeTransition(Character input) {
        if (transitionChar == input) {
            return destination;
        } else {
            return null;
        }
    }
    
    public Character getTransitionChar() {
        return transitionChar;
    }
}
