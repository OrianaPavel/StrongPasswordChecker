import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static int minimumChangeRequired(String input){

        int needsDigit = 1, needsLowercase = 1, needsUppercase = 1;
        int goodCharacters = 0,sameCharacterGroup = 1;

        ArrayList<Integer> repeatingGroups = new ArrayList<Integer>();
        for(int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            if(c >= 'a' && c <='z')
                needsLowercase = 0;
            if(c >= 'A' && c <= 'Z')
                needsUppercase = 0;
            if(c >= '0' && c <= '9')
                needsDigit = 0;
            if(i+1 == input.length()){
                if(sameCharacterGroup == 1) goodCharacters ++;
                if(sameCharacterGroup > 1) repeatingGroups.add(sameCharacterGroup);
            }
            else if (c != input.charAt(i + 1)) {
                if(sameCharacterGroup == 1)
                    goodCharacters++;
                else
                    repeatingGroups.add(sameCharacterGroup);
                sameCharacterGroup=0;
            }
            sameCharacterGroup ++;
        }
        int missingCharacters = needsLowercase + needsDigit + needsUppercase;
        if(input.length() < 6)
            return Math.max(6-input.length(),missingCharacters);
        int quotientDiv3 = 0,multiple3 = 0,multiple3p1 = 0;
        for(Integer n : repeatingGroups) {
            quotientDiv3 += n / 3;
            if(n % 3 == 0) multiple3++;
            if(n % 3 == 1) multiple3p1++;
        }
        if(input.length() <= 20)
           return Math.max(quotientDiv3,missingCharacters);
        if(2 * repeatingGroups.size() + goodCharacters >= 20)
            return input.length()- 20 + missingCharacters;
        int deleteCharacters = input.length() - 20;
        int changes = quotientDiv3;
        int aux = Math.min(multiple3,deleteCharacters);
        changes -= aux; deleteCharacters -= aux;
        aux = Math.min(2*multiple3p1,deleteCharacters);
        changes -= aux/2; deleteCharacters -= aux;
        changes -= deleteCharacters/3;

        return input.length()- 20 + Math.max(changes, missingCharacters);
    }
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String password;
        Scanner sc = new Scanner(System.in);
        password = sc.nextLine();
        System.out.println("Numarul minim de modificari care trebuie efectuate este: " + minimumChangeRequired(password));
    }
}