package capstone.project.lambda;

import capstone.project.activity.requests.GetTripRequest;
import capstone.project.activity.requests.SearchTripsRequest;
import capstone.project.activity.results.SearchTripsResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchTripsLambda extends LambdaActivityRunner<SearchTripsRequest, SearchTripsResult>
        implements RequestHandler<LambdaRequest<SearchTripsRequest>, LambdaResponse> {
    private final Logger log = LogManager.getLogger();
    @Override
    public LambdaResponse handleRequest(LambdaRequest<SearchTripsRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromQuery(query ->
                        SearchTripsRequest.builder()
                                .withMemberId(query.get("memberId"))
                                .withPickupDateTime(query.get("pickupDateTime"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideSearchTripsActivity().handleRequest(request)
        );
    }
}
