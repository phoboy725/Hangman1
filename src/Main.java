/* Основной класс программы */

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // Проверка "Хотите начать новую игру..."
        if (!InOut.welcome()) {                                 // если нет
            System.out.println(InOut.BYE);                      //  выходим из программы
        } else {                                                // если да
            System.out.println(InOut.HI);                       // приветствие
            GameProgress.gameStart();                           // запускаем
            Thread.sleep(1000);
            while (true) {                                      // цикл: считываем ввод с клавиатуры пока true
                if (GameProgress.bodyCount == 6) {              // если ввели 6 неправильных букв
                    System.out.println(Hangman.hangman[6]);     // выводим в консоль рисунок полной виселицы
                    System.out.println(InOut.LOOSE);            // пишем "вы проиграли...
                    System.out.print("Правильное слово: ");     // выводим слово которое было загадано
                    for (String s : GameProgress.word) {
                        System.out.print(s);
                    }
                    break;                                      // выходим из цикла
                } if (GameProgress.letterCount == 0) {          // если счетчик верно угаданных букв в искомом слове = 0
                    System.out.print("Правильное слово: ");     // выводим слово которое было загадано
                    for (String s : GameProgress.word) {
                        System.out.print(s);
                    }
                    System.out.println();
                    System.out.println(InOut.WIN);              // пишем "Победа!"
                    break;                                      // выходим из цикла
                } else {
                    GameProgress.checkLetter(InOut.letter());   // если не выполнены условия выше, запрашиваем у игрока букву
                }
            }
        }
    }
}
