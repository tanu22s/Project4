package in.co.rays.ors.bean;

import java.util.Date;

/**
 * Faculty JavaBean encapsulates Faculty attributes
 * 
 * @author Tanvi
 * @Version:(4.14.0)
 * 
 */
public class FacultyBean extends BaseBean {

 
	private String firstName;

   
	private String lastName;

    
	private String Gender;

    
	private String loginId;
	
	
	private Date dateofjoining;

  
	private String qualification;

  
	private String mobileno;
	
	
	private String address;

	private long collegeid;

   
	private String collegeName;

  
	private long courseId;

   
	private String courseName;

  
	private long subjectId;

	private String subjectName;


  
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

    public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public Date getDateofjoining() {
		return dateofjoining;
	}

	public void setDateofjoining(Date dateofjoining) {
		this.dateofjoining = dateofjoining;
	}


	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}


	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public long getCollegeid() {
		return collegeid;
	}

	public void setCollegeid(long collegeid) {
		this.collegeid = collegeid;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

    public String getKey() {
        return id + "";
    }

   
    public String getValue() {
        return firstName +""+ lastName;
    }
}
