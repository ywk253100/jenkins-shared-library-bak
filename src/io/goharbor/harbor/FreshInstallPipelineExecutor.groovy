package io.goharbor.harbor

public interface FreshInstallPipelineExecutor {
    void preInstall()
    String install()
    void postInstall()
    void preTest()
    void postTest()
}