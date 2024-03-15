package a_Shablony;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Эффективная работа с кодом в IntelliJ IDEA, или Знаете ли вы свою IDE как знаем её мы? Чашников Николай
// источник: https://youtu.be/tpv5n2jWHlw
public class IzVideoOtJetBrains {
    // psvm => tab
    public static void main(String[] args) throws IOException {
        // Bure => tab, r => tab
        BufferedReader reader = new BufferedReader(new FileReader("src\\a_Shablony\\input.txt"));
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
        for (String s : array) {
            System.out.println(s);
        }
    }
}
