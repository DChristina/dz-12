public class Main {
    public static void main(String[] args) {

        Man person1 = new Man("Tom","Sot", 35);
        Woman person2 = new Woman("Lola","Huston",24);
        Man person3 = new Man("Nikki","lius",66);
        Woman person4  = new Woman("Jane","Loverson",61);

        System.out.println( "Last name before get marriage: " + person2.getLastName());

        person2.registerPartnership(person1);

        System.out.println( "Last name after got marride: " +  person2.getLastName());

        person2.deregisterPartnership(true);

        System.out.println( "Last name after got divorced: " +  person2.getLastName());

        System.out.println(person1.getFirstName() + "is retired: "+ person1.isRetired());
        System.out.println(person2.getFirstName() + "is retired: "+ person2.isRetired());
        System.out.println(person3.getFirstName() + "is retired: "+ person3.isRetired());
        System.out.println(person4.getFirstName() + "is retired: "+ person4.isRetired());

    }
}
