package capstone.project.dynamodb;

import capstone.project.dynamodb.models.Trip;
import capstone.project.exceptions.TripNotFoundException;
import capstone.project.metrics.MetricsPublisher;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.*;

public class TripDao {
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;
    private static final String Trips_Member_INDEX = "TripsMemberIndex";
    private static final String Trips_Driver_INDEX = "TripsDriverIndex";
    private final Logger logger = LogManager.getLogger();

    @Inject
    public TripDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }
    public Trip getTrip(String tripId,String memberId) {
        Trip trip = this.dynamoDbMapper.load(Trip.class,tripId,memberId);

        if (trip == null) {
            throw new TripNotFoundException("Could not find any trip with this tripID " + tripId);
        }
        return trip;
    }
    public Trip saveTrip(Trip trip) {
        this.dynamoDbMapper.save(trip);
        return trip;
    }
    public Trip removeTrip(String tripId,String memberId) {
        Trip tripToRemove;
        try{
            tripToRemove=getTrip(tripId,memberId);
        }
        catch (TripNotFoundException e){
            throw new TripNotFoundException("Could not find any trip with this tripID " + tripId );
        }
        this.dynamoDbMapper.delete(tripToRemove);
        return tripToRemove;
    }
    public List<Trip> searchTrips(String memberId, String pickupDateTime ) {
        logger.info("the memberId is" ,memberId);
        logger.info("the pickupDateTime is" ,pickupDateTime);

        if (pickupDateTime==null) {
            Map<String, AttributeValue> valueMap = new HashMap<>();
            valueMap.put(":memberId", new AttributeValue().withS(memberId));
            DynamoDBQueryExpression<Trip> queryExpression = new DynamoDBQueryExpression<Trip>()
                    .withIndexName(Trips_Member_INDEX)
                    .withConsistentRead(false)
                    .withKeyConditionExpression("memberId = :memberId")
                    .withExpressionAttributeValues(valueMap);
            PaginatedQueryList<Trip> tripsFromGSI = dynamoDbMapper.query(Trip.class, queryExpression);
            return tripsFromGSI;
        }
        else {
            Map<String, AttributeValue> valueMap = new HashMap<>();
            valueMap.put(":memberId", new AttributeValue().withS(memberId));
            valueMap.put(":pickupDateTime", new AttributeValue().withS(pickupDateTime));
            DynamoDBQueryExpression<Trip> queryExpression = new DynamoDBQueryExpression<Trip>()
                    .withIndexName(Trips_Member_INDEX)
                    .withConsistentRead(false)
                    .withKeyConditionExpression("memberId = :memberId and pickupDateTime = :pickupDateTime")
                    .withExpressionAttributeValues(valueMap);
            PaginatedQueryList<Trip> tripsFromGSI = dynamoDbMapper.query(Trip.class, queryExpression);
            return tripsFromGSI;
        }
    }
    public List<Trip> searchTripsDriver(String pickupZipCode, String selected ) {
        logger.info("the pickupCity is" ,pickupZipCode);
        logger.info("the selected is" ,selected);

        if (selected==null) {
            Map<String, AttributeValue> valueMap = new HashMap<>();
            valueMap.put(":pickupZipCode", new AttributeValue().withS(pickupZipCode));
            DynamoDBQueryExpression<Trip> queryExpression = new DynamoDBQueryExpression<Trip>()
                    .withIndexName(Trips_Driver_INDEX)
                    .withConsistentRead(false)
                    .withKeyConditionExpression("pickupZipCode = :pickupZipCode")
                    .withExpressionAttributeValues(valueMap);
            PaginatedQueryList<Trip> tripsFromGSI = dynamoDbMapper.query(Trip.class, queryExpression);
            return tripsFromGSI;
        }
        else {
            Map<String, AttributeValue> valueMap = new HashMap<>();
            valueMap.put(":pickupZipCode", new AttributeValue().withS(pickupZipCode));
            valueMap.put(":selected", new AttributeValue().withS(selected));
            DynamoDBQueryExpression<Trip> queryExpression = new DynamoDBQueryExpression<Trip>()
                    .withIndexName(Trips_Driver_INDEX)
                    .withConsistentRead(false)
                    .withKeyConditionExpression("pickupZipCode = :pickupZipCode and selected = :selected")
                    .withExpressionAttributeValues(valueMap);
            PaginatedQueryList<Trip> tripsFromGSI = dynamoDbMapper.query(Trip.class, queryExpression);
            return tripsFromGSI;
        }
    }
}
