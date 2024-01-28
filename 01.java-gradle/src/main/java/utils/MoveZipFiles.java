package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class MoveZipFiles {
    public static void main(String[] args) {
        // 현재 경로
        String currentPath = "D:\\다운로드\\mountain";

        // 목적지 경로
        String destinationPath = currentPath + File.separator + "mountain_json";

        // 폴더 생성
        File destinationFolder = new File(destinationPath);
        if (!destinationFolder.exists()) {
            destinationFolder.mkdirs();
        }

        // 현재 경로에서 _geojson.zip으로 끝나는 파일 찾기
        File[] zipFiles = new File(currentPath).listFiles((dir, name) -> name.endsWith("_geojson.zip"));

        // 파일을 목적지로 이동
        if (zipFiles != null) {
            for (File zipFile : zipFiles) {
                try {
                    Path sourcePath = zipFile.toPath();
                    Path destinationFilePath = new File(destinationFolder, zipFile.getName()).toPath();
                    Files.move(sourcePath, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Moved: " + zipFile.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No matching files found.");
        }
    }
}
