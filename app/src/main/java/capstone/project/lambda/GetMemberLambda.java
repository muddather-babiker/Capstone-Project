package capstone.project.lambda;

import capstone.project.activity.requests.GetMemberRequest;
import capstone.project.activity.results.GetMemberResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetMemberLambda extends LambdaActivityRunner<GetMemberRequest, GetMemberResult>
        implements RequestHandler<LambdaRequest<GetMemberRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetMemberRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromQuery(query ->
                        GetMemberRequest.builder()
                                .withMemberEmail(query.get("memberEmail"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetMemberActivity().handleRequest(request)
        );
    }

}
