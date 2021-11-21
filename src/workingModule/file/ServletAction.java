package workingModule.file;

import DbOperator.getDbConnection;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
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
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("执行service!");
        request.setCharacterEncoding("UTF-8");
        String action=request.getParameter("action");

        //开始查询数据库
        //注意：如果遇到问题，Tomcat的日志在C:\Tomcat\logs\stdout.log，可以查看
        List jsonList = new ArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException classnotfoundexception) {
            classnotfoundexception.printStackTrace();
        }
        try {
            //注意：数据表video_file确保在test数据库下面，如果没有就导入进去，或者放在自己建的数据库，下面的test相应要修改
            getDbConnection dbConnection=new getDbConnection();
            Statement statement = dbConnection.getConnection();
            System.out.println("连接数据库Ok！！！");
            String sql="select * from salary_file order by salary_id";
            System.out.println("构造出来的sql语句是："+sql);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                List list = new ArrayList();
                list.add(rs.getString("salary_id"));
                list.add(rs.getString("employee_id"));
                list.add(rs.getString("employee_name"));
                list.add(rs.getString("employee_duty"));
                list.add(rs.getString("salary_number"));
                list.add(rs.getString("salary_month"));
                list.add(rs.getString("salary_remark"));
                list.add(rs.getString("salary_datetime"));
                jsonList.add(list);
            }
            statement.close();
            dbConnection.close();
            System.out.println("数据库关闭了！！！");
        } catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
        //////////数据库查询完毕，得到了json数组jsonList//////////
        //jsonList.clear();
        //下面开始构建返回的json
        JSONObject jsonObj=new JSONObject();
        try {
            jsonObj.put("aaData",jsonList);
            jsonObj.put("action",action);
            jsonObj.put("result_msg","ok");	//如果发生错误就设置成"error"等
            jsonObj.put("result_code",0);	//返回0表示正常，不等于0就表示有错误产生，错误代码
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("最后构造得到的json是："+jsonObj.toString());
        response.setContentType("text/html; charset=UTF-8");
        try {
            response.getWriter().print(jsonObj);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
