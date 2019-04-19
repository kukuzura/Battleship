package battleship;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorkingWithCoordinates {

    public static boolean coordinateCheck(String str) {
        Pattern pattern = Pattern.compile("[а-к&&[^й^ё]](\\d|10)");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static int[] getTwoCoordinatesAsInt(String str) {
        int coordinates[] = new int[2];
        switch (str.charAt(0)) {
            case 'а':
                coordinates[0] = 0;
                break;
            case 'б':
                coordinates[0] = 1;
                break;
            case 'в':
                coordinates[0] = 2;
                break;
            case 'г':
                coordinates[0] = 3;
                break;
            case 'д':
                coordinates[0] = 4;
                break;
            case 'е':
                coordinates[0] = 5;
                break;
            case 'ж':
                coordinates[0] = 6;
                break;
            case 'з':
                coordinates[0] = 7;
                break;
            case 'и':
                coordinates[0] = 8;
                break;
            case 'к':
                coordinates[0] = 9;
                break;
        }
        coordinates[1] = Integer.parseInt(str.substring(1));
        return coordinates;
    }

    public static String getTwoCoordinatesAsString(int x, int y) {
        String str = new String();
        switch (y) {
            case 0:
                str+='а';
                break;
            case 1:
                str+='б';
                break;
            case 2:
                str+='в';
                break;
            case 3:
                str+='г';
                break;
            case 4:
                str+='д';
                break;
            case 5:
                str+='е';
                break;
            case 6:
                str+='ж';
                break;
            case 7:
                str+='з';
                break;
            case 8:
                str+='и';
                break;
            case 9:
                str+='к';
                break;
        }
        str+=String.valueOf(x+1);
        return str;
    }

}
