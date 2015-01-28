
package anabi.models;




/**
 *
 * @author yosamac
 */
public class TypePublication  {
   
    private Integer codPublicationType;
    private String namePublicationType;
    private String description;
    

	public TypePublication() {
    }

    public TypePublication(Integer codPublicationType) {
        this.codPublicationType = codPublicationType;
    }

    public TypePublication(Integer codPublicationType, String namePublicationType) {
        this.codPublicationType = codPublicationType;
        this.namePublicationType = namePublicationType;
    }

    public Integer getCodCDocument() {
        return codPublicationType;
    }

    public void setCodCDocument(Integer codPublicationType) {
        this.codPublicationType = codPublicationType;
    }

    public String getNameCDocument() {
        return namePublicationType;
    }

    public void setNameCDocument(String namePublicationType) {
        this.namePublicationType = namePublicationType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
