package Back_end;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Index {
    private ArrayList<String> index_wordbook = new ArrayList<>();
    private final String FileIndex = "index.txt";
    public void setIndex_wordbook(String new_wordbook) throws IOException {
        this.index_wordbook.add(new_wordbook);
        PrintIndex();
    }

    public void PrintIndex() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(FileIndex))));
        for (String print: index_wordbook) {
            bw.write(print+"\n");
        }
        bw.flush();
    }

    public ArrayList<String> getIndex_wordbook() {
        return index_wordbook;
    }

    public void LoadIndex() throws IOException {
        String index = "";
        File file = new File(FileIndex);
        Scanner scanner;
        if (!file.exists()) {
            file.createNewFile();
        }
        scanner = new Scanner(file);
        while (scanner.hasNext()) {
            index = scanner.nextLine();
            index_wordbook.add(index);
        }
    }
}
