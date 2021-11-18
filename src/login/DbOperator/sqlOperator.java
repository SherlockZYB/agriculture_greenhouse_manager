package login.DbOperator;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
<<<<<<< HEAD
import java.sql.ResultSetMetaData;
=======
<<<<<<< HEAD
import java.sql.ResultSetMetaData;
=======
>>>>>>> e7cd7b6bd24ce9294431a1282b9c7ac8c306c4d2
>>>>>>> 57168512e5fb467f357fee5b0e0426108031416d
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

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
<<<<<<< HEAD
        System.out.println("[sqlOperator/isExist()]将要执行sql:"+sql);
=======
        System.out.println("sql:"+sql);
>>>>>>> e7cd7b6bd24ce9294431a1282b9c7ac8c306c4d2
>>>>>>> 57168512e5fb467f357fee5b0e0426108031416d
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
<<<<<<< HEAD
        System.out.println("[sqlOperator/Register()]将要执行sql:"+sql);
=======
        System.out.println("sql:"+sql);
>>>>>>> e7cd7b6bd24ce9294431a1282b9c7ac8c306c4d2
>>>>>>> 57168512e5fb467f357fee5b0e0426108031416d
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
<<<<<<< HEAD
        System.out.println("[sqlOperator/isExisted()]将要执行SQL:"+sql);
=======
        System.out.println("SQL:"+sql);
>>>>>>> e7cd7b6bd24ce9294431a1282b9c7ac8c306c4d2
>>>>>>> 57168512e5fb467f357fee5b0e0426108031416d
        ResultSet resultSet=statement.executeQuery(sql);
        if(resultSet.next()){
            // -1 说明用户账户或邮箱已经存在
            return -1;
        }else {
            System.out.println("SQL:"+sql1);
            resultSet=statement.executeQuery(sql1);
            if(resultSet.next()){
                // -2 说明用户账户或邮箱已经被申请注册，但尚未完成审核
                resultSet.close();
                return -2;
            }else{
                // 0表示用户账户或邮箱可以申请注册，合法
                resultSet.close();
                return 0;
            }
        }
    }

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 57168512e5fb467f357fee5b0e0426108031416d
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
<<<<<<< HEAD
=======
=======
    public JSONObject getRecord() throws SQLException, JSONException {
        String sql="select * from accountInfo";
        ResultSet resultSet=this.statement.executeQuery(sql);
        System.out.println("执行SQL："+sql);
>>>>>>> e7cd7b6bd24ce9294431a1282b9c7ac8c306c4d2
>>>>>>> 57168512e5fb467f357fee5b0e0426108031416d
        List jsonList=new ArrayList();
        while (resultSet.next()){
            Map map=new HashMap();
            map.put("id",resultSet.getInt("userId"));
            map.put("account",resultSet.getString("account"));
            map.put("mail",resultSet.getString("mail"));
<<<<<<< HEAD
=======
<<<<<<< HEAD
//            map.put("password",resultSet.getString("PASSWORD"));
=======
>>>>>>> e7cd7b6bd24ce9294431a1282b9c7ac8c306c4d2
>>>>>>> 57168512e5fb467f357fee5b0e0426108031416d
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
<<<<<<< HEAD
        System.out.println("[sqlOperator/getDeviceRecord()]将要执行SQL："+sql);
=======
<<<<<<< HEAD
        System.out.println("[sqlOperator/getDeviceRecord()]将要执行SQL："+sql);
=======
        System.out.println("执行SQL："+sql);
>>>>>>> e7cd7b6bd24ce9294431a1282b9c7ac8c306c4d2
>>>>>>> 57168512e5fb467f357fee5b0e0426108031416d
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

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 57168512e5fb467f357fee5b0e0426108031416d
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

<<<<<<< HEAD

    public void addDeviceInfo(HashMap map) throws SQLException {
        String date=new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date());
        System.out.println(date);
        String sql="insert into deviceinfo(deviceNum,deviceName,deviceCompany,devicePrice,deviceLocation,deviceStatus,deviceModifyTime) values('"+map.get("deviceNum")+"','"+map.get("deviceName")+"','"+map.get("deviceCompany")+"',"+Integer.parseInt((String) map.get("devicePrice"))+",'"+map.get("deviceLocation")+"','"+map.get("deviceStatus")+"','"+date+"')";
        System.out.println(sql);
        this.statement.executeUpdate(sql);
    }

    public void ModifyDeviceInfo(HashMap map) throws SQLException {
        String sql="update deviceInfo set deviceNum='"+map.get("deviceNum")+"',deviceLocation='"+map.get("deviceLocation")+"',deviceStatus='"+map.get("deviceStatus")+"'";
        sql+=" where deviceId="+map.get("deviceId");
=======
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
>>>>>>> 57168512e5fb467f357fee5b0e0426108031416d
        System.out.println("[sqlOperator/ModifyRecord]：将要执行SQL:"+sql);
        this.statement.executeUpdate(sql);
    }

<<<<<<< HEAD
    public JSONObject StatisticsDeviceInfo() throws SQLException, JSONException {
        String sql="select count(*) as sumNum from deviceInfo";
        String sql1="select deviceName,count(*) as deviceSum from deviceInfo group by deviceName";
        String sql2="select deviceLocation,count(*) as deviceNum from deviceInfo group by deviceLocation";

=======
    // 统计用户信息
    public JSONObject StatisticsUserRecord() throws SQLException, JSONException {
        String sql="select count(*) as sumNum from accountInfo";
        String sql1="select userLevel,count(*) as levelNum  from accountInfo group by userLevel";
        String sql2="select count(*) as applyNum from applyInfo";
>>>>>>> 57168512e5fb467f357fee5b0e0426108031416d

        JSONObject json=new JSONObject();

        System.out.println("[sqlOperator/Static]将要执行:"+sql);
        ResultSet resultSet=this.statement.executeQuery(sql);
        resultSet.next();
        json.put("sumNum",resultSet.getInt("sumNum"));
        System.out.println(resultSet.getInt("sumNum"));

        System.out.println("[sqlOperator/Static]将要执行:"+sql1);
        resultSet=this.statement.executeQuery(sql1);
<<<<<<< HEAD
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
=======
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
>>>>>>> 57168512e5fb467f357fee5b0e0426108031416d
            }

        }

        System.out.println("[sqlOperator/Static]将要执行:"+sql2);
<<<<<<< HEAD
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


=======
        resultSet=this.statement.executeQuery(sql2);
        resultSet.next();
        json.put("applyNum",resultSet.getString("applyNum"));
        System.out.println(resultSet.getString("applyNum"));
        return json;
    }

=======
>>>>>>> e7cd7b6bd24ce9294431a1282b9c7ac8c306c4d2
>>>>>>> 57168512e5fb467f357fee5b0e0426108031416d
    // 断开链接。
    public void CloseCn() throws SQLException {
        this.statement.close();
        System.out.println("数据库链接关闭！");
    }
}
