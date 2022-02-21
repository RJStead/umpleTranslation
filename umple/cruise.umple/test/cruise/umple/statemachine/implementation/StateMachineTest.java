/*

 Copyright: All contributers to the Umple Project
 
 This file is made available subject to the open source license found at:
 http://umple.org/license

Test class for code generation for state machines

*/

package cruise.umple.statemachine.implementation;

import java.lang.reflect.Field;

import org.junit.*;

import cruise.umple.compiler.Event;

public class StateMachineTest extends StateMachineTemplateTest
{
	
  // CAPITALIZED STATE MACHINE ATTRIBUTE - Issue 627
  
  @Test
  public void CapitalStateMachineAttribute()
  {
	assertUmpleTemplateFor("capitalizedStateMachineAttribute.ump",languagePath + "/capitalizedStateMachineAttribute."+ languagePath +".txt","Garage");
  }

  // SIMPLE STATES
	
  @Test
  public void NoStates()
  {
    assertUmpleTemplateFor("SimpleStateMachine.ump",languagePath + "/SimpleStateMachine."+ languagePath +".txt","Mentor");
  }

  @Test
  public void OneState()
  {
    assertUmpleTemplateFor("SimpleStateMachine.ump",languagePath + "/SimpleStateMachine_OneState."+ languagePath +".txt","Student");
  }

  @Test
  public void TwoState()
  {
    assertUmpleTemplateFor("SimpleStateMachine.ump",languagePath + "/SimpleStateMachine_TwoStates."+ languagePath +".txt","Course");
  }

  // TRANSITIONS
  
  @Test
  public void EventTransitionSameState()
  {
    assertUmpleTemplateFor("EventTransition.ump",languagePath + "/EventTransition."+ languagePath +".txt","Course");
  }
  
  @Test
  public void EventTransitionNewState()
  {
    assertUmpleTemplateFor("EventTransition.ump",languagePath + "/EventTransition_NewState."+ languagePath +".txt","Light");
  }  
  
  @Test
  public void EmptyAndNonEmptyStates()
  {
    assertUmpleTemplateFor("EmptyAndNonEmptyStates.ump",languagePath + "/EmptyAndNonEmptyStates."+ languagePath +".txt","Light");
  }   
  
  // GUARDS
  
  @Test
  public void oneGuard()
  {
    assertUmpleTemplateFor("oneGuard.ump",languagePath + "/oneGuard."+ languagePath +".txt","LightFixture");
  }

 @Test
  public void testTwoParameterGuard_1()
  {
    assertUmpleTemplateFor("testTwoParameterGuard.ump",languagePath + "/testTwoParameterGuard."+ languagePath +".txt","A_Guard");
  }

  @Test
  public void multipleGuardsSameEvent()
  {
    assertUmpleTemplateFor("multipleGuardsSameEvent.ump",languagePath + "/multipleGuardsSameEvent."+ languagePath +".txt","LightFixture");
  }

  @Test
  public void multipleGuardsSameEventWithDefaultNoGuard()
  {
    assertUmpleTemplateFor("multipleGuardsSameEventWithDefaultNoGuard.ump",languagePath + "/multipleGuardsSameEventWithDefaultNoGuard."+ languagePath +".txt","LightFixture");
  }
  
  @Test
  public void guardNameBothAttributeAndMethod()
  {
	// Reset autotransition counter so isn't carried over to the next test (it's passed from the java test to the php test)
	Event.setNextAutoTransitionId(1); 
	assertUmpleTemplateFor("guardNameBothAttributeAndMethod.ump",languagePath + "/guardNameBothAttributeAndMethod."+ languagePath +".txt","A");
	Event.setNextAutoTransitionId(1); 
  }
  
  @Test
  public void guardNameBothAttributeAndMethod2()
  {
	Event.setNextAutoTransitionId(1); 
	assertUmpleTemplateFor("guardNameBothAttributeAndMethod2.ump",languagePath + "/guardNameBothAttributeAndMethod2."+ languagePath +".txt","A");
    Event.setNextAutoTransitionId(1);
  }
  
  @Test
  public void guardNameBothAttributeAndMethod3()
  {
	Event.setNextAutoTransitionId(1); 
	assertUmpleTemplateFor("guardNameBothAttributeAndMethod3.ump",languagePath + "/guardNameBothAttributeAndMethod3."+ languagePath +".txt","A");
    Event.setNextAutoTransitionId(1);
  }
  


  // SPACING

  // Spacing after the guard negation symbol
  @Test //Test the spacing problems in issue 1536
  public void guardNegSymbolSpacing() {
	  assertUmpleTemplateFor("guardNegSymbolSpacing.ump",languagePath + "/guardNegSymbolSpacing."+ languagePath +".txt","Agent");
  }

  // Spacing of state transaction actions
  
  @Test // Test the spacing problems in issue 155
  public void guardSpacing() {
    
	  assertUmpleTemplateFor("guardSpacing.ump",languagePath + "/guardSpacing."+ languagePath +".txt","Garage");
  }
  
  @Test //Test the spacing problems in issues 154
  public void stateMachineSpacing() {
	  assertUmpleTemplateFor("stateMachineSpacing1.ump",languagePath + "/stateMachineSpacing1."+ languagePath +".txt","Garage");
	  assertUmpleTemplateFor("stateMachineSpacing2.ump",languagePath + "/stateMachineSpacing2."+ languagePath +".txt","Garage");
  }
  
  // Spacing of state names (issue 399)
  
  /**
   * Tests spacing issues by changing the spacing in the test file for <{@link #NoStates()}.
   * Changing the spacing should have no effect on the output and so this method compares to the same
   * output files as {@link #NoStates()}.
   * <p>This test case addresses <b>issue 399</b>.
   * @author Kenan Kigunda
   * @since September 20, 2013
   */
  @Test
  public void stateNameSpacingForNoStates() {
	  assertUmpleTemplateFor("SimpleStateMachineSpacing.ump",languagePath + "/SimpleStateMachine."+ languagePath +".txt","Mentor");
  }
  
  /**
   * Test spacing issues by changing the spacing in the test file for {@link #OneState()}.
   * @see #stateNameSpacingForNoStates()
   */
  @Test
  public void stateNameSpacingForOneState() {
	  assertUmpleTemplateFor("SimpleStateMachineSpacing.ump",languagePath + "/SimpleStateMachine_OneState."+ languagePath +".txt","Student");	  
  }
  
  /**
   * Test spacing issues by changing the spacing in the test file for {@link #TwoState()}.
   * @see #stateNameSpacingForNoStates()
   */
  @Test
  public void stateNameSpacingForTwoStates() {
	  assertUmpleTemplateFor("SimpleStateMachineSpacing.ump",languagePath + "/SimpleStateMachine_TwoStates."+ languagePath +".txt","Course");  
  }
  
  /**
   * Test spacing issues by changing the spacing in the test file for {@link #EventTransitionSameState()}.
   * @see #stateNameSpacingForNoStates()
   */
  @Test
  public void stateNameSpacingForEventTransitionSameState() {
	  assertUmpleTemplateFor("EventTransitionSpacing.ump",languagePath + "/EventTransition_NewState."+ languagePath +".txt","Light");
  }
  
 
  /**
   * Test spacing issues by changing the spacing in the test file for {@link #EventTransitionNewState()}.
   * @see #stateNameSpacingForNoStates()
   */
  @Test
  public void stateNameSpacingForEventTransitionNewState() {
	  assertUmpleTemplateFor("EventTransitionSpacing.ump",languagePath + "/EventTransition."+ languagePath +".txt","Course");	  
  }
  
  // ACTIONS
  
  @Test
  public void transitionAction()
  {
    assertUmpleTemplateFor("transitionAction.ump",languagePath + "/transitionAction."+ languagePath +".txt","Course");
  }
  
  @Test 
  public void entryAction()
  {
    assertUmpleTemplateFor("entryAction.ump",languagePath + "/entryAction."+ languagePath +".txt","Light");
  }
  
  @Test
  public void doActivity()
  {
    assertUmpleTemplateFor("doActivity.ump",languagePath + "/doActivity."+ languagePath +".txt","Switch");
  }
  
  @Test
  public void doActivity_Multiple()
  {
    assertUmpleTemplateFor("doActivity.ump",languagePath + "/doActivityMultiple."+ languagePath +".txt","Lamp");
  }
  
  @Test
  public void doActivityMultipleInSameState()
  {
    assertUmpleTemplateFor("doActivityMulti.ump",languagePath + "/doActivityMulti."+ languagePath +".txt","X", true, false);
  }

  @Test
  public void doActivityMultiMixin()
  {
    assertUmpleTemplateFor("doActivityMultiMixin.ump",languagePath + "/doActivityMulti."+ languagePath +".txt","X");
  }
  
  @Test
  public void doActivityNestedStateMachine()
  {
	  assertUmpleTemplateFor("doActivityNestedStateMachine.ump",languagePath + "/doActivityNestedStateMachine."+ languagePath +".txt","Course");
  }

  @Test
  public void doActivityNoTransitions()
  {
    assertUmpleTemplateFor("doActivityNoTransitions.ump",languagePath + "/doActivityNoTransitions."+ languagePath +".txt","LightFixture");
  }

  @Test
  public void doActivitiesWithAutoTransition() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException
  {
    Field f1 = Event.class.getDeclaredField("nextAutoTransitionId");
    f1.setAccessible(true);
    f1.setInt(null, 1);
    assertUmpleTemplateFor("doActivitiesWithAutoTransition.ump",languagePath + "/doActivitiesWithAutoTransition."+languagePath +".txt","LightFixture");
  }
  
  @Test 
  public void exitAction()
  {
    assertUmpleTemplateFor("exitAction.ump",languagePath + "/exitAction."+ languagePath +".txt","LightFixture");
  }
  
  @Test 
  public void exitActionSelfTransition()
  {
    assertUmpleTemplateFor("exitActionSelfTransition.ump",languagePath + "/exitActionSelfTransition."+ languagePath +".txt","A");
  }

  @Test 
  public void entryExitTransitionAction()
  {
    assertUmpleTemplateFor("entryExitTransitionAction.ump",languagePath + "/entryExitTransitionAction."+ languagePath +".txt","LightFixture");
  }

  @Test
  public void entryExitTransitionActionWithGuard()
  {
    assertUmpleTemplateFor("entryExitTransitionActionWithGuard.ump",languagePath + "/entryExitTransitionActionWithGuard."+ languagePath +".txt","LightFixture");
  }

  @Test
  public void entryExitActionNoTransitions()
  {
    assertUmpleTemplateFor("entryExitActionNoTransitions.ump",languagePath + "/entryExitActionNoTransitions." + languagePath + ".txt", "LightFixture");
  }

  @Test
  public void entryExitActionDuplicates()
  {
  assertUmpleTemplateFor("entryExitActionDuplicates.ump",languagePath + "/entryExitActionDuplicates." + languagePath + ".txt", "Duplicate");    
  }
  
  @Test
  public void externalStateMachine()
  {
    assertUmpleTemplateFor("externalStateMachine.ump",languagePath + "/externalStateMachine."+ languagePath +".txt","Course");
  }
  
  @Test
  public void timedEvent()
  {
    assertUmpleTemplateFor("timedEvent.ump",languagePath + "/timedEvent."+ languagePath +".txt","Mentor");
  }

  @Test
  public void sameEvent_twoStates_differentStatemachines()
  {
    assertUmpleTemplateFor("sameEvent_twoStates_differentStateMachines.ump",languagePath + "/sameEvent_twoStates_differentStatemachines."+ languagePath +".txt","LightFixture");
  }
  
  @Test
  public void nestedStates()
  {
    assertUmpleTemplateFor("nestedStates.ump",languagePath + "/nestedStates."+ languagePath +".txt","LightFixture");
  }

  @Test
  public void nestedStates_exitInnerBeforeOutter()
  {
    assertUmpleTemplateFor("nestedStates_exitInnerBeforeOutter.ump",languagePath + "/nestedStates_exitInnerBeforeOutter."+ languagePath +".txt","LightFixture");
  }

  @Test
  public void concurrentStates()
  {
    assertUmpleTemplateFor("concurrentStates_normal.ump",languagePath + "/concurrentStates_normal."+ languagePath +".txt","LightFixture");
  }
  
  @Test
  public void before_after_setMethod()
  {
    assertUmpleTemplateFor("BeforeAndAfter_StateMachineSet.ump",languagePath + "/BeforeAndAfter_StateMachineSet."+ languagePath +".txt","LightFixture");
  }
  
  @Test
  public void activeObject()
  {
    assertUmpleTemplateFor("activeObject.ump", languagePath + "/activeObject."+ languagePath + ".txt", "Lamp");
  }

  @Test
  public void finalState()
  {
    assertUmpleTemplateFor("FinalState.ump",languagePath + "/FinalState."+ languagePath +".txt","Mentor");
  }

  @Test
  public void eventWithArguments(){
	  assertUmpleTemplateFor("eventWithArguments.ump",languagePath + "/eventWithArguments."+ languagePath +".txt","LightFixture");
  }
  
  
  @Test
  public void eventWithArguments_1(){
	  assertUmpleTemplateFor("eventWithArguments_1.ump",languagePath + "/eventWithArguments_1."+ languagePath +".txt","LightFixture");
  }
  
  @Test
  public void eventWithArguments_2(){
	  assertUmpleTemplateFor("eventWithArguments_2.ump",languagePath + "/eventWithArguments_2."+ languagePath +".txt","Course");
  }
  
  @Test
  public void twoEventsWithArguments(){
	  assertUmpleTemplateFor("twoEventsWithArguments.ump",languagePath + "/twoEventsWithArguments."+ languagePath +".txt","Course");
  }
  
  @Test
  public void autoEventTransition() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException
  {
    Field f1 = Event.class.getDeclaredField("nextAutoTransitionId");
    f1.setAccessible(true);
    f1.setInt(null, 1);
    
    assertUmpleTemplateFor("AutoEventTransition.ump",languagePath + "/AutoEventTransition."+ languagePath +".txt","Light");
  }

  @Test
  public void queuedStateMachine()
  {
    assertUmpleTemplateFor("queuedStateMachine.ump",languagePath + "/queuedStateMachine."+ languagePath +".txt","Course");
  }

  @Test
  public void queuedStateMachine_2()
  {
    assertUmpleTemplateFor("queuedStateMachine_2.ump",languagePath + "/queuedStateMachine_2."+ languagePath +".txt","GarageDoor");
  }

  @Test
  public void queuedStateMachine_withParameters()
  {
    assertUmpleTemplateFor("queuedStateMachine_withParameters.ump",languagePath + "/queuedStateMachine_withParameters."+ languagePath +".txt","LightFixture");
  }

  @Test
  public void queuedStateMachine_withParameters_1()
  {
    assertUmpleTemplateFor("queuedStateMachine_withParameters_1.ump",languagePath + "/queuedStateMachine_withParameters_1."+ languagePath +".txt","LightFixture");
  }
  
  @Test
  public void queuedWithNestingStateMachines()
  {
    assertUmpleTemplateFor("queuedWithNestedStateMachines.ump",languagePath + "/queuedWithNestedStateMachines."+ languagePath +".txt","QueuedWithNestedStateMachines");
  }
  
  @Test
  public void queuedWithConcurrentStateMachines()
  {
    assertUmpleTemplateFor("queuedWithConcurrentStateMachines.ump",languagePath + "/queuedWithConcurrentStateMachines."+ languagePath +".txt","QueuedWithConcurrentStateMachines");
  }
  
  @Test
  public void queuedSMwithConcurrentStatesTest()
  {
    assertUmpleTemplateFor("queuedSMwithConcurrentStatesTest.ump",languagePath + "/queuedSMwithConcurrentStatesTest."+ languagePath +".txt","QueuedSMwithConcurrentStates");
  }
  
  @Test
  public void queuedSMwithConcurrentStatesTest_2()
  {
    assertUmpleTemplateFor("queuedSMwithConcurrentStatesTest_2.ump",languagePath + "/queuedSMwithConcurrentStatesTest_2."+ languagePath +".txt","QueuedSMwithConcurrentStates_2");
  }
  
  @Test
  public void queuedWithConcurrensStatesCourseAttempt() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException
  {
	Field f1 = Event.class.getDeclaredField("nextAutoTransitionId");
	f1.setAccessible(true);
	f1.setInt(null, 1);
		
	assertUmpleTemplateFor("queuedWithConcurrensStatesCourseAttempt.ump",languagePath + "/queuedWithConcurrensStatesCourseAttempt."+ languagePath +".txt","CourseAttempt");
  }
  
  @Test
  public void queuedWithNestingStatesATM()
  {
    assertUmpleTemplateFor("queuedWithNestingStatesATM.ump",languagePath + "/queuedWithNestingStatesATM."+ languagePath +".txt","AutomatedTellerMachine");
  }
  
  @Test
  public void nestedStatesOfQSMwithSameEventNames()
  {
    assertUmpleTemplateFor("nestedStatesOfQSMwithSameEventNames.ump",languagePath + "/nestedStatesOfQSMwithSameEventNames."+ languagePath +".txt","NestedStatesWthSameEventNames");
  }
  
  @Test
  public void queuedStateMachine_autoTransition() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException
  {
	Field f1 = Event.class.getDeclaredField("nextAutoTransitionId");
	f1.setAccessible(true);
	f1.setInt(null, 1);
	
	assertUmpleTemplateFor("queuedStateMachine_autoTransition.ump",languagePath + "/queuedStateMachine_autoTransition."+ languagePath +".txt","Light");
  }
  
  @Test
  public void queuedStateMachine_timedEvents()
  {
	assertUmpleTemplateFor("queuedStateMachine_timedEvents.ump",languagePath + "/queuedStateMachine_timedEvents."+ languagePath +".txt","Mentor");
  }
  
  @Test
  public void pooledStateMachine_timedEvents()
  {
	assertUmpleTemplateFor("pooledStateMachine_timedEvents.ump",languagePath + "/pooledStateMachine_timedEvents."+ languagePath +".txt","Mentor");
  }
  
  @Test
  public void nestedState_StateMachine_timedEvents()
  {
  	assertUmpleTemplateFor("nestedStates_StateMachine_timedEvent.ump",languagePath + "/nestedStates_StateMachine_timedEvent."+ languagePath +".txt","Window");
  }
  
  @Test
  public void pooledStateMachine()
  {
	assertUmpleTemplateFor("pooledStateMachine.ump",languagePath + "/pooledStateMachine."+ languagePath +".txt","Course");
  }
  
  @Test
  public void pooledStateMachine_withParameters()
  {
	assertUmpleTemplateFor("pooledStateMachine_withParameters.ump",languagePath + "/pooledStateMachine_withParameters."+ languagePath +".txt","LightFixture");
  }
  
  @Test
  public void pooledStateMachine_autoTransition() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException
  {
	Field f1 = Event.class.getDeclaredField("nextAutoTransitionId");
	f1.setAccessible(true);
	f1.setInt(null, 1);
	
	assertUmpleTemplateFor("pooledStateMachine_autoTransition.ump",languagePath + "/pooledStateMachine_autoTransition."+ languagePath +".txt","Light");
  }
  
  @Test
  public void pooledStateMachineWithConcurrentStates_autoTransition() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException
  {
	Field f1 = Event.class.getDeclaredField("nextAutoTransitionId");
	f1.setAccessible(true);
	f1.setInt(null, 1);
	
	assertUmpleTemplateFor("pooledStateMachineWithConcurrentStates_autoTransition.ump",languagePath + "/pooledStateMachineWithConcurrentStates_autoTransition."+ languagePath +".txt","CourseAttempt");
  }
  
  @Test
  public void queuedStateMachine_timedEvents_and_autoTansitions() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException
  {
	Field f1 = Event.class.getDeclaredField("nextAutoTransitionId");
	f1.setAccessible(true);
	f1.setInt(null, 1);
	
	assertUmpleTemplateFor("queuedStateMachine_timedEvents_and_autoTansitions.ump",languagePath + "/queuedStateMachine_timedEvents_and_autoTansitions."+ languagePath +".txt","X");
  }
  
  @Test
  public void pooledStateMachine_timedEvents_and_autoTansitions() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException
  {
	Field f1 = Event.class.getDeclaredField("nextAutoTransitionId");
	f1.setAccessible(true);
	f1.setInt(null, 1);
	
	assertUmpleTemplateFor("pooledStateMachine_timedEvents_and_autoTansitions.ump",languagePath + "/pooledStateMachine_timedEvents_and_autoTansitions."+ languagePath +".txt","X");
  }
  
  @Test 
  public void stateMachine_UnspecifiedReception() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException
  {
	Field f1 = Event.class.getDeclaredField("nextAutoTransitionId");
	f1.setAccessible(true);
	f1.setInt(null, 1);
	
	assertUmpleTemplateFor("stateMachine_UnspecifiedReception.ump",languagePath + "/stateMachine_UnspecifiedReception."+ languagePath +".txt","Course");
  }
  
  @Test
  public void stateMachine_unSpecifiedReception_QSM() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException
  {
	Field f1 = Event.class.getDeclaredField("nextAutoTransitionId");
	f1.setAccessible(true);
	f1.setInt(null, 1);
    
	assertUmpleTemplateFor("stateMachine_unSpecifiedReception_QSM.ump",languagePath + "/stateMachine_unSpecifiedReception_QSM."+ languagePath +".txt","QSMwithUnspecifiedRecep");
  }
  
  @Test
  public void queuedSM_UnspecifiedReception() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException
  {
	Field f1 = Event.class.getDeclaredField("nextAutoTransitionId");
	f1.setAccessible(true);
	f1.setInt(null, 1);
	
    assertUmpleTemplateFor("queuedSM_UnspecifiedRecep.ump",languagePath + "/queuedSM_UnspecifiedRecep."+ languagePath +".txt","AutomatedTellerMachine");
  }
  
  @Test
  public void nestedStates_UnspecifiedReception()
  {
	assertUmpleTemplateFor("nestedStates_UnspecifiedReception.ump",languagePath + "/nestedStates_UnspecifiedReception."+ languagePath +".txt","NestedStatesWthSameEventNames");
  }
  
  @Test
  public void pooledStateMachine_UnspecifiedReception() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException
  {
	 Field f1 = Event.class.getDeclaredField("nextAutoTransitionId");
     f1.setAccessible(true);
	 f1.setInt(null, 1);
		
     assertUmpleTemplateFor("pooledStateMachine_UnspecifiedReception.ump",languagePath + "/pooledStateMachine_UnspecifiedReception."+ languagePath +".txt","PooledSMwithUnspecifiedReception");
  }
  
  @Test
  public void testPooledwithNestedStates()
  {
	assertUmpleTemplateFor("testPooledwithNestedStates.ump",languagePath + "/testPooledwithNestedStates."+ languagePath +".txt","X");
  }
  
  @Test
  public void testPooledwithNestedStates_2()
  {
	assertUmpleTemplateFor("testPooledwithNestedStates_2.ump",languagePath + "/testPooledwithNestedStates_2."+ languagePath +".txt","X");
  }
  
  @Test
  public void testPooledwithNestedStates_3()
  {
	assertUmpleTemplateFor("testPooledwithNestedStates_3.ump",languagePath + "/testPooledwithNestedStates_3."+ languagePath +".txt","X");
  }
  
  @Test
  public void testPooledwithNestedStates_4()
  {
	assertUmpleTemplateFor("testPooledwithNestedStates_4.ump",languagePath + "/testPooledwithNestedStates_4."+ languagePath +".txt","X");
  }
  
  @Test
  public void multiplePooledStateMachine()
  {
    assertUmpleTemplateFor("multiplePooledStateMachine.ump",languagePath + "/multiplePooledStateMachine."+ languagePath +".txt","X");
  }
  
  @Test
  public void multiplePooledStateMachine_EventlessStateMachine()
  {
    assertUmpleTemplateFor("multiplePooledStateMachine_EventlessStateMachine.ump",languagePath + "/multiplePooledStateMachine_EventlessStateMachine."+ languagePath +".txt","X");
  }
  
  @Test
  public void multiplePooledStateMachine_nestedStates()
  {
    assertUmpleTemplateFor("multiplePooledStateMachine_nestedStates.ump",languagePath + "/multiplePooledStateMachine_nestedStates."+ languagePath +".txt","X");
  }
  
  @Test
  public void multiplePooledStateMachines_sameEvents()
  {
    assertUmpleTemplateFor("multiplePooledStateMachines_sameEvents.ump",languagePath + "/multiplePooledStateMachines_sameEvents."+ languagePath +".txt","X");
  }
  
  @Test
  public void multipleQSM()
  {
    assertUmpleTemplateFor("multipleQSM.ump",languagePath + "/multipleQSM."+ languagePath +".txt","X");
  }

  @Test
  public void multipleQSM_EventlessStateMachine()
  {
    assertUmpleTemplateFor("multipleQSM_EventlessStateMachine.ump",languagePath + "/multipleQSM_EventlessStateMachine."+ languagePath +".txt","X");
  }
  
  @Test
  public void multipleQSMe_nestedStates()
  {
    assertUmpleTemplateFor("multipleQSMe_nestedStates.ump",languagePath + "/multipleQSMe_nestedStates."+ languagePath +".txt","X");
  }
  
  @Test
  public void multipleQSM_sameEvents()
  {
    assertUmpleTemplateFor("multipleQSM_sameEvents.ump",languagePath + "/multipleQSM_sameEvents."+ languagePath +".txt","X");
  }
  
  @Test
  public void testMultipleQSMs()
  {
    assertUmpleTemplateFor("testMultipleQSMs.ump",languagePath + "/testMultipleQSMs."+ languagePath +".txt","X");
  }

  @Test
  public void eventlessStateMachine_before_QueuedStateMachine()
  {
    assertUmpleTemplateFor("eventlessStateMachine_QueuedStateMachine.ump",languagePath + "/eventlessStateMachine_QueuedStateMachine."+ languagePath +".txt","X");
  }
  
  @Test
  public void eventlessStateMachine_before_PooledStateMachine()
  {
    assertUmpleTemplateFor("eventlessStateMachine_PooledStateMachine.ump",languagePath + "/eventlessStateMachine_PooledStateMachine."+ languagePath +".txt","X");
  }
  
  @Test
  public void nestedStateMachineExtendedByClass()
  {
    assertUmpleTemplateFor("nestedStateMachineExtendedByClass.ump",languagePath + "/nestedStateMachineExtendedByClass."+ languagePath +".txt","Animal");
  }
  
  @Test
  public void nestedStateMachineExtendedByMultipleClasses()
  {
    assertUmpleTemplateFor("nestedStateMachineExtendedByMultipleClasses.ump",languagePath + "/nestedStateMachineExtendedByMultipleClasses."+ languagePath +".txt","Animal");
  }
  
  @Test
  public void nestedStates_timedTransition()
  {
    assertUmpleTemplateFor("nestedStates_timedTransition.ump",languagePath + "/nestedStates_timedTransition."+ languagePath +".txt","X");
  }

  @Test
  public void nestedStates_Two_TimedTransition()
  {
    assertUmpleTemplateFor("nestedStates_Two_TimedTransition.ump",languagePath + "/nestedStates_Two_TimedTransition."+ languagePath +".txt","X");
  }

  @Test
  public void queuedStateMachine_timedTransition_1()
  {
    assertUmpleTemplateFor("queuedStateMachine_timedTransition_1.ump",languagePath + "/queuedStateMachine_timedTransition_1."+ languagePath +".txt","X");
  }
  
  @Test
  public void pooledStateMachine_timedTransition_1()
  {
    assertUmpleTemplateFor("pooledStateMachine_timedTransition_1.ump",languagePath + "/pooledStateMachine_timedTransition_1."+ languagePath +".txt","X");
  }
  
  @Test
  public void queuedStateMachine_timedTransition_2()
  {
    assertUmpleTemplateFor("queuedStateMachine_timedTransition_2.ump",languagePath + "/queuedStateMachine_timedTransition_2."+ languagePath +".txt","X");
  }
  
  @Test
  public void pooledStateMachine_timedTransition_2()
  {
    assertUmpleTemplateFor("pooledStateMachine_timedTransition_2.ump",languagePath + "/pooledStateMachine_timedTransition_2."+ languagePath +".txt","X");
  }
  //Implementing an interface for classes containing queued and pooled state machines
  @Test
  public void queuedStateMachine_implements()
  {
	assertUmpleTemplateFor("queuedStateMachine_implementsInterface.ump",languagePath + "/queuedStateMachine_interfaceX."+ languagePath +".txt","IX");
    assertUmpleTemplateFor("queuedStateMachine_implementsInterface.ump",languagePath + "/queuedStateMachine_implementsInterface."+ languagePath +".txt","X");
    
  }
  
  @Test
  public void stateMachineWithNegativeNumberGuard()
  {
    assertUmpleTemplateFor("stateMachineWithNegativeNumberGuard.ump",languagePath + "/stateMachineWithNegativeNumberGuard."+ languagePath +".txt","stateMachineWithNegativeNumberGuard");
  }
  
  @Test
  public void stateMachineWithNegativeNumberGuard2()
  {
    assertUmpleTemplateFor("stateMachineWithNegativeNumberGuard2.ump",languagePath + "/stateMachineWithNegativeNumberGuard2."+ languagePath +".txt","stateMachineWithNegativeNumberGuard2");
  }
  
  @Test
  public void stateMachineWithStringComparisonGuard()
  {
    assertUmpleTemplateFor("stateMachineWithStringComparisonGuard.ump",languagePath + "/stateMachineWithStringComparisonGuard."+ languagePath +".txt","stateMachineWithStringComparisonGuard");
  }
  
  @Test
  public void equivalentGuards()
  {
    assertUmpleTemplateFor("equivalentGuards.ump",languagePath + "/equivalentGuards."+ languagePath +".txt","A");
  }
  
  @Test
  public void refactorFinalState_hasAllInvalidElements()
  {
	  assertUmpleTemplateFor("refactorFinalState_hasAllInvalidElements.ump",languagePath + "/refactorFinalState_hasAllInvalidElements."+ languagePath +".txt","X");
  }
  
  @Test
  public void refactorFinalState_onlyEntryAction()
  {
	  assertUmpleTemplateFor("refactorFinalState_onlyEntryAction.ump",languagePath + "/refactorFinalState_onlyEntryAction."+ languagePath +".txt","X");
  }
  
  @Test
  public void refactorFinalState_empty()
  {
	  assertUmpleTemplateFor("refactorFinalState_empty.ump",languagePath + "/refactorFinalState_empty."+ languagePath +".txt","X");
  }
  
  @Test
  public void refactorFinalState_invalidElementsInNestedFinalState()
  {
	  assertUmpleTemplateFor("refactorFinalState_invalidElementsInNestedFinalState.ump",languagePath + "/refactorFinalState_invalidElementsInNestedFinalState."+ languagePath +".txt","X");
  }
  
  @Test
  public void parallelSm_sameNameDiffStates()
  {
    assertUmpleTemplateFor("parallelSm_sameNameDiffStates.ump",languagePath + "/parallelSm_sameNameDiffStates."+ languagePath +".txt","X");
  }
  
  @Test
  public void parallelSm_sameNameDiffStates_2()
  {
    assertUmpleTemplateFor("parallelSm_sameNameDiffStates_2.ump",languagePath + "/parallelSm_sameNameDiffStates_2."+ languagePath +".txt","X");
  }
  
  @Test
  public void parallelSm_sameNameDiffStatesEntryExitActions()
  {
    assertUmpleTemplateFor("parallelSm_sameNameDiffStatesEntryExitActions.ump",languagePath + "/parallelSm_sameNameDiffStatesEntryExitActions."+ languagePath +".txt","X");
  }
  
  @Test
  public void parallelSm_diffNamesDiffStates()
  {
    assertUmpleTemplateFor("parallelSm_diffNamesDiffStates.ump",languagePath + "/parallelSm_diffNamesDiffStates."+ languagePath +".txt","X");
  }
  
  @Test
  public void parallelSm_diffNamesDiffStates_2()
  {
    assertUmpleTemplateFor("parallelSm_diffNamesDiffStates_2.ump",languagePath + "/parallelSm_diffNamesDiffStates_2."+ languagePath +".txt","X");
  }
  
  @Test
  public void parallelSm_diffNamesDiffStatesEntryExitActions()
  {
    assertUmpleTemplateFor("parallelSm_diffNamesDiffStatesEntryExitActions.ump",languagePath + "/parallelSm_diffNamesDiffStatesEntryExitActions."+ languagePath +".txt","X");
  }
  
  @Test
  public void checkExternalTransitions_withExitActions_1()
  {
    assertUmpleTemplateFor("checkExternalTransitions_withExitActions_1.ump",languagePath + "/checkExternalTransitions_withExitActions_1."+ languagePath +".txt","X");
  }
  
  @Test
  public void checkExternalTransitions_withExitActions_2()
  {
    assertUmpleTemplateFor("checkExternalTransitions_withExitActions_2.ump",languagePath + "/checkExternalTransitions_withExitActions_2."+ languagePath +".txt","X");
  }
  
  @Test
  public void checkExternalTransitions_noExitActions_1()
  {
    assertUmpleTemplateFor("checkExternalTransitions_noExitActions_1.ump",languagePath + "/checkExternalTransitions_noExitActions_1."+ languagePath +".txt","X");
  }
  
  @Test
  public void checkExternalTransitions_noNestedStateMachines()
  {
    assertUmpleTemplateFor("checkExternalTransitions_noNestedStateMachines.ump",languagePath + "/checkExternalTransitions_noNestedStateMachines."+ languagePath +".txt","X");
  }
  
  @Test
  public void checkExternalTransitions_concurrentStateMachines()
  {
    assertUmpleTemplateFor("checkExternalTransitions_concurrentStateMachines.ump",languagePath + "/checkExternalTransitions_concurrentStateMachines."+ languagePath +".txt","X");
  }
  
  @Test
  public void checkExternalTransitions_concurrentStateMachines_2()
  {
    assertUmpleTemplateFor("checkExternalTransitions_concurrentStateMachines_2.ump",languagePath + "/checkExternalTransitions_concurrentStateMachines_2."+ languagePath +".txt","X");
  }
  
  @Test
  public void noDefaultEntryMethodGenerated()
  {
    assertUmpleTemplateFor("noDefaultEntryMethodGenerated.ump",languagePath + "/noDefaultEntryMethodGenerated."+ languagePath +".txt","X");    
  }
  
  @Test
  public void noDefaultEntryMethodGenerated_2()
  {
    assertUmpleTemplateFor("noDefaultEntryMethodGenerated_2.ump",languagePath + "/noDefaultEntryMethodGenerated_2."+ languagePath +".txt","X");    
  }
  
  @Test
  public void testFinalKeyword()
  {
    assertUmpleTemplateFor("testFinalKeyword.ump",languagePath + "/testFinalKeyword."+ languagePath +".txt","X");
  }
  
  @Test
  public void testFinalKeyword_2()
  {
    assertUmpleTemplateFor("testFinalKeyword_2.ump",languagePath + "/testFinalKeyword_2."+ languagePath +".txt","X");
  }
  
  @Test
  public void testRegionFinalStates_1()
  {
    assertUmpleTemplateFor("testRegionFinalStates_1.ump",languagePath + "/testRegionFinalStates_1."+ languagePath +".txt","X");
  }
  
  @Test
  public void testRegionFinalStates_2()
  {
    assertUmpleTemplateFor("testRegionFinalStates_2.ump",languagePath + "/testRegionFinalStates_2."+ languagePath +".txt","X");
  }
  
  @Test
  public void testRegionFinalStates_3()
  {
    assertUmpleTemplateFor("testRegionFinalStates_3.ump",languagePath + "/testRegionFinalStates_3."+ languagePath +".txt","X");
  }
  
  @Test
  public void testRegionFinalStates_4()
  {
    assertUmpleTemplateFor("testRegionFinalStates_4.ump",languagePath + "/testRegionFinalStates_4."+ languagePath +".txt","X");
  }
  
  @Test
  public void testRegionFinalStates_5()
  {
    assertUmpleTemplateFor("testRegionFinalStates_5.ump",languagePath + "/testRegionFinalStates_5."+ languagePath +".txt","X");
  }
  
  @Test
  public void testRegionFinalStates_6()
  {
    assertUmpleTemplateFor("testRegionFinalStates_6.ump",languagePath + "/testRegionFinalStates_6."+ languagePath +".txt","X");
  }

  @Test
  public void duplicateTimeoutVariableAndMethod()
  {
    assertUmpleTemplateFor("duplicateTimeoutVariableAndMethod.ump",languagePath + "/duplicateTimeoutVariableAndMethod." + languagePath +".txt","X");
  }
  
  // Issue 1600
  @Test
  public void guardsOnEntryAndExit()
  {
    assertUmpleTemplateFor("1600_guardsOnEntryAndExit.ump",languagePath + "/1600_guardsOnEntryAndExit." + languagePath +".txt","X");
  }
}
