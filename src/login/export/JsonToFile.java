package login.export;

import com.sun.xml.internal.ws.util.FastInfosetUtil;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonToFile {


    // 将JSON导出成TXT文件
    public JSONObject setJsonTOTxt(JSONObject jsonObject) throws JSONException, IOException {
        // JSON转字符串
        String jsonstr=jsonObject.toString();

        File jsonFile=new File("C:\\Users\\23639\\Desktop\\JsonToFile\\export.txt");
        if(!jsonFile.exists()){
            jsonFile.createNewFile();
            System.out.println("创建文件成功!");
        }
        // 写入文件
        FileWriter fileWriter=new FileWriter(jsonFile.getAbsoluteFile());
        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
        bufferedWriter.close();
        System.out.println("set JSON to Txt, success");
        // 使用JSON格式反馈下载路径
        JSONObject jsonPath=new JSONObject();
        jsonPath.put("downloadPath","/upload/export.txt");
        return jsonPath;
    }


    // 将json导出成excel
    public void setJsonToExcel(JSONObject jsonObject){


        System.out.println("set JSON to excel, success");
    }

    // 将Json导出成一般的File
    public void setJsonToSimpleFile(JSONObject jsonObject){

        System.out.println("set JSON to simple File, success");
    }

}
