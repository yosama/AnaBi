

package anabi.model;


import java.util.List;


/**
 *
 * @author yosamac
 */

public class Author {

	private Record record;
	private Integer codAuthor = 0;
	private String nameAu;
	private String nameFn;
	private String emailEm;
	private String authorRP;

	private List<Affiliation> affiliationList;
	private List<Document> documentList;

	public Author() {
	}

	public Author ( Integer codAuthor, String nameAu, String nameFn,Record record) {
		this.codAuthor = codAuthor;
		this.nameAu = nameAu;
		this.nameFn = nameFn;
		this.record = record;
	}
	

	public Integer getCodAuthor() {
		return codAuthor;
	}

	public void setCodAuthor(Integer codAuthor) {
		this.codAuthor = codAuthor;
	}

	public String getNameAu() {
		return nameAu;
	}

	public void setNameAu(String nameAu) {
		this.nameAu = nameAu;
	}

	public String getNameFn() {
		return nameFn;
	}

	public void setNameFn(String nameFn) {
		this.nameFn = nameFn;
	}

	public String getEmailEm() {
		return emailEm;
	}

	public void setEmailEm(String emailEm) {
		this.emailEm = emailEm;
	}

	public String getAuthorRp() {
		return authorRP;
	}

	public void setAuthorRp(String codAuthorRp) {
		this.authorRP = codAuthorRp;
	}

	
	public List<Affiliation> getAffiliationList() {
		return affiliationList;
	}

	public void setAffiliationList(List<Affiliation> affiliationList) {
		this.affiliationList = affiliationList;
	}

	
	public List<Document> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<Document> documentList) {
		this.documentList = documentList;
	}

	public Record getRecord(){
		return record;
	}
	
	

}
