package io.goharbor.harbor.jenkins.library

public interface FreshInstallPipelineExecutor {
    void preInstall()
    HarborInstance install()
    void postInstall()
    void preTest()
    void postTest()
}
