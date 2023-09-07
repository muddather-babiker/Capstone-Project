package capstone.project.dynamodb;

import capstone.project.dynamodb.models.Driver;
import capstone.project.exceptions.DriverNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;

public class DriverDao {
    private final DynamoDBMapper dynamoDbMapper;

    @Inject
    public DriverDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }
    public Driver saveDriver(Driver driver) {
        this.dynamoDbMapper.save(driver);
        return driver;
    }
    public Driver getDriver(String driverEmail) {
        Driver driver = this.dynamoDbMapper.load(Driver.class, driverEmail);

        if (driver == null) {
            driver=new Driver();
           // throw new DriverNotFoundException("Could not find driver with driverEmail " + driverEmail);
        }
        return driver;
    }
}
