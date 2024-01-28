package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
/**
 * @author inthegun
 * @since
 * Zip 파일들을 다른 폴더에 해제하는 코드
 */
public class UnzipFiles {

    public static void main(String[] args) {
        String sourceFolder = "D:\\다운로드\\mountain\\mountain_json";
        String destinationFolder = "D:\\다운로드\\mountain\\mountain_json\\unzip_json";

        unzipAllFiles(sourceFolder, destinationFolder);
    }

    private static void unzipAllFiles(String sourceFolder, String destinationFolder) {
        File sourceDir = new File(sourceFolder);
        File destDir = new File(destinationFolder);

        // Get all zip files in the source directory
        File[] zipFiles = sourceDir.listFiles((dir, name) -> name.endsWith(".zip"));

        if (zipFiles != null) {
            for (File zipFile : zipFiles) {
                unzip(zipFile, destDir);
            }
        } else {
            System.out.println("No zip files found in the source directory.");
        }
    }

    private static void unzip(File zipFile, File destDir) {
        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFile), Charset.forName("EUC-KR"))) {
            ZipEntry entry = zipIn.getNextEntry();

            while (entry != null) {
                String filePath = destDir + File.separator + zipFile.getName().replace(".zip", "") +
                        File.separator + entry.getName();

                // Create necessary directories
                File entryFile = new File(filePath);
                if (entry.isDirectory()) {
                    entryFile.mkdirs();
                } else {
                    entryFile.getParentFile().mkdirs();
                    extractFile(zipIn, filePath);
                }

                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }

            System.out.println("Successfully extracted: " + zipFile.getName());
        } catch (IOException e) {
            System.err.println("Error extracting " + zipFile.getName() + ": " + e.getMessage());
        }
    }

    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = zipIn.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }
}
