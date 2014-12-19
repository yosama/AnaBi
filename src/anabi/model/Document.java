
package anabi.model;


import java.util.List;


/**
 *
 * @author yosamac
 */

public class Document  {

	private Record record;
	private String codDocumentUt;
	private String titleTi;
	private String languageTi;
	private String abstractAb;
	private String volumenVl;
	private String yearPublicationPy;
	private String datePublicationPd;
	private String titleSourceDi;
	private String indexKewordsId;
	private String authorKewordsDe;
	private String citedCountNr;
	private Integer citedTotalZ9;
	private Integer citedReferenceTc;
	private String pageCountPg;
	private String pageBp ;
	private String pageEp;
	private List<Integer> listCodAuthor;
	private List<Integer> listCodAffiliation;
	private Funding funding;
	private Journal journal;
	private String documentType;
	private CDocument codCDocument;

	public Document() {
	}



	public Document(String codDocumentUt, String titleTi,
			String languageTi, String abstractAb, String volumenVl,
			String yearPublicationPy, String datePublicationPd,
			String titleSourceDi, String indexKewordsId,
			String authorKewordsDe, String citedCountNr, Integer citedTotalZ9,
			Integer citedReferenceTc,String pageCountPG, String documentType, Record record) {

		super();
		this.codDocumentUt = codDocumentUt;
		this.titleTi = titleTi;
		this.languageTi = languageTi;
		this.abstractAb = abstractAb;
		this.volumenVl = volumenVl;
		this.yearPublicationPy = yearPublicationPy;
		this.datePublicationPd = datePublicationPd;
		this.titleSourceDi = titleSourceDi;
		this.indexKewordsId = indexKewordsId;
		this.authorKewordsDe = authorKewordsDe;
		this.citedCountNr = citedCountNr;
		this.citedTotalZ9 = citedTotalZ9;
		this.citedReferenceTc = citedReferenceTc;
		this.pageCountPg = pageCountPG;
		this.documentType = documentType;
		this.record = record;
	}



	public String getCodDocumentUt() {
		return codDocumentUt;
	}

	public void setCodDocumentUt(String codDocumentUt) {
		this.codDocumentUt = codDocumentUt;
	}

	public String getTitleTi() {
		return titleTi;
	}

	public void setTitleTi(String titleTi) {
		this.titleTi = titleTi;
	}

	public String getLanguageTi() {
		return languageTi;
	}

	public void setLanguageTi(String languageTi) {
		this.languageTi = languageTi;
	}

	public String getAbstractAb() {
		return abstractAb;
	}

	public void setAbstractAb(String abstractAb) {
		this.abstractAb = abstractAb;
	}

	public String getVolumenVl() {
		return volumenVl;
	}

	public void setVolumenVl(String volumenVl) {
		this.volumenVl = volumenVl;
	}

	public String getYearPublicationPy() {
		return yearPublicationPy;
	}

	public void setYearPublicationPy(String yearPublicationPy) {
		this.yearPublicationPy = yearPublicationPy;
	}

	public String getDatePublicationPd() {
		return datePublicationPd;
	}

	public void setDatePublicationPd(String datePublicationPd) {
		this.datePublicationPd = datePublicationPd;
	}

	public String getTitleSourceDi() {
		return titleSourceDi;
	}

	public void setTitleSourceDi(String titleSourceDi) {
		this.titleSourceDi = titleSourceDi;
	}

	public String getIndexKewordsId() {
		return indexKewordsId;
	}

	public void setIndexKewordsId(String indexKewordsId) {
		this.indexKewordsId = indexKewordsId;
	}

	public String getAuthorKewordsDe() {
		return authorKewordsDe;
	}

	public void setAuthorKewordsDe(String authorKewordsDe) {
		this.authorKewordsDe = authorKewordsDe;
	}

	public String getCitedCountNr() {
		return citedCountNr;
	}

	public void setCitedCountNr(String citedCountNr) {
		this.citedCountNr = citedCountNr;
	}

	public Integer getCitedTotalZ9() {
		return citedTotalZ9;
	}

	public void setCitedTotalZ9(Integer citedTotalZ9) {
		this.citedTotalZ9 = citedTotalZ9;
	}

	public Integer getCitedReferenceTc() {
		return citedReferenceTc;
	}

	public void setCitedReferenceTc(Integer citedReferenceTc) {
		this.citedReferenceTc = citedReferenceTc;
	}

	public String getPageCountPg() {
		return pageCountPg;
	}

	public void setPageCountPg(String pageCountPg) {
		this.pageCountPg = pageCountPg;
	}

	public String getPageBp() {
		return pageBp;
	}

	public void setPageBp(String pageBp) {
		this.pageBp = pageBp;
	}

	public String getPageEp() {
		return pageEp;
	}

	public void setPageEp(String pageEp) {
		this.pageEp = pageEp;
	}

	public Funding getFunding() {
		return funding;
	}

	public void setFunding(Funding funding) {
		this.funding = funding;
	}

	public Journal getJournal() {
		return journal;
	}

	public void setJournal(Journal journal) {
		this.journal = journal;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public CDocument getCodCDocument() {
		return codCDocument;
	}

	public void setCodCDocument(CDocument codCDocument) {
		this.codCDocument = codCDocument;
	}

	public void setAffiliationList(List<Integer> listAffiliation){
		this.listCodAffiliation= listAffiliation;
	}

	public List<Integer> getAffiliationList(){
		return listCodAffiliation;
	}


	public List<Integer> getAuthorList() {
		return listCodAuthor;
	}

	public void setAuthorList(List<Integer> authorList) {
		this.listCodAuthor = authorList;
	}
	public Record  getRecord(){
		return record;
	}

}
