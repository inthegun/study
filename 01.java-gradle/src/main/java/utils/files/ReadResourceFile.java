package utils.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 클래스로더를 통해서 리소스 파일 읽기
 */
public class ReadResourceFile {
    public static void main(String[] args) {
        String resourcePath = "/templates/readFile.txt";
        try (
                InputStream inputStream = ReadResourceFile.class.getResourceAsStream(resourcePath);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader)
        ) {
            String line;
            while( (line = reader.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {

        }
    }
}
