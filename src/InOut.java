import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Класс для ввода/вывода  */

public class InOut {
    public static final String YES = "Да";
    public static final String NO = "Нет";
    public static final String RE = "Повторите ввод";
    public static final String EXIST = "Вы уже отгадали эту букву. Введите другую";
    public static final String LE = "Введите букву";
    public static final String WRONG = "Неверная буква";
    public static final String RIGHT = "Верно!";
    public static final String WIN = "ПОБЕДА!";
    public static final String LOOSE = "Вы проиграли!";
    public static final String NEW = "Хотите начать новую игру? Да/Нет";
    public static final String BYE = "До новых встреч!";
    public static final String HI = "Начинаем игру \"Виселица\"";

    private static final BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

    public static boolean welcome() {
        System.out.println(NEW);                            // Спрашиваем "Хотите начать новую игру?
        try {
            while(true) {
                String answer = reader.readLine();
                if (answer.equalsIgnoreCase(YES)) {         // "Да"
                    return true;
                }
                if (answer.equalsIgnoreCase(NO)) {          // "Нет"
                    return false;
                } else {
                    System.out.println(RE);                 // если ни то ни другое, просим ввести да либо нет
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static String letter() {
        for (String s : GameProgress.wordDraw) {            // выводим в консоль слово в виде "_ _ _ _"
            System.out.print(s);
        }
        if (!GameProgress.usedLetters.isEmpty()) {          // учитываем буквы которые ввели, но они неверные
            System.out.print("\u001B[31m" + "     Неверные буквы: ");
            for (String s: GameProgress.usedLetters) {      // выводим их в консоль для удобства игрока
                System.out.print(s + ", ");
            }
            System.out.println("\u001B[0m");
        }
        System.out.println();
        System.out.println(LE);                             // просим игрока ввести букву
        try {
            while (true) {
                String answer = reader.readLine();
                if (answer.length() != 1 || !answer.matches("[а-яА-ЯёЁ]")) { // если ввод > 1 знака или не кириллица
                    System.out.println(RE);                                        // просим игрока повторить ввод
                } else if (GameProgress.wordDraw.stream().
                        anyMatch(s -> s.strip().equalsIgnoreCase(answer))) { // проверка на повторный ввод уже
                    System.out.println(EXIST);                                      // отгаданной буквы - просим ввести другую
                } else {
                    return answer;                                                  // когда буква выполяет все условия забираем ее
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

