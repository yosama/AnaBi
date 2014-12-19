
package anabi.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author yosamac
 */
public class CategoryDocument {
    
    private final SimpleStringProperty year = new SimpleStringProperty();
    private final SimpleIntegerProperty countCitable = new SimpleIntegerProperty();
    private final SimpleIntegerProperty percentCitable = new SimpleIntegerProperty();
    private final SimpleIntegerProperty countNoCitable = new SimpleIntegerProperty();
    private final SimpleIntegerProperty percentNoCitable = new SimpleIntegerProperty();
    private final SimpleIntegerProperty totalProduction = new SimpleIntegerProperty();
   
   

    public void setYear(String year) {
        this.year.set(year);
    }

    public void setCountCitable(Integer countCitable) {
        this.countCitable.set(countCitable);
    }

    public void setPercentCitable(Integer percentCitable) {
        this.percentCitable.set(percentCitable);
    }

    public void setCountNoCitable(Integer countNoCitable) {
        this.countNoCitable.set(countNoCitable);
    }

    public void setPercentNoCitable(Integer percentNoCitable) {
        this.percentNoCitable.set(percentNoCitable);
    }

    
    public void setTotalProduction(Integer totalProduction) {
        
        this.totalProduction.set(totalProduction); 
    }

    public String getYear() {
        return year.get();
    }

    public Integer getCountCitable() {
        return countCitable.get();
    }

    public Integer getPercentCitable() {
        return percentCitable.get();
    }

    public Integer getCountNoCitable() {
        return countNoCitable.get();
    }

    public Integer getPercentNoCitable() {
        return percentNoCitable.get();
    }

    public Integer getTotalProduction() {
        return totalProduction.get();
    }
    
    
    
}
