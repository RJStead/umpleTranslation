package cruise.queued.statemachine.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Test;


public class QueuedStateMachineTest_withParameters
{ 
  @Test 
  public void numberOfMessagesInMessageType()
  {
	  // compare the number of messages in MessageType is equal to the number of events in State Machine except timed events and auto-transition
	  Assert.assertEquals(2, QueuedSM_withParameters.MessageType.values().length);
	  Assert.assertEquals(true, QueuedSM_withParameters.MessageType.valueOf("e1_M").equals(QueuedSM_withParameters.MessageType.e1_M));
	  Assert.assertEquals(true, QueuedSM_withParameters.MessageType.valueOf("e2_M").equals(QueuedSM_withParameters.MessageType.e2_M));  
  }
  
  @Test 
  public void processEvents() throws InterruptedException
  {
	  QueuedSM_withParameters qsm = new QueuedSM_withParameters();
	  int numChecks;
	  //initial state is s1
	  Assert.assertEquals(QueuedSM_withParameters.Sm.s1, qsm.getSm());
	  Assert.assertEquals(0, qsm.getValue());

	  //e1 is triggered: e1 is queued
	  qsm.e1(5);
	  //e1 is dequeued and processed: transition to s2
	  numChecks=200; // we will check for a second
      while(!qsm.getSm().equals(QueuedSM_withParameters.Sm.s2) && qsm.getValue() != 5 && numChecks>0) {
		Thread.sleep(5);
	    numChecks--;
	  }
	  assertThat(numChecks, not(equalTo(0)));
	  Assert.assertEquals(QueuedSM_withParameters.Sm.s2, qsm.getSm());
	  //check that value is changed to 5
	  Assert.assertEquals(5, qsm.getValue());	  
	  // check if there is a message saved in the queue
	  Assert.assertEquals(0, qsm.queue.messages.size());	  
	  
	  //e2 is triggered: e2 is queued
	  qsm.e2();
	  //e2 is dequeued and processed: transition to s2
	  numChecks=200; // we will check for a second
	  while(numChecks>0 && qsm.getSm().equals(QueuedSM_withParameters.Sm.s2)) {
		if(!qsm.queue.messages.isEmpty()){
		  Thread.sleep(5);
		  numChecks--;
		}
	    else
		{
		  Assert.assertEquals(QueuedSM_withParameters.Sm.s2, qsm.getSm());
	      Assert.assertEquals(true, qsm.queue.messages.isEmpty());
		  break;
		}
	  }
   	  assertThat(numChecks, not(equalTo(0)));
	  Assert.assertEquals(QueuedSM_withParameters.Sm.s2, qsm.getSm());
	  // check if there is a message saved in the queue
	  Assert.assertEquals(0, qsm.queue.messages.size());
	  
	  //e2 is triggered: e2 is queued
	  qsm.e2();
	  //e2 is dequeued and processed: transition to s2
	  numChecks=200; // we will check for a second
	  while(numChecks>0 && qsm.getSm().equals(QueuedSM_withParameters.Sm.s2)) {
		if(!qsm.queue.messages.isEmpty()){
		  Thread.sleep(5);
		  numChecks--;
		}
	    else
		{
		  Assert.assertEquals(QueuedSM_withParameters.Sm.s2, qsm.getSm());
	      Assert.assertEquals(true, qsm.queue.messages.isEmpty());
		  break;
		}
	  }
   	  assertThat(numChecks, not(equalTo(0)));
	  Assert.assertEquals(QueuedSM_withParameters.Sm.s2, qsm.getSm());
	  // check if there is a message saved in the queue
	  Assert.assertEquals(0, qsm.queue.messages.size());
	  
	  //e1 is triggered: e1 is queued
	  qsm.e1(8);
	  //e1 is dequeued and ignored (not processed: case of unspecified reception)
	  numChecks=200; // we will check for a second
	  while(numChecks>0 && qsm.getSm().equals(QueuedSM_withParameters.Sm.s2)) {
		if(!qsm.queue.messages.isEmpty()){
		  Thread.sleep(5);
		  numChecks--;
		}
	    else
		{
		  Assert.assertEquals(QueuedSM_withParameters.Sm.s2, qsm.getSm());
	      Assert.assertEquals(true, qsm.queue.messages.isEmpty());
	      assertThat(qsm.getValue(), not(equalTo(8)));
		  break;
		}
	  }
   	  assertThat(numChecks, not(equalTo(0)));
	  Assert.assertEquals(QueuedSM_withParameters.Sm.s2, qsm.getSm());
	  //value is not changed
	  Assert.assertEquals(5, qsm.getValue());
	  // check if there is a message saved in the queue
	  Assert.assertEquals(0, qsm.queue.messages.size());
	  
	  //check that there is no events left in the queue
	  Assert.assertEquals(0, qsm.queue.messages.size());	  
  }
}