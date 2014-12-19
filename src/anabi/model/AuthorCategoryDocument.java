
package anabi.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author yosamac
 */

public class AuthorCategoryDocument {

    private final SimpleStringProperty year = new SimpleStringProperty();
    
    private final SimpleIntegerProperty countAuthorDocCitable = new SimpleIntegerProperty();
    private final SimpleIntegerProperty countCitable = new SimpleIntegerProperty();
    private final SimpleIntegerProperty percentAuthorXDocCitable = new SimpleIntegerProperty();

    private final SimpleIntegerProperty countAuthorDocNoCitable = new SimpleIntegerProperty();
    private final SimpleIntegerProperty countNoCitable = new SimpleIntegerProperty();
    private final SimpleIntegerProperty percentAuthorXDocNoCitable = new SimpleIntegerProperty();
    
    private final SimpleIntegerProperty totalDocument = new SimpleIntegerProperty();
    private final SimpleIntegerProperty totalAuthor = new SimpleIntegerProperty();
    private final SimpleIntegerProperty totalPercent = new SimpleIntegerProperty();

    public void setYear(String year) {
        this.year.set(year);
    }

    public void setCountAuthorDocCitable(Integer countAuthorDocCitable) {
        this.countAuthorDocCitable.set(countAuthorDocCitable);
    }

    public void setCountAuthorDocNoCitable(Integer countAuthorDocNoCitable) {
        this.countAuthorDocNoCitable.set(countAuthorDocNoCitable);
    }

    public void setCountCitable(Integer countCitable) {
        this.countCitable.set(countCitable);
    }

    public void setCountNoCitable(Integer countNoCitable) {
        this.countNoCitable.set(countNoCitable);
    }

    public void setPercentAuthorDocCitable(Integer percentCitable) {
        this.percentAuthorXDocCitable.set(percentCitable);
    }

    public void setPercentAuthorDocNoCitable(Integer percentNoCitable) {
        this.percentAuthorXDocNoCitable.set(percentNoCitable);
    }

    public void setTotalAuthor(Integer totalAuthor) {

        this.totalAuthor.set(totalAuthor);
    }

    public void setTotalDocument(Integer totalDocument) {

        this.totalDocument.set(totalDocument);
    }

    public void setTotalPercent(Integer totalPercent) {

        this.totalPercent.set(totalPercent);
    }

    public String getYear() {
        return year.get();
    }

    public Integer getCountAuthorDocCitable() {
        return countAuthorDocCitable.get();
    }

    public Integer getCountAuthorDocNoCitable() {
        return countAuthorDocNoCitable.get();
    }

    public Integer getCountCitable() {
        return countCitable.get();
    }

    public Integer getCountNoCitable() {
        return countNoCitable.get();
    }

    public Integer getPercentAuthorDocCitable() {
        return percentAuthorXDocCitable.get();
    }

    public Integer getPercentAuthorDocNoCitable() {
        return percentAuthorXDocNoCitable.get();
    }

    public Integer getTotalAuthor() {
        return totalAuthor.get();
    }

    public Integer getTotalDocument() {
        return totalDocument.get();
    }

    public Integer getTotalPercent() {
        return totalPercent.get();
    }

}
