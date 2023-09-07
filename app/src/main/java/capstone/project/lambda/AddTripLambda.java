package capstone.project.lambda;

import capstone.project.activity.requests.AddMemberRequest;
import capstone.project.activity.requests.AddTripRequest;
import capstone.project.activity.results.AddMemberResult;
import capstone.project.activity.results.AddTripResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddTripLambda extends LambdaActivityRunner<AddTripRequest, AddTripResult> implements RequestHandler<AuthenticatedLambdaRequest<AddTripRequest>, LambdaResponse> {
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddTripRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    AddTripRequest authenticatedRequest = input.fromBody(AddTripRequest.class);
                    return AddTripRequest.builder()
                            .withTripId(authenticatedRequest.getTripId())
                            .withMemberId(authenticatedRequest.getMemberId())
                            .withPickupDateTime(authenticatedRequest.getPickupDateTime())
                            .withPickupStreetAddress(authenticatedRequest.getPickupStreetAddress())
                            .withPickupCity(authenticatedRequest.getPickupCity())
                            .withPickupState(authenticatedRequest.getPickupState())
                            .withPickupZipCode(authenticatedRequest.getPickupZipCode())
                            .withDropOffStreetAddress(authenticatedRequest.getDropOffStreetAddress())
                            .withDropOffCity(authenticatedRequest.getDropOffCity())
                            .withDropOffState(authenticatedRequest.getDropOffState())
                            .withDropOffZipCode(authenticatedRequest.getDropOffZipCode())
                            .withMobility(authenticatedRequest.getMobility())
                            .withDriverId(authenticatedRequest.getDriverId())
                            .withSelected(authenticatedRequest.getSelected())
                            .build();
                },
                (request, serviceComponent) ->  serviceComponent.provideAddTripActivity().handleRequest(request)

        );
    }
}
