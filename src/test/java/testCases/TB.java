package testCases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TB {

    @DataProvider
    public Object[][] setData(){
        Object[][] obj = new Object[2][2];
        obj[0][0] = "Sadia";
        obj[0][1] = 25;
        obj[1][0] = "Nasim";
        obj[1][1] = 30;

        Object[][] obj2 = new Object[][]{
                {"Sadia__1", 28},
                {"Nasim__1", 33}
        };
        return obj2;
    }

    @Test(dataProvider = "setData")
    public void testMyDP(String name, int age){
        System.out.println(name + "-->" + age);
        System.out.println(age+3);
    }
}
