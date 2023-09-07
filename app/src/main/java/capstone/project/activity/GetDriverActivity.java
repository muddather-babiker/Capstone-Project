package capstone.project.activity;

import capstone.project.activity.requests.GetDriverRequest;
import capstone.project.activity.results.GetDriverResult;
import capstone.project.converters.ModelConverter;
import capstone.project.dynamodb.DriverDao;
import capstone.project.dynamodb.models.Driver;
import capstone.project.models.DriverModel;

import javax.inject.Inject;
public class GetDriverActivity {
private final DriverDao driverDao;

@Inject
public GetDriverActivity(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    public GetDriverResult handleRequest(final GetDriverRequest getDriverRequest) {
        String requestedDriverEmail = getDriverRequest.getDriverEmail();
        Driver driver = driverDao.getDriver(requestedDriverEmail);
        DriverModel driverModel = new ModelConverter().toDriverModel(driver);

        return GetDriverResult.builder()
                .withDriver(driverModel)
                .build();
    }

}
