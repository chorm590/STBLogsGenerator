package com.chorm;


import java.io.*;
import java.util.HashMap;

import com.chorm.utils.JsonUtil;
import com.chorm.utils.RandomUtil;

public class MakeJson {

    public static void main(String[] args) throws Exception {

        Integer maxIndex = 100;
        Integer index;

        for (index = 1; index <= maxIndex; index++) {

            HashMap<String, String> dataMap = new HashMap<String, String>();

            String     accessDate = RandomUtil.randomAccessDate();          //eg:20170101    20170601    20160101    20160601
            String     accessTime = RandomUtil.randomAccessTime();          //eg:110311  050311  170311  230311
            String     accessHour = RandomUtil.randomAccessHour();          //eg:11  05  17  23
            String     requestMethod = "";                                  //eg:空串
            String     referUrl = "";                                       //eg:空串
            String     requestProtocal = "";                                //eg:空串
            String     returnStatus = "";                                   //eg:空串
            String     requestUrl = RandomUtil.randomRequestURL();          //eg:http://www.it18zhang.com  http://www.it18zhang.com1   http://www.it18zhang.com2   http://www.it18zhang.com3
            String     referDomain = "";                                    //eg:空串
            String     userOrigin = RandomUtil.randomUserOrigin();          //eg:http://www.baidu.com  http://www.google.com   http://www.yahoo.com    http://www.sina.com
            String     originWord = RandomUtil.randomOriginWord();          //eg:it    18  zhang   it18zhang
            String     browser = RandomUtil.randomBowser();                 //eg:firefox chrome   Safari  opera
            String     browserVersion = RandomUtil.randomBowserVersion();   //eg:5.0   6.0 7.0 8.0
            String     operateSystem = RandomUtil.randomOperateSystem();    //eg:Windows macOS   Linux   Android
            String     requestIp = RandomUtil.randomRequestIp();            //eg:192.168.127.1   192.168.127.2   192.168.127.3   192.168.127.4
            int        ipNumber = 0  ;                                      //eg:空串
            String     userProvince = RandomUtil.randomUserProvince();      //eg:HeNan   HeBei   TianJin BeiJing
            String     screenSize = RandomUtil.randomScreenSize();          //eg:1366x768    1920x1080   640x320 1280x1024
            String     screenColor = RandomUtil.randomScreenColor();        //eg:red blue    yellow  green
            String     pageTitle = RandomUtil.randomPageTitle();            //eg:python  python+bigdata  java+bigdata    architecture
            String     siteType = "0";                                      //eg:0
            String     userFlag = RandomUtil.randomUserFlag();              //eg:1   0
            String     visitFlag = RandomUtil.randomVisitFlag();            //eg:1   0
            String     sFlag = RandomUtil.randomSFlag();                    //eg:1   0
            int        timeOnPage = RandomUtil.randomTimeOnPage();          //eg:10  100 1000    10000

            dataMap.put("accessDate",accessDate);
            dataMap.put("accessTime",""+accessTime);
            dataMap.put("accessHour",""+accessHour);
            dataMap.put("requestMethod",requestMethod);
            dataMap.put("referUrl",referUrl);
            dataMap.put("requestProtocal",requestProtocal);
            dataMap.put("returnStatus",returnStatus);
            dataMap.put("requestUrl",requestUrl);
            dataMap.put("referDomain",referDomain);
            dataMap.put("userOrigin",userOrigin);
            dataMap.put("originWord",originWord);
            dataMap.put("browser",browser);
            dataMap.put("browserVersion",browserVersion);
            dataMap.put("operateSystem",operateSystem);
            dataMap.put("requestIp",requestIp);
            dataMap.put("ipNumber","");
            dataMap.put("userProvince",userProvince);
            dataMap.put("screenSize",screenSize);
            dataMap.put("screenColor",screenColor);
            dataMap.put("pageTitle",pageTitle);
            dataMap.put("siteType",siteType);
            dataMap.put("userFlag",userFlag);
            dataMap.put("visitFlag",visitFlag);
            dataMap.put("sFlag",sFlag);
            dataMap.put("timeOnPage",""+timeOnPage);

            String dataJson = JsonUtil.gsonToJson(dataMap);
            String lineJson = dataJson.replace("\n","");
            System.out.println(lineJson);

            //把输出String输出为文本文件
            try {
                FileWriter writer = new FileWriter("F:/"+233+".txt",true);
                BufferedWriter bw = new BufferedWriter(writer);
                bw.write(lineJson+"\n");
                //使用缓冲区中的方法，将数据刷新到目的地文件中去。
                bw.flush();
                //关闭缓冲区,同时关闭了fw流对象

            }
            catch (Exception e){
                e.printStackTrace();
            }


            //负责把String类型转成BufferedReader类型，传给JsonToArr类，输出所需数值
            InputStream is = new ByteArrayInputStream(lineJson.getBytes());
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            JsonToArr.bufferedReaderToArr(br);

        }
    }
}
