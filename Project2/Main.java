public class Main{

    private static String s;
    private static int i;

    public static void main(String[] args){

        s = args.length == 1 ? args[0] : " ";

        if(A() && i == s.length()){
            System.out.println("The string \"" + s + "\" is in the language.");
        } else {
            System.out.println("The string \"" + s + "\" is not in the language.");
        }
    }

    private static boolean A(){


        return false;
    }

    private static boolean E(){

    }

    private static boolean O(){

        return false;
    }

    private static boolean P(){

        return false;
    }

    private static boolean U(){

        return false;
    }

    private static boolean I(){

        return false;
    }

    private static boolean C(){

        return false;
    }

    private static boolean L(){

        return false;
    }

    private static boolean D(){
        if(i < s.length() && '0' <= s.charAt(i) && s.charAt(i) <= '9'){
            ++i;
            return true;
        }
        //just making a change
    }
}