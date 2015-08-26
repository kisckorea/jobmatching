package kr.ac.kaist.ks.jobmatching.domain.model.user;

import kr.ac.kaist.ks.jobmatching.domain.model.company.Company;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class User {
	private String acceptno;
	private String birthday;
	private String sex;
	private String zipcode;
	private String addr;	
	
	public User(String acceptno, String birthday, String sex, String zipcode, String addr) {
		this.acceptno = acceptno;
		this.birthday = birthday;
		this.sex = sex;
		this.zipcode = zipcode;
		this.addr = addr;
	}

	public String getAcceptno() {
		return acceptno;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getSex() {
		return sex;
	}

	public String getZipcode() {
		return zipcode;
	}

	public String getAddr() {
		return addr;
	}

	@Override
	public boolean equals(Object o) {
		if(o == null || this.getClass() != o.getClass())
			return false;
		if(this == o)
			return true;
		Company other = (Company) o;
		return new EqualsBuilder().append(getAcceptno(), other.getWantedAuthNo()).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getAcceptno()).toHashCode();
	}
	
	public String toString(){
		return new ToStringBuilder(this).append(getAcceptno()).append(getBirthday()).append(getSex()).append(getZipcode()).append(getAddr()).toString();
	}
	
}
