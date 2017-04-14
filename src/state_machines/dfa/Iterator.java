package state_machines.dfa;

/**
 * Created by Tom Machado on 13/04/2017.
 */
public class Iterator {
    private State currentState;
    private String input;
    private int head;

    public Iterator() {
        this.currentState = null;
        this.input = "";
        this.head = 0;
    }

    public Iterator(State initialState, String input) {
        this.currentState = initialState;
        this.input = input;
        this.head = 0;
    }

    public boolean runToHalt(String input) {
        head = 0;
        while(head < input.length() && currentState != null) {
            State nextState = null;
            Transition transition = currentState.tryTransition(input.charAt(head));
            if (transition != null) {
                nextState = transition.makeTransition(input.charAt(head));
            }
            currentState = nextState;
            head++;
        }
        return currentState != null && currentState.isAccept() ? true : false;
    }
}
