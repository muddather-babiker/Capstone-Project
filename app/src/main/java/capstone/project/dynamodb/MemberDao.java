package capstone.project.dynamodb;

import capstone.project.dynamodb.models.Driver;
import capstone.project.dynamodb.models.Member;
import capstone.project.exceptions.DriverNotFoundException;
import capstone.project.metrics.MetricsPublisher;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;

public class MemberDao {
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public MemberDao(DynamoDBMapper dynamoDbMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDbMapper;
        this.metricsPublisher = metricsPublisher;
    }
    public Member saveMember(Member member) {
        this.dynamoDbMapper.save(member);
        return member;
    }
    public Member getMember(String memberEmail) {
        Member member = this.dynamoDbMapper.load(Member.class, memberEmail);
        if (member == null) {
            member = new Member();
            //throw new DriverNotFoundException("Could not find Member with Member Email " + memberEmail);
        }
        return member;
    }
}
