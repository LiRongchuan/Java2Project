package Back_end;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class WordBook {
    private File fileLocation;
    private List<Word> words;
    private List<WordGroup> wordGroups;
    private Map<String, String> metadata;


    public WordBook(File fileLocation) {
        this.fileLocation = fileLocation;
        this.words = new ArrayList<>();
        this.wordGroups = new ArrayList<>();
        this.metadata = new HashMap<>();
    }

    //读取词书文件
    public void loadWordFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileLocation))) {
            String line;
            boolean isWordList = false;
            boolean isWordGroups = false;
            boolean isMetadata = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#")) {
                    // 注释，comment：注意，仅仅可以识别在每一行开头的#的注释，不能放在中间，也不要打空格
                    continue;
                }
                if (line.startsWith("WordList:")) {
                    isWordList = true;
                    isWordGroups = false;
                    isMetadata = false;
                    continue;
                } else if (line.startsWith("WordGroups:")) {
                    isWordList = false;
                    isWordGroups = true;
                    isMetadata = false;
                    continue;
                } else if (line.startsWith("Metadata:")) {
                    isWordList = false;
                    isWordGroups = false;
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
                    words.add(word);
                } else if (isWordGroups) {
                    String[] parts = line.split(",");
                    int groupNum = Integer.parseInt(parts[0]);
                    int[] contain = new int[10];
                    for (int i = 1; i <= 10; i++) {
                        contain[i - 1] = Integer.parseInt(parts[i]);
                    }
                    int last_number_of_correctness = Integer.parseInt(parts[11]);
                    // Note: we use milliseconds format
                    long milliseconds = Long.parseLong(parts[12]);
                    Timestamp next_review_time = new Timestamp(milliseconds);
                    double s = Double.parseDouble(parts[13]);
                    int full_mark_in_a_row = Integer.parseInt(parts[14]);
                    boolean need_to_review = Boolean.parseBoolean(parts[15]);
                    WordGroup wordGroup = new WordGroup(groupNum, contain,
                            last_number_of_correctness, next_review_time, s,
                            full_mark_in_a_row, need_to_review);
                } else if (isMetadata) {
                    //todo: if there is other data needed to store in the files
                    String[] parts = line.split(":");
                    metadata.put(parts[0], parts[1]);
                }
            }
        }
    }

    //选择具有最低记忆成都(E值)的词组来进行词书背诵
    public void Generate_Word_Groups() {
        int grp = 1, tot = 0, ingroup = 0;
        WordGroup wg = new WordGroup();
        int[] num = new int[10];
        while (tot < words.size()) {
            Word wd = words.get(tot);
            num[ingroup] = wd.getId();
            tot++;
            ingroup++;
            if (ingroup == 10) {
                wg.setContain(num);
                wg.setGroupNum(grp);
                wordGroups.add(wg);
                wg = new WordGroup();
                grp++;
                ingroup = 0;
            }
        }
        for (int i = ingroup; i < 10; i++) num[i] = 0;
        wg.setContain(num);
        wg.setGroupNum(grp);
        wordGroups.add(wg);
    }

    //存入词书
    public void saveWordsToFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.fileLocation)))) {
            writer.write("WordList:\n");
            for (Word word : words) {
                writer.write(word.toString());
            }
            writer.flush();
            writer.write("WordGroups:\n");
            for (WordGroup wordGroup : wordGroups) {
                writer.write(wordGroup.toString());
            }
            writer.flush();
            writer.write("Metadata:\n");
            for (String key: metadata.keySet()) {
                writer.write(key+":"+ metadata.get(key)+"\n");
            }
            writer.flush();
        }
    }

    public WordGroup Get_Word_Groups_To_Memory() {
        ArrayList<WordGroup> wg = new ArrayList<>();
        for (WordGroup wordGroup : wordGroups) {
            if (wordGroup.isNeed_to_Review()) wg.add(wordGroup);
        }
        wg.sort(new Comparator<WordGroup>() {
            @Override
            public int compare(WordGroup o1, WordGroup o2) {
                double E1 = o1.getCurve().getE(), E2 = o2.getCurve().getE();
                if (E1 <= E2) return -1;
                return 1;
            }
        });
        return wg.get(0);
    }

    public File getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(File fileLocation) {
        this.fileLocation = fileLocation;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public List<WordGroup> getWordGroups() {
        return wordGroups;
    }

    public void setWordGroups(List<WordGroup> wordGroups) {
        this.wordGroups = wordGroups;
    }

    public void Update_WordGroup(WordGroup wordGroup) {
        for (int i = 0; i < wordGroups.size(); i++) {
            if (wordGroups.get(i).getGroupNum() == wordGroup.getGroupNum()) {
                wordGroups.set(i, wordGroup);
                return;
            }
        }
    }
}
