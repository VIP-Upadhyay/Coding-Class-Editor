import java.io.*;
public class StringReverseProgram {
    public static void main(String[] args) {
        String str="hello",r="";
        
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            r= ch+r;
        }
        System.out.println(r);
        
    }
}