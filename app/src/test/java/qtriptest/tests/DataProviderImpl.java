package qtriptest.tests;

import org.testng.annotations.Test;

public class DataProviderImpl {
    
  @Test(dataProvider = "QtripData",dataProviderClass = ExternalDataProvider.class)
  public void testDataprovider(String userName, String password){
    System.out.println("USERNAME:: "+userName+ "||"+ "PASSWORD:: "+password);
  }
}
