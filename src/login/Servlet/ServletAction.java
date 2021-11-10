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
import java.util.ArrayList;
import java.util.List;

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
            case "getRecord":{
                try {
                    jsonObject=sqlOp.getRecord();
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
                // 从前端获取json

                String json =req.getParameter("json");
                System.out.println("从前端获取到JSON:"+json);
                JsonToFile jsonToFile=new JsonToFile();
//                jsonObject=jsonToFile.setJsonTOTxt();
            }

            case "getDeviceRecord":{
                try {
                    jsonObject=sqlOp.getDeviceRecord();
                } catch (JSONException | SQLException e) {
                    e.printStackTrace();
                }
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


}
