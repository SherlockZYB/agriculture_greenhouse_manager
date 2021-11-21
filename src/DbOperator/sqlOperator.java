package DbOperator;

<<<<<<< HEAD
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
=======
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
>>>>>>> 117581ba5fc9261dccc0ee1f9cbdde35e639c486

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
<<<<<<< HEAD
        System.out.println("[sqlOperator/isExist()]将要执行sql:"+sql);
=======
        System.out.println("sql:"+sql);
>>>>>>> 117581ba5fc9261dccc0ee1f9cbdde35e639c486
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
<<<<<<< HEAD
        System.out.println("[sqlOperator/Register()]将要执行sql:"+sql);
=======
        System.out.println("sql:"+sql);
>>>>>>> 117581ba5fc9261dccc0ee1f9cbdde35e639c486
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
<<<<<<< HEAD
        System.out.println("[sqlOperator/isExisted()]将要执行SQL:"+sql);
=======
        System.out.println("SQL:"+sql);
>>>>>>> 117581ba5fc9261dccc0ee1f9cbdde35e639c486
        ResultSet resultSet=statement.executeQuery(sql);
        if(resultSet.next()){
            // -1 说明用户账户或邮箱已经存在
            return -1;
        }else {
            System.out.println("SQL:"+sql1);
            resultSet=statement.executeQuery(sql1);
            if(resultSet.next()){
                // -2 说明用户账户或邮箱已经被申请注册，但尚未完成审核
<<<<<<< HEAD
                resultSet.close();
                return -2;
            }else{
                // 0表示用户账户或邮箱可以申请注册，合法
                resultSet.close();
=======
                return -2;
            }else{
                // 0表示用户账户或邮箱可以申请注册，合法
>>>>>>> 117581ba5fc9261dccc0ee1f9cbdde35e639c486
                return 0;
            }
        }
    }

<<<<<<< HEAD
    public JSONObject getUserRecord(String sortWay) throws SQLException, JSONException {
        String sql="select * from accountInfo";
        // 定义升序或降序
        if(sortWay.equals("up")){
            sql+=" order by account";
        }else if(sortWay.equals("down")){
            sql+=" order by account desc";
        }

        ResultSet resultSet=this.statement.executeQuery(sql);
        System.out.println("[sqlOperator/getUserRecord()]执行SQL："+sql);
        List jsonList=new ArrayList();
        while (resultSet.next()){
            Map map=new HashMap();
            map.put("id",resultSet.getInt("userId"));
            map.put("account",resultSet.getString("account"));
            map.put("mail",resultSet.getString("mail"));
//            map.put("password",resultSet.getString("PASSWORD"));
            map.put("userLevel",resultSet.getString("userLevel"));
            map.put("userName",resultSet.getString("userName"));
            map.put("userGender",resultSet.getString("userGender"));
            map.put("signUpDate",resultSet.getDate("signUpDate"));
            map.put("lastLoginDate",resultSet.getDate("lastLoginDate"));
            map.put("lastModifyDate",resultSet.getDate("lastModifyDate"));
            map.put("isWorker",resultSet.getInt("isWorker"));
            jsonList.add(map);
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("aaData",jsonList);
        return jsonObject;
    }

    public JSONObject getDeviceRecord() throws SQLException, JSONException {
        String sql="select * from deviceInfo";
        ResultSet resultSet=this.statement.executeQuery(sql);
        System.out.println("[sqlOperator/getDeviceRecord()]将要执行SQL："+sql);
        List jsonList=new ArrayList();
        while (resultSet.next()){
            Map map=new HashMap();
            map.put("deviceId",resultSet.getInt("deviceId"));
            map.put("deviceNum",resultSet.getString("deviceNum"));
            map.put("deviceName",resultSet.getString("deviceName"));
            map.put("devicePrice",resultSet.getString("devicePrice"));
            map.put("deviceCompany",resultSet.getString("deviceCompany"));
            map.put("deviceLocation",resultSet.getString("deviceLocation"));
            map.put("deviceStatus",resultSet.getString("deviceStatus"));
            map.put("deviceModifyTime",resultSet.getTimestamp("deviceModifyTime"));
            map.put("lastLocation",resultSet.getString("lastLocation"));
            jsonList.add(map);
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("aaData",jsonList);
        return jsonObject;
    }

    // 该函数用于普适性的获取记录信息，主要用于导出，查询，等情况
    public JSONObject getRecord(String name) throws SQLException, JSONException {
        String sql="select * from "+name;
        System.out.println("[sqlOperator/getRecord()]将要执行SQL："+sql);
        ResultSet resultSet=this.statement.executeQuery(sql);
        // 获取查询到数据的列信息
        ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
        int fieldCount=resultSetMetaData.getColumnCount();

        // 获取数据库中的内容
        List jsonList=new ArrayList();
        while (resultSet.next()){
            Map map=new HashMap();
            for(int i=0;i<fieldCount;i++){
                map.put(resultSetMetaData.getColumnName(i+1),resultSet.getString(resultSetMetaData.getColumnName(i+1)));
            }
            jsonList.add(map);
        }

        // 获取数据库中的属性，以作为表头
        List jsonName=new ArrayList();
        for(int i=0;i<fieldCount;i++){
            jsonName.add(resultSetMetaData.getColumnName(i+1));
        }
        JSONObject json=new JSONObject();
        json.put("record",jsonList);
        json.put("title",jsonName);
        resultSet.close();
        return json;
    }

    // 该函数可以用于记录删除，传入参数：表名；id
    public void DeleteRecord(String id,String tableName) throws SQLException {
        String sql="delete from "+tableName+" where ";
        // 获取删除的sql
        switch (tableName){
            case "accountInfo":{
                sql+="userId="+id;
                break;
            }
            case "deviceInfo":{
                sql+="deviceId="+id;
                break;
            }
        }
        System.out.println("[sqlOperator/DeleteRecord]将要执行SQL:"+sql);
        this.statement.executeUpdate(sql);

    }

    public void AddRecord(HashMap map) throws SQLException {

        // 指定属性插入
        String date=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String sql="insert into accountInfo(account,mail,password,userLevel,signUpDate,lastModifyDate,isWorker) values('"+map.get("account")+"','"+map.get("mail")+"','"+map.get("password")+"','"+map.get("userLevel")+"','"+date+"','"+date+"',"+map.get("isWorker")+")";
        System.out.println("[sqlOperator/AddRecord]将要执行:"+sql);
        this.statement.executeUpdate(sql);
    }

    // 修改信息
    public void ModifyUserRecord(HashMap map) throws SQLException {
        String sql="update accountInfo set account='"+map.get("account")+"',mail='"+map.get("mail")+"',userLevel='"+map.get("userLevel")+"',userName='"+map.get("userName")+"',userGender='"+map.get("userGender")+"',isWorker='"+map.get("isWorker")+"'";
        sql+=" where userId="+map.get("id");
        System.out.println("[sqlOperator/ModifyRecord]：将要执行SQL:"+sql);
        this.statement.executeUpdate(sql);
    }

    // 统计用户信息
    public JSONObject StatisticsUserRecord() throws SQLException, JSONException {
        String sql="select count(*) as sumNum from accountInfo";
        String sql1="select userLevel,count(*) as levelNum  from accountInfo group by userLevel";
        String sql2="select count(*) as applyNum from applyInfo";

        JSONObject json=new JSONObject();

        System.out.println("[sqlOperator/Static]将要执行:"+sql);
        ResultSet resultSet=this.statement.executeQuery(sql);
        resultSet.next();
        json.put("sumNum",resultSet.getInt("sumNum"));
        System.out.println(resultSet.getInt("sumNum"));

        System.out.println("[sqlOperator/Static]将要执行:"+sql1);
        resultSet=this.statement.executeQuery(sql1);
        json.put("manager",0);
        json.put("generalManager",0);
        json.put("generalUser",0);
        while (resultSet.next()){
            if(resultSet.getInt("userLevel")==0){
                json.put("manager",resultSet.getInt("levelNum"));
            }else if(resultSet.getInt("userLevel")==1){
                json.put("generalManager",resultSet.getInt("levelNum"));
            }else{
                json.put("generalUser",resultSet.getInt("levelNum"));
            }

        }

        System.out.println("[sqlOperator/Static]将要执行:"+sql2);
        resultSet=this.statement.executeQuery(sql2);
        resultSet.next();
        json.put("applyNum",resultSet.getString("applyNum"));
        System.out.println(resultSet.getString("applyNum"));
        return json;
    }

    // 设备管理模块开始
    public void addDeviceInfo(HashMap map) throws SQLException {
        String date=new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date());
        System.out.println(date);
        String sql="insert into deviceinfo(deviceNum,deviceName,deviceCompany,devicePrice,deviceLocation,deviceStatus,deviceModifyTime) values('"+map.get("deviceNum")+"','"+map.get("deviceName")+"','"+map.get("deviceCompany")+"',"+Double.parseDouble((String) map.get("devicePrice"))+",'"+map.get("deviceLocation")+"','"+map.get("deviceStatus")+"','"+date+"')";
        System.out.println(sql);
        this.statement.executeUpdate(sql);
    }

    public void ModifyDeviceInfo(HashMap map) throws SQLException {
        String sql="update deviceInfo set deviceNum='"+map.get("deviceNum")+"',deviceLocation='"+map.get("deviceLocation")+"',deviceStatus='"+map.get("deviceStatus")+"'";
        sql+=" where deviceId="+map.get("deviceId");
        System.out.println("[sqlOperator/ModifyRecord]：将要执行SQL:"+sql);
        this.statement.executeUpdate(sql);
    }

    public JSONObject StatisticsDeviceInfo() throws SQLException, JSONException {
        String sql="select count(*) as sumNum from deviceInfo";
        String sql1="select deviceName,count(*) as deviceSum from deviceInfo group by deviceName";
        String sql2="select deviceLocation,count(*) as deviceNum from deviceInfo group by deviceLocation";


        JSONObject json=new JSONObject();

        System.out.println("[sqlOperator/Static]将要执行:"+sql);
        ResultSet resultSet=this.statement.executeQuery(sql);
        resultSet.next();
        json.put("sumNum",resultSet.getInt("sumNum"));
        System.out.println(resultSet.getInt("sumNum"));

        System.out.println("[sqlOperator/Static]将要执行:"+sql1);
        resultSet=this.statement.executeQuery(sql1);
        json.put("温度计",0);
        json.put("湿度计",0);
        json.put("计时器",0);
        while (resultSet.next()){
            if(resultSet.getString("deviceName").equals("温度计")){
                json.put("温度计",resultSet.getInt("deviceSum"));
            }else if(resultSet.getString("deviceName").equals("湿度计")){
                json.put("湿度计",resultSet.getInt("deviceSum"));
            }else{
                json.put("计时器",resultSet.getInt("deviceSum"));
            }

        }

        System.out.println("[sqlOperator/Static]将要执行:"+sql2);
        json.put("一号大棚",0);
        json.put("二号大棚",0);
        resultSet=this.statement.executeQuery(sql2);
        while (resultSet.next()){
            if(resultSet.getString("deviceLocation").equals("1号大棚")){
                json.put("一号大棚",resultSet.getInt("deviceNum"));
            }else if(resultSet.getString("deviceLocation").equals("2号大棚")){
                json.put("二号大棚",resultSet.getInt("deviceNum"));
            }
        }
        return json;
    }

    // 设备管理模块结束

    // 断开链接。
    public void CloseCn() throws SQLException {
        this.statement.close();
        System.out.println("数据库链接关闭！");
    }
=======
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

    public JSONObject showSalaryTable(String isOrdered) throws SQLException, JSONException {
        String sql;
        System.out.println("sqlOperator中isOrdered="+isOrdered);
        if(isOrdered.equals("true")){
            System.out.println("按工资降序");
            sql="select * from salary_file order by salary_number desc ";
        }
        else{
            sql="select * from salary_file";
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

    public Boolean addSalaryRecord(String employee_id,String employee_name,String employee_duty,String salary_number,String salary_remark,String salary_month) throws SQLException, JSONException {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String sql="insert into salary_file(employee_id,employee_name,employee_duty,salary_number,salary_remark,salary_month,salary_datetime)";
        sql=sql+" values('"+employee_id+"'";
        sql=sql+" ,'"+employee_name+"'";
        sql=sql+" ,'"+employee_duty+"'";
        sql=sql+" ,'"+salary_number+"'";
        sql=sql+" ,'"+salary_remark+"'";
        sql=sql+" ,'"+salary_month+"'";
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

    public Boolean deleteSalaryRecord(String salary_id) throws SQLException, JSONException {
        String sql="delete from salary_file where salary_id="+salary_id;

        try {
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[queryRecord]查询数据库出现错误：" + sql);
            return false;
        }
        return true;
    }

    public Boolean modifySalaryRecord(String salary_id,String employee_id,String employee_name,String employee_duty,String salary_number,String salary_remark,String salary_month) throws SQLException, JSONException {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String sql="update salary_file set employee_id='"+employee_id+"',employee_name='"+employee_name+"',employee_duty='"+employee_duty+"',salary_number='"+salary_number+"',salary_remark='"+salary_remark+"',salary_month='"+salary_month+"',salary_datetime='"+dateTime.format(formatter)+"'";
        sql+=" where salary_id="+salary_id+";";
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

    public JSONObject querySalaryRecord(String employee_name,String salary_month) throws SQLException, JSONException {
        String sql="select * from salary_file where employee_name like '"+employee_name+"' or salary_month='"+salary_month+"';";
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

    public JSONObject statisticSalaryTable() throws SQLException, JSONException {
        String sql;
        sql="select employee_name,salary_number as salary_num from salary_file group by employee_id;";

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

>>>>>>> 117581ba5fc9261dccc0ee1f9cbdde35e639c486
}
