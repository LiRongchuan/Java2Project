package Back_end;

import java.sql.Timestamp;

public class WordGroup {
    private int GroupNum;
    private int[] Contain = new int[10];
    private int Last_Number_of_Correctness;
    private Timestamp Next_Review_Time;
    private double S;
    private int Full_Mark_in_a_Row;
    // 如果该词组全对，则Full_Mark_in_a_Row++, 连续三天全对，将移除该词组
    private boolean Need_to_Review;
    private final double E_threshold = 0.22524324;

    private Curve curve;

    public WordGroup() {
        Need_to_Review = true;
        curve = new Curve(0.0,0.3,1.0);
    }

    public WordGroup(int groupNum, int[] contain, int last_Number_of_Correctness,
                     Timestamp next_Review_Time, double s, int full_Mark_in_a_Row,
                     boolean need_to_Review) {
        GroupNum = groupNum;
        Contain = contain;
        Last_Number_of_Correctness = last_Number_of_Correctness;
        Next_Review_Time = next_Review_Time;
        S = s;
        Full_Mark_in_a_Row = full_Mark_in_a_Row;
        Need_to_Review = need_to_Review;
        this.curve = new Curve((double) last_Number_of_Correctness / 10, s);
        //todo: 如何获取总体的时间？
    }

    public void SetContain(int[] Set) {
        Contain = Set;
    }

    public Timestamp Calculate_Next_Review_Time(int R, Timestamp T) {
        Timestamp retT = T;
        //todo:
        return retT;
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

    public int getLast_Number_of_Correctness() {
        return Last_Number_of_Correctness;
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


    public Curve getCurve() {
        return curve;
    }

    public void setCurve(Curve curve) {
        this.curve = curve;
    }

    public void Update_Mark(int Mark, Timestamp Review_Time) {
        Last_Number_of_Correctness = Mark;
        S = 0.3 + 0.02 * (1.0 * Mark / 10);
        //todo: update memory curve
        Next_Review_Time = Calculate_Next_Review_Time(Mark, Review_Time);
        if (Mark == 10) Full_Mark_in_a_Row++;
        else Full_Mark_in_a_Row = 0;
        if (Full_Mark_in_a_Row == 3) Need_to_Review = false;
        //todo: Is it necessary to remove the wordGroup from wordBook?
    }
}
