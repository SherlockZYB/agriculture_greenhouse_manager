package login.DbOperator;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
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
                resultSet.close();
                return -2;
            }else{
                // 0表示用户账户或邮箱可以申请注册，合法
                resultSet.close();
                return 0;
            }
        }
    }

    public JSONObject getRecord() throws SQLException, JSONException {
        String sql="select * from accountInfo";
        ResultSet resultSet=this.statement.executeQuery(sql);
        System.out.println("执行SQL："+sql);
        List jsonList=new ArrayList();
        while (resultSet.next()){
            Map map=new HashMap();
            map.put("id",resultSet.getInt("userId"));
            map.put("account",resultSet.getString("account"));
            map.put("mail",resultSet.getString("mail"));
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
        System.out.println("执行SQL："+sql);
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

    // 断开链接。
    public void CloseCn() throws SQLException {
        this.statement.close();
        System.out.println("数据库链接关闭！");
    }
}
