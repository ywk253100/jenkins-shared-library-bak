package io.goharbor.harbor

public interface FreshInstallPipelineExecutor {
    void preInstall()
    void install()
    void postInstall()
    void preTest()
    void postTest()
}