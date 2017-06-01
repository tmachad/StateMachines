package state_machines;

import java.util.Collection;

public abstract class DeterministicIterator<K,
        S extends DeterministicState<K, ? extends DeterministicTransition<K, S>>,
        T extends  DeterministicTransition<K, ? extends DeterministicState<K, T>>> extends BaseIterator<K, S, T> {
    
    protected DeterministicIterator(int head, Collection<K> input, S initialState) {
        super(head, input, initialState);
    }
    
    @Override
    public boolean hasNext() {
        return head < input.size();
    }
    
    @Override
    public boolean hasNext(K key) {
        return currentState.get().transitions.get(key) != null;
    }
    
    public boolean next() {
        if (runningState.get() == RunningState.Run) {
            if (hasNext()) {
                K inputKey = input.get(head);
                if (currentState.get().hasTransition(inputKey)) {
                    S nextState = currentState.get().getTransitions().get(inputKey).getDestination();
            
                    currentState.set(nextState);
                    head++;
                    return true;
                } else {
                    runningState.set(RunningState.Reject);
                    return false;
                }
            } else {
                if (currentState.get().isAccept()) {
                    runningState.set(RunningState.Accept);
                } else {
                    runningState.set(RunningState.Reject);
                }
                return false;
            }
        } else {
            return false;
        }
    }
}
