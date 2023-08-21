public class Woman extends Person{
    private String maidenlastName;

    public Woman(String firstName, String lastName, int age) {
        super(firstName, lastName, age);
        this.setMaidenlastName(lastName);
    }
    @Override
    public void registerPartnership(Person newPartner){
        if (getPartner() == null) {
            this.partner = newPartner;
            this.partner.partner = this;
            this.setLastName(newPartner.getLastName());
        }
    }

    @Override
    public void  deregisterPartnership(Boolean divorceHappend){
        if (divorceHappend){
            this.partner.setPartner(null);
            setPartner(null);
            this.setLastName(getMaidenlastName());
        }
    }

    @Override
    public boolean isRetired() {
        return (this.getAge()>=60);
    }

    public String getMaidenlastName() {
        return maidenlastName;
    }

    public void setMaidenlastName(String maidenlastName) {
        this.maidenlastName = maidenlastName;
    }
}
