<?php
  // Calls log on the server for testing purposes
  // Also see UmpleServerTest.php for command line use

  require_once ("setPortNumber.php"); 

  $commandLine = "-log";
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <script src="scripts/_load.js" type="text/javascript"></script>
  <title>Umpleonline internal server log</title>
  <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" /> 
</head>
<body>
<h2>Log of UmpleOnline internal server</h2>
<p>The following line should indicate java and the process number running the server</p>
<?php
  passthru('lsof -i :'.$portnumber.' | grep LISTEN ');
  $theSocket = socket_create(AF_INET, SOCK_STREAM, SOL_TCP);
  if($theSocket === FALSE) {
    echo "<p>Cannot create socket\n";
    $isSuccess = FALSE;
  }
  else {
    $isSuccess= @socket_connect($theSocket, "Localhost", $portnumber);
    if($isSuccess === FALSE) {
      // Exec to start server first, then sleep a bit then try again
      echo "<p>Server doesn't appear to be running; trying to start it\n";
      exec("java -jar umplesync.jar -server ".$portnumber." > /dev/null 2>&1 &"); 
      sleep(1);
      $isSuccess= @socket_connect($theSocket, "Localhost", $portnumber);
      if($isSuccess === FALSE) {
        echo "<p>Try accessing this page again to see if the server has started\n";
      }
      else $isSuccess = TRUE;
    }
  }
  
  if($isSuccess) {
    echo "<p>Successfully established connection to server\n";
   
    echo "\n<p>php version: ".PHP_VERSION;
        
    echo "<p>Java version: ";
    passthru("java -version 2>&1");
 
    $PATH = getenv("PATH");
    if(strpos($PATH, "/usr/bin") == FALSE){
      $PATH .= ":/usr/bin";
    }
    if(strpos($PATH, "/usr/local/bin") == FALSE){
      $PATH .= ":/usr/local/bin";
    }
    putenv("PATH=$PATH");
 
    echo "<p>Dot/Graphviz version: ";
    passthru("dot -V 2>&1");
 
   
    $numBytesSent= socket_write($theSocket, $commandLine);
    if($numBytesSent === FALSE) {
      echo "<p>Cound not send log command to server\n";
      $isSuccess - FALSE;
    }
  }
  
  if($isSuccess) {
    echo "<p>Successfully sent log command to server. Results follow\n<pre>\n";  
    socket_set_option($theSocket, SOL_SOCKET, SO_RCVTIMEO,array("sec"=>25,"usec"=>500000) ); 
    $readMoreLines = TRUE;
    while ($readMoreLines === TRUE) {
      $output = socket_read($theSocket, 65534, PHP_BINARY_READ);
    
      if ($output === FALSE) {
        echo "<p>Socket closed unexpectedly with error code ".socket_last_error($theSocket)."\n";
        echo "<p>Reason: ".socket_strerror(socket_last_error($theSocket))."\n";
        @socket_close($theSocket);;
        break;
      }
      if(strlen($output) == 0) {
        $readMoreLines = FALSE;
      }
      else {
         $output2 = str_replace("JVM up: ", "JVM up: <span style='color: red'>", $output);
         $output3 = str_replace("Version: ", "</span>Version: ", $output2);
         $output2 = str_replace("run since start: ", "run since start: <span style='color: green'>", $output3);
         $output3 = str_replace(" log: ", "</span> log: ", $output2);
         $output2 = str_replace("since July 2019: ", "since July 2019: <span style='color: blue'>", $output3);
         $output3 = str_replace("Number of clients queued: ", "</span>Number of clients queued: ", $output2);
        echo $output3;
      }
    }
    echo "</pre>\n";
    socket_close($theSocket);
  }
?>
</body>
</html>

