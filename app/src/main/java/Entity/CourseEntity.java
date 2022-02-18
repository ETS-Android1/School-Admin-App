package Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "course_table")
public class CourseEntity {

        @PrimaryKey(autoGenerate = true)
        private int courseID;

        private String courseTitle;
        private String startDate;
        private String endDate;
        private String status;
        private String courseInstructorName;
        private String courseInstructorEmail;
        private String courseInstructorPhone;
        private String notes;


        private int associatedTermID;

        public CourseEntity(String courseTitle, String startDate, String endDate, String status, String courseInstructorName, String courseInstructorEmail, String courseInstructorPhone, String notes, int associatedTermID) {
                this.courseTitle = courseTitle;
                this.startDate = startDate;
                this.endDate = endDate;
                this.status = status;
                this.courseInstructorName = courseInstructorName;
                this.courseInstructorEmail = courseInstructorEmail;
                this.courseInstructorPhone = courseInstructorPhone;
                this.notes = notes;
                this.associatedTermID = associatedTermID;
        }

        @Override
        public String toString() {
                return "Course{" +
                        "courseID=" + courseID +
                        ", startDate='" + startDate + '\'' +
                        ", endDate='" + endDate + '\'' +
                        ", status='" + status + '\'' +
                        ", courseInstructorName='" + courseInstructorName + '\'' +
                        ", courseInstructorEmail='" + courseInstructorEmail + '\'' +
                        ", courseInstructorPhone='" + courseInstructorPhone + '\'' +
                        '}';
        }

        public int getCourseID() {
                return courseID;
        }

        public void setCourseID(int courseID) {
                this.courseID = courseID;
        }

        public String getStartDate() {
                return startDate;
        }

        public void setStartDate(String startDate) {
                this.startDate = startDate;
        }

        public String getEndDate() {
                return endDate;
        }

        public void setEndDate(String endDate) {
                this.endDate = endDate;
        }

        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }

        public String getCourseInstructorName() {
                return courseInstructorName;
        }

        public void setCourseInstructorName(String courseInstructorName) {
                this.courseInstructorName = courseInstructorName;
        }

        public String getCourseInstructorEmail() {
                return courseInstructorEmail;
        }

        public void setCourseInstructorEmail(String courseInstructorEmail) {
                this.courseInstructorEmail = courseInstructorEmail;
        }

        public String getCourseInstructorPhone() {
                return courseInstructorPhone;
        }

        public void setCourseInstructorPhone(String courseInstructorPhone) {
                this.courseInstructorPhone = courseInstructorPhone;
        }


        public int getAssociatedTermID() {
                return associatedTermID;
        }

        public void setAssociatedTermID(int associatedTermID) {
                this.associatedTermID = associatedTermID;
        }

        public String getCourseTitle() {
                return courseTitle;
        }

        public void setCourseTitle(String courseTitle) {
                this.courseTitle = courseTitle;
        }

        public String getNotes() {
                return notes;
        }

        public void setNotes(String notes) {
                this.notes = notes;
        }
}
