import io.goharbor.harbor.jenkins.library.FreshInstallPipelineExecutor
import io.goharbor.harbor.jenkins.library.HarborInstance
import io.goharbor.harbor.jenkins.library.TestCaseRunner
import io.goharbor.harbor.jenkins.library.TestResultPublisher

def call(FreshInstallPipelineExecutor executor) {
    node("helm-client") {
        checkout scm
        HarborInstance instance
        String resultPath
        stage('Pre-Install') {
            executor.preInstall()
        }
        stage('Install') {
            instance = executor.install()
        }
        stage('Post-Install') {
            executor.postInstall()
        }
        stage('Health Check') {
            echo "health checking"
        }
        stage('Pre-Test') {
            executor.preTest()
        }
        stage('Test') {
            TestCaseRunner runner = new TestCaseRunner(this, instance)
            resultPath = runner.run()
        }
        stage('Post-Test') {
            executor.postTest()
            TestResultPublisher publisher = new TestResultPublisher(this, resultPath)
            publisher.publish()
        }
    }
}