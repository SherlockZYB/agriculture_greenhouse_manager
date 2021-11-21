package Servlet;

import DbOperator.sendCode;
import DbOperator.sqlOperator;
<<<<<<< HEAD
import login.export.JsonToFile;
import org.apache.commons.mail.EmailException;
import org.json.JSONException;
import org.json.JSONObject;
=======
import org.apache.commons.mail.EmailException;
import org.json.JSONException;
import org.json.JSONObject;
import warningModule.file.MyExcel;
>>>>>>> 117581ba5fc9261dccc0ee1f9cbdde35e639c486

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
<<<<<<< HEAD
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
=======
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
>>>>>>> 117581ba5fc9261dccc0ee1f9cbdde35e639c486

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
            // 获取用户信息，用于构成DataTable
            case "getUserRecord":{
                String sort=req.getParameter("sort");
                System.out.println(sort);
                try {
                    jsonObject=sqlOp.getUserRecord(sort);
                } catch (SQLException | JSONException e) {
                    System.out.println("获取数据失败!");
                    e.printStackTrace();
                }
                break;
            }
            // 登录
=======
>>>>>>> 117581ba5fc9261dccc0ee1f9cbdde35e639c486
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
<<<<<<< HEAD
//                    responseBack(req,resp,jsonObject);
=======
                    responseBack(req,resp,jsonObject);
>>>>>>> 117581ba5fc9261dccc0ee1f9cbdde35e639c486
                } catch (SQLException | JSONException e) {
                    System.out.println("数据库查询出现异常！");
                    e.printStackTrace();
                }
                break;
            }

<<<<<<< HEAD
            // 注册
=======
>>>>>>> 117581ba5fc9261dccc0ee1f9cbdde35e639c486
            case "register":{
                String registerAccount=req.getParameter("account");
                String password=req.getParameter("password");
                String mail=req.getParameter("mail");
<<<<<<< HEAD
//                String code=req.getParameter("code");
=======
                String code=req.getParameter("code");
>>>>>>> 117581ba5fc9261dccc0ee1f9cbdde35e639c486
                try {
                    if(sqlOp.Register(registerAccount,password,mail)){
                        jsonObject.put("ok",200);
                    }else{
                        jsonObject.put("ok",400);
                    }
<<<<<<< HEAD
//                    responseBack(req,resp,jsonObject);
                } catch (SQLException | JSONException e) {
                    e.printStackTrace();
                }
                break;
            }

            // 判断注册信息是否合法
=======
                    responseBack(req,resp,jsonObject);
                } catch (SQLException | JSONException e) {
                    e.printStackTrace();
                }

                break;
            }

>>>>>>> 117581ba5fc9261dccc0ee1f9cbdde35e639c486
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
<<<<<<< HEAD
//                    responseBack(req,resp,jsonObject);
=======
                    responseBack(req,resp,jsonObject);
>>>>>>> 117581ba5fc9261dccc0ee1f9cbdde35e639c486
                } catch (SQLException | JSONException e) {
                    e.printStackTrace();
                }
                break;
            }

<<<<<<< HEAD
            // 发送验证码
=======
>>>>>>> 117581ba5fc9261dccc0ee1f9cbdde35e639c486
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
<<<<<<< HEAD
//                    responseBack(req,resp,jsonObject);
=======
                    responseBack(req,resp,jsonObject);
>>>>>>> 117581ba5fc9261dccc0ee1f9cbdde35e639c486
                } catch (EmailException | JSONException e) {
                    e.printStackTrace();
                }
            }

<<<<<<< HEAD
            // 导出文件
            case "exportFile":{
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
=======
            case "showWarningTable":
                String isOrdered=req.getParameter("isOrdered");
                System.out.println("isOrdered="+isOrdered);
                try {
                    responseBack(req,resp,sqlOp.showWarningTable(isOrdered));
                } catch (JSONException | SQLException e) {
                    e.printStackTrace();
                }
                break;

            case "addWarningRecord":
                String warningRecord=req.getParameter("warning_record");
                String greenhouseId=req.getParameter("greenhouse_id");

                try {
                    if(sqlOp.addWarningRecord(warningRecord,greenhouseId)){
                        jsonObject.put("ok",200);
                    }else{
                        jsonObject.put("ok",400);
                    }
                    responseBack(req,resp,jsonObject);
>>>>>>> 117581ba5fc9261dccc0ee1f9cbdde35e639c486
                } catch (SQLException | JSONException e) {
                    e.printStackTrace();
                }
                break;
<<<<<<< HEAD
            }

            // 设备管理模块开始
            case "getDeviceRecord":{
                try {
                    jsonObject=sqlOp.getDeviceRecord();
=======

            case "deleteWarningRecord":
                String warningId=req.getParameter("id");

                try {
                    if(sqlOp.deleteWarningRecord(warningId)){
                        jsonObject.put("ok",200);
                    }else{
                        jsonObject.put("ok",400);
                    }
                    responseBack(req,resp,jsonObject);
                } catch (SQLException | JSONException e) {
                    e.printStackTrace();
                }
                break;

            case "modifyWarningRecord":
                String modifyWarningRecord=req.getParameter("warning_record");
                int modifyWarningId=Integer.parseInt(req.getParameter("warning_id"));
                String modifyGreenhouseId=req.getParameter("greenhouse_id");

                try {
                    if(sqlOp.modifyWarningRecord(modifyWarningRecord,modifyGreenhouseId,modifyWarningId)){
                        jsonObject.put("ok",200);
                        System.out.println("成功修改");
                    }else{
                        System.out.println("修改失败");
                        jsonObject.put("ok",400);
                    }
                    responseBack(req,resp,jsonObject);
                } catch (SQLException | JSONException e) {
                    e.printStackTrace();
                }
                break;

            case "exportWarningRecord":
                try {
                    JSONObject json=sqlOp.showWarningTable("false");
//                    getExportWarningRecordToFile(json);
                    getExportWarningRecordToExcel(json);
                    json.put("ok",200);
                    responseBack(req,resp,json);
                } catch (JSONException | SQLException e) {
                    e.printStackTrace();
                }
                break;

            case "statisticWarningRecord":
                try {
                    System.out.println("进入统计ServletAction");
                    responseBack(req,resp,sqlOp.statisticWarningTable());
>>>>>>> 117581ba5fc9261dccc0ee1f9cbdde35e639c486
                } catch (JSONException | SQLException e) {
                    e.printStackTrace();
                }
                break;
<<<<<<< HEAD
            }

            case "addDeviceInfo":{
                System.out.println("添加设备信息");
                HashMap map=new HashMap();
                map.put("deviceNum",req.getParameter("deviceNum"));
                map.put("deviceName",req.getParameter("deviceName"));
                map.put("deviceCompany",req.getParameter("deviceCompany"));
                map.put("devicePrice",req.getParameter("devicePrice"));
                map.put("deviceLocation",req.getParameter("deviceLocation"));
                map.put("deviceStatus",req.getParameter("deviceStatus"));
                System.out.println(map);
                try {
                    sqlOp.addDeviceInfo(map);
                    jsonObject.put("ok",200);
=======

            case "queryWarningRecord":
                warningRecord=req.getParameter("warning_record");
                greenhouseId=req.getParameter("greenhouse_id");

                try {
                    responseBack(req,resp,sqlOp.queryWarningRecord(warningRecord,greenhouseId));
>>>>>>> 117581ba5fc9261dccc0ee1f9cbdde35e639c486
                } catch (SQLException | JSONException e) {
                    e.printStackTrace();
                }
                break;
<<<<<<< HEAD
            }

            case "modifyDeviceInfo":{
                System.out.println("修改设备信息");
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
            }
            // 设备管理模块结束

=======

            case "showSalaryTable":
                isOrdered=req.getParameter("isOrdered");
                System.out.println("isOrdered="+isOrdered);
                try {
                    responseBack(req,resp,sqlOp.showSalaryTable(isOrdered));
                } catch (JSONException | SQLException e) {
                    e.printStackTrace();
                }
                break;

            case "addSalaryRecord":
                String employee_id=req.getParameter("employee_id");
                String employee_name=req.getParameter("employee_name");
                String employee_duty=req.getParameter("employee_duty");
                String salary_number=req.getParameter("salary_number");
                String salary_remark=req.getParameter("salary_remark");
                String salary_month=req.getParameter("salary_month");

                try {
                    if(sqlOp.addSalaryRecord(employee_id,employee_name,employee_duty,salary_number,salary_remark,salary_month)){
                        jsonObject.put("ok",200);
                    }else{
                        jsonObject.put("ok",400);
                    }
                    responseBack(req,resp,jsonObject);
                } catch (SQLException | JSONException e) {
                    e.printStackTrace();
                }
                break;

            case "deleteSalaryRecord":
                String salary_id=req.getParameter("salary_id");

                try {
                    if(sqlOp.deleteSalaryRecord(salary_id)){
                        jsonObject.put("ok",200);
                    }else{
                        jsonObject.put("ok",400);
                    }
                    responseBack(req,resp,jsonObject);
                } catch (SQLException | JSONException e) {
                    e.printStackTrace();
                }
                break;

            case "modifySalaryRecord":
                salary_id=req.getParameter("salary_id");
                employee_id=req.getParameter("employee_id");
                employee_name=req.getParameter("employee_name");
                employee_duty=req.getParameter("employee_duty");
                salary_number=req.getParameter("salary_number");
                salary_remark=req.getParameter("salary_remark");
                salary_month=req.getParameter("salary_month");

                try {
                    if(sqlOp.modifySalaryRecord(salary_id,employee_id,employee_name,employee_duty,salary_number,salary_remark,salary_month)){
                        jsonObject.put("ok",200);
                        System.out.println("成功修改");
                    }else{
                        System.out.println("修改失败");
                        jsonObject.put("ok",400);
                    }
                    responseBack(req,resp,jsonObject);
                } catch (SQLException | JSONException e) {
                    e.printStackTrace();
                }
                break;

            case "querySalaryRecord":
                String query_employee_name=req.getParameter("employee_name");
                String query_salary_month=req.getParameter("salary_month");

                try {
                    responseBack(req,resp,sqlOp.querySalaryRecord(query_employee_name,query_salary_month));
                } catch (SQLException | JSONException e) {
                    e.printStackTrace();
                }
                break;

            case "exportSalaryRecord":
                try {
                    JSONObject json=sqlOp.showSalaryTable("false");
//                    getExportWarningRecordToFile(json);
                    getExportSalaryRecordToExcel(json);
                    json.put("ok",200);
                    responseBack(req,resp,json);
                } catch (JSONException | SQLException e) {
                    e.printStackTrace();
                }
                break;

            case "statisticSalaryRecord":
                try {
                    System.out.println("进入统计ServletAction!!!!");
                    responseBack(req,resp,sqlOp.statisticSalaryTable());
                } catch (JSONException | SQLException e) {
                    e.printStackTrace();
                }
                break;
>>>>>>> 117581ba5fc9261dccc0ee1f9cbdde35e639c486

            default:{
                break;
            }
        }
<<<<<<< HEAD

        // 关闭数据库链接
        try {
            sqlOp.CloseCn();
            responseBack(req,resp,jsonObject);
        } catch (JSONException | SQLException e) {
            System.out.println("回调失败！|| 数据库链接关闭失败!");
            e.printStackTrace();
        }
=======
>>>>>>> 117581ba5fc9261dccc0ee1f9cbdde35e639c486
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
    private void getExportWarningRecordToFile(JSONObject json) throws JSONException {
        String jsonStr = json.toString();
        File jsonFile = new File("D:\\test\\maintain\\device\\export_device.txt");
        json.put("download_url","/test/maintain/device/export_device.txt");
        try {
            // 文件不存在就创建文件
            if (!jsonFile.exists()) {
                jsonFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(jsonFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(jsonStr);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getExportWarningRecordToExcel(JSONObject json) throws JSONException, IOException {
        MyExcel me=new MyExcel("D:\\test\\maintain\\device\\export_device.xls");
        json.put("download_url","/test/maintain/device/export_device.xls");
        json.put("file_path","D:\\test\\maintain\\device\\export_device.xls");
        me.exportData(json);
    }

    private void getExportSalaryRecordToExcel(JSONObject json) throws JSONException, IOException {
        MyExcel me=new MyExcel("C:\\upload\\maintain\\device\\export_salary.xls");
        json.put("download_url","/upload/maintain/device/export_salary.xls");
        json.put("file_path","C:\\upload\\maintain\\device\\export_salary.xls");
        me.exportData(json);
    }
>>>>>>> 117581ba5fc9261dccc0ee1f9cbdde35e639c486

}
