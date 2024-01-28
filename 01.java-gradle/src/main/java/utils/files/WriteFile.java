package utils.files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 파일 생성 및 쓰기
 */
public class WriteFile {
    static final String FILE_PATH = Paths.get("C:","filePath","post").toString();

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String fileName = "writeFile" + now.format(formatter) + ".txt";
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File(FILE_PATH + File.separator + fileName)))){
            StringBuilder builder = new StringBuilder();
            String line;
            writer.write("안녕하세요 1");
        }catch (IOException e){

        }
    }



}
