import Back_end.Index;
import Back_end.WordBook;
import Back_end.WordList;
import UIDesign.Frame;

import java.io.File;
import java.sql.Timestamp;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    static Timestamp systemTime; // 模拟系统时间，人为跳转时间，方便演示
    static ArrayList<WordBook> WordBooks = new ArrayList<>();
    static WordList wordList = new WordList();
    public static void main(String[] args) throws IOException {
        Index index = new Index();
        index.LoadIndex();
        for (String ind : index.getIndex_wordbook()) {
            File file = new File(ind);
            WordBook wb = new WordBook(file);
            WordBooks.add(wb);
            wordList.AddBook(wb);
        }
        new Frame().launchFrame();
    }
}
