package qtriptest.tests;

import org.testng.annotations.Test;

public class DataProviderImpl {
    
  @Test(dataProvider = "QtripData",dataProviderClass = ExternalDataProvider.class)
  public void testDataprovider(String userName, String password){
    System.out.println("USERNAME:: "+userName+ "||"+ "PASSWORD:: "+password);
  }
 
  @Test(dataProvider = "QtripCityData",dataProviderClass = ExternalDataProvider.class)
  public void testCityData(String CityName,String Category_Filter, String DurationFilter, String ExpectedFilteredResults, String ExpectedUnFilteredResults){
    System.out.println("CITY:: "+CityName+ "||"+ "CATEGORY:: "+Category_Filter+ "||"+ "DURATION::"+DurationFilter+"||"+
    "EXPECTEDFILTER:: "+ExpectedFilteredResults+"||"+ "EXPECTEDUNFILTER::"+ExpectedUnFilteredResults);
 
  }

  @Test(dataProvider = "QtripNewUserData",dataProviderClass = ExternalDataProvider.class)
  public void testNewUserData(String NewUserName, String Password, String SearchCity, String AdventureName, String GuestName, String Date, String Count){
    System.out.println("NEWUSERNAME:: "+NewUserName+ "||"+ "PASSWORD:: "+Password+ "||"+ "SEARCHCITY::"+SearchCity+"||"+
    "ADVENTURENAME:: "+AdventureName+"||"+ "GUESTNAME::"+GuestName+ "||"+ "DATE:: "+Date+"||"+ "COUNT:: "+Count);
 
  }

  @Test(dataProvider ="QtripDataSet",dataProviderClass = ExternalDataProvider.class)
  public void testDataSet(String NewUserName,String Password,String dataset1, String dataset2, String dataset3){
     System.out.println("NEWUSERNAME:: "+NewUserName+ "||"+ "PASSWORD:: "+Password+ "||"+ "DATASET1:: "+dataset1+"||"+
    "DATASET2:: "+dataset2+ "||"+"DATASET3:: "+dataset3);
  }
}
