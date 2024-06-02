
import Back_end.WordBook;
import UIDesign.Frame;

import java.io.File;
import java.sql.Timestamp;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static Timestamp systemTime; // 模拟系统时间，人为跳转时间，方便演示
    public static void main(String[] args) throws IOException {
//        File file_routine = new File("routine.txt");
//        File file_BookList = new File("BookList.txt");
//        File file_record = new File("record.txt");
//        File file_star = new File("star.txt");
//        File file_user = new File("user.txt");
//        try {
//            file_routine.createNewFile();
//            file_BookList.createNewFile();
//            file_record.createNewFile();
//            file_star.createNewFile();
//            file_user.createNewFile();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        new Frame().launchFrame();
    }
}
