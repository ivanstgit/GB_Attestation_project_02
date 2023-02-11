import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import ToyPrizeApp.ToyPrizeApp;

public class ToyPrizeAppMain {
    static String inputFileName = "Призы.txt";
    static String outputFileName = "Статистика.txt";

    public static void main(String[] args) {

        try {
            ArrayList<String> inputStrings = new ArrayList<>();
            // Считываем данные из файла
            File dir = new File(".");
            File inFile = new File(dir.getCanonicalPath() + File.separator + inputFileName);
            Scanner fileReader = new Scanner(inFile);
            while (fileReader.hasNextLine()) {
              String data = fileReader.nextLine();
              inputStrings.add(data);
            }
            fileReader.close();

            // и добавляем в наш класс
            ToyPrizeApp tpInstance = new ToyPrizeApp(inputStrings);

            // Инициализируем коллекцию для сбора статистики
            HashMap<Long, Long> statistics = new HashMap<>();
            tpInstance.getIdList().forEach((id) -> {
                statistics.put(id, 0L);
            });

            // Сбор статистики (по заданию - 10 раз)
            for (int i = 0; i < 10; i++) {
                Long id = tpInstance.get().getId();
                statistics.replace(id, statistics.get(id) + 1);
            }

            // System.out.println();
            // System.out.println("Статистика розыгрыша");
            FileWriter fw = new FileWriter(outputFileName);

            statistics.forEach((key, value) -> {
                String str = key + "=" + value + '\n';
                // System.out.print(str);
                try {
                    fw.write(str);
                } catch (IOException e) {
                    System.out.println("Ошибка открытия файла");
                }
            });
            fw.close();
        } catch (IOException e) {
            System.out.println("Ошибка открытия файла");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка обработки файла");
        }
    }
}