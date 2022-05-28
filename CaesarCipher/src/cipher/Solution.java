package cipher;

import java.util.Scanner;

import static cipher.EncryptCaesar.*;

public class Solution {

    protected static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};
    protected static String ORIGINAlTEXT;// = "C:\Users\User\Documents\texts\\text1.txt";
    protected static String cipherText;// = "C:\Users\User\Documents\texts\\text2.txt";
    protected static String caesarDecipheredText;// = "C:\Users\User\Documents\texts\\text3.txt";
    protected static String decodedByBruteForce;// = "C:\Users\User\Documents\texts\\text4.txt";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Здравстуйте!");



            System.out.println("Оригинальный текст, введите первый путь");
            ORIGINAlTEXT = scanner.nextLine();
            System.out.println("Текст будет зашифрован методом Цезаря, введите второй путь");
            cipherText = scanner.nextLine();
            System.out.println("Текст будет расшифрован по методу Цезаря, известным вам ключем, введите третий путь");
            caesarDecipheredText = scanner.nextLine();
            System.out.println("Текст будет расшифрован методом BruteForce, введите 4 путь");
            decodedByBruteForce = scanner.nextLine();


        System.out.println("Если хотите зашифровать текст по методу Цезаря, нажмите цифру 1");
        System.out.println("Если хотите расшифровать текст нажмите цифру 2 ");
        if (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            if (number == 1) {
                System.out.println(EncryptCaesar.encrypt(ORIGINAlTEXT, cipherText));
            }
            if (number == 2) {
                System.out.println("Если хотите расшифровать методом Цезаря по известному Вам ключу, нажмите 1");
                System.out.println("Если хотите расшифровать методом BruteForce, нажмите 2");
            } else {
                scanner.close();
                System.out.println("Вы вышли из программы");
                return;
            }
        } else {
            scanner.close();
            System.out.println("Вы вышли из программы");
            return;
        }
        if (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            if (number == 1) {
                System.out.println(DecipherCaesar.decipher(cipherText, caesarDecipheredText));
            }
            if (number == 2) {
                System.out.println(DecipherBruteForce.deBruteForce(cipherText, decodedByBruteForce));
            } else {
                scanner.close();
                System.out.println("Вы вышли из программы");
                return;
            }
        } else {
            scanner.close();
            return;
        }
        scanner.close();
        System.out.println("Вы вышли из программы");

    }
}
