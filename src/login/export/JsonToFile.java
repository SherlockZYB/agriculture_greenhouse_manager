package login.export;

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 57168512e5fb467f357fee5b0e0426108031416d
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;

public class JsonToFile {
    // 定义导出的路径信息
<<<<<<< HEAD
    final String txtPath="C:\\Users\\23632\\Desktop\\export\\export.txt";
    final String excelPath="C:\\Users\\23632\\Desktop\\export\\export.xls";
=======
    final String txtPath="C:\\Users\\ecw\\Desktop\\JsonToFile\\export.txt";
    final String excelPath="C:\\Users\\ecw\\Desktop\\JsonToFile\\export.xls";
=======
import com.sun.xml.internal.ws.util.FastInfosetUtil;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonToFile {
>>>>>>> e7cd7b6bd24ce9294431a1282b9c7ac8c306c4d2
>>>>>>> 57168512e5fb467f357fee5b0e0426108031416d


    // 将JSON导出成TXT文件
    public JSONObject setJsonTOTxt(JSONObject jsonObject) throws JSONException, IOException {
        // JSON转字符串
        String jsonstr=jsonObject.toString();

<<<<<<< HEAD
        File jsonFile=new File(this.txtPath);
=======
<<<<<<< HEAD
        File jsonFile=new File(this.txtPath);
=======
        File jsonFile=new File("C:\\Users\\23639\\Desktop\\JsonToFile\\export.txt");
>>>>>>> e7cd7b6bd24ce9294431a1282b9c7ac8c306c4d2
>>>>>>> 57168512e5fb467f357fee5b0e0426108031416d
        if(!jsonFile.exists()){
            jsonFile.createNewFile();
            System.out.println("创建文件成功!");
        }
        // 写入文件
        FileWriter fileWriter=new FileWriter(jsonFile.getAbsoluteFile());
        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 57168512e5fb467f357fee5b0e0426108031416d

        System.out.println("[sqlOperator/setJsonTOTxt]正在写入文件!");
        bufferedWriter.write(jsonstr);
        bufferedWriter.close();
        System.out.println("[sqlOperator/setJsonTOTxt]set JSON to Txt, success");
        // 使用JSON格式反馈下载路径
        JSONObject json=new JSONObject();
        json.put("txtDownloadPath","/upLoad/export.txt");
        return json;
<<<<<<< HEAD
=======
=======
        bufferedWriter.close();
        System.out.println("set JSON to Txt, success");
        // 使用JSON格式反馈下载路径
        JSONObject jsonPath=new JSONObject();
        jsonPath.put("downloadPath","/upload/export.txt");
        return jsonPath;
>>>>>>> e7cd7b6bd24ce9294431a1282b9c7ac8c306c4d2
>>>>>>> 57168512e5fb467f357fee5b0e0426108031416d
    }


    // 将json导出成excel
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 57168512e5fb467f357fee5b0e0426108031416d
    public void setJsonToExcel(JSONObject json,JSONObject jsonObject) throws IOException, JSONException {
        HSSFWorkbook workbook=new HSSFWorkbook();
        HSSFSheet sheet=workbook.createSheet("sheet0");

        // 获取表头
        JSONArray jsonTitle=json.getJSONArray("title");
        // 创建第一行
        HSSFRow rowTitle=sheet.createRow(0);
        // 设置表头
        for(int i=0;i<jsonTitle.length();i++){
            HSSFCell cell=rowTitle.createCell(i);
            cell.setCellValue((String) jsonTitle.get(i));
        }
        // 填充表内容
        JSONArray jsonArrayContent=json.getJSONArray("record");
        for(int i=0;i<jsonArrayContent.length();i++){
            HSSFRow row=sheet.createRow(i+1);

            // 获取内容
            int j=0;
            HashMap<String, String> record= (HashMap<String, String>) jsonArrayContent.get(i);
            for(int col=0;col<record.size();col++){
                HSSFCell cell=row.createCell(j);
                cell.setCellValue(record.get(jsonTitle.get(col)));
                j++;
            }
        }
        FileOutputStream outputStream=new FileOutputStream(this.excelPath);
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        jsonObject.put("excelDownloadPath","/upLoad/export.xls");
        jsonObject.put("ok",200);
<<<<<<< HEAD
=======
=======
    public void setJsonToExcel(JSONObject jsonObject){


>>>>>>> e7cd7b6bd24ce9294431a1282b9c7ac8c306c4d2
>>>>>>> 57168512e5fb467f357fee5b0e0426108031416d
        System.out.println("set JSON to excel, success");
    }

    // 将Json导出成一般的File
    public void setJsonToSimpleFile(JSONObject jsonObject){

        System.out.println("set JSON to simple File, success");
    }

}
