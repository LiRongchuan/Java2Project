package Back_end;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordList {
    private Map<Integer, Word> wordlist;
    // 映射id和单词

    private Map<String, Integer> idlist;
    // 映射英文和id

    private Map<String, String> metadata;

    private File fileLocation;


    public void Add_Word(Integer number, Word NewWord) {
        wordlist.put(number, NewWord);
        idlist.put(NewWord.getEnglish(), number);
    }

    public Word Get_Word_By_Num(Integer num) {
        return wordlist.get(num);
    }

    public int Get_Id_By_English(String english) {
        return idlist.get(english);
    }

    public Word Get_Word_By_English(String english) {
        int id = Get_Id_By_English(english);
        return Get_Word_By_Num(id);
    }

    public boolean Search_If_Is_Existed(Integer num) {
        return wordlist.containsKey(num);
    }

    public boolean Search_If_Is_Existed_By_English(String english) {
        return idlist.containsKey(english);
    }

    public Map<Integer, Word> getWordlist() {
        return wordlist;
    }

    public void setWordlist(Map<Integer, Word> wordlist) {
        this.wordlist = wordlist;
    }

    public WordList(File fileLocation) {
        wordlist = new HashMap<>();
        idlist = new HashMap<>();
        this.fileLocation = fileLocation;
        this.metadata = new HashMap<>();
    }

    //读取词典文件
    public void loadWordFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileLocation))) {
            String line;
            boolean isWordList = false;
            boolean isMetadata = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#")) {
                    // 注释，comment：注意，仅仅可以识别在每一行开头的#的注释，不能放在中间，也不要打空格
                    continue;
                }
                if (line.startsWith("WordList:")) {
                    isWordList = true;
                    isMetadata = false;
                    continue;
                } else if (line.startsWith("Metadata:")) {
                    isWordList = false;
                    isMetadata = true;
                    continue;
                }

                if (isWordList) {
                    String[] parts = line.split(",");
                    int id = Integer.parseInt(parts[0]);
                    String english = parts[1];
                    String chinese = parts[2];
                    boolean liked = Boolean.parseBoolean(parts[3]);
                    String[] choices = new String[4];
                    for (int i = 4; i < parts.length - 1; i++) {
                        choices[i - 4] = parts[i];
                    }
                    int correct_option = Integer.parseInt(parts[8]);
                    Word word = new Word(id, english, chinese, liked,
                            choices, correct_option);
                    Add_Word(id, word);
                } else if (isMetadata) {
                    //todo: if there is other data needed to store in the files
                    String[] parts = line.split(":");
                    metadata.put(parts[0], parts[1]);
                }
            }
        }
    }


    public void AddBook(WordBook wordBook) {
        List<Word> words = wordBook.getWords();
        for (Word wd : words) {
            if (Search_If_Is_Existed(wd.getId())) Add_Word(wd.getId(), wd);
        }
    }


}
