

package anabi.model;


import java.util.List;


/**
 *
 * @author yosamac
 */

public class TDocument  {
    
    private Integer codTDocument;
    private String nameTDocument;
    private String description;
    private List<Document> documentList;

    public TDocument() {
    }

    public TDocument(Integer codTDocument) {
        this.codTDocument = codTDocument;
    }

    public TDocument(Integer codTDocument, String nameTDocument) {
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

    
    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }

   
    
}
