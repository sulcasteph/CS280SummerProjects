import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


public class Main{

    private static String s;
    private static int i;

    public static void main(String[] args){
        try(BufferedReader br = new BufferedReader(new FileReader("input.txt"))){
            String line;
            while((line = br.readLine()) != null){
                s = line.trim();
                i = 0;

                if(A() && i == s.length()){
                    System.out.println("The string \"" + s + "\" is in the language.");
                }
                else{
                    System.out.println("The string \"" + s + "\" is NOT in the language.");
                }
            }
        } catch (IOException e){
            System.err.println("Error reading from input.txt file: " + e.getMessage());
        }
    }


    private static boolean A(){
        if(I()){
            if(i < s.length() && s.charAt(i) == '='){
                ++i;
                if(E()){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean E() {
        if (!P()) return false;
        while (O()) {
            if (!P()) return false;
        }
        return true;
    }

    private static boolean O(){
        if(i < s.length()){
            if(i + 1 < s.length() && s.charAt(i) == '*' && s.charAt(i + 1) == '*'){
                i += 2;
                return true;
            }
            else if(s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/'){
                ++i;
                return true;
            }
        }
        return false;
    }

    private static boolean P(){
        if(I()){
            return true;
        }
        else if(L()){
            return true;
        }
        else if(U()){
            if(I() || L()){
                return true;
            }
        }
        else if(i < s.length() && s.charAt(i) == '('){
            ++i;
            if(E()){
                if(i < s.length() && s.charAt(i) == ')'){
                    ++i;
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean U() {
        if (i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '!')) {
            ++i;
            return true;
        }
        return false;
    }

    private static boolean I() {
        int start = i;
        if (C()) {
            while (C()) {}
            return true;
        }
        i = start;
        return false;
    }

    private static boolean C(){
        if(i < s.length() && s.charAt(i) <= 'z' && s.charAt(i) >= 'a'){
            ++i;
            return true;
        }
        return false;
    }

    private static boolean L() {
        int start = i;
        if (D()) {
            while (D()) {}
            return true;
        }
        i = start;
        return false;
    }

    private static boolean D(){
        if(i < s.length() && '0' <= s.charAt(i) && s.charAt(i) <= '9'){
            ++i;
            return true;
        }
        return false;
    }
}