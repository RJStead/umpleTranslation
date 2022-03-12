import java.io.*;

public class TXLRunner {
    
    public static void main(String[] args) throws Exception {
        
        //Here is what content looks like directly from umple :
		String umpleContent = "/*PLEASE DO NOT EDIT THIS CODE*/\n/*This code was generated using the UMPLE @UMPLE_VERSION@ modeling language!*/\n\n\n\n// line 1 \"A.ump\"\npublic class A\n{\n\n//------------------------\n// MEMBER VARIABLES\n//------------------------\n\n//------------------------\n// CONSTRUCTOR\n//------------------------\n\npublic A()\n{}\n\n//------------------------\n// INTERFACE\n//------------------------\n\npublic void delete()\n{}\n\n// line 3 \"A.ump\"\nprivate String printHello(){\nreturn \"Hello\";\n}\n\n}";
		
		//here is what umpleContent looks like in a file
		// ========= Umple Content Starts Here =========== //
		// /*PLEASE DO NOT EDIT THIS CODE*/
		// /*This code was generated using the UMPLE @UMPLE_VERSION@ modeling language!*/



		// // line 1 \"A.ump\"
		// public class A
		// {

		// //------------------------
		// // MEMBER VARIABLES
		// //------------------------

		// //------------------------
		// // CONSTRUCTOR
		// //------------------------

		// public A()
		// {}

		// //------------------------
		// // INTERFACE
		// //------------------------

		// public void delete()
		// {}

		// // line 3 \"A.ump\"
		// private String printHello(){
		// 	return \"Hello\";
		// }

		// }
		// ========= Umple Content Ends Here =========== //

        // Build the file almost identical to how it is currently done in umple (I am not sure of what is in contents)
        String contents = "public class B {  }";
        String path = System.getProperty("user.dir");

        String filename = path + File.separator + "test" + ".java";
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));

		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		printWriter.println("public classB {");
		printWriter.println("}");
		
		String contentTest2 = stringWriter.toString();

        try {
          bw.write(umpleContent);
          bw.flush();
        } finally {
          bw.close();
        }

        //Build the command line query
        String query1 = " txl \"" + filename + "\" ./Txl/java2python.Txl -o ./output/test.py"; //Txl without pipe + created java file
        String query2 = "TYPE \"" + filename + "\" | txl stdin ./Txl/java2python.Txl -o ./output/test2.py"; //Txl with pipe + created java file
        String query3 = "ECHO " + contents + " | txl stdin ./Txl/java2python.Txl -o ./output/test3.py"; //Txl with pipe + without creating a java file (Cant have \n)
        String query4 = "ECHO " + contentTest2 + " | txl stdin ./Txl/java2python.Txl -o ./output/test4.py"; //The issue with this query is that ECHO executes as soon as it gets an enter (aka. "\n"). So the programme cant properly pipe the string

        // System.out.println(query4);

        //Run cmd command
        ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", "cd \"..\\JavaTxlRunner\" && " + query2);
        builder.redirectErrorStream(true);

        Process p = builder.start();
        
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
        }
    }
}