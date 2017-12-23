package state_machines.dfa;

import state_machines.DeterministicState;

import java.util.Map;

public class State extends DeterministicState<Character, Transition> {
    public State() {
        super();
    }
    
    public State(String name, boolean start, boolean accept) {
        super(name, start, accept);
    }
    
    public State(String name, boolean start, boolean accept, Map<Character, Transition> transitions) {
        super(name, start, accept, transitions);
    }
    
    public State(StateConfig stateConfig) {
        super(stateConfig);
    }
    
    public static class StateConfig extends DeterministicStateConfig<Character, Transition> {
        public StateConfig() {
            super();
        }
        
        public StateConfig(String name, boolean start, boolean accept) {
            super(name, start, accept);
        }
        
        public StateConfig(String name, boolean start, boolean accept, Map<Character, Transition> transitions) {
            super(name, start, accept, transitions);
        }
    }
}
