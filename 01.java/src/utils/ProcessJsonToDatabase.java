package utils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class ProcessJsonToDatabase {
    static int count = 0;

    public static void main(String[] args) {
        // 폴더 정리된 경로
        String unzipJsonFolder = "/Users/lsm/Downloads/unzip_json";
        String databaseUrl = "jdbc:mariadb://springboot-service.c6kerfpxgq04.ap-northeast-2.rds.amazonaws.com:3306/springboot_service";
        String databaseUsername = "admin";
        String databasePassword = "!Project123";

        // 폴더를 돌면서 처리
        processFolders(new File(unzipJsonFolder), databaseUrl, databaseUsername, databasePassword);
    }
    private static void processFolders(File folder, String databaseUrl, String databaseUsername, String databasePassword) {
        File[] subFolders = folder.listFiles(File::isDirectory);

        if (subFolders != null) {
            // 하위 폴더가 있다면 각 폴더를 처리
            for (File subFolder : subFolders) {
                processFolder(subFolder, databaseUrl, databaseUsername, databasePassword);
            }
        }
    }

    private static void processFolder(File folder, String databaseUrl, String databaseUsername, String databasePassword) {
        File[] jsonFiles = folder.listFiles((dir, name) -> name.endsWith(".json"));

        if (jsonFiles != null) {
            // 폴더 내의 모든 JSON 파일을 처리
            for (File jsonFile : jsonFiles) {
                processJsonFile(jsonFile, databaseUrl, databaseUsername, databasePassword);
            }
        }
    }

    private static void processJsonFile(File jsonFile, String databaseUrl, String databaseUsername, String databasePassword) {
        try (FileReader fileReader = new FileReader(jsonFile)) {
            JsonElement jsonElement = JsonParser.parseReader(fileReader);

            if (jsonElement.isJsonObject()) {
                // JSON이 객체인 경우
                JsonObject jsonObject = jsonElement.getAsJsonObject();

                if (jsonObject.has("features")) {
                    JsonArray featuresArray = jsonObject.getAsJsonArray("features");

                    for (JsonElement featureElement : featuresArray) {
                        processFeature(jsonFile,featureElement.getAsJsonObject(), databaseUrl, databaseUsername, databasePassword);
                    }
                }

                // 필요한 데이터에 따라 insertDataIntoDatabase 메소드 수정
//                if (jsonFile.getName().contains("SPOT")) {
//                    insertDataIntoSpotTable(databaseUrl, databaseUsername, databasePassword, attributeName);
//                } else {
//                    insertDataIntoNormalTable(databaseUrl, databaseUsername, databasePassword, attributeName);
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processFeature(File jsonFile ,JsonObject featureObject, String databaseUrl, String databaseUsername, String databasePassword) {
        if (featureObject.has("attributes") && featureObject.has("geometry")) {
            JsonObject attributes = featureObject.getAsJsonObject("attributes");
            JsonObject geometry = featureObject.getAsJsonObject("geometry");


            int propertyCount = attributes.size(); // JsonObject 내의 속성 개수
            String[] propertyNames = attributes.keySet().toArray(new String[propertyCount]);
            int index =0;
            for(String propertyName : attributes.keySet()){
                propertyNames[index++] =  attributes.get(propertyName).getAsString();
            }
            System.out.println(String.format("현재 count %d  , %s 파일 실행중 __  ",count,jsonFile.getName()));
            //String attributeName = attributes.getAsJsonPrimitive("attributeName").getAsString();

            if (jsonFile.getName().contains("PMNTN_SPOT")) {
                double x= geometry.getAsJsonPrimitive("x").getAsDouble();
                double y= geometry.getAsJsonPrimitive("y").getAsDouble();
                double[] spot = {x,y};
                insertDataIntoSpotTable(databaseUrl, databaseUsername, databasePassword, propertyNames, Arrays.toString(spot));
            } else if(!jsonFile.getName().contains("SAFE")) {
                // 등산 경로
                JsonArray paths = geometry.getAsJsonArray("paths");
                insertDataIntoNormalTable(databaseUrl, databaseUsername, databasePassword, propertyNames,paths.toString());
            }
        }
    }


    private static void insertDataIntoNormalTable(String databaseUrl, String databaseUsername, String databasePassword, String[] attributeName, String paths) {
        try (Connection connection = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword)) {
            ++count;
            connection.setAutoCommit(true);
            // 데이터베이스에 데이터 삽입하는 코드 작성
            // PreparedStatement를 사용하면 SQL 인젝션을 방지할 수 있습니다.
            String sql = "INSERT INTO pmntn (FID, PMNTN_SN , MNTN_CODE, MNTN_NM, PMNTN_NM, PMNTN_MAIN , PMNTN_LT , PMNTN_DFFL , PMNTN_UPPL, PMNTN_GODN, PMNTN_MTRQ , PMNTN_CNRL,PMNTN_CLS_, PMNTN_RISK, PMNTN_RECO , DATA_STDR_ , MNTN_ID)" +
                         " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            boolean bool = attributeName.length < 17 ? false : true;

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, attributeName[0]); // FID
                preparedStatement.setString(2, attributeName[1]); // PMNTN_SN
                preparedStatement.setString(3, attributeName[2]); // MNTN_CODE
                preparedStatement.setString(4, attributeName[3]); // MNTN_NM
                preparedStatement.setString(5, attributeName[4]); // PMNTN_NM
                preparedStatement.setString(6, attributeName[5]); // PMNTN_MAIN
                preparedStatement.setString(7, attributeName[6]); // PMNTN_LT
                preparedStatement.setString(8, attributeName[7]); // PMNTN_DFFL
                preparedStatement.setString(9, attributeName[8]); // PMNTN_UPPL
                preparedStatement.setString(10, attributeName[9]); // PMNTN_GODN
                preparedStatement.setString(11, attributeName[10]); // PMNTN_MTRQ
                preparedStatement.setString(12, attributeName[11]); //PMNTN_CNRL
                preparedStatement.setString(13, attributeName[12]); //PMNTN_CLS_
                preparedStatement.setString(14, attributeName[13]); // PMNTN_RISK
                preparedStatement.setString(15, attributeName[14]); //PMNTN_RECO
                preparedStatement.setString(16, attributeName[15]); //DATA_STDR_
                preparedStatement.setString(17, bool ? attributeName[16] : "" ); // MNTN_ID
                // 필요한 데이터에 따라 set 메소드 호출 및 인덱스 수정

                // 실행
                preparedStatement.executeUpdate();
//                if(count % 100 == 0){
//                    connection.commit();
//                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * SPOT TABLE INSERT
     * @param databaseUrl
     * @param databaseUsername
     * @param databasePassword
     * @param attributeName
     */
    private static void insertDataIntoSpotTable(String databaseUrl, String databaseUsername, String databasePassword, String[] attributeName, String spot) {
        try (Connection connection = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword)) {
            ++count;
            connection.setAutoCommit(true);
            String sql = "INSERT INTO pmntn_spot (FID ,PMNTN_SPOT, MNTN_CODE, MANAGE_SP1 ,MANAGE_SP2 , DETAIL_SPO, ETC_MATTER,MNTN_NM,PAST_SPOT_,MNTN_ID ) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, attributeName[0]); // FID
                preparedStatement.setString(2, attributeName[1]); // PMNTN_SN
                preparedStatement.setString(3, attributeName[2]); // MNTN_CODE
                preparedStatement.setString(4, attributeName[3]); // MANAGE_SP1
                preparedStatement.setString(5, attributeName[4]); // MANAGE_SP2
                preparedStatement.setString(6, attributeName[5]); // DETAIL_SPO
                preparedStatement.setString(7, attributeName[6]); // ETC_MATTER
                preparedStatement.setString(8, attributeName[7]); // MNTN_NM
                preparedStatement.setString(9, attributeName[8]); // PAST_SPOT_
                preparedStatement.setString(10, attributeName[9]); // MNTN_ID

                preparedStatement.executeUpdate();
//                if(count % 100 == 0){
//                    connection.commit();
//                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
