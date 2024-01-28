package database;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author inthegun
 * DataBase 연결 및 DML(insert,update,delete,select) 실행 테스트 해보는 코드
 * mysql table 사용
 *
 */
public class DriverConnectDB {
    private Connection connection;
   // private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    //private Scanner scanner;
//    private BufferedReader bufferedReader;

    public static void main(String[] args)  {
        DriverConnectDB ConnectDb = new DriverConnectDB();
//        ConnectDb.scanner = new Scanner(System.in);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        boolean Type = true;
        int input = 0;
        try {
            while (Type){
                System.out.print("1. 조회\t2. 입력\t3. 수정\t4. 삭제\t그외 입력은.. 종료됩니다.\t");
//                input = Integer.parseInt(ConnectDb.scanner.nextLine());
                input = Integer.parseInt(bufferedReader.readLine());
                String id,Name,Sex;
                StringTokenizer stringTokenizer;
                switch (input){
                    case 1:
                        System.out.println("gitTable 모든 목록을 조회합니다.");
                        ConnectDb.selectTable();
                        break;
                    case 2:
                        System.out.println("gitTable 신규입력 합니다. ");
                        System.out.println("ID,이름,성별 입력해주세요.");
                        System.out.println("공백으로 구분됩니다 ex)23 홍길동 남자");
                        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                        id = stringTokenizer.nextToken();
                        Name = stringTokenizer.nextToken();
                        Sex = stringTokenizer.nextToken();
                        ConnectDb.insertTable(id,Name,Sex);
                        break;
                    case 3:
                        System.out.println("gitTable 데이터를 수정합니다. ");
                        System.out.println("ID,변경할 이름을 입력해주세요.");
                        System.out.println("공백으로 구분됩니다 ex)23 홍길동");
                        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                        id = stringTokenizer.nextToken();
                        Name = stringTokenizer.nextToken();
                        List<gitTable> list = ConnectDb.selectTable(id);
                        if(list.size() > 0 && !list.isEmpty()){
                            ConnectDb.updateTable(id,Name);
                        }else {
                            System.out.println("해당하는 ID가 없습니다.\n메뉴로 돌아갑니다.");
                        }
                        break;
                    case 4:
                        System.out.println("gitTable 데이터를 삭제합니다. ");
                        System.out.println("삭제할 ID 입력해주세요.");
                        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                        id = stringTokenizer.nextToken();
                        ConnectDb.deleteTable(id);
                        break;
                    default:
                        System.out.println("종료되었습니다. ");
                        return;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
//            ConnectDb.selectTable();     // Where 조건 없는경우
//            ConnectDb.selectTable(1); // Where 조건이 들어가는 경우
            // 입력
//            ConnectDb.insertTable(2,"이번","M");
            // 삭제
//            ConnectDb.deleteTable(2);
            // 수정
//            ConnectDb.updateTable(2,"이번수정");

    }
//    private

    /**
     *  JDBC 연결하는 코드
     * @throws Exception
     */
    public Connection getConnection() throws Exception{
        try {
            Class.forName("org.gjt.mm.mysql.Driver").newInstance(); // com.mysql.jdbc.Driver
            String uri = "jdbc:mysql://localhost:3306/gitBase?useSSL=false"; // database 뒤에 ?serverTimezon=UTC&useUnicode=true 등 옵션 필요시 추가
            String id = "root";
            String pw = "1234";
            connection = DriverManager.getConnection(uri,id,pw);
            connection.setAutoCommit(false);
//            System.out.println("데이터 베이스 접속 성공");
        }catch (ClassNotFoundException e){
            System.err.println("드라이버를 찾지 못했습니다.");
            System.err.println(e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * @throws Exception
     * Select 쿼리 실행
     */
    public void selectTable() throws Exception{
        String sqlQuery = "SELECT * from gitTable";
        try{
            connection = getConnection();
            /**
             * 1번 statment
             */
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery(sqlQuery);
            /**
             * 2번 preparedStatement
             */
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String Id = resultSet.getString(1);
                String Name = resultSet.getString(2);
                String Sex = resultSet.getString(3);
                System.out.println("Id : "+Id+ "\tName :"+Name+"\tSex : "+Sex);
            }
            close();
        }catch (Exception e){

        }
    }

    /**
     *
     * @throws Exception
     * Select 쿼리 실행 [ Where 문에 조건이 들어갈 경우 ]
     */
    public List<gitTable> selectTable(String id) throws Exception {
        String sqlQuery = "SELECT * from gitTable WHERE Id = ?";
        List<gitTable> selectList = null;
        try {
            connection = getConnection();
            /**
             * 1번 statment
             */
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery(sqlQuery);
            /**
             * 2번 preparedStatement
             */
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, id);
            ;
            resultSet = preparedStatement.executeQuery();
            selectList = new ArrayList<>();

            if (resultSet.next()) {
                String Id = resultSet.getString(1);
                String Name = resultSet.getString(2);
                String Sex = resultSet.getString(3);
//                System.out.println("Id : "+Id+ " Name :"+Name+" Sex : "+Sex);
                gitTable table = new gitTable(Id, Name, Sex);
//                selectList.add(table); // 임시주석
            }
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return selectList;
    }

    /**
     * Insert 쿼리 실행
     * @param id
     * @param Name
     * @param Sex
     */
    public int insertTable(String id,String Name ,String Sex){
        String sqlQuery = "INSERT INTO gitTable values(?,?,?)";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,Name);
            preparedStatement.setString(3,Sex);
            int result = preparedStatement.executeUpdate();
            System.out.printf("%d 건의 레코드가 추가되었습니다.\n",result);
            connection.commit();
            return result;
        }catch (Exception e){
            return -1;
        }
    }

    /**
     * Delete 쿼리 실행
     * @param id
     * @return
     */
    public int deleteTable(String  id){
        String sqlQuery = "DELETE FROM gitTable WHERE id = ?";

        try{
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,id);
            int result = preparedStatement.executeUpdate();
            System.out.printf("%d 건의 레코드가 삭제되었습니다.\n",result);
            connection.commit();
            return result;
        }catch (Exception e){
            return -1;
        }
    }

    /**
     * Update 쿼리 실행
     * @param id
     * @param name
     * @return
     */
    public int updateTable(String id,String name){
        String sqlQuery = "UPDATE gitTable SET Name = ? WHERE Id = ?";
        try{
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,id);
            int result = preparedStatement.executeUpdate();
            System.out.printf("%d 건의 레코드가 수정되었습니다.\n",result);
            connection.commit();
            return result;
        }catch (Exception e){
            return -1;
        }
    }

    /**
     * 객체 close
     */
    private void close(){
        try {
            resultSet.close();
            //statement.close();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

class gitTable{
    private String id;
    private String Name;
    private String Sex;

    public gitTable(String id, String name, String sex) {
        this.id = id;
        Name = name;
        Sex = sex;
    }

}

/**
 * MySQL
 * ## create database gitBase;
 *  use gitBase;
 *  create table gitTable(
 *   	ID VARCHAR(50),
 *      Name VARCHAR(30),
 *      Sex VARCHAR(10)
 *   );
 */