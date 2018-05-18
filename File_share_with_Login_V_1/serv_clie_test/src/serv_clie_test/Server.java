
package serv_clie_test;
/**
 *
 * @author DIPU
 */

import java.io.*;  
import java.net.*;  
   
class Server  
{    
    public static void main(String args[])throws Exception  
    {  
        // Login ob=new Login();

        System.out.println("Server starts running...");  
   
        /* Listen on  port 1201 */  
   
        ServerSocket server = new ServerSocket(1201);  
   
        /* Accept the sk */  
   
        Socket sk = server.accept();  
         
        System.out.println("Request Accepted");  
        InputStream input = sk.getInputStream();  
        BufferedReader inReader = new BufferedReader(new InputStreamReader(sk.getInputStream()));  
        BufferedWriter outReader = new BufferedWriter(new OutputStreamWriter(sk.getOutputStream()));  
   
        /*  filename  reading*/  
        String filename = inReader.readLine(); 
         String id = inReader.readLine();
         
       // String s_ip= inReader.readLine();
         String path= "C:\\"+"\\"+"TEMP\\"+"\\"+id;
             File file=new File(path);
         file.mkdirs();
      //   file.getParentFile().mkdirs();
     //    FileWriter writer= new FileWriter(file);
       //  FileOutputStream wr1= new FileOutputStream(new File(writer+filename));
        System.out.println(path);
  //      if(!file.exists()){
            
          FileOutputStream wr = new FileOutputStream(new File(file +"/"+ filename));  
    //    }
      //  else{
         //   FileOutputStream wr = new FileOutputStream(new File(file +"/"+ filename));  
        //    System.out.println("Can't create file.");
       // }
   
        if ( !filename.equals(" ") ){  
            outReader.write("READY to Connect\n");  
            outReader.flush();  
        }  
   
        /* Create a new file in the TEMP directory using the filename */ 
        //System.out.println(ob.std_id.getText());
        
        
      
   
        byte[] buffer = new byte[sk.getReceiveBufferSize()];  
   
        int bytesCount = 0;  
   
        while((bytesCount = input.read(buffer))>0)  
        {  
            /* Write to the file */ 
       /*     System.out.println("Bytes received: \n" + bytesReceived);*/
           wr.write(buffer,0,bytesCount);  
        }  
    }  
} 
