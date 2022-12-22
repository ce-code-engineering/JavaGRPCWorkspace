# Steps
0. Guideline on :
  * AWS cli setup: https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html
    * setup Admin with programmatic Access then `aws configure`
  * AWS CDK setup: https://docs.aws.amazon.com/cdk/v2/guide/getting_started.html#getting_started_install
  * Maven wrapper: https://maven.apache.org/wrapper/
1. `cdk init app --language=java`
2. Add mvnwrapper to make CDK portable & Edit the cdk.json into `./mnvw`
3. Bootstrap AWS environment so we can deploy > 50kb deployment
    `cdk bootstrap aws://<account-id>/<region>`
4. CDK Deploy & Destroy commands
```
export accountId=<CHANGE-WITH-YOUR-AWS-ACCOUNT-ID>

cdk deploy -c accountId=<CHANGE-WITH-YOUR-AWS-ACCOUNT-ID> \
 --app "./mvnw -e -q compile exec:java -Dexec.mainClass=com.codeengineering.hello.cdk.DockerRepositoryApp"

# to destroy
cdk destroy -c accountId=<CHANGE-WITH-YOUR-AWS-ACCOUNT-ID> \
 --app "./mvnw -e -q compile exec:java -Dexec.mainClass=com.codeengineering.hello.cdk.DockerRepositoryApp"
```


# Welcome to your CDK Java project!

This is a blank project for CDK development with Java.

The `cdk.json` file tells the CDK Toolkit how to execute your app.

It is a [Maven](https://maven.apache.org/) based project, so you can open this project with any Maven compatible Java IDE to build and run tests.

## Useful commands

 * `mvn package`     compile and run tests
 * `cdk ls`          list all stacks in the app
 * `cdk synth`       emits the synthesized CloudFormation template
 * `cdk deploy`      deploy this stack to your default AWS account/region
 * `cdk diff`        compare deployed stack with current state
 * `cdk docs`        open CDK documentation

Enjoy!
