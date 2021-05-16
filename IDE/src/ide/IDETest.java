/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package ide;

import java.io.IOException;
import java.util.Scanner;

/**

 @author EMAM
 */
public class IDETest {


          public static void main(String[] args) throws IOException {

                    Scanner in = new Scanner(System.in);
                    System.out.println("Enter \"done\" to stop : ");
                    System.out.println(" ^^^^^^^^^^^ ");
                    String code = "";
                    String s = "";
                    do {
                              code += s;
                              s = in.nextLine();
                    } while ( !s.trim().equalsIgnoreCase("done") );
                    System.out.println(" ^^^^^^^^^^^ ");
                    JavaCode.run(code);
          }

}
