package capstone.project.activity;

import capstone.project.activity.requests.AddDriverRequest;
import capstone.project.activity.results.AddDriverResult;
import capstone.project.converters.ModelConverter;
import capstone.project.dynamodb.DriverDao;
import capstone.project.dynamodb.models.Driver;
import capstone.project.models.DriverModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class AddDriverActivity {
    private final Logger log = LogManager.getLogger();
    private final DriverDao driverDao;


    @Inject
    public AddDriverActivity(DriverDao driverDao) {
        this.driverDao = driverDao;
    }
    public AddDriverResult handleRequest(final AddDriverRequest addDriverRequest) {
        log.info("Received AddDriverRequest {}",addDriverRequest);
        Driver newDriver = new Driver();
        newDriver.setDriverEmail(addDriverRequest.getDriverEmail());
        newDriver.setName(addDriverRequest.getName());
        newDriver.setPhoneNumber(addDriverRequest.getPhoneNumber());
        newDriver.setDateOfBirth(addDriverRequest.getDateOfBirth());
        newDriver.setStreetAddress(addDriverRequest.getStreetAddress());
        newDriver.setCity(addDriverRequest.getCity());
        newDriver.setState(addDriverRequest.getState());
        newDriver.setZipCode(addDriverRequest.getZipCode());

        driverDao.saveDriver(newDriver);

        DriverModel driverModel = new ModelConverter().toDriverModel(newDriver);
        return AddDriverResult.builder()
                .withDriver(driverModel)
                .build();


    }

}
