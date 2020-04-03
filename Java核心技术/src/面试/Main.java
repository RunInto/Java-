package 面试;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        String s = "h5_g2";
        System.out.println(s.toUpperCase(Locale.getDefault()));
    }

}



class AlternatingString  {
    public static void main(String[] args) {
        solve("0101010101");
    }

    public static int solve(String arg) {
        String str = "0101010101";
        int count = 0;
        for (int i = 0;i<str.length();i++) {
            if (i != str.length() -4) {
                char one = str.charAt(i);
                char two = str.charAt(i+1);
                char three = str.charAt(i+2);
                 if (one == three && one != two && three != two) {
                     count++;
                 }else {
                     System.out.println(count);
                 }
            }
        }
        System.out.println(count);
        return 0;
    }

}