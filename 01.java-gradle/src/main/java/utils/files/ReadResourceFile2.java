package utils.files;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadResourceFile2 {
    public static void main(String[] args) {
        final Path DIR_PATH = Paths.get("C:","filePath","post");
        Path filePath = Paths.get(DIR_PATH + File.separator + "writeFile.txt");
        try(BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(filePath))){
            StringBuilder builder = new StringBuilder();
            byte[] buffer = new byte[1024];
            int byteRead;

            while( (byteRead = bis.read(buffer)) != -1){
                builder.append(new String(buffer, 0,byteRead));
            }
            System.out.println("builder = " + builder.toString());
        }catch (IOException e){

        }
    }
}
