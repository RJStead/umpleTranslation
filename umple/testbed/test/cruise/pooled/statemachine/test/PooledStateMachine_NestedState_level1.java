package cruise.pooled.statemachine.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class PooledStateMachine_NestedState_level1
{ 
  @Test 
  public void numberOfMessagesInMessageType()
  {
	  // compare the number of messages in MessageType is equal to the number of events in State Machine except timed events and auto-transition
	  Assert.assertEquals(6, PooledSM_NestedState_L1.MessageType.values().length);
	  Assert.assertEquals(true, PooledSM_NestedState_L1.MessageType.valueOf("e1_M").equals(PooledSM_NestedState_L1.MessageType.e1_M));
	  Assert.assertEquals(true, PooledSM_NestedState_L1.MessageType.valueOf("e2_M").equals(PooledSM_NestedState_L1.MessageType.e2_M));
	  Assert.assertEquals(true, PooledSM_NestedState_L1.MessageType.valueOf("e3_M").equals(PooledSM_NestedState_L1.MessageType.e3_M));
	  Assert.assertEquals(true, PooledSM_NestedState_L1.MessageType.valueOf("e4_M").equals(PooledSM_NestedState_L1.MessageType.e4_M));
	  Assert.assertEquals(true, PooledSM_NestedState_L1.MessageType.valueOf("e5_M").equals(PooledSM_NestedState_L1.MessageType.e5_M));
	  Assert.assertEquals(true, PooledSM_NestedState_L1.MessageType.valueOf("null_M").equals(PooledSM_NestedState_L1.MessageType.null_M));
  }
  
  @Test 
  public void numberOfKeysInstateMessageMap()
  {
	  // compare the number of states is equal to the number of keys in stateMessageMap
	  Assert.assertEquals((PooledSM_NestedState_L1.SmS2.values().length+PooledSM_NestedState_L1.Sm.values().length), PooledSM_NestedState_L1.stateMessageMap.keySet().size());
	  Assert.assertEquals(5, PooledSM_NestedState_L1.stateMessageMap.keySet().size());
  }
  
  @Test 
  public void eachStateOfStateMachineAddedTostateMessageMap()
  {
	  //check that every state of SM it is put in stateMessageMap
	  Assert.assertEquals(true, PooledSM_NestedState_L1.stateMessageMap.containsKey(PooledSM_NestedState_L1.Sm.s1));
	  Assert.assertEquals(true, PooledSM_NestedState_L1.stateMessageMap.containsKey(PooledSM_NestedState_L1.Sm.s2));
	  Assert.assertEquals(true, PooledSM_NestedState_L1.stateMessageMap.containsKey(PooledSM_NestedState_L1.SmS2.Null));
	  Assert.assertEquals(true, PooledSM_NestedState_L1.stateMessageMap.containsKey(PooledSM_NestedState_L1.SmS2.s2a));
	  Assert.assertEquals(true, PooledSM_NestedState_L1.stateMessageMap.containsKey(PooledSM_NestedState_L1.SmS2.s2b));
  }
  
  @Test 
  public void everyStateHasListOfMessages()
  {
	  //check that every state has its list Of messages
	  Assert.assertEquals(2, PooledSM_NestedState_L1.stateMessageMap.get(PooledSM_NestedState_L1.Sm.s1).size());
	  Assert.assertEquals(1, PooledSM_NestedState_L1.stateMessageMap.get(PooledSM_NestedState_L1.Sm.s2).size());
	  Assert.assertEquals(1, PooledSM_NestedState_L1.stateMessageMap.get(PooledSM_NestedState_L1.SmS2.Null).size());
	  Assert.assertEquals(1, PooledSM_NestedState_L1.stateMessageMap.get(PooledSM_NestedState_L1.SmS2.s2a).size());
	  Assert.assertEquals(1, PooledSM_NestedState_L1.stateMessageMap.get(PooledSM_NestedState_L1.SmS2.s2b).size());
	  Assert.assertEquals(true , PooledSM_NestedState_L1.stateMessageMap.get(PooledSM_NestedState_L1.Sm.s1).containsAll(PooledSM_NestedState_L1.stateMessageMap.get(PooledSM_NestedState_L1.Sm.s1)));
	  Assert.assertEquals(true , PooledSM_NestedState_L1.stateMessageMap.get(PooledSM_NestedState_L1.Sm.s2).containsAll(PooledSM_NestedState_L1.stateMessageMap.get(PooledSM_NestedState_L1.Sm.s2)));
	  Assert.assertEquals(true , PooledSM_NestedState_L1.stateMessageMap.get(PooledSM_NestedState_L1.SmS2.Null).containsAll(PooledSM_NestedState_L1.stateMessageMap.get(PooledSM_NestedState_L1.SmS2.Null)));
	  Assert.assertEquals(true , PooledSM_NestedState_L1.stateMessageMap.get(PooledSM_NestedState_L1.SmS2.s2a).containsAll(PooledSM_NestedState_L1.stateMessageMap.get(PooledSM_NestedState_L1.SmS2.s2a)));
	  Assert.assertEquals(true , PooledSM_NestedState_L1.stateMessageMap.get(PooledSM_NestedState_L1.SmS2.s2b).containsAll(PooledSM_NestedState_L1.stateMessageMap.get(PooledSM_NestedState_L1.SmS2.s2b)));
  }
  
  @Test 
  public void sizeOfstateMessageMap()
  {
	  //size of stateMessageMap which contains (state, list of MessageTypes)
	  Assert.assertEquals(5, PooledSM_NestedState_L1.stateMessageMap.size());
  }
  
  @Test 
  public void processEvents() throws InterruptedException
  {
	  PooledSM_NestedState_L1 psm = new PooledSM_NestedState_L1();
	  // check initial state is s1
	  Assert.assertEquals(PooledSM_NestedState_L1.Sm.s1, psm.getSm());
	  
	  psm.e1();//event e1 is called and is added to the pool
	  // event e1 is taken off the pool and is processed: transition to state s2 
	  // Now, the pool is empty
	  psm.e3();//event e1 is called and is added to the pool
	  // event e3 is taken off the pool and is processed: transition to state s1 
	  // Now, the pool is empty
	  psm.e2();//event e2 is called and is added to the pool
	  // event e2 is taken off the pool and is processed: transition to state s2b 
	  // Now, the pool is empty
	  psm.e1();//event e1 is called and is added to the pool
	  // event e1 is unspecified, it is saved at the head of the pool, current sate is not changed
	  // The pool has e1 event saved at its head
	  // The size of pool is 1: e1
	  psm.e5();//event e5 is called and is added to the pool
	  // event e5 is taken off the pool and is processed: transition to state s2a 
	  // The pool has e1 event saved at its head
	  // The size of pool is 1: e1
	  psm.e2();//event e2 is called and is added to the pool
	  // event e2 is unspecified, it is saved at the head of the pool, current sate is not changed
	  // The pool has e1, and e2 events saved at its head
	  // The size of pool is 2: e1 and e2
	  psm.e3();//event e3 is called and is added to the pool
	  // event e3 is taken off the pool and is processed: transition to state s1 
	  // event e1 is taken off the pool and is processed: transition to state s2 
	  // The pool has e2 event saved at its head
	  // The size of pool is 1: e2
	  psm.e5();//event e5 is called and is added to the pool
	  // event e5 is unspecified, it is saved at the head of the pool, current sate is not changed
	  // The pool has e2, and e5 events saved at its head
	  // The size of pool is 2: e2 and e5
	  psm.e4();//event e4 is called and is added to the pool
	  // event e4 is taken off the pool and is processed: transition to state s2b 
	  // event e5 is taken off the pool and is processed: transition to state s2a 
	  // The pool has e2 event saved at its head
	  // The size of pool is 1: e2
	  psm.e3();//event e3 is called and is added to the pool
	  // event e3 is taken off the pool and is processed: transition to state s1 
	  // event e2 is taken off the pool and is processed: transition to state s2b 
	  // Now, the pool is empty
	  psm.e5();//event e5 is called and is added to the pool
	  // event e5 is taken off the pool and is processed: transition to state s2a 
	  // Now, the pool is empty
	  psm.e1();//event e1 is called and is added to the pool
	  // event e1 is unspecified, it is saved at the head of the pool, current sate is not changed
	  // The pool has e1 event saved at its head
	  // The size of pool is 1: e1
	  psm.e3();//event e3 is called and is added to the pool
	  // event e3 is taken off the pool and is processed: transition to state s1 
	  // event e1 is taken off the pool and is processed: transition to state s2 
	  // Now, the pool is empty

	  // Check that the pool does not have events
	  while(!psm.pool.messages.isEmpty())
	  {
		  Thread.sleep(10);
	  }
	  
	  // There is not any event left in the pool
	  Assert.assertEquals(0, psm.pool.messages.size());
	  
	  // Check the current state is s2 and the current substate is s2q
	  Assert.assertEquals(PooledSM_NestedState_L1.Sm.s2, psm.getSm());
	  Assert.assertEquals(PooledSM_NestedState_L1.SmS2.s2a, psm.getSmS2());

  }
}