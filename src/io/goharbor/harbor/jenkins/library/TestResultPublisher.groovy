package io.goharbor.harbor.jenkins.library

class TestResultPublisher implements Serializable {
    private Script script;
    private String path; // the path of the test result

    TestResultPublisher(Script script, String path) {
      this.script = script
      this.path = path
    }

    // TODO: persistent the result
    // publish the test result
    void publish(){
        publishRobotTestCaseResult()
        sendToSlack()
    }

    void publishRobotTestCaseResult(){
        script.step([$class : "RobotPublisher",
            outputPath : "$path",
            outputFileName : "output.xml",
            reportFileName : "report.html",
            logFileName : "log.html",
            disableArchiveOutput : false,
            passThreshold : 100.00,
            unstableThreshold: 100.00,
            otherFiles : "**/*.png" ])
    }

    // TODO: refine the message
    // TODO: get the token from credential store
    void sendToSlack(){
        script.slackSend(channel: "#yinw-channel",
            token: "B9F9Uqbn8XfxkH6HNeyT4Qzp",
            teamDomain: "vmware",
            message: "\${env.JOB_NAME} \${env.BUILD_NUMBER} (<\${env.BUILD_URL}|Open>)")
    }
}