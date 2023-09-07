package capstone.project.lambda;

import capstone.project.activity.requests.GetDriverRequest;
import capstone.project.activity.requests.RemoveTripRequest;
import capstone.project.activity.results.RemoveTripResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class RemoveTripLambda extends LambdaActivityRunner<RemoveTripRequest, RemoveTripResult>
        implements RequestHandler<AuthenticatedLambdaRequest<RemoveTripRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<RemoveTripRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromQuery(query ->
                        RemoveTripRequest.builder()
                                .withTripId(query.get("tripId"))
                                .withMemberId(query.get("memberId"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideRemoveTripActivity().handleRequest(request)
        );
    }
}
