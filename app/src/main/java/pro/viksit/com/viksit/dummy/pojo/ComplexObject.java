package pro.viksit.com.viksit.dummy.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Akshay on 26/04/2017.
 */


@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class ComplexObject implements Serializable {


    private Integer id;
    private StudentProfile studentProfile;
    private List<SkillReportPOJO> skills;
    private List<TaskSummaryPOJO> tasks;
    private List<AssessmentPOJO> assessments;
    private List<AssessmentReportPOJO> assessmentReports;
    private List<AssessmentResponsePOJO> assessmentResponses;
    private List<CoursePOJO> courses;
    private List<CourseRankPOJO> leaderboards;
    private List<DailyTaskPOJO> events;
    private List<NotificationPOJO> notifications;

    public ComplexObject() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public List<SkillReportPOJO> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillReportPOJO> skills) {
        this.skills = skills;
    }

    public List<TaskSummaryPOJO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskSummaryPOJO> tasks) {
        this.tasks = tasks;
    }

    public List<AssessmentPOJO> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<AssessmentPOJO> assessments) {
        this.assessments = assessments;
    }

    public List<AssessmentReportPOJO> getAssessmentReports() {
        return assessmentReports;
    }

    public void setAssessmentReports(List<AssessmentReportPOJO> assessmentReports) {
        this.assessmentReports = assessmentReports;
    }

    public List<AssessmentResponsePOJO> getAssessmentResponses() {
        return assessmentResponses;
    }

    public void setAssessmentResponses(List<AssessmentResponsePOJO> assessmentResponses) {
        this.assessmentResponses = assessmentResponses;
    }

    public List<CoursePOJO> getCourses() {
        return courses;
    }

    public void setCourses(List<CoursePOJO> courses) {
        this.courses = courses;
    }

    public List<CourseRankPOJO> getLeaderboards() {
        return leaderboards;
    }

    public void setLeaderboards(List<CourseRankPOJO> leaderboards) {
        this.leaderboards = leaderboards;
    }

    public List<DailyTaskPOJO> getEvents() {
        return events;
    }

    public void setEvents(List<DailyTaskPOJO> events) {
        this.events = events;
    }

    public List<NotificationPOJO> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationPOJO> notifications) {
        this.notifications = notifications;
    }
}
