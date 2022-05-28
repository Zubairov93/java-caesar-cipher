package cipher;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DecipherBruteForce {
    private static int key = 1;
    private static HashMap<Integer, Integer> nums = new HashMap<>();

    protected static String deBruteForce(String str1, String str2) {
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


        for (int s = 0; s < Solution.ALPHABET.length; s++) {
            char[] symbol = new char[encryptText.length()];
            for (int i = 0; i < symbol.length; i++) {
                symbol[i] = encryptText.charAt(i);
                for (int j = 0; j < Solution.ALPHABET.length; j++) {
                    if (Solution.ALPHABET[j] == encryptText.charAt(i)) {
                        symbol[i] = Solution.ALPHABET[(j + Solution.ALPHABET.length - key) % Solution.ALPHABET.length];
                        break;
                    }
                }
            }
            int count = 0;
            for (int i = 0; i < symbol.length; i++) {
                if (symbol[i] == ' ') {
                    count++;
                }
                nums.put(key, count);
            }
            key++;
        }
        Integer key1 = 0;
        Integer value1 = 0;
        for (Map.Entry<Integer, Integer> pair : nums.entrySet()) {
            Integer key2 = pair.getKey();
            Integer value2 = pair.getValue();
            if (value2 > value1) {
                key1 = key2;
                value1 = value2;
            }
        }
        char[] symbol2 = new char[encryptText.length()];
        for (int i = 0; i < symbol2.length; i++) {
            symbol2[i] = encryptText.charAt(i);
            for (int j = 0; j < Solution.ALPHABET.length; j++) {
                if (Solution.ALPHABET[j] == encryptText.charAt(i)) {
                    symbol2[i] = Solution.ALPHABET[(j + Solution.ALPHABET.length - key1) % Solution.ALPHABET.length];
                    break;
                }
            }
        }
        String originalText = String.valueOf(symbol2);

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
