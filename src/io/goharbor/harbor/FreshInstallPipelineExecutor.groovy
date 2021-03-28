package io.goharbor.harbor

public interface FreshInstallPipelineExecutor {
    void preInstall()
    HarborInstance install()
    void postInstall()
    void preTest()
    void postTest()
}
