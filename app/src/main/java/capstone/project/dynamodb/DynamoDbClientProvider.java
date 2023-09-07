package capstone.project.dynamodb;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

/**
 * Provides an {@code AmazonDynamoDB} client using default AWS credentials provider chain.
 */
public class DynamoDbClientProvider {

    /**
     * Returns DynamoDB client using default region.
     * @return AmazonDynamoDB
     */
    public static AmazonDynamoDB getDynamoDBClient() {
        return getDynamoDBClient(Regions.US_EAST_2);
    }

    /**
     * Returns DynamoDB client using default region.
     * @param region If present, will be used as the region for the DynamoDB client
     * @return AmazonDynamoDB
     */
    public static AmazonDynamoDB getDynamoDBClient(Regions region) {
        if (null == region) {
            throw new IllegalArgumentException("region cannot be null");
        }

        return AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                .withRegion(region)
                .build();
    }
}
