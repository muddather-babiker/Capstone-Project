package capstone.project.lambda;

import capstone.project.activity.requests.UpdateTripMemberRequest;
import capstone.project.activity.requests.UpdateTripRequest;
import capstone.project.activity.results.UpdateTripMemberResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateTripMemberLambda extends LambdaActivityRunner<UpdateTripMemberRequest, UpdateTripMemberResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateTripMemberRequest>, LambdaResponse>{
    private final Logger log = LogManager.getLogger();
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateTripMemberRequest> input, Context context){
        return super.runActivity(
                () -> {
                    UpdateTripMemberRequest authenticatedRequest = input.fromBody(UpdateTripMemberRequest.class);
                    log.info("Out UpdateTipRequest: '{}'", authenticatedRequest.toString());

                    return UpdateTripMemberRequest.builder()
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
                            .build();
                },
                (request, serviceComponent) ->
                        serviceComponent.provideUpdateTripMemberActivity().handleRequest(request));
    }
}
