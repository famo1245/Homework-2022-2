package hw;

import java.util.Date;

public class MovieInfo {
    private String id;
    private String title;
    private String company;
    private Date releaseDate;
    private String country;
    private int totalScreen;
    private double profit;
    private int totalNum;
    private String grade;

    public MovieInfo(String id, String title, String company, Date releaseDate, String country, int totalScreen, double profit, int totalNum, String grade) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.releaseDate = releaseDate;
        this.country = country;
        this.totalScreen = totalScreen;
        this.profit = profit;
        this.totalNum = totalNum;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "| " + id + " | " + title + " | " + company + " | " + releaseDate.toString() + " | " + country +
                " | " + totalScreen + " | " +  String.format("%.2f",profit) + " | " + totalNum + " | " + grade;
    }
}
