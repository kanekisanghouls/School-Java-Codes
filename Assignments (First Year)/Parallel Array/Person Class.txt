public class Person {
    private int idNo;
    private String lastname,firstname;
    public Person(int idNo, String lastname, String firstname) {
        this.idNo = idNo;
        this.lastname = lastname;
        this.firstname = firstname;
    }
    public int getIdNo() {
        return idNo;
    }
    public void setIdNo(int idNo) {
        this.idNo = idNo;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
