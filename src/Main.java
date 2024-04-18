import java.io.*;
import java.nio.file.FileSystemException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException {

        try {
            Record();
            System.out.println("успешно");
        } catch (FileSystemException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

    }

    public static void Record() throws Exception{
        System.out.println("Введите фамилию, имя, отчество, дату рождения (в формате dd.mm.yyyy), " +
                "номер телефона (число без разделителей) и пол(символ латиницей f или m)");

        String text;
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            text = bf.readLine();
        } catch (IOException e) {
            throw new Exception("Произошла ошибка при работе с консолью");
        }

        String[] arr = text.split(" ");
        if ( arr.length != 6) {
            throw new Exception("Введено неверно количество параметров");
        }

        String surname = arr[0];
        String name = arr[1];
        String patronymic = arr[2];

        SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
        Date dr;
        try {
            dr = format.parse(arr[3]);
        } catch (ParseException e) {
            throw new ParseException("Неверный формат даты рождения", e.getErrorOffset());
        }

        int phone;
        try {
            phone = Integer.parseInt(arr[4]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Неверный формат ввода номера телефона");
        }

        String sex = arr[5];
        if (!sex.toLowerCase().equals("m") && !sex.toLowerCase().equals("f")){
            throw new RuntimeException("Неверно введен пол");
        }

        String filename = "C:\\Certification by exception\\Certification\\files\\" + surname.toLowerCase() + ".txt";
        File file = new File(filename);
        try (FileWriter fileWriter = new FileWriter(file, true)){
            if (file.length() > 0){
                fileWriter.write('\n');
            }
            fileWriter.write(String.format("%s %s %s %s %s %s", surname, name, patronymic, format.format(dr), phone, sex));
        }catch (IOException e){
            throw new FileSystemException("Возникла ошибка при работе с файлом");
        }

    }
}