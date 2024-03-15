package a_Shablony;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Эффективная работа с кодом в IntelliJ IDEA, или Знаете ли вы свою IDE как знаем её мы? Чашников Николай
// источник: https://youtu.be/tpv5n2jWHlw
public class IzVideoOtJetBrains_Refactoring {
    // psvm => tab
    public static void main(String[] args) throws IOException {
        // Bure => tab, r => tab
        // нажимаем Introduce Variable
        Reader in = new FileReader("src\\a_Shablony\\input.txt");
//        BufferedReader reader = new BufferedReader(new FileReader("src\\a_Shablony\\input.txt"));
        String[] array = loadAndSort(in);
        for (String s : array) {
            System.out.println(s);
        }
    }

    static String[] loadAndSort(Reader in) throws IOException {
        BufferedReader reader = new BufferedReader(in);
        //  пишем код справа налево: new Arli потом Refactor => Introduce Variable
        List<String> lines = new ArrayList<String>();
        String line;
        while ((line = reader.readLine()) != null) {
            // l.a =>  tab
            lines.add(line);
        }
        //  r.c =>  tab
        reader.close();
        // чтобы отсортировать список, преобразовываем его в массив, после array = нажать Shift + Ctrl + два раза
        String[] array = lines.toArray(new String[lines.size()]);
        Arrays.sort(array);
        return array;
    }
}
