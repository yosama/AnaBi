

package anabi.models;


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

	private List<Integer> listCodAffiliation;
	private List<Integer> listCodDocument;

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

	
	public List<Integer> getAffiliationList() {
		return listCodAffiliation;
	}

	public void setAffiliationList(List<Integer> listCodAffiliation) {
		this.listCodAffiliation = listCodAffiliation;
	}

	
	public List<Integer> getDocumentList() {
		return listCodDocument;
	}

	public void setDocumentList(List<Integer> documentList) {
		this.listCodDocument = documentList;
	}

	public Record getRecord(){
		return record;
	}
	
	

}
