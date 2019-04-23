package battleship.util;

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

    public static void inputGameResult(String fileName, String text) {
        try (FileOutputStream fstream = new FileOutputStream(fileName)) {
            BufferedWriter br = new BufferedWriter(new OutputStreamWriter(fstream));
            br.write(text + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("Файл " + fileName + "не найден");
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл " + fileName);
        }
    }
}
