package state_machines.dfa;

public class Iterator implements java.util.Iterator {
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
            Transition transition = currentState.getTransition(input.charAt(head));
            if (transition != null) {
                nextState = transition.makeTransition(input.charAt(head));
            }
            currentState = nextState;
            head++;
        }
        return currentState != null && currentState.isAccept() ? true : false;
    }
    
    @Override
    public boolean hasNext() {
        return currentState.getTransition(input.charAt(head)) != null;
    }
    
    @Override
    public Object next() {
        Transition transition = currentState.getTransition(input.charAt(head));
        return currentState.getTransition(input.charAt(head)) != null ?
                transition : null;
    }
}
