package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author inthegun
 * @since
 * DataBase DML(insert,update,delete..)이 적힌 txt 파일을 읽어서
 * 실행되도록 하는 코드
 */
public class FileReaderProcess {
    private Connection connection;
    private Statement statement;

    /**
     * DB 연동을 합니다
     * @throws Exception
     */
    public void init() throws Exception{
        /**
         * Class.forName
         * mysql : com.mysql.jdbc.Driver
         * oracle :
         * tibero : com.tmax.tibero.jdbc.TbDriver
         */
        try{
            Class.forName("com.mysql.jdbc.Driver"); // mysql connection
            String uri = "jdbc:mysql://localhost:3306/test";
            String id = "";
            String pw = "";
            connection = DriverManager.getConnection(uri,id,pw);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    } // init()

    /**
     * 파일을 읽어서 Query를 실행합니다.
     * @param fileName
     * @throws Exception
     */
    public void readFile(String fileName) throws Exception{
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            LineNumberReader lr = new LineNumberReader(br);

            int count = 0;
            String linedata ;

            while( (linedata = lr.readLine())  != null ){
                ++count;
                try {
                    if (linedata== null || linedata.trim().length() < 1){
                        continue;
                    }
                    executeQuery(linedata,count);
                    Thread.sleep(100);
                }catch(Exception e){
                    System.out.println(String.format("%d 번 Line 은 실패했습니다. %s",count,linedata));
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    private void executeQuery(String query, int count) throws Exception {
        statement.execute(query);
        if(count % 100 == 0){
            connection.commit();
        }
    }// executeQuery

    private void close(){
        try {
            connection.commit();

            statement.close();
            connection.close();
        }catch (Exception e){

        }
    }//close

    public static void main(String args[]){
        FileReaderProcess process = new FileReaderProcess();
        try {
            process.init();
            process.readFile(args[0]);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            process.close();
        }

    }// main
}