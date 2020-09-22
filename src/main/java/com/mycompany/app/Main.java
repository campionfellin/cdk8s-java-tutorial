package com.mycompany.app;

import software.constructs.Construct;

import java.util.ArrayList;
import java.util.List;

import org.cdk8s.App;
import org.cdk8s.Chart;
import org.cdk8s.ChartOptions;
import org.cdk8s.plus.Container;
import org.cdk8s.plus.ContainerProps;
import org.cdk8s.plus.Pod;
import org.cdk8s.plus.PodSpec;
import org.cdk8s.plus.DeploymentSpec;
import org.cdk8s.plus.DeploymentProps;
import org.cdk8s.plus.Deployment;
import org.cdk8s.plus.ExposeOptions;
import org.cdk8s.plus.ServiceType;
import org.cdk8s.plus.ImagePullPolicy;

public class Main extends Chart 
{

    public Main(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public Main(final Construct scope, final String id, final ChartOptions options) {
        super(scope, id, options);

        final List<Container> containers = new ArrayList<>();
        final Container container = new Container(new ContainerProps.Builder().image("helloworld").port(8080).imagePullPolicy(ImagePullPolicy.NEVER).name("hello-cdk8s").build());
        containers.add(container);

        final PodSpec podSpec = new PodSpec.Builder().containers(containers).build();

        final DeploymentSpec deploymentSpec = new DeploymentSpec.Builder().replicas(3).podSpecTemplate(podSpec).build();

        final Deployment deployment = new Deployment(this, "deployment", new DeploymentProps.Builder().spec(deploymentSpec).build());

        deployment.expose(new ExposeOptions.Builder().port(8080).serviceType(ServiceType.LOAD_BALANCER).build());
    }

    public static void main(String[] args) {
        final App app = new App();
        new Main(app, "cdk8s-java-tutorial");
        app.synth();
    }
}