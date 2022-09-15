/**
 * Scannertest
 */
public class Scannertest {

    public static void main(String[] args) {
        

        String s = "    int x = 1 #hva skjer etter denne";

        String[] sc = s.split(" ");

        for (String word : sc) {
            System.out.println(word);
        }
    }   
}