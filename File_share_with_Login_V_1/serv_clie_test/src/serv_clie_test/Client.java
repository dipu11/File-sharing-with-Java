
package serv_clie_test;

/**
 *
 * @author DIPU
 */
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import java.net.*;  
import java.io.*;  
import javax.swing.JOptionPane;


   
public class Client extends JFrame implements ActionListener {  
   
    private JTextField txtFile;
    String id, ip;
    int port;
     public Client(String x, String y, String z)
     {
         
         ip=x;
         port=Integer.parseInt(y);
         id=z;
     }
//    public static void main(String args[]){       
//     //  String ip,std_id,port_no;
////        Global.ip =JOptionPane.showInputDialog( "Enter the Server IP");
////        Global.std_id =JOptionPane.showInputDialog( "Enter Student ID");
////       Global.port_no =JOptionPane.showInputDialog( "Enter Port No.");
             
//   }
    public void Display(){  
   
        JFrame frame = new JFrame();  
        frame.setTitle("Client: "+ id+" Side");  
   
        FlowLayout layout = new FlowLayout();  
        layout.setAlignment(FlowLayout.CENTER);  
   
        JLabel lblFile = new JLabel("File Name:");  
         
        txtFile = new JTextField();  
        txtFile.setPreferredSize(new Dimension(150,40));  
   
        JButton btnTransfer = new JButton("Select File");  
        btnTransfer.addActionListener(this);  
   
        JPanel mainPanel = new JPanel();  
        mainPanel.setLayout(layout);  
        mainPanel.add(lblFile);  
        mainPanel.add(txtFile);  
        mainPanel.add(btnTransfer);  
   
        frame.getContentPane().add(mainPanel);  
        frame.pack();  
        frame.setVisible(true);  
    }  
   
    public void actionPerformed(ActionEvent e) {  
  
        JFileChooser fileDlg = new JFileChooser();  
        fileDlg.showOpenDialog(this);  
        String filename = fileDlg.getSelectedFile().getAbsolutePath();  
        txtFile.setText(filename);  
   
        try{  
   
            /* Try to connect to the server on localhost, port 1201 */  
   
            Socket sk = new Socket(ip, port);  
            OutputStream output = sk.getOutputStream();  
     //filename sending to server
   
            OutputStreamWriter outputStream = new OutputStreamWriter(sk.getOutputStream());  
            outputStream.write(fileDlg.getSelectedFile().getName() + "\n");  
            outputStream.flush();
            
            outputStream.write(id+ "\n");  
            outputStream.flush();   
   
            BufferedReader inReader = new BufferedReader(new InputStreamReader(sk.getInputStream()));  
   
            String serverStatus = inReader.readLine(); // Read the first line  
   
              
   
           if ( serverStatus.equals("READY to Connect") && ip.equals("127.0.0.1")){  
   
                FileInputStream file = new FileInputStream(filename);  
   
                byte[] buffer = new byte[sk.getSendBufferSize()];  
   
                int bytesCount = 0;  
   
                while((bytesCount = file.read(buffer))>0)  
                {  
                    output.write(buffer,0,bytesCount);  
                }  
   
                output.close();  
                file.close();  
                sk.close();  
   
                JOptionPane.showMessageDialog(this, "Transfer complete!");  
            } 
           else{
             // System.out.println("Sorry, Server IP is not Correct!");
               JOptionPane.showMessageDialog(this, "Sorry, Server IP is not Correct!"); 
               
           }
        }  
        catch (Exception ex){  
            /* Catch any errors */  
            JOptionPane.showMessageDialog(this, ex.getMessage());  
        }  
    }  
}
