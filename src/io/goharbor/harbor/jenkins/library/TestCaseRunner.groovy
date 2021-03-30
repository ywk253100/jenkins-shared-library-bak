package io.goharbor.harbor.jenkins.library

class TestCaseRunner implements Serializable {
    private Script script;
    private String testCaseRef = "master"; // the branch/tag of test cases
    private HarborInstance instance;

    TestCaseRunner(Script script, HarborInstance instance) {
      this.script = script
      this.instance = instance
    }

    void run(){
        // checkout the test case into the "harbor" folder
        script.checkout scm: [$class: 'GitSCM', userRemoteConfigs: [[url: 'https://github.com/goharbor/harbor.git']], branches: [[name: 'master']],
            extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'harbor']]]

        String coreServiceURL = instance.coreServiceURL()
        script.sh """
            docker run -i --privileged --rm -v \$(pwd)/harbor:/drone -w /drone \
                harbor-repo.vmware.com/harbor-ci/goharbor/harbor-e2e-engine:2.6.3 \
                robot -v ip:$coreServiceURL \
                    /drone/tests/robot-cases/Group1-Nightly/Setup_Nightly.robot
        """
    }
}