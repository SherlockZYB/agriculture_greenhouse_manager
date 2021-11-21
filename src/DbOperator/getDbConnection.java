package DbOperator;


import java.sql.*;

public class getDbConnection {

    // 数据库链接相关常量
<<<<<<< HEAD
    final String http="jdbc:mysql://www.ylxteach.net:3366/";
    final String dbName="xm08";
=======

    final String http="jdbc:mysql://www.ylxteach.net:3366/";
    final String dbName="xm08";


>>>>>>> 117581ba5fc9261dccc0ee1f9cbdde35e639c486
    private Statement statement;

    // 构造函数，
    public getDbConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
<<<<<<< HEAD
            String connStr=http+dbName+"?user=Administrator&password=XWClassroom20202023&useUnicode=true&characterEncoding=UTF-8";
=======

            String connStr=http+dbName+"?user=Administrator&password=XWClassroom20202023&useUnicode=true&characterEncoding=UTF-8";

>>>>>>> 117581ba5fc9261dccc0ee1f9cbdde35e639c486
            Connection connection= DriverManager.getConnection(connStr);
            statement=connection.createStatement();
        }catch (Exception e){
            System.out.println("数据库链接失败！");
            e.printStackTrace();
        }
    }


    // 返回数据库链接
    public Statement getConnection(){
        return this.statement;
    }

    // 关闭数据库
    public void close() throws SQLException {
        this.statement.close();
        System.out.println("数据库关闭");
    }
}
