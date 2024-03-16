package task4_6_1;

import java.util.InputMismatchException;
import java.util.Scanner;

//public class Task4_6_1 {
//    public static void main(String[] args) throws IOException {
//        System.out.println("""
//                Задание:\s
//                Модуль 4. Наследование. Задание №6:\s
//                    1. Доработайте крестики-нолики; создайте исключение, которое будете бросать при неверном
//                       вводе пользователя\s
//
//                Решение:\s""");
//
//                X0Game game = new X0Game();
//                game.play();
//            }
//        }
//
//class FieldXO {
//    char[][] field;
//    int size = 3;
//    int countToWin = 3;
//
//    void initField() {
//        this.field = new char[size][size];
//        for (int row = 0; row < size; row++) {
//            for (int col = 0; col < size; col++) {
//                field[row][col] = ' ';
//            }
//        }
//        System.out.println("Field initialized");
//        this.printField();
//    }
//
//    void printField() {
//        System.out.print("   ");
//        for (int i = 1; i <= size; i++) {
//            System.out.print(i + "  ");
//        }
//        System.out.println();
//
//        for (int row = 0; row < size; row++) {
//            int rowNumber = row + 1;
//            System.out.print(rowNumber + " ");
//            for (int col = 0; col < size; col++) {
//                System.out.print("[" + this.field[row][col] + "]");
//            }
//            System.out.println();
//        }
//    }
//
//    boolean isPlaceFree(int rowIndex, int colIndex) {
//        if (rowIndex < 0 || rowIndex >= size || colIndex < 0 || colIndex >= size) {
//            return false;
//        } else if (this.field[rowIndex][colIndex] == ' ') {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    void setValue(int rowIndex, int colIndex, char value) {
//        this.field[rowIndex][colIndex] = value;
//    }
//
//    boolean isGameOver(char player) {
//        for (int row = 0; row < this.size; row++) {
//            for (int col = 0; col < this.size; col++) {
//                if (checkRightDirection(row, col, player)) {
//                    return true;
//                } else if (checkDownDirection(row, col, player)) {
//                    return true;
//                } else if (checkRightDiagonal(row, col, player)) {
//                    return true;
//                } else if (checkLeftDiagonal(row, col, player)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    boolean checkRightDirection(int row, int col, char player) {
//        if (col > this.size - this.countToWin) {
//            return false;
//        }
//        for (int i = col; i < col + this.countToWin; i++) {
//            if (this.field[row][i] != player) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    boolean checkDownDirection(int row, int col, char player) {
//        if (row > this.size - this.countToWin) {
//            return false;
//        }
//        for (int i = row; i < row + this.countToWin; i++) {
//            if (this.field[i][col] != player) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    boolean checkRightDiagonal(int row, int col, char player) {
//        if (row > this.size - this.countToWin) {
//            return false;
//        }
//        if (col > this.size - this.countToWin) {
//            return false;
//        }
//
//        for (int sdvig = 0; sdvig < this.countToWin; sdvig++) {
//            int rowToCheck = row + sdvig;
//            int colToCheck = col + sdvig;
//            if (this.field[rowToCheck][colToCheck] != player) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    boolean checkLeftDiagonal(int row, int col, char player) {
//        if (row > this.size - this.countToWin) {
//            return false;
//        }
//        if (col < this.countToWin - 1) {
//            return false;
//        }
//
//        for (int sdvig = 0; sdvig < this.countToWin; sdvig++) {
//            int rowToCheck = row + sdvig;
//            int colToCheck = col - sdvig;
//            if (this.field[rowToCheck][colToCheck] != player) {
//                return false;
//            }
//
//        }
//        return true;
//    }
//}
//
//class X0Game {
//    FieldXO gameField;
//    Scanner scanner = new Scanner(System.in);
//    char whoMakeNextTurn;
//    boolean gameOver = false;
//
//    void setupNewGame() {
//        System.out.println("Will play new X0 game");
//        this.gameField = new FieldXO();
//        this.gameField.initField();
//    }
//
//    void play() {
//        this.setupNewGame();
//        System.out.println("Who will make first turn (Enter X or 0 (zero) in Latin alphabet): ");
//        char first = this.scanner.next().charAt(0);
//        if (first == 'X' || first == '0') {
//            this.whoMakeNextTurn = first;
//        } else {
//            System.out.println("I recognizing only X or 0 (zero). So first will be X");
//            this.whoMakeNextTurn = 'X';
//        }
//
//        while (!gameOver) {
//            turn();
//            this.gameOver = this.gameField.isGameOver(whoMakeNextTurn);
//            if (this.gameOver) {
//                System.out.println(this.whoMakeNextTurn + " win!");
//            }
//            if (this.whoMakeNextTurn == 'X') {
//                this.whoMakeNextTurn = '0';
//            } else {
//                this.whoMakeNextTurn = 'X';
//            }
//        }
//        System.out.println("Game over.");
//    }
//
//    void turn() {
//        System.out.println(this.whoMakeNextTurn + ", your turn.");
//        System.out.print("Chose row: ");
//        int rowNumber = this.scanner.nextInt();
//        System.out.print("Chose column: ");
//        int colNumber = this.scanner.nextInt();
//        int rowIndex = rowNumber - 1;
//        int colIndex = colNumber - 1;
//        if (this.gameField.isPlaceFree(rowIndex, colIndex)) {
//            this.gameField.setValue(rowIndex, colIndex, whoMakeNextTurn);
//            this.gameField.printField();
//        } else {
//            System.out.println("Wrong number (maybe this place is not free?). Make turn again.");
//            turn();
//        }
//    }
//}




//// ПРИМЕР 6
public class Task4_6_1 {
    public static void main(String[] args) {
        char[][] board = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
        char currentPlayer = 'X';
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Отрисовка игрового поля
            System.out.println("  1 2 3");
            for (int i = 0; i < 3; i++) {
                System.out.print((i + 1) + " ");
                for (int j = 0; j < 3; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }

            // Проверка на победу
            if (checkWin(board, currentPlayer)) {
                System.out.println("Игрок " + currentPlayer + " победил!");
                break;
            }

            // Ввод координат от игрока с обработкой исключения
            try {
                int row, col;
                System.out.print("Игрок " + currentPlayer + ", введите номер строки: ");
                row = scanner.nextInt() - 1;
                System.out.print("Введите номер столбца: ");
                col = scanner.nextInt() - 1;

                // Проверка введенных координат
                if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ') {
                    throw new InputMismatchException("Некорректно введены координаты. Попробуйте снова.");
                }

                // Установка символа на игровое поле
                board[row][col] = currentPlayer;

                // Смена игрока
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                scanner.nextLine(); // Очистка буфера ввода
            }
        }
    }

    // Проверка на победу
    public static boolean checkWin(char[][] board, char player) {
        // Горизонталь и вертикаль
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }

        // Диагональ
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[2][0] == player && board[1][1] == player && board[0][2] == player)) {
            return true;
        }

        return false;
    }
}
//// КОНЕЦ ПРИМЕРА 6




//// ПРИМЕР 5
//public class Task4_6_1 {
//    public static void main(String[] args) {
//        char[][] board = {
//                {' ', ' ', ' '},
//                {' ', ' ', ' '},
//                {' ', ' ', ' '}
//        };
//        char currentPlayer = 'X';
//        Scanner scanner = new Scanner(System.in);
//
//        while (true) {
//            // Отрисовка игрового поля
//            System.out.println("  1 2 3");
//            for (int i = 0; i < 3; i++) {
//                System.out.print((i+1) + " ");
//                for (int j = 0; j < 3; j++) {
//                    System.out.print(board[i][j] + " ");
//                }
//                System.out.println();
//            }
//
//            // Проверка на победу
//            if (checkWin(board, currentPlayer)) {
//                System.out.println("Игрок " + currentPlayer + " победил!");
//                break;
//            }
//
//            // Ввод координат от игрока
//            System.out.print("Игрок " + currentPlayer + ", введите номер строки: ");
//            int row = scanner.nextInt() - 1;
//            System.out.print("Введите номер столбца: ");
//            int col = scanner.nextInt() - 1;
//
//            // Проверка введенных координат
//            if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ') {
//                System.out.println("Недопустимые координаты. Попробуйте снова.");
//                continue;
//            }
//
//            // Установка символа на игровое поле
//            board[row][col] = currentPlayer;
//
//            // Смена игрока
//            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
//        }
//    }
//
//    // Проверка на победу
//    public static boolean checkWin(char[][] board, char player) {
//        // Горизонталь и вертикаль
//        for (int i = 0; i < 3; i++) {
//            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
//                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
//                return true;
//            }
//        }
//
//        // Диагональ
//        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
//                (board[2][0] == player && board[1][1] == player && board[0][2] == player)) {
//            return true;
//        }
//
//        return false;
//    }
//}
//// КОНЕЦ ПРИМЕРА 5




//// ПРИМЕР 4
//public class Task4_6_1 {
//    public static void main(String args) {
//        X0Game game = new X0Game();
//        game.play();
//    }
//}
//
//class X0Game {
//    FieldXO gameField;
//    Scanner scanner = new Scanner(System.in);
//    char whoMakeNextTurn;
//    boolean gameOver = false;
//
//    void setupNewGame() {
//        System.out.println("Will play new X0 game");
//        this.gameField = new FieldXO();
//        this.gameField.initField();
//    }
//
//    void play() {
//        this.setupNewGame();
//        System.out.println("Who will make first turn (Enter X or 0 (zero) in Latin alphabet): ");
//        char first = this.scanner.next().charAt(0);
//        if (first == 'X' || first == '0') {
//            this.whoMakeNextTurn = first;
//        } else {
//            System.out.println("I recognizing only X or 0 (zero). So first will be X");
//            this.whoMakeNextTurn = 'X';
//        }
//
//        while (!gameOver) {
//            turn();
//            this.gameOver = this.gameField.isGameOver(whoMakeNextTurn);
//            if (this.gameOver) {
//                System.out.println(this.whoMakeNextTurn + " win!");
//            }
//            if (this.whoMakeNextTurn == 'X') {
//                this.whoMakeNextTurn = '0';
//            } else {
//                this.whoMakeNextTurn = 'X';
//            }
//        }
//        System.out.println("Game over.");
//    }
//
//    void turn() {
//        System.out.println(this.whoMakeNextTurn + ", your turn.");
//        System.out.print("Chose row: ");
//        int rowNumber;
//        int colNumber;
//        try {
//            rowNumber = Integer.parseInt(scanner.next());
//            System.out.print("Chose column: ");
//            colNumber = Integer.parseInt(scanner.next());
//        } catch (NumberFormatException e) {
//            System.out.println("Ошибка ввода: введите числа от 1 до 3.");
//            turn();
//            return;
//        }
//
//        int rowIndex = rowNumber - 1;
//        int colIndex = colNumber - 1;
//        if (this.gameField.isPlaceFree(rowIndex, colIndex)) {
//            this.gameField.setValue(rowIndex, colIndex, whoMakeNextTurn);
//            this.gameField.printField();
//        } else {
//            System.out.println("Wrong number (maybe this place is not free?). Make turn again.");
//            turn();
//        }
//    }
//}
//
//class FieldXO {
//    char field;
//    int size = 3;
//    int countToWin = 3;
//
//    void initField() {
//        this.field = new charsizesize;
//        for (int row = 0; row < size; row++) {
//            for (int col = 0; col < size; col++) {
//                fieldrowcol = ' ';
//            }
//        }
//        System.out.println("Field initialized");
//        this.printField();
//    }
//
//    void printField() {
//        System.out.print("   ");
//        for (int i = 1; i <= size; i++) {
//            System.out.print(i + "  ");
//        }
//        System.out.println();
//
//        for (int row = 0; row < size; row++) {
//            int rowNumber = row + 1;
//            System.out.print(rowNumber + " ");
//            for (int col = 0; col < size; col++) {
//                System.out.print("" + this.field[rowcol + "]");
//            }
//            System.out.println();
//        }
//    }
//
//    boolean isPlaceFree(int rowIndex, int colIndex) {
//        if (rowIndex < 0 || rowIndex >= size || colIndex < 0 || colIndex >= size) {
//            return false;
//        } else if (this.fieldrowIndexcolIndex == ' ') {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    void setValue(int rowIndex, int colIndex, char value) {
//        this.fieldrowIndexcolIndex = value;
//    }
//
//    boolean isGameOver(char player) {
//        // Check for win conditions
//        return false;
//    }
//}
//// КОНЕЦ ПРИМЕРА 4




//// ПРИМЕР 3
//public class Task4_6_1 {
//    public static void main(String[] args) throws IOException {
//        System.out.println("""
//                Задание:\s
//                Модуль 4. Наследование. Задание №6:\s
//                    1. Доработайте крестики-нолики; создайте исключение, которое будете бросать при неверном
//                       вводе пользователя\s
//
//                Решение:\s""");
//
//                X0Game game = new X0Game();
//                game.play();
//            }
//        }
//
//class FieldXO {
//    char[][] field;
//    int size = 3;
//    int countToWin = 3;
//
//    void initField() {
//        this.field = new char[size][size];
//        for (int row = 0; row < size; row++) {
//            for (int col = 0; col < size; col++) {
//                field[row][col] = ' ';
//            }
//        }
//        System.out.println("Field initialized");
//        this.printField();
//    }
//
//    void printField() {
//        System.out.print("   ");
//        for (int i = 1; i <= size; i++) {
//            System.out.print(i + "  ");
//        }
//        System.out.println();
//
//        for (int row = 0; row < size; row++) {
//            int rowNumber = row + 1;
//            System.out.print(rowNumber + " ");
//            for (int col = 0; col < size; col++) {
//                System.out.print("[" + this.field[row][col] + "]");
//            }
//            System.out.println();
//        }
//    }
//
//    boolean isPlaceFree(int rowIndex, int colIndex) {
//        if (rowIndex < 0 || rowIndex >= size || colIndex < 0 || colIndex >= size) {
//            return false;
//        } else if (this.field[rowIndex][colIndex] == ' ') {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    void setValue(int rowIndex, int colIndex, char value) {
//        this.field[rowIndex][colIndex] = value;
//    }
//
//    boolean isGameOver(char player) {
//        for (int row = 0; row < this.size; row++) {
//            for (int col = 0; col < this.size; col++) {
//                if (checkRightDirection(row, col, player)) {
//                    return true;
//                } else if (checkDownDirection(row, col, player)) {
//                    return true;
//                } else if (checkRightDiagonal(row, col, player)) {
//                    return true;
//                } else if (checkLeftDiagonal(row, col, player)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    boolean checkRightDirection(int row, int col, char player) {
//        if (col > this.size - this.countToWin) {
//            return false;
//        }
//        for (int i = col; i < col + this.countToWin; i++) {
//            if (this.field[row][i] != player) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    boolean checkDownDirection(int row, int col, char player) {
//        if (row > this.size - this.countToWin) {
//            return false;
//        }
//        for (int i = row; i < row + this.countToWin; i++) {
//            if (this.field[i][col] != player) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    boolean checkRightDiagonal(int row, int col, char player) {
//        if (row > this.size - this.countToWin) {
//            return false;
//        }
//        if (col > this.size - this.countToWin) {
//            return false;
//        }
//
//        for (int sdvig = 0; sdvig < this.countToWin; sdvig++) {
//            int rowToCheck = row + sdvig;
//            int colToCheck = col + sdvig;
//            if (this.field[rowToCheck][colToCheck] != player) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    boolean checkLeftDiagonal(int row, int col, char player) {
//        if (row > this.size - this.countToWin) {
//            return false;
//        }
//        if (col < this.countToWin - 1) {
//            return false;
//        }
//
//        for (int sdvig = 0; sdvig < this.countToWin; sdvig++) {
//            int rowToCheck = row + sdvig;
//            int colToCheck = col - sdvig;
//            if (this.field[rowToCheck][colToCheck] != player) {
//                return false;
//            }
//
//        }
//        return true;
//    }
//}
//
//class X0Game {
//    FieldXO gameField;
//    Scanner scanner = new Scanner(System.in);
//    char whoMakeNextTurn;
//    boolean gameOver = false;
//
//    void setupNewGame() {
//        System.out.println("Will play new X0 game");
//        this.gameField = new FieldXO();
//        this.gameField.initField();
//    }
//
//    void play() {
//        this.setupNewGame();
//        System.out.println("Who will make first turn (Enter X or 0 (zero) in Latin alphabet): ");
//        char first = this.scanner.next().charAt(0);
//        if (first == 'X' || first == '0') {
//            this.whoMakeNextTurn = first;
//        } else {
//            System.out.println("I recognizing only X or 0 (zero). So first will be X");
//            this.whoMakeNextTurn = 'X';
//        }
//
//        while (!gameOver) {
//            turn();
//            this.gameOver = this.gameField.isGameOver(whoMakeNextTurn);
//            if (this.gameOver) {
//                System.out.println(this.whoMakeNextTurn + " win!");
//            }
//            if (this.whoMakeNextTurn == 'X') {
//                this.whoMakeNextTurn = '0';
//            } else {
//                this.whoMakeNextTurn = 'X';
//            }
//        }
//        System.out.println("Game over.");
//    }
//
//    void turn() {
//        System.out.println(this.whoMakeNextTurn + ", your turn.");
//        System.out.print("Chose row: ");
//        int rowNumber = this.scanner.nextInt();
//        System.out.print("Chose column: ");
//        int colNumber = this.scanner.nextInt();
//        int rowIndex = rowNumber - 1;
//        int colIndex = colNumber - 1;
//        if (this.gameField.isPlaceFree(rowIndex, colIndex)) {
//            this.gameField.setValue(rowIndex, colIndex, whoMakeNextTurn);
//            this.gameField.printField();
//        } else {
//            System.out.println("Wrong number (maybe this place is not free?). Make turn again.");
//            turn();
//        }
//    }
//}
//// КОНЕЦ ПРИМЕРА 3




//// ПРИМЕР 2
//public class Task4_6_1 {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Введите первое число: ");
//        int num1 = scanner.nextInt();
//
//        System.out.print("Введите второе число: ");
//        int num2 = scanner.nextInt();
//
//        try {
//            if (num2 == 0) {
//                throw new Exception("Ошибка ввода");
//            }
//
//            int result = num1 / num2;
//            System.out.println("Результат деления: " + result);
//        } catch (Exception e) {
//            System.out.println("Ошибка: " + e.getMessage());
//        }
//    }
//}
//// КОНЕЦ ПРИМЕРА 2