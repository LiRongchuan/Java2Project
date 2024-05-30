package Back_end;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Index {
    private ArrayList<String> index_wordbook = new ArrayList<>();

    public void setIndex_wordbook(String new_wordbook) {
        this.index_wordbook.add(new_wordbook);
    }

    public ArrayList<String> getIndex_wordbook() {
        return index_wordbook;
    }

    public void ScanIndex() {
        String index = "";
        File file = new File(index);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNext()) {
            index = scanner.nextLine();
            index_wordbook.add(index);
        }
    }
}
