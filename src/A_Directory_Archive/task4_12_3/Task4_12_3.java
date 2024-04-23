package task4_12_3;

public class Task4_12_3 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                Модуль 4. Наследование. Задание №12:\s
                    3. Что будет, если ее убрать?\s

                Решение:\s""");

        System.out.println("""
                Если аннотацию @Override убрать перед методом, который, как предполагается, должен
                переопределять метод из суперкласса, то программа будет по-прежнему компилироваться
                и работать, так как отсутствие аннотации @Override не влияет на выполнение программы.
                
                Однако, без аннотации @Override компилятор Java не будет предупреждать о том, что метод
                не переопределяет метод из суперкласса, что может привести к ошибкам в выполнении
                приложения.
                
                Поэтому использование аннотации @Override важно для того, чтобы избежать ошибок при
                переопределении методов и обнаружить их на этапе компиляции. Рекомендуется всегда
                использовать эту аннотацию при переопределении методов в Java.\s""");
    }
}
