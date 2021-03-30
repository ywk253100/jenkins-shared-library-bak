package io.goharbor.harbor.jenkins.library

class TestCaseRunner implements Serializable {
    private Script script;
    private String testCaseRef = "master"; // the branch/tag of the harbor repository where the test cases are in
    private HarborInstance instance;

    TestCaseRunner(Script script, HarborInstance instance) {
      this.script = script
      this.instance = instance
    }

    void run(){
        // checkout the test case into the "harbor" folder
        script.checkout scm: [$class: "GitSCM", userRemoteConfigs: [[url: "https://github.com/goharbor/harbor.git"]], branches: [[name: "$testCaseRef"]],
            extensions: [[$class: "RelativeTargetDirectory", relativeTargetDir: "harbor"]]]

        // TODO 1. set the image as parameter?
        // TODO 2. support other options
        // TODO 3. refine the image
        // TODO 4. replace the "harbor-repo.vmware.com"?
        // TODO 5. set the dockerhub credential
        script.sh """
            docker run -i --privileged --rm -w /drone -v \$(pwd)/harbor:/drone \
                harbor-repo.vmware.com/harbor-ci/goharbor/harbor-e2e-engine:2.6.3 \
                robot -v ip:$instance.coreServiceURL -v notaryServerEndpoint:$instance.notaryServiceURL \
                    -v LOCAL_REGISTRY:harbor-repo.vmware.com -v LOCAL_REGISTRY_NAMESPACE:harbor-ci \
                    -v DOCKER_USER:username -v DOCKER_PWD:password \
                    -v HARBOR_PASSWORD:$instance.systemAdminPassword \
                    /drone/tests/robot-cases/Group1-Nightly/Setup_Nightly.robot
        """
    }
}