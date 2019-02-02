package com.chorm;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Created by  on 2017/3/30.
 */

/**
 * 这里考虑多行截串的问题，一行返回一个arr给KafkaConsumer来消费，然后让他写入到HDFS
 */
public class JsonToArr {

    public static void bufferedReaderToArr(BufferedReader br) {

        JSONParser parser=new JSONParser();
        try{
            String line;
            while ((line = br.readLine()) != null ) {
                JSONObject obj = (JSONObject)parser.parse(line);
                System.out.println(obj);
                String accessDate = obj.get("accessDate").toString();
                String accessTime = obj.get("accessTime").toString();
                String accessHour = obj.get("accessHour").toString();
                String requestMethod = obj.get("requestMethod").toString();
                String referUrl = obj.get("referUrl").toString();
                String requestProtocal = obj.get("requestProtocal").toString();
                String returnStatus = obj.get("returnStatus").toString();
                String requestUrl = obj.get("requestUrl").toString();
                String referDomain = obj.get("referDomain").toString();
                String userOrigin = obj.get("userOrigin").toString();
                String originWord = obj.get("originWord").toString();
                String browser = obj.get("browser").toString();
                String browserVersion = obj.get("browserVersion").toString();
                String operateSystem = obj.get("operateSystem").toString();
                String requestIp = obj.get("requestIp").toString();
                String ipNumber = obj.get("ipNumber").toString();
                String userProvince = obj.get("userProvince").toString();
                String screenSize = obj.get("screenSize").toString();
                String screenColor = obj.get("screenColor").toString();
                String pageTitle = obj.get("pageTitle").toString();
                String siteType = obj.get("siteType").toString();
                String userFlag = obj.get("userFlag").toString();
                String visitFlag = obj.get("visitFlag").toString();
                String sFlag = obj.get("sFlag").toString();
                String timeOnPage = obj.get("timeOnPage").toString();
                String[] arr = new String[]{
                    accessDate,
                    accessTime,
                    accessHour,
                    requestMethod,
                    referUrl,
                    requestProtocal,
                    returnStatus,
                    requestUrl,
                    referDomain,
                    userOrigin,
                    originWord,
                    browser,
                    browserVersion,
                    operateSystem,
                    requestIp,
                    ipNumber,
                    userProvince,
                    screenSize,
                    screenColor,
                    pageTitle,
                    siteType,
                    userFlag,
                    visitFlag,
                    sFlag,
                    timeOnPage};
                    String newMsg = arr[0]+" "+arr[1]+" "+arr[2]+" "+arr[3]+" "+arr[4]+" "+arr[5]+" "
                            +arr[6]+" "+arr[7]+" "+arr[8]+" "+arr[9]+" "+arr[10]+" "
                            +arr[11]+" "+arr[12]+" "+arr[13]+" "+arr[14]+" "+arr[15]+" "
                            +arr[16]+" "+arr[17]+" "+arr[18]+" "+arr[19]+" "+arr[20]+" "
                            +arr[21]+" "+arr[22]+" "+arr[23]+" "+arr[24]+"\n";
                    WriterStream(newMsg,arr[0]);
                }

            br.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private static void WriterStream(String str, String name){
        try {
            FileWriter writer = new FileWriter("F:/"+name+".txt",true);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(str);
            //使用缓冲区中的方法，将数据刷新到目的地文件中去。
            bw.flush();
            //关闭缓冲区,同时关闭了fw流对象

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
