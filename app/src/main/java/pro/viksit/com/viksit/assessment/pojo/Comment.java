package pro.viksit.com.viksit.assessment.pojo;

/**
 * Created by Akshay on 27/03/2017.
 */

public class Comment {

    private String commentor;
    private String time;
    private String commentText;
    private int imageResID;

    public Comment(String commentor, String time, String commentText, int imageResID) {
        this.commentor = commentor;
        this.time = time;
        this.commentText = commentText;
        this.imageResID = imageResID;
    }

    public String getCommentor() {
        return commentor;
    }

    public void setCommentor(String commentor) {
        this.commentor = commentor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public int getImageResID() {
        return imageResID;
    }

    public void setImageResID(int imageResID) {
        this.imageResID = imageResID;
    }
}
