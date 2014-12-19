
package anabi.model;

import java.util.List;


/**
 *
 * @author yosamac
 */
public class CDocument  {
   
    private Integer codCDocument;
    private String nameCDocument;
    private String description;
    private List<Document> documentList;
    
    
   

	public CDocument() {
    }

    public CDocument(Integer codCDocument) {
        this.codCDocument = codCDocument;
    }

    public CDocument(Integer codCDocument, String nameCDocument) {
        this.codCDocument = codCDocument;
        this.nameCDocument = nameCDocument;
    }

    public Integer getCodCDocument() {
        return codCDocument;
    }

    public void setCodCDocument(Integer codCDocument) {
        this.codCDocument = codCDocument;
    }

    public String getNameCDocument() {
        return nameCDocument;
    }

    public void setNameCDocument(String nameCDocument) {
        this.nameCDocument = nameCDocument;
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
