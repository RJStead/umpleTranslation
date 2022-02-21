package cruise.tracer.test;

import java.io.PrintStream;
import java.util.Arrays;

import org.junit.*;

@Ignore
public class ConsoleTracerTest
{

  //StringTracer tracer;
  PrintStream defaultPS;


  @Before
  public void setUp()
  {
    //tracer = StringTracer.getInstance();
    //tracer.reset();
    defaultPS = System.err;
  }

  @Test @Ignore
  public void traceConsole()
  {
//    PrintStream ps = new PrintStream(System.err){
//      int index=0;
//      String[] attrTraceExpected = {"Time",
//          "at_s,name,null,Geoff",
//          "at_s,name,Geoff,Hamoud",
//          "at_g,address,800 king Edward",
//          "at_s,salary,0.0,1000.0",
//          "at_g,salary,1000.0",
//          "at_s,number1,7,8",
//          "at_s,number2,22",
//          "at_s,number2,33",
//          "at_s,number3,45",
//          "at_s,number3,46",
//          "at_s,number4,5.5",
//          "at_s,number4,6.6",
//          "at_s,str,null,String 1",
//          "at_s,str,String 1,String 2",
//          "at_s,str,String 2,String 3"
//      };
//
//      String[] attrTraceCondExpected = {
//          "at_s,n1,50,110",
//          "at_s,n1,110,120",
//          "at_s,n2,70",
//          "at_s,n2,80",
//          "at_s,n3,33",
//          "at_s,n3,44",
//          "at_s,n4,99.0"
//      };
//
//      String[] recordTraceExpected = {
//          "at_s,number1,0,100,TracingNumber1",
//          "at_s,number2,0,200,Tracing Number2"
//      };
//
//      String[] stmTraceExpected = {
//          //=== GarageDoorA
//          "sm_t,Open,buttonOrObstacle,Closing",
//          "sm_t,Closing,reachBottom,Closed",
//          "sm_t,Open,buttonOrObstacle,Closing",
//          "sm_t,Closing,reachBottom,Closed",
//          "sm_e,Closed,actionUmpleLine=46,GarageDoor",
//          "sm_t,Closing,reachBottom,Closed",
//          "sm_e,Closed,actionUmpleLine=46,GarageDoor",
//          "sm_x,Closed,actionUmpleLine=81,GarageDoor",
//          "sm_t,Closed,buttonOrObstacle,Opening",
//          "sm_t,Open,buttonOrObstacle,Closing",
//          "sm_t,Closed,buttonOrObstacle,Opening",
//          "sm_t,Open,buttonOrObstacle,Closing",
//          "sm_t,Closing,buttonOrObstacle,Opening",
//          "sm_t,Opening,buttonOrObstacle,HalfOpen",
//          //=== LightFixtureA
//          "sm_t,Open,flip,Closed",
//          "sm_da,Closed,doActivitedStarted,status",
//          "sm_di,Closed,doActivitedInterrupted,status",
//          "sm_t,Closed,flip,Open",
//          //=== LightFixtureB
//          "sm_t,Off,flip,On",
//          "sm_t,Normal,touch,Dimmed",
//          "sm_t,Dimmed,touch,Off",
//          "sm_t,Off,flip,On",
//          "sm_t,On,flip,Off",
//          //=== NestedStateA
//          "sm_t,sLevel1,e1,sLevel2b",
//          "sm_t,sLevel2b,e2,sLevel2aLevel3a",
//          "sm_t,sLevel2aLevel3a,e3,sLevel2aLevel3b",
//          "sm_t,sLevel2aLevel3bLevel4,e4,outsideNested",
//          //=== NestedStateB
//          //					"sm_t,Off,flip,Idle",
//          //					"sm_t,Idle,flip,Running",
//          //=== NestedStateLevel1
//          "sm_t,sLevel1,e1,sLevel2b",
//          //=== NestedStateLevel2
//          "sm_t,sLevel1,e1,sLevel2b",
//          "sm_t,sLevel2b,e2,sLevel2aLevel3a",
//          //=== NestedStateLevel4
//          "sm_t,sLevel1,e1,sLevel2b",
//          "sm_t,sLevel2b,e2,sLevel2aLevel3a",
//          "sm_t,sLevel2aLevel3a,e3,sLevel2aLevel3b",
//          "sm_t,sLevel2aLevel3bLevel4,e4,outsideNested",
//          //=== ConcurrentStmA
//          "sm_t,Off,turnOn,On",
//          "sm_t,MotorIdle,flip,MotorRunning",
//          "sm_t,FanIdle,flop,FanRunning",
//          "sm_t,MotorRunning,flip,MotorIdle",
//          "sm_t,MotorIdle,flup,Off",
//          //=== TimedEventA
//          //					"sm_t,Open,timeoutOpenToClosed,Closed",
//          //					"sm_t,Closed,timeoutClosedToFinal,Final",
//          //=== QueuedStmTraceA
//          //					"sm_t,Open,register,Full",
//          //					"sm_t,Full,reject,Full"
//      };
//
//      String[] assocTraceExpected = {
//          "as_a,managerA,1",
//          "as_a,managerA,2",
//          "as_r,managerA,1",
////          "as_a,managerB,1",
////          "as_a,managerB,2",
////          "as_a,managerB,3",
////          "as_a,managerB,3"
//      };
//
//      String[] expected = concatAll(attrTraceExpected, attrTraceCondExpected, recordTraceExpected, stmTraceExpected, assocTraceExpected);
//
//      Integer[] testField = {0,9,9};
//      @Override
//      public void println(String x){
//        if(index<expected.length){
//
//          					System.out.println("out = "+x);
//          String[] actualOutput = x.split(",");
//          String[] expectedOutput = expected[index].split(",");
//
//          for(int i = 0 ; i<actualOutput.length;++i )
//            System.out.println("actualOutput["+i+"]"+" = "+actualOutput[i]);
//          for(int i = 0 ; i<expectedOutput.length;++i )
//            System.out.println("expectedOutput["+i+"]"+" = "+expectedOutput[i]);
//          // header skip
//          if( index != 0 )
//            for( int i = 0 ; i < expectedOutput.length ; ++i )
//              Assert.assertEquals(expectedOutput[i],actualOutput[i+6]);
//
//          index++;
//        }
//        else {
//          Assert.assertTrue(false);
//        }
//      }
//    };
//    System.setErr(ps);
//
//    //--------------------------------------- invoke attributes tracing
//
//    TraceAttr aTest = new TraceAttr(null, null, 0, false, 0, null, 0, 0, 0, 0, null);
//
//    aTest.setName("Geoff");
//    aTest.setName("Hamoud");
//    aTest.getName();
//
//    aTest.setFlag(false);
//
//    aTest.setAddress("800 king Edward");
//    aTest.getAddress();
//
//    aTest.setSalary(1000);
//    aTest.getSalary();
//
//    aTest.setNumber1(7);
//    aTest.setNumCond(110);
//    aTest.setNumber1(8);
//
//    aTest.setNumber2(11);
//    aTest.setNumCond(20);
//    aTest.setNumber2(22);
//    aTest.setNumber2(33);
//
//    aTest.setNumber3(44);
//    aTest.setNumCond(1);
//    aTest.setNumber3(45);
//    aTest.setNumber3(46);
//
//    aTest.setNumber4(5.5);
//    aTest.setNumber4(6.6);
//    aTest.setNumCond(-99);
//    aTest.setNumber4(7.7);
//
//    aTest.setStr("String 1");
//    aTest.setStr("String 2");
//    aTest.setStr("String 3");
//    aTest.setStr("String 4");
//
//    TraceAttrCond acTest = new TraceAttrCond(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
//
//    acTest.setN1(50);
//    acTest.setN1(110);
//    acTest.setN1(120);
//    acTest.setN1(130);
//
//    acTest.setN2(-2);
//    acTest.setN2(70);
//    acTest.setN2(80);
//    acTest.setN2(90);
//
//    acTest.setN3(0);
//    acTest.setN3(22);
//    acTest.setN3(33);
//    acTest.setN3(44);
//
//    acTest.setN4(99);
//    acTest.setN4(0.5);
//    acTest.setN4(-8);
//
//    //	  acTest.setN5(35);
//    //	  acTest.setN5(15);
//    //	  acTest.setN5(55);
//    //	  acTest.setN5(-1);
//
//    //==== invoke trace record
//
//    TraceRecord rTrace = new TraceRecord(0, 0, null);
//    rTrace.setNumber1(100);
//    rTrace.setStr("Tracing Number2");
//    rTrace.setNumber2(200);
//
//    //--------------------------------------- invoke state machine tracing
//
//    GarageDoorA gaTrace = new GarageDoorA();
//    gaTrace.buttonOrObstacle();
//    gaTrace.reachBottom();
//    gaTrace.buttonOrObstacle();
//    gaTrace.reachTop();
//    gaTrace.buttonOrObstacle();
//
//    GarageDoorB gbTrace = new GarageDoorB();
//    gbTrace.buttonOrObstacle();
//    gbTrace.reachBottom();
//    gbTrace.buttonOrObstacle();
//    gbTrace.reachTop();
//    gbTrace.buttonOrObstacle();
//    gbTrace.reachBottom();
//
//    GarageDoorC gcTrace = new GarageDoorC();
//    gcTrace.buttonOrObstacle();
//    gcTrace.reachBottom();
//    gcTrace.buttonOrObstacle();
//    gcTrace.reachTop();
//    gcTrace.buttonOrObstacle();
//    gcTrace.reachBottom();
//
//    GarageDoorD gD = new GarageDoorD();
//    gD.buttonOrObstacle();
//    gD.reachBottom();
//    gD.buttonOrObstacle();
//    gD.reachTop();
//    gD.buttonOrObstacle();
//    gD.buttonOrObstacle();
//    gD.buttonOrObstacle();
//
//    LightFixtureA lA = new LightFixtureA();
//    lA.flip();
//    try {Thread.sleep(500);} catch (InterruptedException e) { e.printStackTrace();}
//    lA.flip();
//    try {Thread.sleep(500);} catch (InterruptedException e) { e.printStackTrace();}
//
//    LightFixtureB lB = new LightFixtureB();
//    lB.flip();
//    lB.touch();
//    lB.touch();
//    lB.flip();
//    lB.flip();
//
//    NestedStateA nsA = new NestedStateA();
//    nsA.e1();
//    nsA.e2();
//    nsA.e3();
//    nsA.e4();
//
//    //	  NestedStateB nsB = new NestedStateB();
//    //	  nsB.flip();
//    //	  nsB.flip();
//
//    NestedStateLevel1 ns1 = new NestedStateLevel1();
//    ns1.e1();
//    ns1.e2();
//    ns1.e3();
//    ns1.e4();
//
//    NestedStateLevel2 ns2 = new NestedStateLevel2();
//    ns2.e1();
//    ns2.e2();
//    ns2.e3();
//    ns2.e4();
//
//    NestedStateLevel4 ns4 = new NestedStateLevel4();
//    ns4.e1();
//    ns4.e2();
//    ns4.e3();
//    ns4.e4();
//
//    ConcurrentStmA cA = new ConcurrentStmA();
//    cA.turnOn();
//    cA.flip();
//    cA.flop();
//    cA.flip();
//    cA.flup();
//
//    //	  TimedEventA tA = new TimedEventA();
//
//    //	  HisTest ht = new HisTest();
//    //
//    //	   // Initial state s1a
//    //	   ht.e1();  // s1b
//    //	   ht.e2();  // s2a;
//    //	   ht.e2();  // s1b based on history
//    //	   ht.e2();  // s2a based on history
//    //	   ht.e1();  // s2b;
//    //	   ht.e2();  // s1b based on history
//    //	   ht.e1();  // s1a;
//
//    //	  QueuedStmTraceA qA = new QueuedStmTraceA();
//    //	  qA.register();
//    //	  qA.reject();
//
//    //--------------------------------------- invoke attributes tracing
//
//    CompanyManyToMany compM = new CompanyManyToMany();
//
//    ManagerA m1 = new ManagerA();
//    ManagerA m2 = new ManagerA();
//    ManagerA m3 = new ManagerA();
//
//    compM.addManagerA(m1);
//    compM.addManagerA(m2);
//    compM.addManagerA(m3);
//
////    compM.addManagerB(mB);
////    compM.addManagerB(mB);
////    compM.addManagerB(mB);
////    compM.removeManagerB(mB);
////    compM.addManagerB(mB);
//
//    //    CompanyA compA = new CompanyA();
//    //    Manager m1 = new Manager();
//    //    Manager m2 = new Manager();
//    //    compA.addManager(m1);
//    //    compA.addManager(m2);
//    //    compA.removeManager(m1);
//



  }

  public static <String> String[] concatAll(String[] first, String[]... rest) {
    int totalLength = first.length;
    for (String[] array : rest) {
      totalLength += array.length;
    }
    String[] result = Arrays.copyOf(first, totalLength);
    int offset = first.length;
    for (String[] array : rest) {
      System.arraycopy(array, 0, result, offset, array.length);
      offset += array.length;
    }
    for(int i = 0 ; i<result.length;++i )
      System.out.println("result[i] = "+result[i]);
    return result;
  }

  @After
  public void cleanUp()
  {
    System.setErr(defaultPS);
    //tracer = StringTracer.getInstance();
    //tracer.reset();
  }
}
