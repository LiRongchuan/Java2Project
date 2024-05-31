package Back_end;

public class Word {
    private int id;
    private String english;
    private String chinese;
    private boolean liked;

    private String[] Choices = new String[4];
    private int Correct_option;

    public Word(int id, String english, String chinese,
                boolean liked, String[] choices, int correct_option) {
        this.id = id;
        this.english = english;
        this.chinese = chinese;
        this.liked = liked;
        Choices = choices;
        Correct_option = correct_option;
    }

    public String getEnglish() {
        return english;
    }

    public boolean isLiked() {
        return liked;
    }

    public int getId() {
        return id;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setChoices(String[] choices) {
        Choices = choices;
    }

    public int getCorrect_option() {
        return Correct_option;
    }

    public String[] getChoices() {
        return Choices;
    }

    public void setCorrect_option(int correct_option) {
        Correct_option = correct_option;
    }

    @Override
    public String toString() {
        String s = new String();
        s+=id;
        s+=","+english+","+chinese+","+liked+","+Choices[0]+","+Choices[1]+","+Choices[2]+","+Choices[3]+","+Correct_option+"\n";
        return s;
    }

    public void shuffleOptions(boolean refresh_flag) {
        if (refresh_flag) {
            //todo: after each time of reciting, shuffle the 4 options of a word
        }
        //todo: write files again
    }
}
