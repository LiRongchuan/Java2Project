package Back_end;

import java.sql.Timestamp;

public class WordGroup {
    private int GroupNum;
    private int[] Contain = new int[10];
    private int Last_Rate_of_Correct;
    private Timestamp Next_Review_Time;
    private double S;
    private int Full_Mark_in_a_Row;
    private boolean Need_to_Review;
    private final double E_threshold = 0.22524324;

    public WordGroup() {
        Need_to_Review = true;
    }

    public void SetContain(int[] Set) {
        Contain = Set;
    }
    public Timestamp Calculate_Next_Review_Time(int R,Timestamp T) {
        Timestamp retT = T;
        return retT;
    }

    public void Update(int Correct_Rate,Timestamp Review_Time) {
        Last_Rate_of_Correct = Correct_Rate;
        S = 0.3 + 0.02 * (1.0 * Correct_Rate / 10);
        Next_Review_Time = Calculate_Next_Review_Time(Correct_Rate, Review_Time);
    }

    public double getE_threshold() {
        return E_threshold;
    }

    public double getS() {
        return S;
    }

    public int getGroupNum() {
        return GroupNum;
    }

    public int getLast_Rate_of_Correct() {
        return Last_Rate_of_Correct;
    }

    public int[] getContain() {
        return Contain;
    }

    public void setGroupNum(int groupNum) {
        GroupNum = groupNum;
    }

    public Timestamp getNext_Review_Time() {
        return Next_Review_Time;
    }

    public void setContain(int[] contain) {
        Contain = contain;
    }

    public void setS(double s) {
        S = s;
    }

    public void setNext_Review_Time(Timestamp next_Review_Time) {
        Next_Review_Time = next_Review_Time;
    }

    public boolean isNeed_to_Review() {
        return Need_to_Review;
    }

    public int getFull_Mark_in_a_Row() {
        return Full_Mark_in_a_Row;
    }

    public void Update_Mark(int Mark) {
        Last_Rate_of_Correct = Mark;
        if (Mark == 10) Full_Mark_in_a_Row++;
        else Full_Mark_in_a_Row = 0;
        if (Full_Mark_in_a_Row == 3) Need_to_Review = false;
    }
}
