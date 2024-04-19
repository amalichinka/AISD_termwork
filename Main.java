import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File folderTests = new File("files");
        File results = new File("results.txt");

        results.createNewFile();
        FileWriter writer = new FileWriter("results.txt");
        String headlines = String.format("Размер входных данных", "Время работы в наносекундах", "Количество итераций");
        writer.write(headlines);

        File[] testsData = folderTests.listFiles();
        int k = 0;
        int countIterations = 0;
        if (testsData != null) {
            for (File file : testsData) {
                if (file.isFile()) {
                    try {
                        Scanner scanner = new Scanner(file);

                        String text = scanner.next();
                        String template = "";
                        for(int i = 0; i < 5; i++){
                            char randomSymbol = (char) (Math.random() * 26 + 97);
                            template += Character.toString(randomSymbol);
                        }
                        BoyerMoore boyerMoore = new BoyerMoore(template);
                        long start = System.nanoTime();
                        boyerMoore.run(text);
                        long finish = System.nanoTime();
                        long time = finish - start;

                        String data = String.format(file.getName(), text.length() ,time, boyerMoore.getCountIterations());
                        writer.write(data);
                        k++;

                        scanner.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("Файл не найден.");
                    }
                }
            }
        } else {
            System.out.println("Папка пуста или не существует.");
        }

        System.out.println("Отсортировали файлов всего: "+ k);
        writer.close();
    }
}