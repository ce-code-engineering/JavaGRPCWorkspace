package com.codeengineering.hello.cdk.commons.constructs;

import org.jetbrains.annotations.NotNull;
import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.services.ecr.IRepository;
import software.amazon.awscdk.services.ecr.LifecycleRule;
import software.amazon.awscdk.services.ecr.Repository;
import software.amazon.awscdk.services.iam.AccountPrincipal;
import software.constructs.Construct;

import java.util.Collections;

/**
 * Construct for ECR repository of Docker images.
 *
 * Permission: grant pull & push to all users in the inputParameters.accountId
 */
public class DockerRepository extends Construct {
    private final static String ID_ECR_REPOSITORY = "ecrRepository";
    private final IRepository ecrRepository;

    public DockerRepository(
        final @NotNull Construct scope,
        final @NotNull String id,
        final InputParameters inputParameters
    ) {
        super(scope, id);

        this.ecrRepository = Repository.Builder.create(this, ID_ECR_REPOSITORY)
            .repositoryName(inputParameters.repositoryName)
            .removalPolicy(inputParameters.retainRegistryOnDelete ?
                RemovalPolicy.RETAIN : RemovalPolicy.DESTROY)
            .lifecycleRules(Collections.singletonList(LifecycleRule.builder()
                    .rulePriority(1)
                    .description("max images: " + inputParameters.maxImageCount)
                    .maxImageCount(inputParameters.maxImageCount)
                    .build()))
            .build();

        this.ecrRepository.grantPullPush(new AccountPrincipal(inputParameters.accountId));
    }

    public static class InputParameters {
        private final static int DEFAULT_MAX_IMAGE_COUNT = 10;
        private final static boolean DEFAULT_RETAIN_REGISTRY_ON_DELETE = true;
        private final String repositoryName;
        private final String accountId;
        private final int maxImageCount;
        private final boolean retainRegistryOnDelete;

        public InputParameters(final String repositoryName, final String accountId) {
            this(repositoryName, accountId, DEFAULT_MAX_IMAGE_COUNT, DEFAULT_RETAIN_REGISTRY_ON_DELETE);
        }

        public InputParameters(
            final String repositoryName,
            final String accountId,
            final int maxImageCount,
            final boolean retainRegistryOnDelete
        ) {
            this.repositoryName = repositoryName;
            this.accountId = accountId;
            this.maxImageCount = maxImageCount;
            this.retainRegistryOnDelete = retainRegistryOnDelete;
        }
    }
}
