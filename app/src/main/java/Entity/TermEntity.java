package Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "term_table")
public class TermEntity {
    @PrimaryKey(autoGenerate = true)
    private int termID;

    private String termName;
    private String termStartDate;
    private String termEndDate;

    public TermEntity(String termName, String termStartDate, String termEndDate) {

        this.termName = termName;
        this.termStartDate = termStartDate;
        this.termEndDate = termEndDate;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getTermStartDate() {
        return termStartDate;
    }

    public void setTermStartDate(String termStartDate) {
        this.termStartDate = termStartDate;
    }

    public String getTermEndDate() {
        return termEndDate;
    }

    public void setTermEndDate(String termEndDate) {
        this.termEndDate = termEndDate;
    }



}
