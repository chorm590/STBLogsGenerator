package com.chorm.utils;

import java.util.Random;

/**
 * Created by chang on 2017/4/1.
 */
public class RandomUtil {

    public static String randomAccessDate(){
        String[] b = {"20170101",    "20170601",    "20160101",    "20160601"};
        Random rand = new Random();
        int num = rand.nextInt(4);
        return b[num];
    }

    public static String randomAccessTime(){
        String[] b = {"110311",  "050311",  "170311",  "230311"};
        Random rand = new Random();
        int num = rand.nextInt(4);
        return b[num];
    }

    public static String randomAccessHour(){
        String[] b = {"11",  "05",  "17",  "23"};
        Random rand = new Random();
        int num = rand.nextInt(4);
        return b[num];
    }

    public static String randomRequestURL() {
        String[] b = {"http://www.it18zhang.com",  "http://www.it18zhang.com1",   "http://www.it18zhang.com2",   "http://www.it18zhang.com3"};
        Random rand = new Random();
        int num = rand.nextInt(4);
        return b[num];
    }

    public static String randomUserOrigin() {
        String[] b = {"http://www.baidu.com",  "http://www.google.com",   "http://www.yahoo.com",    "http://www.sina.com"};
        Random rand = new Random();
        int num = rand.nextInt(4);
        return b[num];
    }

    public static String randomOriginWord() {
        String[] b = {"it",    "18",  "zhang",   "it18zhang"};
        Random rand = new Random();
        int num = rand.nextInt(4);
        return b[num];
    }

    public static String randomBowser() {
        String[] b = {"firefox", "chrome",   "Safari",  "opera"};
        Random rand = new Random();
        int num = rand.nextInt(4);
        return b[num];
    }

    public static String randomBowserVersion() {
        String[] b = {"5.0",   "6.0", "7.0", "8.0"};
        Random rand = new Random();
        int num = rand.nextInt(4);
        return b[num];
    }

    public static String randomOperateSystem() {
        String[] b = {"Windows", "macOS",   "Linux",   "Android"};
        Random rand = new Random();
        int num = rand.nextInt(4);
        return b[num];
    }

    public static String randomRequestIp() {
        String[] b = {"192.168.127.1",   "192.168.127.2",   "192.168.127.3",   "192.168.127.4"};
        Random rand = new Random();
        int num = rand.nextInt(4);
        return b[num];
    }

    public static String randomUserProvince() {
        String[] b = {"HeNan",   "HeBei",   "TianJin", "BeiJing"};
        Random rand = new Random();
        int num = rand.nextInt(4);
        return b[num];
    }

    public static String randomScreenSize() {
        String[] b = {"1366x768",    "1920x1080",   "640x320", "1280x1024"};
        Random rand = new Random();
        int num = rand.nextInt(4);
        return b[num];
    }

    public static String randomScreenColor() {
        String[] b = {"red", "blue",    "yellow",  "green"};
        Random rand = new Random();
        int num = rand.nextInt(4);
        return b[num];
    }

    public static String randomPageTitle() {
        String[] b = {"python",  "python+bigdata",  "java+bigdata",    "architecture"};
        Random rand = new Random();
        int num = rand.nextInt(4);
        return b[num];
    }

    public static String randomUserFlag(){
        String[] b = {"1","0"};
        Random rand = new Random();
        int num = rand.nextInt(2);
        return  b[num];
    }

    public static String randomVisitFlag(){
        String[] b = {"1","0"};
        Random rand = new Random();
        int num = rand.nextInt(2);
        return  b[num];
    }

    public static String randomSFlag(){
        String[] b = {"1","0"};
        Random rand = new Random();
        int num = rand.nextInt(2);
        return  b[num];
    }

    public static int randomTimeOnPage(){
        int[] b = {10,  100, 1000,    10000};
        Random rand = new Random();
        int num = rand.nextInt(4);
        return b[num];
    }

}
