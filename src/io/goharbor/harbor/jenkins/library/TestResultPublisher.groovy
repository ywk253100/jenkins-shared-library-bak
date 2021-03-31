package io.goharbor.harbor.jenkins.library

class TestResultPublisher implements Serializable {
    private Script script;
    private String path; // the path of the test result

    TestResultPublisher(Script script, String path) {
      this.script = script
      this.path = path
    }

    // TODO: 1. store the result
    // TODO: 2. publish to slack channel
    // publish the test result
    void publish(){
        script.step([$class : "RobotPublisher",
            outputPath : "$path",
            outputFileName : "output.xml",
            reportFileName : "report.html",
            logFileName : "log.html",
            disableArchiveOutput : false,
            passThreshold : 100.00,
            unstableThreshold: 100.00,
            otherFiles : "**/*.png" ])

        script.slackSend channel: "#yinw-channel" ,token: "xxxx", teamDomain: "vmware"
    }
}