package state_machines.dfa;

import state_machines.BaseTransition;

public class Transition extends BaseTransition<Character, State> {

    public Transition(Character transitionKey, State destination) {
        super(transitionKey, destination);
    }

    public State makeTransition(Character input) {
        if (transitionKey.get() == input) {
            return destination.get();
        } else {
            return null;
        }
    }
}
