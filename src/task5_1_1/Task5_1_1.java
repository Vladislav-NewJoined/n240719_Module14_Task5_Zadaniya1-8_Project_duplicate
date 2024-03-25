package task5_1_1;


import java.util.HashMap;
import java.util.Map;

public class Task5_1_1 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №1:\s
                    1. Сделайте интерфейс Decoder. В нем два метода String encode(String source) и
                       String decode(String encoded). Реализуйте этот интерфейс: пусть класс меняет
                       русские символы на цифры и латиницу (ш на w, г на r и так далее), и наоборот.
                       Сделайте ещё одну реализацию Decoder: он будет шифровать и дешифровать азбуку
                       морзе. Еще одну реализацию: он будет менять символ на код символа (который
                       можно получить как (int)str.charAt ). Доработать ConsoleTableWriter, чтобы
                       длина левой колонки была одинаковой для всех строк и равна длине самой большой\s

                Решение:\s""");

        // Пример использования RussianLettersDecoder
        RussianLettersDecoder russianLettersDecoder = new RussianLettersDecoder();
        String originalPhrase = "Дешифрование русских символов";
        System.out.println("Исходная фраза: " + originalPhrase);

        String encodedPhrase = russianLettersDecoder.encode(originalPhrase);
        System.out.println("Зашифрованная фраза: " + encodedPhrase);

        String decodedPhrase = russianLettersDecoder.decode(encodedPhrase);
        System.out.println("Дешифрованная фраза: " + decodedPhrase);
        System.out.println();

        // Пример использования MorseCodeDecoder
        MorseCodeDecoder morseCodeDecoder = new MorseCodeDecoder();
        String morseEncodedPhrase = morseCodeDecoder.encode("SOS");
        System.out.println("Исходная фраза для конвертации в азбуку Морзе: SOS");
        System.out.println("Зашифрованная фраза в азбуке Морзе: " + morseEncodedPhrase);

        String morseDecodedPhrase = morseCodeDecoder.decode(morseEncodedPhrase);
        System.out.println("Дешифрованная фраза из азбуки Морзе: " + morseDecodedPhrase);
        System.out.println();

        // Пример использования CharCodeDecoder
        System.out.println("Исходная фраза: SOS Hello");

        // Пример использования CharCodeDecoder
        CharCodeDecoder charCodeDecoder = new CharCodeDecoder();
        String charCodeEncodedPhrase = charCodeDecoder.encode("Hello");
        System.out.println("Зашифрованная фраза: " + charCodeEncodedPhrase);

        String charCodeDecodedPhrase = charCodeDecoder.decode(charCodeEncodedPhrase);
        System.out.println("Дешифрованная фраза: " + charCodeDecodedPhrase);
    }
}

interface Decoder {
    String encode(String source);
    String decode(String encoded);
}

class RussianLettersDecoder implements Decoder {
    @Override
    public String encode(String source) {
        return source.replaceAll("ш", "w")
                .replaceAll("г", "R")
                .replaceAll("ф", "f")
                .replaceAll("Д", "D")
                .replaceAll("р", "r");
        // Добавляем другие замены по аналогии
    }

    @Override
    public String decode(String encoded) {
        return encoded.replaceAll("w", "ш")
                .replaceAll("R", "г")
                .replaceAll("f", "ф")
                .replaceAll("D", "Д")
                .replaceAll("r", "р");
        // Добавляем другие замены по аналогии
    }
}

class MorseCodeDecoder implements Decoder {
    private static final Map<String, String> morseAlphabet = new HashMap<>();
    static {
        morseAlphabet.put("S", "...");
        morseAlphabet.put("O", "---");
        morseAlphabet.put(" ", " ");
    }

    @Override
    public String encode(String source) {
        // Логика для шифрования в азбуке Морзе
        StringBuilder morseCode = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            String letter = String.valueOf(source.charAt(i)).toUpperCase();
            morseCode.append(morseAlphabet.get(letter)).append(" ");
        }
        return morseCode.toString().trim();
    }

    @Override
    public String decode(String encoded) {
        // Логика для дешифрования из азбуки Морзе
        StringBuilder decodedString = new StringBuilder();
        String[] parts = encoded.split(" ");
        for (String part : parts) {
            for (Map.Entry<String, String> entry : morseAlphabet.entrySet()) {
                if (entry.getValue().equals(part)) {
                    decodedString.append(entry.getKey());
                    break;
                }
            }
        }
        return decodedString.toString();
    }
}

class CharCodeDecoder implements Decoder {
    @Override
    public String encode(String source) {
        StringBuilder encodedString = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            char ch = source.charAt(i);
            encodedString.append((int) ch).append(" ");
        }
        return encodedString.toString().trim();
    }

    @Override
    public String decode(String encoded) {
        StringBuilder decodedString = new StringBuilder();
        String[] parts = encoded.split(" ");
        for (String part : parts) {
            int code = Integer.parseInt(part);
            char ch = (char) code;
            decodedString.append(ch);
        }
        return decodedString.toString();
    }
}



//// ПРИМЕР 10 работает без char
//import java.util.HashMap;
//import java.util.Map;
//
//public class Task5_1_1 {
//    public static void main(String[] args) {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №1:\s
//                    1. Сделайте интерфейс Decoder. В нем два метода String encode(String source) и
//                       String decode(String encoded). Реализуйте этот интерфейс: пусть класс меняет
//                       русские символы на цифры и латиницу (ш на w, г на r и так далее), и наоборот.
//                       Сделайте ещё одну реализацию Decoder: он будет шифровать и дешифровать азбуку
//                       морзе. Еще одну реализацию: он будет менять символ на код символа (который
//                       можно получить как (int)str.charAt ). Доработать ConsoleTableWriter, чтобы
//                       длина левой колонки была одинаковой для всех строк и равна длине самой большой\s
//
//                Решение:\s""");
//
//        // Пример использования RussianLettersDecoder
//        RussianLettersDecoder russianLettersDecoder = new RussianLettersDecoder();
//        String originalPhrase = "Дешифрование русских символов";
//        System.out.println("Исходная фраза: " + originalPhrase);
//
//        String encodedPhrase = russianLettersDecoder.encode(originalPhrase);
//        System.out.println("Зашифрованная фраза: " + encodedPhrase);
//
//        String decodedPhrase = russianLettersDecoder.decode(encodedPhrase);
//        System.out.println("Дешифрованная фраза: " + decodedPhrase);
//        System.out.println();
//
//        // Пример использования MorseCodeDecoder
//        MorseCodeDecoder morseCodeDecoder = new MorseCodeDecoder();
//        String morseEncodedPhrase = morseCodeDecoder.encode("SOS");
//        System.out.println("Исходная фраза для конвертации в азбуку Морзе: SOS");
//        System.out.println("Зашифрованная фраза в азбуке Морзе: " + morseEncodedPhrase);
//
//        String morseDecodedPhrase = morseCodeDecoder.decode(morseEncodedPhrase);
//        System.out.println("Дешифрованная фраза из азбуки Морзе: " + morseDecodedPhrase);
//        System.out.println();
//
//        // Пример использования CharCodeDecoder
//        CharCodeDecoder charCodeDecoder = new CharCodeDecoder();
//        String charCodeEncodedPhrase = charCodeDecoder.encode("Hello");
//        System.out.println("Закодированная строка: " + charCodeEncodedPhrase);
//
//        String charCodeDecodedPhrase = charCodeDecoder.decode("72 101 108 108 111");
//        System.out.println("Декодированная строка: " + charCodeDecodedPhrase);
//    }
//}
//
//interface Decoder {
//    String encode(String source);
//    String decode(String encoded);
//}
//
//class RussianLettersDecoder implements Decoder {
//    @Override
//    public String encode(String source) {
//        return source.replaceAll("ш", "w")
//                .replaceAll("г", "R")
//                .replaceAll("ф", "f")
//                .replaceAll("Д", "D")
//                .replaceAll("р", "r");
//        // Добавляем другие замены по аналогии
//    }
//
//    @Override
//    public String decode(String encoded) {
//        return encoded.replaceAll("w", "ш")
//                .replaceAll("R", "г")
//                .replaceAll("f", "ф")
//                .replaceAll("D", "Д")
//                .replaceAll("r", "р");
//        // Добавляем другие замены по аналогии
//    }
//}
//
//class MorseCodeDecoder implements Decoder {
//    private static final Map<String, String> morseAlphabet = new HashMap<>();
//    static {
//        morseAlphabet.put("S", "...");
//        morseAlphabet.put("O", "---");
//        morseAlphabet.put(" ", " ");
//    }
//
//    @Override
//    public String encode(String source) {
//        // Логика для шифрования в азбуке Морзе
//        StringBuilder morseCode = new StringBuilder();
//        for (int i = 0; i < source.length(); i++) {
//            String letter = String.valueOf(source.charAt(i)).toUpperCase();
//            morseCode.append(morseAlphabet.get(letter)).append(" ");
//        }
//        return morseCode.toString().trim();
//    }
//
//    @Override
//    public String decode(String encoded) {
//        // Логика для дешифрования из азбуки Морзе
//        StringBuilder decodedString = new StringBuilder();
//        String[] parts = encoded.split(" ");
//        for (String part : parts) {
//            for (Map.Entry<String, String> entry : morseAlphabet.entrySet()) {
//                if (entry.getValue().equals(part)) {
//                    decodedString.append(entry.getKey());
//                    break;
//                }
//            }
//        }
//        return decodedString.toString();
//    }
//}
//
//class CharCodeDecoder implements Decoder {
//    @Override
//    public String encode(String source) {
//        // Реализация преобразования символа в его код символа
//        return "CharCodeEncodedString"; // замените на логику преобразования символа в код символа
//    }
//
//    @Override
//    public String decode(String encoded) {
//        // Реализация преобразования кода символа в символ
//        return "CharCodeDecodedString"; // замените на логику преобразования кода символа в символ
//    }
//}
//// КОНЕЦ ПРИМЕРА 10




//// ПРИМЕР 10
//public class Task5_1_1 {
//    public static void main(String[] args) {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №1:\s
//                    1. Сделайте интерфейс Decoder. В нем два метода String encode(String source) и
//                       String decode(String encoded). Реализуйте этот интерфейс: пусть класс меняет
//                       русские символы на цифры и латиницу (ш на w, г на r и так далее), и наоборот.
//                       Сделайте ещё одну реализацию Decoder: он будет шифровать и дешифровать азбуку
//                       морзе. Еще одну реализацию: он будет менять символ на код символа (который
//                       можно получить как (int)str.charAt ). Доработать ConsoleTableWriter, чтобы
//                       длина левой колонки была одинаковой для всех строк и равна длине самой большой\s
//
//                Решение:\s""");
//
//        String originalPhrase = null;
//        // Пример использования RussianLettersDecoder
//        RussianLettersDecoder russianLettersDecoder = new RussianLettersDecoder();
//        originalPhrase = "Дешифрование русских символов";
//        System.out.println("Исходная фраза: " + originalPhrase);
//
//        String encodedPhrase = russianLettersDecoder.encode(originalPhrase);
//        System.out.println("Зашифрованная фраза: " + encodedPhrase);
//
//        String decodedPhrase = russianLettersDecoder.decode(encodedPhrase);
//        System.out.println("Дешифрованная фраза: " + decodedPhrase);
//
//        // Пример использования MorseCodeDecoder
//        MorseCodeDecoder morseCodeDecoder = new MorseCodeDecoder();
//        originalPhrase = "SOS в азбуку Морзе";
//        System.out.println("Исходная фраза: " + originalPhrase);
//        String morseEncodedPhrase = morseCodeDecoder.encode(originalPhrase);
//        System.out.println("Зашифрованная азбука Морзе: " + morseEncodedPhrase);
//
//        String morseDecodedPhrase = morseCodeDecoder.decode(morseEncodedPhrase);
//        System.out.println("Дешифрованная азбука Морзе: " + morseDecodedPhrase);
//
//        // Пример использования CharCodeDecoder
//        CharCodeDecoder charCodeDecoder = new CharCodeDecoder();
//        originalPhrase = "SOS в символы char";
//        System.out.println("Исходная фраза: " + originalPhrase);
//        String charCodeEncodedPhrase = charCodeDecoder.encode(originalPhrase);
//        System.out.println("Закодированная строка: " + charCodeEncodedPhrase);
//
//        String charCodeDecodedPhrase = charCodeDecoder.decode("72 101 108 108 111");
//        System.out.println("Декодированная строка: " + charCodeDecodedPhrase);
//    }
//}
//
//interface Decoder {
//    String encode(String source);
//    String decode(String encoded);
//}
//
//class RussianLettersDecoder implements Decoder {
//    @Override
//    public String encode(String source) {
//        return source.replaceAll("ш", "w")
//                .replaceAll("г", "r");
//        // Добавляем другие замены по аналогии
//    }
//
//    @Override
//    public String decode(String encoded) {
//        return encoded.replaceAll("w", "ш")
//                .replaceAll("r", "г");
//        // Добавляем другие замены по аналогии
//    }
//}
//
//class MorseCodeDecoder implements Decoder {
//    @Override
//    public String encode(String source) {
//        // Реализация шифрования азбуки Морзе
//        return "MorseCodeEncodedString"; // замените на логику шифрования азбуки Морзе
//    }
//
//    @Override
//    public String decode(String encoded) {
//        // Реализация дешифрования азбуки Морзе
//        return "MorseCodeDecodedString"; // замените на логику дешифрования азбуки Морзе
//    }
//}
//
//class CharCodeDecoder implements Decoder {
//    @Override
//    public String encode(String source) {
//        // Реализация преобразования символа в его код символа
//        return "CharCodeEncodedString"; // замените на логику преобразования символа в код символа
//    }
//
//    @Override
//    public String decode(String encoded) {
//        // Реализация преобразования кода символа в символ
//        return "CharCodeDecodedString"; // замените на логику преобразования кода символа в символ
//    }
//}
//// КОНЕЦ ПРИМЕРА 10




//// ПРИМЕР 9_2
//import java.util.HashMap;
//import java.util.Map;
//
//public class Task5_1_1 {
//
//    private static final Map<Character, String> morseAlphabet = new HashMap<>();
//    static {
//        morseAlphabet.put('А', ".-");
//        morseAlphabet.put('Б', "-...");
//        morseAlphabet.put('В', ".--");
//        // Добавьте остальные буквы кириллицы и латиницы с их кодами азбуки Морзе
//        morseAlphabet.put('S', "...");
//        morseAlphabet.put('O', "---");
//        morseAlphabet.put(' ', " "); // пробел
//    }
//
//    public static String convertToMorseCode(String phrase) {
//        StringBuilder morseCode = new StringBuilder();
//        phrase = phrase.toUpperCase(); // переводим фразу в верхний регистр для удобства
//
//        for (int i = 0; i < phrase.length(); i++) {
//            char currentChar = phrase.charAt(i);
//            String morseChar = morseAlphabet.get(currentChar);
//            if (morseChar != null) {
//                morseCode.append(morseChar).append(" ");
//            }
//        }
//
//        return morseCode.toString();
//    }
//
//    public static void main(String[] args) {
//        String originalPhrase = "SOS в азбуку Морзе"; // исходная фраза
//        System.out.println("Исходная фраза: " + originalPhrase);
//        String morseCodePhrase = convertToMorseCode(originalPhrase);
//        System.out.println("Фраза в азбуке Морзе: " + morseCodePhrase);
//    }
//}
//// КОНЕЦ ПРИМЕРА 9_2




//// ПРИМЕР 9
//import java.util.ArrayList;
//import java.util.List;
//
//public class Task5_1_1 {
//    public static void main(String[] args) {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №1:\s
//                    1. Сделайте интерфейс Decoder. В нем два метода String encode(String source) и
//                       String decode(String encoded). Реализуйте этот интерфейс: пусть класс меняет
//                       русские символы на цифры и латиницу (ш на w, г на r и так далее), и наоборот.
//                       Сделайте ещё одну реализацию Decoder: он будет шифровать и дешифровать азбуку
//                       морзе. Еще одну реализацию: он будет менять символ на код символа (который
//                       можно получить как (int)str.charAt ). Доработать ConsoleTableWriter, чтобы
//                       длина левой колонки была одинаковой для всех строк и равна длине самой большой\s
//
//                Решение:\s""");
//
//        // Пример использования RussianLettersDecoder
//        RussianLettersDecoder russianLettersDecoder = new RussianLettersDecoder();
//        String originalPhrase = "Дешифрование русских символов";
//        System.out.println("Исходная фраза: " + originalPhrase);
//
//        String encodedPhrase = russianLettersDecoder.encode(originalPhrase);
//        System.out.println("Зашифрованная фраза: " + encodedPhrase);
//
//        String decodedPhrase = russianLettersDecoder.decode(encodedPhrase);
//        System.out.println("Дешифрованная фраза: " + decodedPhrase);
//    }
//}
//
//interface Decoder {
//    String encode(String source);
//    String decode(String encoded);
//}
//
//class RussianLettersDecoder implements Decoder {
//    @Override
//    public String encode(String source) {
//        return source.replaceAll("ш", "w")
//                .replaceAll("г", "r");
//        // Добавляем другие замены по аналогии
//    }
//
//    @Override
//    public String decode(String encoded) {
//        return encoded.replaceAll("w", "ш")
//                .replaceAll("r", "г");
//        // Добавляем другие замены по аналогии
//    }
//}
//
//class MorseCodeDecoder implements Decoder {
//    @Override
//    public String encode(String source) {
//        // Реализация шифрования азбуки Морзе
//        return "MorseCodeEncodedString"; // замените на логику шифрования азбуки Морзе
//    }
//
//    @Override
//    public String decode(String encoded) {
//        // Реализация дешифрования азбуки Морзе
//        return "MorseCodeDecodedString"; // замените на логику дешифрования азбуки Морзе
//    }
//}
//
//class CharCodeDecoder implements Decoder {
//    @Override
//    public String encode(String source) {
//        // Реализация преобразования символа в его код символа
//        return "CharCodeEncodedString"; // замените на логику преобразования символа в код символа
//    }
//
//    @Override
//    public String decode(String encoded) {
//        // Реализация преобразования кода символа в символ
//        return "CharCodeDecodedString"; // замените на логику преобразования кода символа в символ
//    }
//}
//// КОНЕЦ ПРИМЕРА 9




//// ПРИМЕР 8
//public class Task5_1_1 {
//    public static void main(String[] args) {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №1:\s
//                    1. Сделайте интерфейс Decoder. В нем два метода String encode(String source) и
//                       String decode(String encoded). Реализуйте этот интерфейс: пусть класс меняет
//                       русские символы на цифры и латиницу (ш на w, г на r и так далее), и наоборот.
//                       Сделайте ещё одну реализацию Decoder: он будет шифровать и дешифровать азбуку
//                       морзе. Еще одну реализацию: он будет менять символ на код символа (который
//                       можно получить как (int)str.charAt ). Доработать ConsoleTableWriter, чтобы
//                       длина левой колонки была одинаковой для всех строк и равна длине самой большой\s
//
//                Решение:\s""");
//
//        // Пример использования RussianLettersDecoder
//        RussianLettersDecoder russianLettersDecoder = new RussianLettersDecoder();
//        String originalPhrase = "Дешифрование русских символов";
//        System.out.println("Исходная фраза: " + originalPhrase);
//
//        String encodedPhrase = russianLettersDecoder.encode(originalPhrase);
//        System.out.println("Зашифрованная фраза: " + encodedPhrase);
//
//        String decodedPhrase = russianLettersDecoder.decode(encodedPhrase);
//        System.out.println("Дешифрованная фраза: " + decodedPhrase);
//    }
//}
//
//interface Decoder {
//    String encode(String source);
//    String decode(String encoded);
//}
//
//class RussianLettersDecoder implements Decoder {
//    @Override
//    public String encode(String source) {
//        return source.replaceAll("ш", "w")
//                .replaceAll("г", "r");
//        // Добавляем другие замены по аналогии
//    }
//
//    @Override
//    public String decode(String encoded) {
//        return encoded.replaceAll("w", "ш")
//                .replaceAll("r", "г");
//        // Добавляем другие замены по аналогии
//    }
//}
//
//class MorseCodeDecoder implements Decoder {
//    @Override
//    public String encode(String source) {
//        // Реализация шифрования азбуки Морзе
//        return "MorseCodeEncodedString"; // замените на логику шифрования азбуки Морзе
//    }
//
//    @Override
//    public String decode(String encoded) {
//        // Реализация дешифрования азбуки Морзе
//        return "MorseCodeDecodedString"; // замените на логику дешифрования азбуки Морзе
//    }
//}
//
//class CharCodeDecoder implements Decoder {
//    @Override
//    public String encode(String source) {
//        // Реализация преобразования символа в его код символа
//        return "CharCodeEncodedString"; // замените на логику преобразования символа в код символа
//    }
//
//    @Override
//    public String decode(String encoded) {
//        // Реализация преобразования кода символа в символ
//        return "CharCodeDecodedString"; // замените на логику преобразования кода символа в символ
//    }
//}
//// КОНЕЦ ПРИМЕРА 8




//// ПРИМЕР 7_2
//public class Task5_1_1 {
//    public static void main(String[] args) {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №1:\s
//                    1. Сделайте интерфейс Decoder. В нем два метода String encode(String source) и
//                       String decode(String encoded). Реализуйте этот интерфейс: пусть класс меняет
//                       русские символы на цифры и латиницу (ш на w, г на r и так далее), и наоборот.
//                       Сделайте ещё одну реализацию Decoder: он будет шифровать и дешифровать азбуку
//                       морзе. Еще одну реализацию: он будет менять символ на код символа (который
//                       можно получить как (int)str.charAt ). Доработать ConsoleTableWriter, чтобы
//                       длина левой колонки была одинаковой для всех строк и равна длине самой большой\s
//
//                Решение:\s""");
//
//        // Пример использования RussianLettersDecoder
//        RussianLettersDecoder russianLettersDecoder = new RussianLettersDecoder();
//        String originalPhrase = "Дешифрование русских символов";
//        System.out.println("Исходная фраза: " + originalPhrase);
//
//        String encodedPhrase = russianLettersDecoder.encode(originalPhrase);
//        System.out.println("Зашифрованная фраза: " + encodedPhrase);
//
//        String decodedPhrase = russianLettersDecoder.decode(encodedPhrase);
//        System.out.println("Дешифрованная фраза: " + decodedPhrase);
//
//        // Пример использования MorseCodeDecoder
//        MorseCodeDecoder morseCodeDecoder = new MorseCodeDecoder();
//        String morseEncodedPhrase = morseCodeDecoder.encode("SOS");
//        System.out.println("Зашифрованная азбука Морзе: " + morseEncodedPhrase);
//
//        String morseDecodedPhrase = morseCodeDecoder.decode(morseEncodedPhrase);
//        System.out.println("Дешифрованная азбука Морзе: " + morseDecodedPhrase);
//
//        // Пример использования CharCodeDecoder
//        CharCodeDecoder charCodeDecoder = new CharCodeDecoder();
//        String charCodeEncodedPhrase = charCodeDecoder.encode("Hello");
//        System.out.println("Закодированная строка: " + charCodeEncodedPhrase);
//
//        String charCodeDecodedPhrase = charCodeDecoder.decode("72 101 108 108 111");
//        System.out.println("Декодированная строка: " + charCodeDecodedPhrase);
//    }
//}
//// КОНЕЦ ПРИМЕРА 7_2




//// ПРИМЕР 7
//import java.util.ArrayList;
//import java.util.List;
//
//public class Task5_1_1 {
//    public static void main(String[] args) {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №1:\s
//                    1. Сделайте интерфейс Decoder. В нем два метода String encode(String source) и
//                       String decode(String encoded). Реализуйте этот интерфейс: пусть класс меняет
//                       русские символы на цифры и латиницу (ш на w, г на r и так далее), и наоборот.
//                       Сделайте ещё одну реализацию Decoder: он будет шифровать и дешифровать азбуку
//                       морзе Еще одну реализацию: он будет менять символ на код символа ( который
//                       можно получить как (int)str.charAt ). Доработать ConsoleTableWriter, чтобы
//                       длина левой колонки была одинаковой для всех строк и равна длине самой большой\s
//
//                Решение:\s""");
//
//        // Пример использования RussianLettersDecoder
//        RussianLettersDecoder russianLettersDecoder = new RussianLettersDecoder();
//        String originalPhrase = "Дешифрование русских символов";
//        System.out.println("Исходная фраза: " + originalPhrase);
//
//        String encodedPhrase = russianLettersDecoder.encode(originalPhrase);
//        System.out.println("Зашифрованная фраза: " + encodedPhrase);
//
//        String decodedPhrase = russianLettersDecoder.decode(encodedPhrase);
//        System.out.println("Дешифрованная фраза: " + decodedPhrase);
//    }
//}
//
//interface Decoder {
//    String encode(String source);
//    String decode(String encoded);
//}
//
//class RussianLettersDecoder implements Decoder {
//    @Override
//    public String encode(String source) {
//        return source.replaceAll("ш", "w")
//                .replaceAll("г", "r");
//        // Добавляем другие замены по аналогии
//    }
//
//    @Override
//    public String decode(String encoded) {
//        return encoded.replaceAll("w", "ш")
//                .replaceAll("r", "г");
//        // Добавляем другие замены по аналогии
//    }
//}
//// КОНЕЦ ПРИМЕРА 7




//// ПРИМЕР 6
//import java.util.ArrayList;
//import java.util.List;
//
//public class Task5_1_1 {
//    public static void main(String[] args) {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №1:\s
//                    1. Сделайте интерфейс Decoder. В нем два метода String encode(String source) и
//                       String decode(String encoded). Реализуйте этот интерфейс: пусть класс меняет
//                       русские символы на цифры и латиницу (ш на w, г на r и так далее), и наоборот.
//                       Сделайте ещё одну реализацию Decoder: он будет шифровать и дешифровать азбуку
//                       морзе. Еще одну реализацию: он будет менять символ на код символа (который
//                       можно получить как (int)str.charAt). Доработать ConsoleTableWriter, чтобы
//                       длина левой колонки была одинаковой для всех строк и равна длине самой большой\s
//                Решение:\s""");
//
//        List<String> data = new ArrayList<>();
//        data.add("Russian Letters: Дешифрование русских символов");
//        data.add("Morse Code: ... --- ...");
//        data.add("Char Codes: 72 101 108 108 111");
//
//        Decoder russianLettersDecoder = new RussianLettersDecoder();
//        Decoder morseCodeDecoder = new MorseCodeDecoder();
//        Decoder charCodeDecoder = new CharCodeDecoder();
//
//        for (String row : data) {
//            System.out.println("Original Data: " + row);
//            System.out.println("Russian Letters Decoder: " + russianLettersDecoder.decode(row));
//            System.out.println("Morse Code Decoder: " + morseCodeDecoder.decode(row));
//            System.out.println("Char Code Decoder: " + charCodeDecoder.decode(row));
//            System.out.println();
//        }
//    }
//}
//
//interface Decoder {
//    String encode(String source);
//    String decode(String encoded);
//}
//
//class RussianLettersDecoder implements Decoder {
//    @Override
//    public String encode(String source) {
//        return source.replaceAll("ш", "w")
//                .replaceAll("г", "r");
//        // Добавляем другие замены по аналогии
//    }
//
//    @Override
//    public String decode(String encoded) {
//        return encoded.replaceAll("w", "ш")
//                .replaceAll("r", "г");
//        // Добавляем другие замены по аналогии
//    }
//}
//
//class MorseCodeDecoder implements Decoder {
//    @Override
//    public String encode(String source) {
//        return source
//                .replaceAll("A", ".-")
//                .replaceAll("B", "-...");
//        // Добавляем другие замены по аналогии
//    }
//
//    @Override
//    public String decode(String encoded) {
//        return encoded
//                .replaceAll(".-", "A")
//                .replaceAll("-...", "B");
//        // Добавляем другие замены по аналогии
//    }
//}
//
//class CharCodeDecoder implements Decoder {
//    @Override
//    public String encode(String source) {
//        StringBuilder encodedString = new StringBuilder();
//        for (char c : source.toCharArray()) {
//            encodedString.append((int) c).append(" ");
//        }
//        return encodedString.toString().trim();
//    }
//
//    @Override
//    public String decode(String encoded) {
//        StringBuilder decodedString = new StringBuilder();
//        String[] parts = encoded.split(" ");
//        for (String part : parts) {
//            int code = Integer.parseInt(part);
//            decodedString.append((char) code);
//        }
//        return decodedString.toString();
//    }
//}
//// КОНЕЦ ПРИМЕРА 6




//// ПРИМЕР 5
//import java.util.ArrayList;
//import java.util.List;
//
//public class Task5_1_1 {
//    public static void main(String[] args) {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №1:\s
//                    1. Сделайте интерфейс Decoder. В нем два метода String encode(String source) и
//                       String decode(String encoded). Реализуйте этот интерфейс: пусть класс меняет
//                       русские символы на цифры и латиницу (ш на w, г на r и так далее), и наоборот.
//                       Сделайте ещё одну реализацию Decoder: он будет шифровать и дешифровать азбуку
//                       морзе Еще одну реализацию: он будет менять символ на код символа ( который
//                       можно получить как (int)str.charAt ). Доработать ConsoleTableWriter, чтобы
//                       длина левой колонки была одинаковой для всех строк и равна длине самой большой\s
//
//                Решение:\s""");
//
//        // Пример использования RussianLettersDecoder
//        RussianLettersDecoder russianLettersDecoder = new RussianLettersDecoder();
//        String originalPhrase = "Дешифрование русских символов";
//        System.out.println("Исходная фраза: " + originalPhrase);
//
//        String encodedPhrase = russianLettersDecoder.encode(originalPhrase);
//        System.out.println("Зашифрованная фраза: " + encodedPhrase);
//
//        String decodedPhrase = russianLettersDecoder.decode(encodedPhrase);
//        System.out.println("Дешифрованная фраза: " + decodedPhrase);
//    }
//}
//
//interface Decoder {
//    String encode(String source);
//    String decode(String encoded);
//}
//
//class RussianLettersDecoder implements Decoder {
//    @Override
//    public String encode(String source) {
//        return source.replaceAll("ш", "w")
//                .replaceAll("г", "r");
//        // Добавляем другие замены по аналогии
//    }
//
//    @Override
//    public String decode(String encoded) {
//        return encoded.replaceAll("w", "ш")
//                .replaceAll("r", "г");
//        // Добавляем другие замены по аналогии
//    }
//}
//// КОНЕЦ ПРИМЕРА 5




//// ПРИМЕР 4
//import java.util.ArrayList;
//import java.util.List;
//
//public class Task5_1_1 {
//    public static void main(String[] args) {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №1:\s
//                    1. Сделайте интерфейс Decoder. В нем два метода String encode(String source) и
//                       String decode(String encoded). Реализуйте этот интерфейс: пусть класс меняет
//                       русские символы на цифры и латиницу (ш на w, г на r и так далее), и наоборот.
//                       Сделайте ещё одну реализацию Decoder: он будет шифровать и дешифровать азбуку
//                       морзе Еще одну реализацию: он будет менять символ на код символа ( который
//                       можно получить как (int)str.charAt ). Доработать ConsoleTableWriter, чтобы
//                       длина левой колонки была одинаковой для всех строк и равна длине самой большой\s
//
//                Решение:\s""");
//
//        List<String> data = new ArrayList<>();
//        data.add("Russian Letters: Шифрование и дешифрование русских букв");
//        data.add("Morse Code: .- --.. -... ..- -.-. --- ... -.. . .-. ...- .. -. -.-. .");
//        data.add("Char Codes: 72 101 108 108 111");
//
//        writeTable(data);
//    }
//
//    public static void writeTable(List<String> data) {
//        int maxLeftColumnLength = 0;
//        for (String row : data) {
//            String[] columns = row.split(":");
//            String leftColumn = columns[0];
//            if (leftColumn.length() > maxLeftColumnLength) {
//                maxLeftColumnLength = leftColumn.length();
//            }
//        }
//
//        for (String row : data) {
//            String[] columns = row.split(":");
//            String leftColumn = columns[0];
//            String rightColumn = columns[1];
//            int spacesToAdd = maxLeftColumnLength - leftColumn.length();
//            StringBuilder sb = new StringBuilder(leftColumn);
//            for (int i = 0; i < spacesToAdd; i++) {
//                sb.append(" ");
//            }
//            sb.append(": ").append(rightColumn);
//            System.out.println(sb.toString());
//        }
//    }
//}
//
//interface Decoder {
//    String encode(String source);
//    String decode(String encoded);
//}
//
//class RussianLettersDecoder implements Decoder {
//    @Override
//    public String encode(String source) {
//        return source.replaceAll("ш", "w")
//                .replaceAll("г", "r");
//        // Добавляем другие замены по аналогии
//    }
//
//    @Override
//    public String decode(String encoded) {
//        return encoded.replaceAll("w", "ш")
//                .replaceAll("r", "г");
//        // Добавляем другие замены по аналогии
//    }
//}
//// КОНЕЦ ПРИМЕРА 4




//// ПРИМЕР 3
//import java.util.ArrayList;
//import java.util.List;
//
//public class Task5_1_1 {
//    public static void main(String[] args) {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №1:\s
//                    1. Сделайте интерфейс Decoder. В нем два метода String encode(String source) и
//                       String decode(String encoded). Реализуйте этот интерфейс: пусть класс меняет
//                       русские символы на цифры и латиницу (ш на w, г на r и так далее), и наоборот.
//                       Сделайте ещё одну реализацию Decoder: он будет шифровать и дешифровать азбуку
//                       морзе Еще одну реализацию: он будет менять символ на код символа ( который
//                       можно получить как (int)str.charAt ). Доработать ConsoleTableWriter, чтобы
//                       длина левой колонки была одинаковой для всех строк и равна длине самой большой\s
//
//                Решение:\s""");
//
//        List<String> data = new ArrayList<>();
//        data.add("Russian Letters: Шифрование и дешифрование русских букв");
//        data.add("Morse Code: .- --.. -... ..- -.-. --- ... -.. . .-. ...- .. -. -.-. .");
//        data.add("Char Codes: 72 101 108 108 111");
//
//        writeTable(data);
//    }
//
//    public static void writeTable(List<String> data) {
//        int maxLeftColumnLength = data.stream()
//                .map(row -> row.split(":")[0].length())
//                .max(Integer::compare)
//                .orElse(0);
//
//        for (String row : data) {
//            String[] columns = row.split(":");
//            String leftColumn = columns[0];
//            String rightColumn = columns[1];
//            int spacesToAdd = maxLeftColumnLength - leftColumn.length();
//            StringBuilder sb = new StringBuilder(leftColumn);
//            for (int i = 0; i < spacesToAdd; i++) {
//                sb.append(" ");
//            }
//            System.out.println(sb.toString() + ": " + rightColumn);
//        }
//    }}
//
//interface Decoder {
//    String encode(String source);
//    String decode(String encoded);
//}
//
//class RussianLettersDecoder implements Decoder {
//
//    @Override
//    public String encode(String source) {
//        return source.replaceAll("ш", "w")
//                .replaceAll("г", "r");
//        // Добавляем другие замены по аналогии
//    }
//
//    @Override
//    public String decode(String encoded) {
//        return encoded.replaceAll("w", "ш")
//                .replaceAll("r", "г");
//        // Добавляем другие замены по аналогии
//    }
//}
//
//class MorseCodeDecoder implements Decoder {
//    @Override
//    public String encode(String source) {
//        // реализация кодирования азбуки Морзе
//        return source;
//    }
//
//    @Override
//    public String decode(String encoded) {
//        // реализация декодирования азбуки Морзе
//        return encoded;
//    }
//}
//
//class CharCodeDecoder implements Decoder {
//    @Override
//    public String encode(String source) {
//        // реализация кодирования символов в их ASCII код
//        return source;
//    }
//
//    @Override
//    public String decode(String encoded) {
//        // реализация декодирования ASCII кода в символ
//        return encoded;
//    }
//}
//// КОНЕЦ ПРИМЕРА 3




//// ПРИМЕР 2
//import java.util.ArrayList;
//import java.util.List;
//
//public class Task5_1_1 {
//    public static void main(String[] args) {
//        System.out.println("""
//                Задание:\s
//                Модуль 5. Интерфейсы, абстрактные классы, статические методы. Задание №1:\s
//                    1. Сделайте интерфейс Decoder. В нем два метода String encode(String source) и
//                       String decode(String encoded). Реализуйте этот интерфейс: пусть класс меняет
//                       русские символы на цифры и латиницу (ш на w, г на r и так далее), и наоборот.
//                       Сделайте ещё одну реализацию Decoder: он будет шифровать и дешифровать азбуку
//                       морзе Еще одну реализацию: он будет менять символ на код символа ( который
//                       можно получить как (int)str.charAt ). Доработать ConsoleTableWriter, чтобы
//                       длина левой колонки была одинаковой для всех строк и равна длине самой большой\s
//
//                Решение:\s""");
//
//        List<String> data = new ArrayList<>();
//        data.add("Russian Letters: Шифрование и дешифрование русских букв");
//        data.add("Morse Code: .- --.. -... ..- -.-. --- ... -.. . .-. ...- .. -. -.-. .");
//        data.add("Char Codes: 72 101 108 108 111");
//
//        writeTable(data);
//    }
//
//    public static void writeTable(List<String> data) {
//        int maxLeftColumnLength = 0;
//        for (String row : data) {
//            String leftColumn = row.split(":")[0];
//            if (leftColumn.length() > maxLeftColumnLength) {
//                maxLeftColumnLength = leftColumn.length();
//            }
//        }
//
//        for (String row : data) {
//            String[] columns = row.split(":");
//            String leftColumn = columns[0];
//            String rightColumn = columns[1];
//            int spacesToAdd = maxLeftColumnLength - leftColumn.length();
//            StringBuilder sb = new StringBuilder(leftColumn);
//            for (int i = 0; i < spacesToAdd; i++) {
//                sb.append(" ");
//            }
//            System.out.println(sb.toString() + ": " + rightColumn);
//        }
//    }
//}
//
//interface Decoder {
//    String encode(String source);
//    String decode(String encoded);
//}
//
//class RussianLettersDecoder implements Decoder {
//    @Override
//    public String encode(String source) {
//        // реализация кодирования русских букв
//        return source;
//    }
//
//    @Override
//    public String decode(String encoded) {
//        // реализация декодирования русских букв
//        return encoded;
//    }
//}
//
//class MorseCodeDecoder implements Decoder {
//    @Override
//    public String encode(String source) {
//        // реализация кодирования азбуки Морзе
//        return source;
//    }
//
//    @Override
//    public String decode(String encoded) {
//        // реализация декодирования азбуки Морзе
//        return encoded;
//    }
//}
//
//class CharCodeDecoder implements Decoder {
//    @Override
//    public String encode(String source) {
//        // реализация кодирования символов в их ASCII код
//        return source;
//    }
//
//    @Override
//    public String decode(String encoded) {
//        // реализация декодирования ASCII кода в символ
//        return encoded;
//    }
//}
//// КОНЕЦ ПРИМЕРА 2