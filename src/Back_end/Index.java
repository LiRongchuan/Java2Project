package Back_end;

import java.util.ArrayList;

public class Index {
    private ArrayList<String> index_wordbook = new ArrayList<>();

    public void setIndex_wordbook(String new_wordbook) {
        this.index_wordbook.add(new_wordbook);
    }

    public ArrayList<String> getIndex_wordbook() {
        return index_wordbook;
    }
}
