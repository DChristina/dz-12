import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class PersonTest {

    private enum ExpectedStatus{
        PASSED,
        FAILED,
        EXCEPTION
    }

    @DataProvider(name = "creationPersonData")
    public Object[][] parseForCreationData (){
        return new Object[][]{
                {ExpectedStatus.PASSED,"Tom Sot 35","Tom", "Sot",35},
                {ExpectedStatus.FAILED,"Tom Sot 35","Tim","Sit",20},
                {ExpectedStatus.EXCEPTION,"",null,"",0}
        };
    }
    @DataProvider(name = "SettingPersonData")
    public Object[][] parseForSettingData (){
        return new Object[][]{
                {ExpectedStatus.PASSED,"Tom Sot 35","Adam", "Sendler","Adam","Sendler"},
                {ExpectedStatus.FAILED,"Tom Sot 35","Adam", "Sendler","Tom","Sot"},
                {ExpectedStatus.FAILED,"Tom Sot 35","Adam", "Sendler","Adim","Sot"},
                {ExpectedStatus.FAILED,"Tom Sot 35","Adam", "Sendler","Tom","Sedler"},
                {ExpectedStatus.EXCEPTION,null,null,null,"Adam","Sendler" },
                {ExpectedStatus.EXCEPTION,null,"Adam", "Sendler","Adam","Sendler"},
                {ExpectedStatus.EXCEPTION,"Tom",null, "Sendler","Adam","Sendler"},
                {ExpectedStatus.EXCEPTION,"Tom Sot","Adam", null,"Adam","Sendler"},
                {ExpectedStatus.EXCEPTION,"Tom 35","Adam", "Sendler",null,"Sendler"},
                {ExpectedStatus.EXCEPTION,"Sot 35","Adam", "Sendler","Adam",null}
        };
    }
    @DataProvider(name = "retiredManStatusPersonData")
    public Object[][] parseRetiredStatus (){
        return new Object[][]{
                {ExpectedStatus.PASSED,"Tom Sot 75",true},
                {ExpectedStatus.PASSED,"John Wik 88",true},
                {ExpectedStatus.PASSED,"Tom Sot 65",true},
                {ExpectedStatus.FAILED,"Tom Sot 35",true},
                {ExpectedStatus.FAILED,"Tom Sot 60",true},
                {ExpectedStatus.EXCEPTION,"88",null},
                {ExpectedStatus.EXCEPTION,null,true},
                {ExpectedStatus.EXCEPTION,"Tom",true},
                {ExpectedStatus.EXCEPTION,"Tom Sot",true},
                {ExpectedStatus.EXCEPTION,"Tom 35",true},
                {ExpectedStatus.EXCEPTION,"Sot 35",true}
        };
    }
    @DataProvider(name = "retiredWomanStatusPersonData")
    public Object[][] parseWomanRetiredStatus (){
        return new Object[][]{
                {ExpectedStatus.PASSED,"Emma Stoun 60",true},
                {ExpectedStatus.PASSED,"Kary Wik 88",true},
                {ExpectedStatus.PASSED,"Samanta Sot 65",true},
                {ExpectedStatus.FAILED,"Nikki Breadshow 35",true},
                {ExpectedStatus.FAILED,"Rima Nalson 59",true},
                {ExpectedStatus.EXCEPTION,"88",null},
                {ExpectedStatus.EXCEPTION,null,true},
                {ExpectedStatus.EXCEPTION,"Rima",true},
                {ExpectedStatus.EXCEPTION,"Rima Wik",true},
                {ExpectedStatus.EXCEPTION,"Rima 35",true},
                {ExpectedStatus.EXCEPTION,"Sot 35",true}
        };
    }

    @DataProvider(name = "gettingMarriageData")
    public Object[][] registerMarriageData (){
        return new Object[][]{
                {ExpectedStatus.PASSED,"Tom Sot 35,Lola Huston 24","Sot", "Tom"},
                {ExpectedStatus.FAILED,"Tom Sot 35,Lola Huston 24","Huston", null},
                {ExpectedStatus.EXCEPTION,"","Haston","Tom"},
                {ExpectedStatus.EXCEPTION,"Tom Sot 35",null,"Tom"},
                {ExpectedStatus.EXCEPTION,null,"Haston","Tom"},
                {ExpectedStatus.EXCEPTION,"Tom Sot","Sot","Tom"}

        };
    }
    @DataProvider(name = "gettingMarriageDataMan")
    public Object[][] registerMarriageDataMan (){
        return new Object[][]{
                {ExpectedStatus.PASSED,"Tom Sot 35,Lola Huston 24","Sot", "Sot"},
                {ExpectedStatus.PASSED,"Sam Pitbul 70,Suzy Polson 65","Pitbul", "Pitbul"},
                {ExpectedStatus.FAILED,"Tom Sot 35,Lola Huston 24","Huston", "Huston"},
                {ExpectedStatus.FAILED,"Sam Pitbul 70,Suzy Polson 65","Polson", "Suzy"},
                {ExpectedStatus.EXCEPTION,"","Haston","Sot"},
                {ExpectedStatus.EXCEPTION,"Tom Sot 35",null,"Tom"},
                {ExpectedStatus.EXCEPTION,null,"Haston","Tom"},
                {ExpectedStatus.EXCEPTION,"Lola Huston 24","Sot",null}

        };
    }

    @DataProvider(name = "gettingDivorcedData")
    public Object[][] deregisterMarriageData (){
        return new Object[][]{
                {ExpectedStatus.PASSED,"Tom Sot 35,Lola Huston 24","Huston", "Sot"},
                {ExpectedStatus.PASSED,"Sam Pitbul 70,Suzy Polson 65","Polson", "Pitbul"},
                {ExpectedStatus.FAILED,"Tom Sot 35,Lola Huston 24","Sot", "Huston"},
                {ExpectedStatus.FAILED,"Sam Pitbul 70,Suzy Polson 65","Pitbul", "Suzy"},
                {ExpectedStatus.EXCEPTION,"","Haston","Sot"},
                {ExpectedStatus.EXCEPTION,"Tom Sot 35",null,"Tom"},
                {ExpectedStatus.EXCEPTION,null,"Haston","Tom"},
                {ExpectedStatus.EXCEPTION,"Lola Huston 24","Sot",null}
        };
    }

    @Test(dataProvider = "creationPersonData", groups="lesson22")
    public void testChechGettingNamesSurnamesOfPerson(ExpectedStatus status,String personsData,String expectedFirstNameValue, String expectedLastNameValue, Integer expectedAge){

        if(status==ExpectedStatus.PASSED){
            String[] personData = personsData.split(" ");
            Man person1 = new Man (personData[0],personData[1],parseInt(personData[2]));
            Assert.assertEquals(person1.getLastName(),expectedLastNameValue,"There is an error in the getting last name process!");
            Assert.assertEquals(person1.getFirstName(),expectedFirstNameValue,"There is an error in the getting first name process!");
            Assert.assertEquals(person1.getAge(),expectedAge,"There is an error in the getting age process!");
        } else if (status==ExpectedStatus.FAILED){
            String[] personData = personsData.split(" ");
            Man person1 = new Man (personData[0],personData[1],parseInt(personData[2]));
            Assert.assertNotEquals(person1.getLastName(),expectedLastNameValue,"There is an error in the getting last name process!");
            Assert.assertNotEquals(person1.getFirstName(),expectedFirstNameValue,"There is an error in the getting first name process!");
        } else{Assert.assertThrows(new Assert.ThrowingRunnable( ) {
            @Override
            public void run() throws Throwable {
                String[] personData = personsData.split(" ");
                Man person1 = new Man (personData[0],personData[1],parseInt(personData[2]));
            }
        });
        }
    }

    @Test(dataProvider = "SettingPersonData", groups="lesson22")
    public void testCheckSettingNamesSurnamesOfPerson(ExpectedStatus status,String personsData,String newFirstNameValue, String newLastNameValue, String expectedFirstNameValue, String expectedLastNameValue){
        if(status==ExpectedStatus.PASSED){
            String[] personData = personsData.split(" ");
            Man person1 = new Man (personData[0],personData[1],parseInt(personData[2]));
            person1.setFirstName(newFirstNameValue);
            person1.setLastName(newLastNameValue);
            Assert.assertEquals(person1.getLastName(),expectedLastNameValue,"There is an error in the setting last name process!");
            Assert.assertEquals(person1.getFirstName(),expectedFirstNameValue,"There is an error in the setting first name process!");
        }else if (status==ExpectedStatus.FAILED){
            String[] personData = personsData.split(" ");
            Man person1 = new Man (personData[0],personData[1],parseInt(personData[2]));
            person1.setFirstName(newFirstNameValue);
            person1.setLastName(newLastNameValue);
            Assert.assertNotEquals(person1.getLastName(),expectedLastNameValue,"There is an error in the setting last name process!");
            Assert.assertNotEquals(person1.getFirstName(),expectedFirstNameValue,"There is an error in the setting first name process!");
        }else{Assert.assertThrows(new Assert.ThrowingRunnable( ) {
            @Override
            public void run() throws Error {
                String[] personData = personsData.split(" ");
                Man person1 = new Man (personData[0],personData[1],parseInt(personData[2]));
                person1.setFirstName(newFirstNameValue);
                person1.setLastName(newLastNameValue);
            }
        });
        }
    }
    @Test(dataProvider = "retiredManStatusPersonData", groups="lesson22")
    public void testCheckManRetiresStatud(ExpectedStatus status,String personsData,Boolean expectedStatus){
        if(status==ExpectedStatus.PASSED){
            String[] personData = personsData.split(" ");
            Man person1 = new Man (personData[0],personData[1],parseInt(personData[2]));
            Assert.assertEquals(person1.isRetired(),expectedStatus,"There is an error in the person checking retire status process!");
        }else if (status==ExpectedStatus.FAILED){
            String[] personData = personsData.split(" ");
            Man person1 = new Man (personData[0],personData[1],parseInt(personData[2]));
            Assert.assertNotEquals(person1.isRetired(),expectedStatus,"There is an error in the person checking retire status process!");
        }else{Assert.assertThrows(new Assert.ThrowingRunnable( ) {
            @Override
            public void run() throws Error {
                String[] personData = personsData.split(" ");
                Man person1 = new Man (personData[0],personData[1],parseInt(personData[2]));
            }
        });
        }
    }

    @Test(dataProvider = "retiredWomanStatusPersonData", groups="lesson22")
    public void testCheckWomanRetiresStatus(ExpectedStatus status,String personsData,Boolean expectedStatus){
        if(status==ExpectedStatus.PASSED){
            String[] personData = personsData.split(" ");
            Woman person1 = new Woman (personData[0],personData[1],parseInt(personData[2]));
            Assert.assertEquals(person1.isRetired(),expectedStatus,"There is an error in the person checking retire status process!");
        }else if (status==ExpectedStatus.FAILED){
            String[] personData = personsData.split(" ");
            Woman person1 = new Woman (personData[0],personData[1],parseInt(personData[2]));
            Assert.assertNotEquals(person1.isRetired(),expectedStatus,"There is an error in the person checking retire status process!");
        }else{Assert.assertThrows(new Assert.ThrowingRunnable( ) {
            @Override
            public void run() throws Error {
                String[] personData = personsData.split(" ");
                Woman person1 = new Woman (personData[0],personData[1],parseInt(personData[2]));
            }
        });
        }
    }

    public ArrayList<Person> registerPartnershipForWoman (String personsData){
        String[] coupleData = personsData.split(",");
        String[] manData = coupleData[0].split(" ");
        String[] womanData = coupleData[1].split(" ");
        Man person1 = new Man(manData[0],manData[1], parseInt(manData[2]));
        Woman person2 = new Woman(womanData[0],womanData[1], parseInt(womanData[2]));
        person2.registerPartnership(person1);
        ArrayList<Person>  partners = new ArrayList<>();
        partners.add(person1);
        partners.add(person2);
        return partners;
    }
    @Test(dataProvider = "gettingMarriageData", groups="lesson22")
    public void testCheckRegisterPartnetProcessForWaman(ExpectedStatus status,String personsData,String newLastName, String partnerName){
        if(status==ExpectedStatus.PASSED){
            Woman person2 = (Woman) registerPartnershipForWoman(personsData).get(1);
            Assert.assertEquals(person2.getLastName(),newLastName,"Setting partners doesn't work!");
            Assert.assertEquals(person2.getPartner().getFirstName(),partnerName,"Setting partners doesn't work!");
        }else if (status==ExpectedStatus.FAILED){
            Woman person2 = (Woman) registerPartnershipForWoman(personsData).get(1);
            Assert.assertNotEquals(person2.getLastName(),newLastName,"Setting partners doesn't work!");
            Assert.assertNotEquals(person2.getPartner().getFirstName(),partnerName,"Setting partners doesn't work!");
        }else{Assert.assertThrows(new Assert.ThrowingRunnable( ) {
            @Override
            public void run() throws Error {
                Woman person2 = (Woman) registerPartnershipForWoman(personsData).get(1);
            }
        });
        }
    }

    public ArrayList<Person> registerPartnershipForMan (String personsData){
        String[] coupleData = personsData.split(",");
        String[] manData = coupleData[0].split(" ");
        String[] womanData = coupleData[1].split(" ");
        Man person1 = new Man(manData[0],manData[1], parseInt(manData[2]));
        Woman person2 = new Woman(womanData[0],womanData[1], parseInt(womanData[2]));
        person1.registerPartnership(person2);
        ArrayList<Person>  partners = new ArrayList<>();
        partners.add(person1);
        partners.add(person2);
        return partners;
    }

    @Test(dataProvider = "gettingMarriageDataMan", groups="lesson22")
    public void testCheckRegisterPartnetProcessForMan(ExpectedStatus status,String personsData,String partnerLastName, String manLastName){
        if(status==ExpectedStatus.PASSED){
            Man person1 = (Man) registerPartnershipForMan(personsData).get(0);
            Assert.assertEquals(person1.getLastName(),manLastName,"Setting partners doesn't work!");
            Assert.assertEquals(person1.getPartner().getLastName(),partnerLastName,"Setting partners doesn't work!");
        }else if (status==ExpectedStatus.FAILED){
            Man person1 = (Man) registerPartnershipForMan(personsData).get(0);
            Assert.assertNotEquals(person1.getLastName(),manLastName,"Setting partners doesn't work!");
            Assert.assertNotEquals(person1.getPartner().getFirstName(),partnerLastName,"Setting partners doesn't work!");
        }else{Assert.assertThrows(new Assert.ThrowingRunnable( ) {
            @Override
            public void run() throws Error {
                Man person1 = (Man) registerPartnershipForMan(personsData).get(0);
            }
        });
        }
    }

    public ArrayList<Person> registerAndDeregisterMarriageForMan (String personsData){
        String[] coupleData = personsData.split(",");
        String[] manData = coupleData[0].split(" ");
        String[] womanData = coupleData[1].split(" ");
        Man person1 = new Man(manData[0],manData[1], parseInt(manData[2]));
        Woman person2 = new Woman(womanData[0],womanData[1], parseInt(womanData[2]));
        ArrayList<Person>  partners = new ArrayList<>();
        partners.add(person1);
        partners.add(person2);
        person1.registerPartnership(person2);
        person1.deregisterPartnership(true);
        return partners;
    }
    @Test(dataProvider = "gettingDivorcedData", groups="lesson22")
    public void testDeregisterOfPartnershipForMan(ExpectedStatus status,String personsData,String womanLastNameAfterDivorce, String manLastNameAfterDivorce){

        if(status==ExpectedStatus.PASSED){
            Person person1 = registerAndDeregisterMarriageForMan(personsData).get(0);
            Person person2 = registerAndDeregisterMarriageForMan(personsData).get(1);
            Assert.assertEquals(person1.getLastName(),manLastNameAfterDivorce,"Deregisting partners process doesn't work, woman lastName is wrong!");
            Assert.assertNull(person1.getPartner(),"Deregisting partners process doesn't work, field partner isn't empty!");
            Assert.assertEquals(person2.getLastName(),womanLastNameAfterDivorce,"Deregisting partners process doesn't work, woman lastName is wrong!");
        }else if (status==ExpectedStatus.FAILED){
            Person person1 = registerAndDeregisterMarriageForMan(personsData).get(0);
            Person person2 = registerAndDeregisterMarriageForMan(personsData).get(1);
            Assert.assertNotEquals(person1.getLastName(),manLastNameAfterDivorce,"Deregisting partners process doesn't work!");
            Assert.assertNotEquals(person1.getPartner(),person2,"Deregisting partners process doesn't work,field partner isn't empty!");
            Assert.assertNotEquals(person2.getLastName(),womanLastNameAfterDivorce,"Deregisting partners process doesn't work, woman lastName is wrong!");
        }else{Assert.assertThrows(new Assert.ThrowingRunnable( ) {
            @Override
            public void run() throws Error {
                    Person person1 = registerAndDeregisterMarriageForMan(personsData).get(0);
                    Person person2 = registerAndDeregisterMarriageForMan(personsData).get(1);
            }
        });
        }

    }
    public ArrayList<Person> registerAndDeregisterMarriageForWoman (String personsData){
        String[] coupleData = personsData.split(",");
        String[] manData = coupleData[0].split(" ");
        String[] womanData = coupleData[1].split(" ");
        Man person1 = new Man(manData[0],manData[1], parseInt(manData[2]));
        Woman person2 = new Woman(womanData[0],womanData[1], parseInt(womanData[2]));
        ArrayList<Person>  partners = new ArrayList<>();
        partners.add(person1);
        partners.add(person2);
        person2.registerPartnership(person1);
        person2.deregisterPartnership(true);
        return partners;
    }
    @Test(dataProvider = "gettingDivorcedData", groups="lesson22")
    public void testDeregisterOfPartnershipForWoman(ExpectedStatus status,String personsData,String womanLastNameAfterDivorce, String manLastNameAfterDivorce){

        if(status==ExpectedStatus.PASSED){
            Person person1 = registerAndDeregisterMarriageForWoman(personsData).get(0);
            Person person2 = registerAndDeregisterMarriageForWoman(personsData).get(1);
            Assert.assertEquals(person1.getLastName(),manLastNameAfterDivorce,"Deregisting partners process doesn't work, woman lastName is wrong!");
            Assert.assertNull(person2.getPartner(),"Deregisting partners process doesn't work, field partner isn't empty!");
            Assert.assertEquals(person2.getLastName(),womanLastNameAfterDivorce,"Deregisting partners process doesn't work, woman lastName is wrong!");
        }else if (status==ExpectedStatus.FAILED){
            Person person1 = registerAndDeregisterMarriageForWoman(personsData).get(0);
            Person person2 = registerAndDeregisterMarriageForWoman(personsData).get(1);
            Assert.assertNotEquals(person1.getLastName(),manLastNameAfterDivorce,"Deregisting partners process doesn't work!");
            Assert.assertNotEquals(person1.getPartner(),person2,"Deregisting partners process doesn't work,field partner isn't empty!");
            Assert.assertNotEquals(person2.getLastName(),womanLastNameAfterDivorce,"Deregisting partners process doesn't work, woman lastName is wrong!");
        }else{Assert.assertThrows(new Assert.ThrowingRunnable( ) {
            @Override
            public void run() throws Error {
                Person person1 = registerAndDeregisterMarriageForWoman(personsData).get(0);
                Person person2 = registerAndDeregisterMarriageForWoman(personsData).get(1);
            }
        });
        }

    }
}
