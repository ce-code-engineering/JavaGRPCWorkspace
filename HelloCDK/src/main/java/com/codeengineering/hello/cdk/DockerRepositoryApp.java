package com.codeengineering.hello.cdk;

import com.codeengineering.hello.cdk.commons.constructs.DockerRepository;
import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;

import static com.codeengineering.hello.cdk.commons.Constants.CTX_KEY_ACCOUNT_ID;
import static com.codeengineering.hello.cdk.commons.Constants.CTX_KEY_APPLICATION_NAME;
import static com.codeengineering.hello.cdk.commons.Constants.CTX_KEY_REGION;
import static com.codeengineering.hello.cdk.commons.Validations.requireNonEmpty;

public class DockerRepositoryApp {
    private final static String ID_STACK = "DockerRepositoryStack";
    private final static String STACK_NAME_SUFFIX = "-DockerRepository";
    private final static String ID_CONSTRUCT_DOCKER_REPOSITORY = "DockerRepository";

    public static void main(String[] args) {
        final App app = new App();

        final String accountId = getFromContextOrFailIfEmpty(app, CTX_KEY_ACCOUNT_ID);
        final String region = getFromContextOrFailIfEmpty(app, CTX_KEY_REGION);
        final String applicationName = getFromContextOrFailIfEmpty(app, CTX_KEY_APPLICATION_NAME);

        final Environment awsEnvironment = Environment.builder()
            .account(accountId)
            .region(region)
            .build();

        final Stack dockerRepositoryStack = new Stack(
            app,
            ID_STACK,
            StackProps.builder()
                .stackName(applicationName + STACK_NAME_SUFFIX)
                .env(awsEnvironment)
                .build());

        new DockerRepository(
            dockerRepositoryStack,
            ID_CONSTRUCT_DOCKER_REPOSITORY,
            new DockerRepository.InputParameters(applicationName, accountId)
        );

        app.synth();
    }

    private static String getFromContextOrFailIfEmpty(final App app, final String contextKey) {
        final String value = (String) app
            .getNode()
            .tryGetContext(contextKey);
        requireNonEmpty(value, contextKey + " cannot be empty");

        return value;
    }
}
