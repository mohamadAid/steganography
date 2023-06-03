package steganography3;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class MainFrame extends javax.swing.JFrame {
    static int row;
    static int col;
    static int height;
    static int width;
    public static void nextpixel()
    {
        if (col<height) col++;
        else {  if (row<width) { row++;col=0;}}
    }
    public static int[] getPixelDetails(int p)
    {
        int[]d=new int[4];
        // get alpha 
        d[3] = (p>>24) & 0xff; 
        // get red 
        d[2] = (p>>16) & 0xff; 
        // get green 
        d[1] = (p>>8) & 0xff; 
        // get blue 
        d[0] = p & 0xff; 
        return d;
    }
    public static int setPixelDetails(int a,int r, int g, int b)
    {
        return (a<<24) | (r<<16) | (g<<8) | b; 
    }
    public static int[] char2bin(char ch)
    {
        int bin[]=new int[8];
        int i, value;
        value=ch;
        i=0;
        while (value>0)
        {
            bin[i]=value%2;
            value=value/2;
            i++;
        }
        return bin;
    }
    public static char bin2char(int []bin)
    {
        int value=0;
        for (int i=0;i<bin.length;i++)
            value+=Math.pow(2, i)*bin[i];
        char ch=(char)value;
        return ch;
    }
    public static int bin2dec(int []bin)
    {
        int value=0;
        for (int i=0;i<bin.length;i++)
            value+=Math.pow(2, i)*bin[i];
        return value;
    }
    public static int[] dec2bin(int value)
    {
        int bin[]=new int[16];
        int i;
        i=0;
        while (value>0)
        {
            bin[i]=value%2;
            value=value/2;
            i++;
        }
        return bin;
    }

    public static void writetofile(ArrayList<String> list, String filepath) throws IOException
    {
        int i;
        PrintWriter f1=new PrintWriter(filepath);
        for(i=0;i<list.size();i++)
            if(list.get(i).equals("\n"))
                f1.println();
            else
                f1.print(list.get(i));
        f1.close();
    }
    
    public static ArrayList<String> readfromfile(String filepath) throws IOException
    {
        String s;
        ArrayList<String> list=new ArrayList<String>();
        File f1=new File(filepath);
        Scanner input=new Scanner(f1);
        while(input.hasNextLine())
        {
            s=input.nextLine();
            list.add(s);
        }
        return list;
    }
    public static void storByte(int data[],BufferedImage image )
    {
        int p,i,ind=0;
        int []d;
        while (ind<data.length)
        {
            p = image.getRGB(row,col); 
            d=getPixelDetails(p);
            if (data[ind]==0 && d[0]%2==1) d[0]--;
            else if (data[ind]==1 && d[0]%2==0) d[0]++;
            p=setPixelDetails(d[3],d[2],d[1],d[0]);
            image.setRGB(row, col, p);
            nextpixel();
            ind++;
        }
    }
    public static int[] readByte(BufferedImage image )
    {
        int ind=0,p,i;
        int []d;
        int data[]=new int [8];
        while (ind<data.length)
        {
            p = image.getRGB(row,col); 
            d=getPixelDetails(p);
            data[ind]=d[0]%2;
            nextpixel();
            ind++;
        }
        return data;
    }
    public MainFrame() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(204, 255, 255));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 102, 102));
        jButton1.setText("Encryption");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 150, 50));

        jButton2.setBackground(new java.awt.Color(204, 255, 255));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 102, 102));
        jButton2.setText("Decryption");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, 150, 50));

        jButton3.setBackground(new java.awt.Color(204, 255, 255));
        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 51, 0));
        jButton3.setText("Close");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 380, 190, 60));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Start Encrypting and Decrypting Messages");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 570, 46));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/steganography3/Main.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int x,textlength=0,w,z;
        String s;
        int []data=new int[8];
        int []textlengtharr=new int [16];
        BufferedImage image = null;
        ArrayList<String> wordslist;
        String selectedFile1 ,selectedFile2 ,selectedFile3 ; 
            JFileChooser fileChooser = new JFileChooser("C:\\");
            fileChooser.setDialogTitle("Select a bmp Image to Encrypt the Message");
            int result = fileChooser.showOpenDialog(this);
            if (result != JFileChooser.APPROVE_OPTION)
                JOptionPane.showMessageDialog(null,"you did not choose image which you wante to hide date in it");
            else
            {
                selectedFile1 = fileChooser.getSelectedFile().getAbsolutePath();
                fileChooser.setDialogTitle("Choose a file to save the encrypted image");
                result = fileChooser.showSaveDialog(this);
                if (result != JFileChooser.APPROVE_OPTION)
                    JOptionPane.showMessageDialog(null,"you did not choose output image file");    
                else
                {
                    selectedFile2 = fileChooser.getSelectedFile().getAbsolutePath();
                    fileChooser.setDialogTitle("Select the text file which you want to encrypt it");
                    result = fileChooser.showOpenDialog(this);
                    if (result != JFileChooser.APPROVE_OPTION)
                        JOptionPane.showMessageDialog(null,"you did not choose text file which you want to encrypt it" );    
                    else
                    {
                        selectedFile3 = fileChooser.getSelectedFile().getAbsolutePath();
                        File input_file1 = new File(selectedFile1);
                        try
                        {
                            image = ImageIO.read(input_file1);
                            width = image.getWidth(); 
                            height = image.getHeight(); 
                            row=0;col=16;
                            wordslist=readfromfile(selectedFile3);
                            for(w=0;w<wordslist.size();w++)
                            {
                                s = wordslist.get(w);
                                s=s+"\n";
                                textlength+=s.length();
                                for(int let=0;let<s.length();let++)
                                {
                                    data=char2bin(s.charAt(let));
                                    storByte(data,image);
                                }
                            }
                            //store text length;
                            row=0;col=0;
                            textlengtharr=dec2bin(textlength);
                            for( z=0;z<8;z++)
                                data[z]=textlengtharr[z];
                            storByte(data,image);
                            for(z=0;z<8;z++)
                                data[z]=textlengtharr[z+8];
                            storByte(data,image);
                            File output_file = new File(selectedFile2);
                            ImageIO.write(image, "bmp", output_file);
                            JOptionPane.showMessageDialog(null,"Encryption done\nText length="+textlength);
                        }
                        catch(IOException e)
                        {
                            System.out.println("Error: "+e);
                        }  
                    }

                }
            } 
        //}
        //catch(IOException e) 
        //{ 
          //  System.out.println("Error: "+e); 
        //} 

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int x,textlength,w,z;
        String s;
        String selectedFile1 ,selectedFile2;
        int []data;
        int []textlengtharr=new int [16];
        BufferedImage image= null;
        ArrayList<String> outlist=new ArrayList<String>();
        JFileChooser fileChooser = new JFileChooser("C:\\");
        fileChooser.setDialogTitle("Choose the image which contain encryption data");
        int result = fileChooser.showOpenDialog(this);
        if (result != JFileChooser.APPROVE_OPTION)
            JOptionPane.showMessageDialog(null,"you did not choose image which contain encryption data");
        else
        {
            selectedFile1 = fileChooser.getSelectedFile().getAbsolutePath();
            fileChooser.setDialogTitle("Choose a file to save the Output Text File and named it");
            result = fileChooser.showSaveDialog(this);
            if (result != JFileChooser.APPROVE_OPTION)
                JOptionPane.showMessageDialog(null,"you did not choose output text file");    
            else
            {
                selectedFile2 = fileChooser.getSelectedFile().getAbsolutePath();
                File input_file1 = new File(selectedFile1);
                try
                {
                    image = ImageIO.read(input_file1);
                    width = image.getWidth(); 
                    height = image.getHeight();
                    //read text length
                    row=0;col=0;
                    data=readByte(image);
                    for (z=0;z<8;z++)
                        textlengtharr[z]=data[z];
                    data=readByte(image);
                    for (z=0;z<8;z++)
                        textlengtharr[z+8]=data[z];
                    textlength=bin2dec(textlengtharr);
                    row=0;col=16;
                    for(x=1;x<=textlength;x++)
                    {
                        data=readByte(image);
                        s=String.valueOf(bin2char(data));
                        outlist.add(s);
                    }
                    writetofile(outlist,selectedFile2);
                    JOptionPane.showMessageDialog(null,"Decryption Done");
                }
                catch(IOException e) 
                {    
                    System.out.println("Error: "+e); 
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
