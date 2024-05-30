package Back_end;

import java.util.HashMap;
import java.util.Map;

public class WordList {
    private Map<Integer, Word> wordlist = new HashMap<>();

    public void Add_Word(Integer number, Word NewWord) {
        wordlist.put(number, NewWord);
    }

    public Word Get_Word_By_Num(Integer num) {
        return wordlist.get(num);
    }

    public boolean Search_If_Is_Existed(Integer num) {
        return wordlist.containsKey(num);
    }

    public Map<Integer, Word> getWordlist() {
        return wordlist;
    }
}
