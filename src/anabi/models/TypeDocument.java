

package anabi.models;



/**
 *
 * @author yosamac
 */

public class TypeDocument  {
    
    private Integer codTDocument;
    private String nameTDocument;
    private String description;
    

    public TypeDocument() {
    }

    public TypeDocument(Integer codTDocument) {
        this.codTDocument = codTDocument;
    }

    public TypeDocument(Integer codTDocument, String nameTDocument) {
        this.codTDocument = codTDocument;
        this.nameTDocument = nameTDocument;
    }

    public Integer getCodTDocument() {
        return codTDocument;
    }

    public void setCodTDocument(Integer codTDocument) {
        this.codTDocument = codTDocument;
    }

    public String getNameTDocument() {
        return nameTDocument;
    }

    public void setNameTDocument(String nameTDocument) {
        this.nameTDocument = nameTDocument;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
