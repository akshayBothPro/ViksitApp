package pro.viksit.com.viksit.role.pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Akshay on 20/04/2017.
 */

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class AssessmentReport implements Serializable {

    private Integer id;
    private String name;
    private Double userScore;
    private Double totalScore;
    private Double accuracy;
    private Double batchAverage;
    private Integer usersAttemptedCount;
    private Integer totalNumberOfUsersInBatch;
    private Integer totalNumberOfQuestions;
    private Integer totalNumberOfCorrectlyAnsweredQuestions;
    private ArrayList<RoleParent> skillsReport;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUserScore() {
        return userScore;
    }

    public void setUserScore(Double userScore) {
        this.userScore = userScore;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    public Double getBatchAverage() {
        return batchAverage;
    }

    public void setBatchAverage(Double batchAverage) {
        this.batchAverage = batchAverage;
    }

    public Integer getUsersAttemptedCount() {
        return usersAttemptedCount;
    }

    public void setUsersAttemptedCount(Integer usersAttemptedCount) {
        this.usersAttemptedCount = usersAttemptedCount;
    }

    public Integer getTotalNumberOfUsersInBatch() {
        return totalNumberOfUsersInBatch;
    }

    public void setTotalNumberOfUsersInBatch(Integer totalNumberOfUsersInBatch) {
        this.totalNumberOfUsersInBatch = totalNumberOfUsersInBatch;
    }

    public Integer getTotalNumberOfQuestions() {
        return totalNumberOfQuestions;
    }

    public void setTotalNumberOfQuestions(Integer totalNumberOfQuestions) {
        this.totalNumberOfQuestions = totalNumberOfQuestions;
    }

    public Integer getTotalNumberOfCorrectlyAnsweredQuestions() {
        return totalNumberOfCorrectlyAnsweredQuestions;
    }

    public void setTotalNumberOfCorrectlyAnsweredQuestions(Integer totalNumberOfCorrectlyAnsweredQuestions) {
        this.totalNumberOfCorrectlyAnsweredQuestions = totalNumberOfCorrectlyAnsweredQuestions;
    }

    public ArrayList<RoleParent> getSkillsReport() {
        return skillsReport;
    }

    public void setSkillsReport(ArrayList<RoleParent> skillsReport) {
        this.skillsReport = skillsReport;
    }
}
