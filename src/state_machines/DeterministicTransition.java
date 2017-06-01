package state_machines;

public class DeterministicTransition<K, S extends DeterministicState<K, ? extends DeterministicTransition<K, S>>> extends BaseTransition<K, S> {
    protected DeterministicTransition() {
        super();
    }
    
    protected DeterministicTransition(K transitionKey, S destination) {
        super(transitionKey, destination);
    }
}
