package state_machines.dfa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class State {
    private String name;
    private HashMap<Character, ArrayList<Transition>> transitions;
    private boolean accept, start;

    public State(String name) {
        this(name, new ArrayList<>(0));
    }

    public State(String name, Collection<Transition> transitions) {
        this(name, transitions, false, false);
    }

    public State(String name, Collection<Transition> transitions, boolean accept, boolean start) {
        this.name = name;
        this.transitions = new HashMap<>();
        transitions.forEach(transition -> {
            addTransition(transition);
        });
        this.accept = accept;
        this.start = start;
    }
    
    /**
     * Adds a new {@link Transition} to this {@link State}.
     * @param transition The new {@link Transition} being added.
     */
    public void addTransition(Transition transition) {
        Character transChar = transition.getTransitionChar();
        if (this.transitions.containsKey(transChar)) {
            this.transitions.get(transChar).add(transition);
        } else {
            ArrayList<Transition> newBucket = new ArrayList<>(1);
            newBucket.add(transition);
            this.transitions.put(transChar, newBucket);
        }
    }

    public boolean isStart() {
        return start;
    }

    public boolean isAccept() {
        return accept;
    }
    
    /**
     * Returns any possible transitions on the given Character, or null
     * if no transitions are possible.
     * @param transitionCharater The character to transition on.
     * @return An ArrayList of transitions that can occur on the given character.
     */
    public ArrayList<Transition> getTransitions(Character transitionCharater) {
        return transitions.get(transitionCharater);
    }
    
    /**
     * Returns the first available transition on the given Charater,
     * or null if there are no transitions possible.
     * @param transitionCharacter The character to transition on.
     * @return The first possible transition on the given charater, or null.
     */
    public Transition getTransition(Character transitionCharacter) {
        ArrayList<Transition> availableTransitions = getTransitions(transitionCharacter);
        return availableTransitions != null ? availableTransitions.get(0) : null;
    }
}
