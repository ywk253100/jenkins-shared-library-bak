package io.goharbor.harbor

interface FreshInstallPipelineExecutor {
    void preInstall()
    void install()
    void postInstall()
    void preTest()
    void postTest()
}