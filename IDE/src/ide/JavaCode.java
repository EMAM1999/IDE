/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package ide;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.Scanner;

/**

 @author EMAM
 
 whatsapp -> +201552551432
 facebook -> https://www.facebook.com/EMAM1999
 telegram -> @EMAM1999
 github -> https://github.com/EMAM1999
 youtube list for java level 1 -> https://www.youtube.com/playlist?list=PLy7RKU-J34Z1P_v-rXWCLGoRJK1o-RxEL

 */
public class JavaCode {


          private static int classesNo;

          private File file;
          private String code;
          private String fileName;



          public JavaCode() throws IOException {
                    this("");
          }



          public JavaCode(String code) throws IOException {
                    this("NewClass" + JavaCode.classesNo , code);
          }



          public JavaCode(String fileName , String code) throws IOException {
                    this(new File(fileName.replace(".java" , "") + ".java") , code);
          }



          public JavaCode(File file , String code) throws IOException {
                    classesNo++;
                    this.fileName = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("\\") + 1 , file.getAbsolutePath().lastIndexOf("."));
                    this.file = file;
                    this.code = code;

                    Process p;
                    try ( BufferedWriter f = new BufferedWriter(new FileWriter(file)) ) {
                              f.write(this.code);
                    }
          }



          @SuppressWarnings("empty-statement")
          public boolean compile() {
                    try {
                              Process p = Runtime.getRuntime().exec("javac \"" + file.getAbsolutePath() + "\"");
//                              System.out.println("javac \"" + file.getAbsolutePath() + "\"");
                              while ( p.isAlive() ) ;
                              Scanner in = new Scanner(p.getErrorStream());
                              while ( in.hasNextLine() ) {
                                        System.out.println(in.nextLine());
                              }
                              return p.exitValue() == 0;
                    } catch ( IOException e ) {
                              System.out.println("can not compile");
                    }
                    return false;
          }



          @SuppressWarnings("empty-statement")
          public boolean run() throws IOException {
                    if ( compile() ) {
                              String path = file.getAbsolutePath().substring(0 , file.getAbsolutePath().lastIndexOf("\\"));
//                              System.out.println("java -cp \"" + path + "\" " + fileName);
                              Process p = Runtime.getRuntime().exec("java -cp \"" + path + "\" " + fileName);
//                              while ( p.isAlive() ) ;
                              Scanner in = new Scanner(p.getInputStream());
                              while ( in.hasNextLine() ) {
                                        System.out.println(in.nextLine());
                              }
                              return p.exitValue() == 0;
                    }
                    return false;
          }



          public String getCode() {
                    return code;
          }



          public void setCode(String code) {
                    this.code = code;
          }



          public File getFile() {
                    return file;
          }



          public void setFile(File file) {
                    this.file = file;
          }



          public String getFileName() {
                    return fileName;
          }



          public void setFileName(String fileName) {
                    this.fileName = fileName;
                    String path = this.file.getAbsolutePath().substring(0 , file.getAbsolutePath().lastIndexOf("\\"));
                    this.file.renameTo(new File(path + "\\" + fileName));
          }



          public static boolean run(Path path) throws IOException {
                    return JavaCode.run(path.toUri());
          }



          public static boolean run(URI uri) throws IOException {
                    return JavaCode.run(new File(uri));
          }



          public static boolean run(File file) throws IOException {
                    String code = "";
                    Scanner in = new Scanner(file);
                    while ( in.hasNextLine() ) {
                              code += in.nextLine();
                    }
                    return JavaCode.run(code);
          }



          public static boolean run(String code) throws IOException {
                    code = "public class test {"
                            + "\n         public static void main(String[] args){"
                            + "\n                   " + code 
                            + "\n         }"
                            + "\n}";
                    return new JavaCode("test" , code).run();
          }



          public static boolean compile(Path path) throws IOException {
                    return JavaCode.compile(path.toUri());
          }



          public static boolean compile(URI uri) throws IOException {
                    return JavaCode.compile(new File(uri));
          }



          public static boolean compile(File file) throws IOException {
                    String code = "";
                    Scanner in = new Scanner(file);
                    while ( in.hasNextLine() ) {
                              code += in.nextLine();
                    }
                    return JavaCode.compile(code);
          }



          public static boolean compile(String code) throws IOException {
                    code = "public class test {\n"
                            + "         public static void main(String[] args){\n"
                            + "                   " + code + "\n"
                            + "         }\n"
                            + "}\n";
                    return new JavaCode("test" , code).compile();
          }

}
