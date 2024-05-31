package Back_end;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Curve {
    private double last_value;
    private Date lastDate;
    private double value;
    private Date thisTime;
    public void loadCurveData(String bookName, double todayValue) throws IOException {
        File dataFile = new File("curveData\\"+bookName+".txt");
        Scanner scanner;
        scanner = new Scanner(dataFile);
        lastDate = Date.valueOf(scanner.nextLine());
        last_value = Double.parseDouble(scanner.nextLine());
        calHistory();
        calFuture(todayValue);
        updateCurveData(bookName, todayValue);
    }
    private final ArrayList<Double> values = new ArrayList<>();
    private final ArrayList<Date> dates = new ArrayList<>();
    private final Calendar calendar = Calendar.getInstance();

    private void calHistory(){
        Date midDate = (Date) lastDate.clone();
        double midValue = last_value;
        calendar.set(midDate.getYear()+1900, midDate.getMonth(), midDate.getDate());
        Date today = new Date(System.currentTimeMillis());
        while(midDate.getYear()!=today.getYear()||midDate.getMonth()!=today.getMonth()||midDate.getDate()!=today.getDate()) {
            dates.add(midDate);
            values.add(midValue);
            midValue *= 0.7;
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            midDate = new Date(calendar.getTimeInMillis());
        }
    }
    private void calFuture(double todayValue){
        double midValue = todayValue>=0?todayValue:values.get(values.size()-1);
        Date midDate = new Date(System.currentTimeMillis());
        calendar.set(midDate.getYear()+1900, midDate.getMonth(), midDate.getDate());
        for(int i=0;i<5;i++) {
            dates.add(midDate);
            values.add(midValue);
            midValue *= 0.7;
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            midDate = new Date(calendar.getTimeInMillis());
        }
    }
    private void updateCurveData(String bookName, double todayValue) throws IOException {
        File dataFile = new File("curveData\\"+bookName+".txt");
        dataFile.delete();
        dataFile.createNewFile();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dataFile)));
        bw.write(new Date(System.currentTimeMillis()) +"\n");
        bw.write(todayValue+"");
        bw.flush();
        bw.close();
    }

    public ArrayList<Double> getValues() {
        return values;
    }

    public ArrayList<Date> getDates() {
        return dates;
    }
}

