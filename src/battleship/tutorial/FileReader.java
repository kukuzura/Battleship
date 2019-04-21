package battleship.tutorial;

import java.io.*;

public class FileReader {
    public static void outputText(String fileName) {

        try (FileInputStream fstream = new FileInputStream(fileName)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                System.out.println(strLine);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл " + fileName + "не найден");
        } catch (IOException e) {
            System.out.println("Ошибка чтения из файла " + fileName);
        }

    }
}
