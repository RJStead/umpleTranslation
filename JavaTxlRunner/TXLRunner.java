import java.io.*;

public class TXLRunner {

    
    public static void main(String[] args) throws Exception {
        
        // Build the file almost identical to how it is currently done in umple (I am not sure of what is in contents)
        String contents = "public class B {  }";
        String path = System.getProperty("user.dir");

        String filename = path + File.separator + "test" + ".java";
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));

        try {
          bw.write(contents);
          bw.flush();
        } finally {
          bw.close();
        }

        //Build the command line query
        String query1 = " txl \"" + filename + "\" ./Txl/java2python.Txl -o ./output/test.py"; //Txl without pipe + created java file
        String query2 = "TYPE \"" + filename + "\" | txl stdin ./Txl/java2python.Txl -o ./output/test2.py"; //Txl with pipe + created java file
        String query3 = "ECHO " + contents + " | txl stdin ./Txl/java2python.Txl -o ./output/test3.py"; //Txl with pipe + without creating a java file (Cant have \n)

        // System.out.println(query4);

        //Run cmd command
        ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", "cd \"..\\JavaTxlRunner\" && " + query3);
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