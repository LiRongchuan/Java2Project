package Back_end;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public void AddBook(WordBook wordBook) {
        List<Word> words = wordBook.getWords();
        for (Word wd : words) {
            if (Search_If_Is_Existed(wd.getId())) Add_Word(wd.getId(),wd);
        }
    }
}
