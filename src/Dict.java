import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/* Класс словаря */

public class Dict {
    private static final String DATA_DIR = "data";                  // имя папка с файлом .txt со словами
    private static final String BASE_NAME = "words";                // имя файла .txt со словами
    public static ArrayList<String> dict = new ArrayList();         // лист слов для игры


    public static void setDict() {
        String fileName = BASE_NAME + ".txt";
        Path path = Paths.get(DATA_DIR, fileName);
        try {                                                       // Читаем все строки, фильтруем пустые и комментарии
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (String l : lines) {
                String s = l.strip();
                if (!s.isEmpty() && !s.startsWith("#")) {
                    dict.add(s);                                   // заполняем лист словами
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to load " + path + ": " + e.getMessage());
        }
    }
}
