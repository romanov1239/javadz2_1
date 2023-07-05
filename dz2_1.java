// *Получить исходную json строку из файла, используя FileReader или Scanner
// Дана json строка вида:
// String json = "[{\"фамилия\":\"Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"}," +
// "{\"фамилия\":\"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"}," +
// "{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}]";

// Задача написать метод(ы), который распарсить строку и выдаст ответ вида:
// Студент Иванов получил 5 по предмету Математика.
// Студент Петрова получил 4 по предмету Информатика.
// Студент Краснов получил 5 по предмету Физика.

// Используйте StringBuilder для подготовки ответа. Далее создайте метод, который запишет
// результат работы в файл. Обработайте исключения и запишите ошибки в лог файл с помощью Logger.



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class dz2_1 {
private static final Logger logger = Logger.getLogger(dz2_1.class.getName());

public static void main(String[] args) {
    FileHandler fileHandler = null;
    try {
        fileHandler = new FileHandler("error.log");
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);

        String str1 = "[{\"фамилия\":\"Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"}," +
                "{\"фамилия\":\"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"}," +
                "{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}]";

        StringBuilder result = new StringBuilder();
        String[] students = str1.split("},");
        for (String student : students) {
            try {
                String surname = getValue(student, "фамилия");
                String rating = getValue(student, "оценка");
                String subject = getValue(student, "предмет");

                result.append("Студент ").append(surname).append(" получил ").append(rating)
                        .append(" по предмету ").append(subject).append(".\n");
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error processing student:", e);
            }
        }

        writeToFile(result.toString(), "output.txt");
    } catch (IOException e) {
        logger.log(Level.SEVERE, "Error creating file handler:", e);
    } finally {
        if (fileHandler != null) {
            fileHandler.close();
        }
    }
}

private static String getValue(String student, String field) {
    int start = student.indexOf("\"" + field + "\":\"") + field.length() + 4;
    int end = student.indexOf("\"", start);
    return student.substring(start, end).replace("\\", "");
}

private static void writeToFile(String contents, String filePath) {
    try (FileWriter writer = new FileWriter(new File(filePath))) {
        writer.write(contents);
    } catch (IOException e) {
        logger.log(Level.SEVERE, "Error writing to file:", e);
    }
}
}

