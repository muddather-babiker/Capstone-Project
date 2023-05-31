package capstone.project.dynamodb;

import capstone.project.dynamodb.models.Driver;
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
}
