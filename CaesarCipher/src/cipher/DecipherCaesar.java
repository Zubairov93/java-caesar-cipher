package cipher;

import java.io.*;
import java.util.Scanner;

public class DecipherCaesar {
    private static int key;
    private static final String ERROR = "Это должно быть целое число от 1 до 39 включительно.";
    private static final String NUMBER = "Вы ввели символ, надо целое число от 1 до 39 включительно";

    protected static String decipher(String str1, String str2) {
        System.out.println("Введите ключ, это должно быть целое число от 1 до 39 включительно.");
        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextInt()) {
            int a = scanner.nextInt();
            if (a > 0 && a < 40) {
                key = a;
            } else {
                return ERROR;
            }
        } else if (scanner.hasNextLine()) {
            return NUMBER;
        }
        scanner.close();

        File file1 = new File(str1);
        if (!file1.exists()) {
            System.out.println("Нет файла");
            return "Исходный файл по этому пути не обнаружен - " + str1 + "\n" + "Расшифрованый текст по этому пути не создан - " + str2;
        }
        StringBuilder line1 = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line1.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encryptText = String.valueOf(line1);

        char[] symbol = new char[encryptText.length()];

        for (int i = 0; i < encryptText.length(); i++) {
            symbol[i] = encryptText.charAt(i);
            for (int j = 0; j < Solution.ALPHABET.length; j++) {
                if (Solution.ALPHABET[j] == encryptText.charAt(i)) {
                    symbol[i] = Solution.ALPHABET[(j + Solution.ALPHABET.length - key) % Solution.ALPHABET.length];
                    break;
                }
            }
        }
        String originalText = String.valueOf(symbol);

        File file2 = new File(str2);
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileWriter writer = new FileWriter(file2)) {
            writer.write(originalText);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Расшифровка прошла успешно, исходный тект в файле - " + str1 + "\n" + "Расшифрованый текст лежит - " + str2;
    }
}
