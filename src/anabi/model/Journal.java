

package anabi.model;



/**
 *
 * @author yosamac
 */


public class Journal {

	private Record record;
	private Integer codJournal = 0;
	private String nameSo;
	private String issnSo;
	private String webSciencewc;
	private String researchAreasc;
	private String issueIs;
	private String sourceAbbrej9;
	private String sourceAbbreji;
	//	private Publisher codPublisher;

	public Journal() {
	}

	public Journal( String nameSo,
			String issnSo, String webSciencewc, String researchAreasc,
			String issueIs, String sourceAbbrej9, String sourceAbbreji, Record record) {
		super();
		this.record = record;
		this.codJournal =+ 1;
		this.nameSo = nameSo;
		this.issnSo = issnSo;
		this.webSciencewc = webSciencewc;
		this.researchAreasc = researchAreasc;
		this.issueIs = issueIs;
		this.sourceAbbrej9 = sourceAbbrej9;
		this.sourceAbbreji = sourceAbbreji;
	}



	public Integer getCodJournal() {
		return codJournal;
	}

	public void setCodJournal(Integer codJournal) {
		this.codJournal = codJournal;
	}

	public String getNameSo() {
		return nameSo;
	}

	public void setNameSo(String nameSo) {
		this.nameSo = nameSo;
	}

	public String getIssnSo() {
		return issnSo;
	}

	public void setIssnSo(String issnSo) {
		this.issnSo = issnSo;
	}

	public String getWebSciencewc() {
		return webSciencewc;
	}

	public void setWebSciencewc(String webSciencewc) {
		this.webSciencewc = webSciencewc;
	}

	public String getResearchAreasc() {
		return researchAreasc;
	}

	public void setResearchAreasc(String researchAreasc) {
		this.researchAreasc = researchAreasc;
	}

	public String getIssueIs() {
		return issueIs;
	}

	public void setIssueIs(String issueIs) {
		this.issueIs = issueIs;
	}

	public String getSourceAbbrej9() {
		return sourceAbbrej9;
	}

	public void setSourceAbbrej9(String sourceAbbrej9) {
		this.sourceAbbrej9 = sourceAbbrej9;
	}

	public String getSourceAbbreji() {
		return sourceAbbreji;
	}

	public void setSourceAbbreji(String sourceAbbreji) {
		this.sourceAbbreji = sourceAbbreji;
	}

	//	public Publisher getCodPublisher() {
	//		return codPublisher;
	//	}
	//
	//	public void setCodPublisher(Publisher codPublisher) {
	//		this.codPublisher = codPublisher;
	//	}

	public Record getRecord(){
		return record;
	}

}
