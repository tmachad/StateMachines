package state_machines.dfa;

public class DFATest {
    
    private DFA under_test;
    
    @org.junit.Before
    public void setUp() throws Exception {
        under_test = new DFA.DFABuilder()
                .withState("1", true, false)
                .withState("2", false, true)
                .withTransition("1", 'a', "2")
                .withTransition("2", 'a', "2")
                .build();
    }
    
    @org.junit.After
    public void tearDown() throws Exception {
    }
}