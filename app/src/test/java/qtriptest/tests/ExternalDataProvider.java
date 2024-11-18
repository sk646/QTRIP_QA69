package qtriptest.tests;

import qtriptest.DP;
import java.io.IOException;
import org.testng.annotations.DataProvider;

public class ExternalDataProvider {
  
@DataProvider(name="QtripData")
public Object[][] QtripData() throws IOException{
    DP dataProvider = new DP();
    return dataProvider.dpMethod("TestCase01");
}

@DataProvider(name="QtripCityData")
public Object[][] QtripCityData() throws IOException{
    DP data = new DP();
    return data.dpMethod("TestCase02");
}

}
