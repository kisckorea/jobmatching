package kr.ac.kaist.ks.jobmatching.domain.model.req;

public class Req {
	private String accptno;
	private String busiSize;
	private String jobsNm;
	private String workRegion;
	
	public Req(String accptno, String busiSize, String jobsNm, String workRegion) {
		this.accptno = accptno;
		this.busiSize = busiSize;
		this.jobsNm = jobsNm;
		this.workRegion = workRegion;
	}

	public String getAccptno() {
		return accptno;
	}

	public String getBusiSize() {
		return busiSize;
	}

	public String getJobsNm() {
		return jobsNm;
	}

	public String getWorkRegion() {
		return workRegion;
	}
	

}
