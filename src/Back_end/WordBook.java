package Back_end;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class WordBook {


    //从词书列表里找出所有的词书名字
    public static List<String> filePath(String filename) throws IOException {
        List<String> all = new ArrayList<>();
//        String path = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                all.add(parts[0]);
//                if (parts[0].equals(filename)) {
//                    path = parts[1];
//                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();  // 处理异常，打印错误轨迹
        }
//        all.add(path);
        return all;
    }

    //根据对应的名字找出对应的路径
    public static String findPath(String booklistPath, String filename) throws IOException {
//        List<String> all = new ArrayList<>();
        String path = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(booklistPath))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
//                all.add(parts[0]);
                if (parts[0].equals(filename)) {
                    path = parts[1];
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();  // 处理异常，打印错误轨迹
        }
//        all.add(path);
        return path;
    }

    //生成今天的背诵单词  20个new word 10个wrong word
    public static List<String[]> todayRecite(int start, String originFile, String wrongFile) throws IOException {
//        String[][] wordlist = new String[30][8];
        int end = start + 20;
        List<String[]> wordlist = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(originFile));
             BufferedReader reader2 = new BufferedReader(new FileReader(wrongFile))){
            String line;
//            int pointer = 0;

            //1,banana,[bə'nɑːnə],n.香蕉,香蕉,橘子,西瓜,苹果,香蕉
            //new word
            while ((line = reader.readLine()) != null) {
                String[] word = new String[8];
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                if(id >= start && id < end){
                    word[0] = parts[1];//eng
                    word[1] = parts[2];//wordsound
                    word[2] = parts[3];//meaing
                    word[3] = parts[4];//a
                    word[4] = parts[5];//b
                    word[5] = parts[6];//c
                    word[6] = parts[7];//d
                    word[7] = parts[8];//ans
                    wordlist.add(word);
                }else if(id >= end){
                    break;
                }
            }

            //wrong file
            while ((line = reader2.readLine()) != null) {
                String[] word = new String[8];
                String[] parts = line.split(",");
//                int id = Integer.parseInt(parts[0]);
                word[0] = parts[0];//eng
                word[1] = parts[1];//wordsound
                word[2] = parts[2];//meaing
                word[3] = parts[3];//a
                word[4] = parts[4];//b
                word[5] = parts[5];//c
                word[6] = parts[6];//d
                word[7] = parts[7];//ans
                wordlist.add(word);
            }


        }
        catch (IOException e) {
            e.printStackTrace();  // 处理异常，打印错误轨迹
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(wrongFile))){
            //清空wrong.txt
            writer.write("");
            writer.flush();
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();  // 处理异常，打印错误轨迹
        }

        return wordlist;
    }

    public static int pre_record(String name) throws IOException {
        int newid = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("record.txt"))){
            String line = "";
//            String last_line = "";
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts[0].equals(name)){
                    newid = Integer.parseInt(parts[1]);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();  // 处理异常，打印错误轨迹
        }
        return newid;
    }

    //record : 每日背诵记录new中 + wrong中
    //背到哪里了
    public static void record(String name, int id) throws IOException {
        int newid = 0;
        int wrongid = 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("record.txt", true))){
            writer.write(name + "," + id + "\n");
        }
        catch (IOException e) {
            e.printStackTrace();  // 处理异常，打印错误轨迹
        }
    }

    //word.getText(), wordSound.getText(), meaning.getText(), a.getText(), b.getText(), c.getText(), d.getText(), ans
    public static void wrong(String word, String wordSound, String meaning, String a,String b, String c, String d, String ans) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("wrong.txt", true))){
            writer.write(word+","+ wordSound+","+meaning+","+a+","+b+","+c+","+d+","+ans + "\n");
        }
        catch (IOException e) {
            e.printStackTrace();  // 处理异常，打印错误轨迹
        }
    }

}
