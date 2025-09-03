import java.util.*;

/* Класс этапов игры */

public class GameProgress {

    public static ArrayList<String> word = new ArrayList<>();           // загаданное слово в листе по буквам
    public static ArrayList<String> wordDraw = new ArrayList<>();       // то же слово в листе по буквам для вывода в консоль
    public static ArrayList<String> usedLetters = new ArrayList<>();    // лист для учета введенных неправильных букв
    public static int bodyCount = 0;                                    // счетчик неправильных ответов
    public static int letterCount;                                      // счетчик правильных ответов

   // начало игры
    public static void gameStart() {
        Dict.setDict();                                                 // набиваем словарь словами)
        Random rand = new Random();
        int ri = rand.nextInt(0, Dict.dict.size());
        String x = Dict.dict.get(ri);                                   // рандомно выбираем слово из словаря

        for (int i = 0; i < x.length(); i++) {
            word.add(String.valueOf(x.charAt(i)));                      // делаем лист из букв загадонного слова
        }
        System.out.printf("Отгадайте слово из %s букв\n", word.size());
        letterCount = word.size();                                      // счетчик правильных ответов = кол-ву букв в слове
        for (String s: word) {
            wordDraw.add(" _ ");                                        // набиваем лист для вывода слова в консоль " _ "
        }
    }
    // проверка введенной буквы
    public static void checkLetter(String letter) {
        if (word.stream().anyMatch(s -> s.equalsIgnoreCase(letter))) { // если буква есть в листе со словом
            System.out.println(InOut.RIGHT);                                  // пишем "верно"
            for (int i = 0; i < word.size(); i++) {                           // заменяем в листе со словом для вывода
                if (letter.equalsIgnoreCase(word.get(i))) {
                    wordDraw.set(i, " " + letter + " ");                      // " _ " на угаданную букву
                    letterCount--;                                            // уменьшаем счетчик угаданных букв на 1
                }
            }
        } else {                                                              // если буквы нет в листе со словом
            System.out.println(InOut.WRONG);                                  // пишем "неверно"
            System.out.println(Hangman.hangman[bodyCount]);                   // выводим виселицу в консоль в зависимости от счетчика неправильных ответов
            bodyCount++;                                                      // увеличиваем счетчик непр. ответов
            usedLetters.add(letter);                                          // добавляем букву в лист неверных букв
        }
    }
}
