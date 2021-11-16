package DbOperator;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class sqlOperator {

    private Statement statement;

    public sqlOperator(){
        getDbConnection dbConnection=new getDbConnection();
        this.statement=dbConnection.getConnection();
        System.out.println("准备数据库操作完毕!");
    }

    // 以下是相关数据库的操作，包括增删改查
    // 判断用户是否可以登录成功
    public int isExist(String account, String password) throws SQLException {
        String sql="select * from accountInfo where account='"+account+"' and PASSWORD='"+password+"'";
        System.out.println("sql:"+sql);
        ResultSet resultSet=statement.executeQuery(sql);
        if(resultSet.next()){
            return resultSet.getInt("userLevel");
        }else{
            sql="select * from accountInfo where mail='"+account+"' and PASSWORD='"+password+"'";
            System.out.println("SQL:"+sql);
            resultSet=statement.executeQuery(sql);
            if(resultSet.next()){
                return resultSet.getInt("userLevel");
            }else{
                return -1;
            }
        }
    }

    // 用户注册
    public Boolean Register(String account, String password, String mail) throws SQLException {
        System.out.println("注册!");
        String date=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        System.out.println(date);
        String sql="insert into applyInfo (account, PASSWORD, mail, applyDate) VALUES('"+account+"','"+password+"','"+mail+"','"+date+"')";
        System.out.println("sql:"+sql);
        if(statement.executeUpdate(sql)>0){
            return true;
        }else{
            return false;
        }

    }

    // 判断邮箱或用户名是否已注册或已申请注册
    public int isExisted(String content,String key) throws SQLException {
        String sql = null;
        String sql1= null;
        if(key.equals("mail")){
            sql="select * from accountInfo where mail='"+content+"'";
            sql1="select * from applyInfo where mail='"+content+"'";
        }else if(key.equals("account")){
            sql="select * from accountInfo where account='"+content+"'";
            sql1="select * from applyInfo where account='"+content+"'";
        }
        System.out.println("SQL:"+sql);
        ResultSet resultSet=statement.executeQuery(sql);
        if(resultSet.next()){
            // -1 说明用户账户或邮箱已经存在
            return -1;
        }else {
            System.out.println("SQL:"+sql1);
            resultSet=statement.executeQuery(sql1);
            if(resultSet.next()){
                // -2 说明用户账户或邮箱已经被申请注册，但尚未完成审核
                return -2;
            }else{
                // 0表示用户账户或邮箱可以申请注册，合法
                return 0;
            }
        }
    }

    public JSONObject showWarningTable(String isOrdered) throws SQLException, JSONException {
        String sql;
        System.out.println("sqlOperator中isOrdered="+isOrdered);
        if(isOrdered.equals("true")){
            System.out.println("按大棚编号排序");
            sql="select * from warning_file order by greenhouse_id";
        }
        else{
            System.out.println("按预警信息编号排序");
            sql="select * from warning_file";
        }

        ArrayList jsonList=new ArrayList();
        ArrayList jsonName=new ArrayList();
        try {
            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int fieldCount = rsmd.getColumnCount();
            while (rs.next()) {
                Map map = new HashMap();
                for (int i = 0; i < fieldCount; i++) {
                    map.put(rsmd.getColumnName(i + 1), rs.getString(rsmd.getColumnName(i + 1)));
                }
                jsonList.add(map);
            }
            rs.close();
            //加表头信息
            for(int i=0;i<rsmd.getColumnCount();i++){
                String columLabel= rsmd.getColumnLabel(i+1);
                jsonName.add(columLabel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[queryRecord]查询数据库出现错误：" + sql);
        }
        JSONObject json=new JSONObject();
        json.put("aaFieldName",jsonName);
        json.put("aaData",jsonList);
        return json;
    }

    public Boolean addWarningRecord(String warningRecord,String greenhouseId) throws SQLException, JSONException {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String sql="insert into warning_file(warning_record,greenhouse_id,greenhouse_name,warning_datetime)";
        sql=sql+" values('"+warningRecord+"'";
        sql=sql+" ,'"+greenhouseId+"'";
        sql=sql+" ,'农业大棚"+greenhouseId+"'";
        sql=sql+" ,'"+ dateTime.format(formatter) +"')";
        try {
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[queryRecord]查询数据库出现错误：" + sql);
            return false;
        }
        return true;
    }

    public Boolean deleteWarningRecord(String warningId) throws SQLException, JSONException {
        String sql="delete from warning_file where warning_id="+warningId;

        try {
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[queryRecord]查询数据库出现错误：" + sql);
            return false;
        }
        return true;
    }

    public Boolean modifyWarningRecord(String warningRecord,String greenhouseId,int warningId) throws SQLException, JSONException {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String sql="update warning_file set warning_record='"+warningRecord+"',greenhouse_id='"+greenhouseId+"',greenhouse_name='农业大棚"+greenhouseId+"',warning_datetime='"+dateTime.format(formatter)+"'";
        sql+=" where warning_id="+warningId+";";
        try {
            statement.executeUpdate(sql);
            System.out.println("成功执行sql语句："+sql);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[queryRecord]查询数据库出现错误：" + sql);
            return false;
        }
        return true;
    }

    public JSONObject statisticWarningTable() throws SQLException, JSONException {
        String sql;
        sql="select greenhouse_id,count(*) as warning_num from warning_file group by greenhouse_id;";

        ArrayList jsonList=new ArrayList();
        try {
            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int fieldCount = rsmd.getColumnCount();
            while (rs.next()) {
                Map map = new HashMap();
                for (int i = 0; i < fieldCount; i++) {
                    map.put(rsmd.getColumnName(i + 1), rs.getString(rsmd.getColumnName(i + 1)));
                }
                jsonList.add(map);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[queryRecord]查询数据库出现错误：" + sql);
        }
        JSONObject json=new JSONObject();
        json.put("aaData",jsonList);
        json.put("ok",200);
        return json;
    }

    public JSONObject queryWarningRecord(String warningRecord,String greenhouseId) throws SQLException, JSONException {
        String sql="select * from warning_file where warning_record like '"+warningRecord+"' or greenhouse_id='"+greenhouseId+"';";
        ArrayList jsonList=new ArrayList();
        System.out.println("执行的sql语句为："+sql);

        try {
            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int fieldCount = rsmd.getColumnCount();
            while (rs.next()) {
                Map map = new HashMap();
                for (int i = 0; i < fieldCount; i++) {
                    map.put(rsmd.getColumnName(i + 1), rs.getString(rsmd.getColumnName(i + 1)));
                }
                jsonList.add(map);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[queryRecord]查询数据库出现错误：" + sql);
        }

        JSONObject json=new JSONObject();
        json.put("ok",200);
        json.put("aaData",jsonList);
        return json;
    }
}
