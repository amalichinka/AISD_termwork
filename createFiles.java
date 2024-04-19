import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class createFiles {
    public static void main(String[] args) throws IOException {
        List<String> fileNames = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if(i < 10){
                fileNames.add("file" + 0 + i + ".txt");
            }else{
                fileNames.add("file" + i + ".txt");
            }
        }
        int countSymbolInFile = 0;
        for(int i = 0; i < 100; i++){
            countSymbolInFile += 100;
            File newFile = new File(fileNames.get(i));
            OutputStream curFile = new FileOutputStream(newFile);
            for(int j = 0; j < countSymbolInFile; j++){
                char randomSymbol = (char) (Math.random() * 26 + 97);
                curFile.write(randomSymbol);
            }
            curFile.close();
        }
    }
}