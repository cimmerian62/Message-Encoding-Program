/*
 * class: CSC191
 * project: Hw4
 * date: Feb. 13, 2020
 * author: Josiah Luikham
 * purpose: Encoding a message
 */

import java.util.Scanner;

public class Hw0004 {
    static String encode(String p, String key) { //this method adds the ascii value of the encoding character to that of the message character to encode
        String code = "";
        int keyNum = 0, asci = 0;
        for(int i = 0; i < p.length(); i++) {
            asci = (p.charAt(i) - 96) + (key.charAt(keyNum) - 96); //the key is added to the char to create an encoded char
            if (asci > 26) 
                asci -= 27;
            if (asci > 0)          
                code += (char)(96 + asci);        
            else if (asci == 0)     
                code += ' ';
            else if (p.charAt(i) == ' ') {       //this code handles when one or both of the strings is a ' '
                if (key.charAt(keyNum) == ' ')
                    code += ' ';
                else 
                    code += (char)(96 + 64 + asci); //bring the value back up to a because ' '+64 = a
            }
            else if (key.charAt(keyNum) == ' ')
                code += (char)(96 + 64 + asci);
                            
            
                
            keyNum++;                        // this counts the encoding variable up and stops it when it gets too high 
            if (keyNum == key.length())
                keyNum = 0;
        }
        return code;
    }
        
    static String decode(String c, String key) { //this method subtracts the ascii value of the encoding character from that of the message character to decode
        String decode = "";
        int keyNum = 0, asci = 0;
        for (int i = 0; i < c.length(); i++) {
            asci = (c.charAt(i) - 96) - (key.charAt(keyNum) - 96);    //the same as encodeing but he key is subtracted
            if (asci < 0)
                asci += 27;
            if (asci > 0)          
                decode += (char)(96 + asci);    
            else if (asci == 0)     
                decode += ' ';
        
            else if (c.charAt(i) == ' ') {       //this code handles when one or both of the strings is a ' '
                if (key.charAt(keyNum) == ' ')
                    decode += ' ';
                else 
                    decode += (char)(96 + 64 + asci);
            }
            else if (key.charAt(keyNum) == ' ')
                decode += (char)(96 + 64 + asci);
            keyNum++;                        // this counts the decoding variable up and stops it when it gets too high 
            if (keyNum == key.length())
                keyNum = 0;
        }
        return decode;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String res;
        
        do {  //menu setup
            System.out.println("e. ecode");
            System.out.println("d. decode");

            System.out.println("q. quit");
            
            System.out.print("select: ");
            res = in.next(); 
            in.nextLine();
            
            switch (res.toLowerCase().charAt(0)) {
                case 'e':
                    System.out.print("Enter a plain text: ");
                    String pText = in.nextLine();
                    System.out.print("Enter a key: ");
                    String key = in.nextLine();

                    System.out.println(pText+" encoded to "+encode(pText, key));
                    break;
                case 'd':
                    System.out.print("Enter a cipher text: ");
                    String cText = in.nextLine();
                    System.out.print("Enter a key: ");
                    key = in.nextLine();

                    System.out.println(cText+" decoded to "+decode(cText, key));
                    break;
                
                case 'q':
                    System.out.println("Thanks for using my program");
                    break;
                default:
                    System.out.println("invalid!");       
            }          
        } while (res.toLowerCase().charAt(0) != 'q');  
    }        
}
