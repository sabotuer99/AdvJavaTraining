package lab1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee implements Serializable {

	private static final long serialVersionUID = 1234L;
	
	private String id;
	private String firstName;
	private String lastName;
	private String title;
	private Date hireDate;
	private Date departmentStartDate;
	
	public Employee(){}
	
	public Employee(String id, String firstName, String lastName, String title, Date hireDate){
		this(id, firstName, lastName, title, hireDate, hireDate);
	}
	
	public Employee(String id, String firstName, String lastName, String title, Date hireDate, Date departmentStartDate){
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.hireDate = hireDate;
		this.departmentStartDate = departmentStartDate;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public Date getDepartmentStartDate() {
		return departmentStartDate;
	}
	public void setDepartmentStartDate(Date departmentStartDate) {
		this.departmentStartDate = departmentStartDate;
	}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
		in.defaultReadObject();
		if(this.departmentStartDate == null)
			this.departmentStartDate = this.hireDate;
	}
	
	@Override
	public String toString(){
		return firstName + " " + lastName + " : " + title + "\n" +
				"    Hired: " + new SimpleDateFormat("yyyy/MM/dd").format(hireDate) + "\n" +
				"    Dept Start: " +  new SimpleDateFormat("yyyy/MM/dd").format(departmentStartDate);
	}
}
