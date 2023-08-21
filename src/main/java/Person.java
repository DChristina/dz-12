public abstract class Person {
    private String firstName;
    private String lastName;
    private int age;
    public Person partner;


    public Person(String firstName, String lastName, int age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;

    }
    public void registerPartnership(Person newPartner){
        if (getPartner() == null) {
            this.partner = newPartner;
            this.partner.partner = this;
            newPartner.setLastName(this.getLastName());
            // this.partner.setLastName(this.partner.getLastName());
        } else {
            System.out.println("This person have already has partner, divorce process is needed before the marriage process " );
        }
    }

    public void  deregisterPartnership(Boolean divorceHappend) {
        if (divorceHappend) {
            this.partner.deregisterPartnership(true);
            setPartner(null);
        }
    }

    public boolean isRetired(){
        return false;
    }

    public Person getPartner() {
        return partner;
    }

    public void setPartner(Person partner) {
        this.partner = partner;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

}
