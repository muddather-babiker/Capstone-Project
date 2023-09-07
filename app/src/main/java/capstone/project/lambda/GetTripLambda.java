package capstone.project.lambda;

import capstone.project.activity.requests.GetTripRequest;
import capstone.project.activity.results.GetTripResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetTripLambda extends LambdaActivityRunner<GetTripRequest, GetTripResult>
        implements RequestHandler<LambdaRequest<GetTripRequest>, LambdaResponse> {
    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetTripRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
                () -> input.fromQuery(query ->
                        GetTripRequest.builder()
                                .withTripId(query.get("tripId"))
                                .withMemberId(query.get("memberId"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetTripActivity().handleRequest(request)
        );
    }
}
