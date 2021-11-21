package login.Servlet;

import login.DbOperator.sendCode;
import login.DbOperator.sqlOperator;
import login.export.JsonToFile;
import org.apache.commons.mail.EmailException;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
<<<<<<< HEAD
import java.util.HashMap;
=======
<<<<<<< HEAD
import java.util.HashMap;
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> e7cd7b6bd24ce9294431a1282b9c7ac8c306c4d2
>>>>>>> 57168512e5fb467f357fee5b0e0426108031416d

public class ServletAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        System.out.println("执行doGet!");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        System.out.println("执行doPost!");

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("执行service!");
        req.setCharacterEncoding("UTF-8");
        // 返回给前端的json
        JSONObject jsonObject=new JSONObject();
        String action=req.getParameter("Action");
        sqlOperator sqlOp=new sqlOperator();
        switch (action){
<<<<<<< HEAD

=======
<<<<<<< HEAD
            // 此增加功能只能进行用户表的增加
            case "addUserInfo":{
                String account=req.getParameter("account");
                String mail=req.getParameter("mail");
                String password=req.getParameter("password");
                String userLevel=req.getParameter("userLevel");
                String isWorker=req.getParameter("isWorker");
                HashMap map=new HashMap();
                map.put("account",account);
                map.put("mail",mail);
                map.put("password",password);
                map.put("userLevel",userLevel);
                map.put("isWorker",isWorker);
                try {
                    sqlOp.AddRecord(map);
                    jsonObject.put("ok",200);
                } catch (SQLException | JSONException e) {
                    e.printStackTrace();
                }
                break;
            }

            // 此delete可以对不同的数据表进行适配
            case "deleteInfo":{
                String id=req.getParameter("id");
                String dbName=req.getParameter("tableName");
                System.out.println("ID:"+id+"  Dbname:"+dbName);
                try {
                    sqlOp.DeleteRecord(id,dbName);
                    jsonObject.put("ok",200);
                } catch (SQLException | JSONException e) {
                    e.printStackTrace();
                }
                break;
            }

            case "modifyUserInfo":{
                String id=req.getParameter("id");
                String account=req.getParameter("account");
                String mail=req.getParameter("mail");
                String userLevel=req.getParameter("userLevel");
                String userName=req.getParameter("userName");
                String userGender=req.getParameter("userGender");
                String isWorker=req.getParameter("isWorker");
                HashMap map=new HashMap();
                map.put("id",id);
                map.put("account",account);
                map.put("mail",mail);
                map.put("userLevel",Integer.parseInt(userLevel));
                map.put("userName",userName);
                map.put("userGender",userGender);
                map.put("isWorker",isWorker);
                try {
                    sqlOp.ModifyUserRecord(map);
                    jsonObject.put("ok",200);
                } catch (SQLException | JSONException e) {
                    e.printStackTrace();
                }
                break;
            }

            case "statisticsUserInfo":{
                try {
                    jsonObject=sqlOp.StatisticsUserRecord();
                    jsonObject.put("ok",200);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            }
>>>>>>> 57168512e5fb467f357fee5b0e0426108031416d
            // 获取用户信息，用于构成DataTable
            case "getUserRecord":{
                String sort=req.getParameter("sort");
                System.out.println(sort);
                try {
                    jsonObject=sqlOp.getUserRecord(sort);
<<<<<<< HEAD
=======
=======
            case "getRecord":{
                try {
                    jsonObject=sqlOp.getRecord();
>>>>>>> e7cd7b6bd24ce9294431a1282b9c7ac8c306c4d2
>>>>>>> 57168512e5fb467f357fee5b0e0426108031416d
                } catch (SQLException | JSONException e) {
                    System.out.println("获取数据失败!");
                    e.printStackTrace();
                }
                break;
            }
            // 登录
            case "login":{
                String account=req.getParameter("account");
                String password=req.getParameter("password");
                try {
                    int level= sqlOp.isExist(account,password);
                    if(level>=0){
                        jsonObject.put("level",level);
                        jsonObject.put("ok",200);
                        jsonObject.put("account",account);
                    }else{
                        jsonObject.put("ok",404);
                    }
//                    responseBack(req,resp,jsonObject);
                } catch (SQLException | JSONException e) {
                    System.out.println("数据库查询出现异常！");
                    e.printStackTrace();
                }
                break;
            }

            // 注册
            case "register":{
                String registerAccount=req.getParameter("account");
                String password=req.getParameter("password");
                String mail=req.getParameter("mail");
//                String code=req.getParameter("code");
                try {
                    if(sqlOp.Register(registerAccount,password,mail)){
                        jsonObject.put("ok",200);
                    }else{
                        jsonObject.put("ok",400);
                    }
//                    responseBack(req,resp,jsonObject);
                } catch (SQLException | JSONException e) {
                    e.printStackTrace();
                }
                break;
            }

            // 判断注册信息是否合法
            case "isExisted":{
                String mail=req.getParameter("mail");
                String account=req.getParameter("account");
                System.out.println("mail:"+mail+",account:"+account);
                String content,key;
                if(mail == null){
                    content=account;
                    key="account";
                }else{
                    content=mail;
                    key="mail";
                }
                System.out.println("content:"+content+",key:"+key);
                try {
                    int status=sqlOp.isExisted(content,key);
                    if(status==-1){
                        jsonObject.put("ok",500);
                    }else if(status==-2) {
                        jsonObject.put("ok",400);
                    }else if(status==0){
                        jsonObject.put("ok",200);
                    }
//                    responseBack(req,resp,jsonObject);
                } catch (SQLException | JSONException e) {
                    e.printStackTrace();
                }
                break;
            }

            // 发送验证码
            case "sendCode":{
                String mail=req.getParameter("mail");
                sendCode sendC=new sendCode(mail);
                try {
                    if(sendC.Send()){
                        jsonObject.put("ok",200);
                        System.out.println("验证码:"+sendC.getCode());
                        jsonObject.put("code",sendC.getCode());
                    }else{
                        jsonObject.put("ok",404);
                    }
//                    responseBack(req,resp,jsonObject);
                } catch (EmailException | JSONException e) {
                    e.printStackTrace();
                }
            }

            // 导出文件
            case "exportFile":{
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 57168512e5fb467f357fee5b0e0426108031416d
                // 该json用于保存导出的数据
                JSONObject json=new JSONObject();
                System.out.println("正在导出");
                // 获取需要操作的表名
                String name=req.getParameter("tableName");
                System.out.println("将要操作的表:"+name);
                try {
                    // 获取到查询结果的json
                    json=sqlOp.getRecord(name);

                    // JSON导出文件的工具类对象
                    JsonToFile jsonToFile=new JsonToFile();
                    jsonObject=jsonToFile.setJsonTOTxt(json);

                    // 转excel
                    jsonToFile.setJsonToExcel(json,jsonObject);
                    System.out.println(jsonObject);
                } catch (SQLException | JSONException e) {
                    e.printStackTrace();
                }
                break;
            }

            // 获取设备信息
<<<<<<< HEAD
            case "getDeviceRecord":{
                try {
                    jsonObject=sqlOp.getDeviceRecord();
                } catch (JSONException | SQLException e) {
                    e.printStackTrace();
                }
                break;
            }

            case "addDeviceInfo":{
                HashMap map=new HashMap();
                map.put("deviceNum",req.getParameter("deviceNum"));
                map.put("deviceName",req.getParameter("deviceName"));
                map.put("deviceCompany",req.getParameter("deviceCompany"));
                map.put("devicePrice",req.getParameter("devicePrice"));
                map.put("deviceLocation",req.getParameter("deviceLocation"));
                map.put("deviceStatus",req.getParameter("deviceStatus"));
                try {
                    sqlOp.addDeviceInfo(map);
                    jsonObject.put("ok",200);
                } catch (SQLException | JSONException e) {
                    e.printStackTrace();
                }
                break;
            }

            case "deleteInfo":{
                String tablename=req.getParameter("tableName");
                String id=req.getParameter("id");
                try {
                    sqlOp.DeleteRecord(id,tablename);
                    jsonObject.put("ok",200);
                } catch (SQLException | JSONException e) {
                    e.printStackTrace();
                }
                break;
            }

            case "modifyDeviceInfo":{
                HashMap map=new HashMap();
                map.put("deviceNum",req.getParameter("deviceNum"));
                map.put("deviceLocation",req.getParameter("deviceLocation"));
                map.put("deviceStatus",req.getParameter("deviceStatus"));
                map.put("deviceId",req.getParameter("id"));
                try {
                    sqlOp.ModifyDeviceInfo(map);
                    jsonObject.put("ok",200);
                } catch (SQLException | JSONException e) {
                    e.printStackTrace();
                }

                break;
            }

            case "StatisticsDeviceInfo":{
                try {
                    jsonObject=sqlOp.StatisticsDeviceInfo();
                    jsonObject.put("ok",200);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
=======
=======
                // 从前端获取json

                String json =req.getParameter("json");
                System.out.println("从前端获取到JSON:"+json);
                JsonToFile jsonToFile=new JsonToFile();
//                jsonObject=jsonToFile.setJsonTOTxt();
            }

>>>>>>> e7cd7b6bd24ce9294431a1282b9c7ac8c306c4d2
            case "getDeviceRecord":{
                try {
                    jsonObject=sqlOp.getDeviceRecord();
                } catch (JSONException | SQLException e) {
                    e.printStackTrace();
                }
<<<<<<< HEAD
                break;
=======
>>>>>>> e7cd7b6bd24ce9294431a1282b9c7ac8c306c4d2
>>>>>>> 57168512e5fb467f357fee5b0e0426108031416d
            }

            default:{
                break;
            }
        }
        try {
            // 关闭数据库链接
            sqlOp.CloseCn();
            responseBack(req,resp,jsonObject);
        } catch (JSONException | SQLException e) {
            System.out.println("回调失败！|| 数据库链接关闭失败!");
            e.printStackTrace();
        }
    }

    private void responseBack(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws JSONException {
        boolean isAjax=true;if (request.getHeader("x-requested-with") == null || request.getHeader("x-requested-with").equals("com.tencent.mm")){isAjax=false;}	//判断是异步请求还是同步请求，腾讯的特殊
        if(isAjax){
            response.setContentType("application/json; charset=UTF-8");
            try {
                response.getWriter().print(json);
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("异步方式");
//            String action=json.getString("action");
//            String errorNo="0";
//            String errorMsg="ok";
//            String url = result.jsp?action="+action+"&result_code="+errorNo+ "&result_msg=" + errorMsg;
//            try {
//                response.sendRedirect(url);
//            } catch (IOException e) {
//                e.printStackTrace();
            }
        }

<<<<<<< HEAD
=======

>>>>>>> 57168512e5fb467f357fee5b0e0426108031416d
}
