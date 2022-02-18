package Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity(tableName = "assessment_table")
public class AssessmentEntity {

    @PrimaryKey(autoGenerate = true)
    private int assessmentID;

    private String assessmentTitle;
    private String assessmentType;
    private String assessmentStartDate;
    private String assessmentEndDate;

    private int associatedCourse;

    public AssessmentEntity(String assessmentTitle, String assessmentType, String assessmentStartDate, String assessmentEndDate, int associatedCourse) {
        this.assessmentTitle = assessmentTitle;
        this.assessmentType = assessmentType;
        this.assessmentStartDate = assessmentStartDate;
        this.assessmentEndDate = assessmentEndDate;
        this.associatedCourse = associatedCourse;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        this.assessmentTitle = assessmentTitle;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public String getAssessmentStartDate() {
        return assessmentStartDate;
    }

    public void setAssessmentStartDate(String assessmentStartDate) {
        this.assessmentStartDate = assessmentStartDate;
    }

    public String getAssessmentEndDate() {
        return assessmentEndDate;
    }

    public void setAssessmentEndDate(String assessmentEndDate) {
        this.assessmentEndDate = assessmentEndDate;
    }

    public int getAssociatedCourse() {
        return associatedCourse;
    }

    public void setAssociatedCourse(int associatedCourse) {
        this.associatedCourse = associatedCourse;
    }
}
