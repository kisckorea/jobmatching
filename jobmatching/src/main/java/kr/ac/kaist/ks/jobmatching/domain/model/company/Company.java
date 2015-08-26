package kr.ac.kaist.ks.jobmatching.domain.model.company;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Company {
	private String wantedAuthNo;
	private String busiSize;
	private String jobsNm;
	private String salTpNm;
	private String enterTpNm;
	private String eduNm;
	private String workRegion;
	
	public Company(String wantedAuthNo, String busiSize, String jobsNm,
			String salTpNm, String enterTpNm, String eduNm, String workRegion) {
		this.wantedAuthNo = wantedAuthNo;
		this.busiSize = busiSize;
		this.jobsNm = jobsNm;
		this.salTpNm = salTpNm;
		this.enterTpNm = enterTpNm;
		this.eduNm = eduNm;
		this.workRegion = workRegion;
	}
	
	public String getWantedAuthNo() {
		return wantedAuthNo;
	}
	public String getBusiSize() {
		return busiSize;
	}
	public String getJobsNm() {
		return jobsNm;
	}
	public String getSalTpNm() {
		return salTpNm;
	}
	public String getEnterTpNm() {
		return enterTpNm;
	}
	public String getEduNm() {
		return eduNm;
	}
	public String getWorkRegion() {
		return workRegion;
	}

	@Override
	public boolean equals(Object o) {
		if(o == null || this.getClass() != o.getClass())
			return false;
		if(this == o)
			return true;
		Company other = (Company) o;
		return new EqualsBuilder().append(getWantedAuthNo(), other.getWantedAuthNo()).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getWantedAuthNo()).toHashCode();
	}
	
	public String toString(){
		return new ToStringBuilder(this).append(getWantedAuthNo()).append(getBusiSize()).append(getBusiSize()).append(getJobsNm()).append(getSalTpNm()).append(getWorkRegion()).toString();
	}
	
	
}
