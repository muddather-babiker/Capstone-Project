package capstone.project.lambda;

import capstone.project.activity.requests.AddDriverRequest;
import capstone.project.activity.requests.AddMemberRequest;
import capstone.project.activity.results.AddDriverResult;
import capstone.project.activity.results.AddMemberResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddMemberLambda extends LambdaActivityRunner<AddMemberRequest, AddMemberResult> implements RequestHandler<AuthenticatedLambdaRequest<AddMemberRequest>, LambdaResponse> {
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddMemberRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    AddMemberRequest authenticatedRequest = input.fromBody(AddMemberRequest.class);
                    return AddMemberRequest.builder()
                            .withMemberEmail(authenticatedRequest.getMemberEmail())
                            .withName(authenticatedRequest.getName())
                            .withPhoneNumber(authenticatedRequest.getPhoneNumber())
                            .withDateOfBirth(authenticatedRequest.getDateOfBirth())
                            .withStreetAddress(authenticatedRequest.getStreetAddress())
                            .withCity(authenticatedRequest.getCity())
                            .withState(authenticatedRequest.getState())
                            .withZipCode(authenticatedRequest.getZipCode())
                            .build();
                },
                (request, serviceComponent) ->  serviceComponent.provideAddMemberActivity().handleRequest(request)

        );
    }
}
