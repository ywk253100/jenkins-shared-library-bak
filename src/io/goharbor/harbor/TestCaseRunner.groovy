package io.goharbor.harbor

class TestCaseRunner implements Serializable {
    private Script script;
    private HarborInstance instance;

    TestCaseRunner(Script script, HarborInstance instance) {
      this.script = script
      this.instance = instance
    }

    void run(){
        String coreServiceURL = instance.getCoreServiceURL()
        script.sh """
            docker run -i --rm -v /harbor/workspace/harbor_nightly_executor_1/test-case:/drone \
                -w /drone \
                harbor-repo.vmware.com/harbor-ci/goharbor/harbor-e2e-engine:2.6.3 \
                /bin/bash /drone/nightly_test.sh --endpoint $coreServiceURL
        """
        /*
        #docker run -i -v /harbor/workspace/harbor_nightly_executor_1/framework/cert/:/ecs_ca \
                    #    -v /harbor/workspace/harbor_nightly_executor_1/framework/execution/resolv_bak.conf:/etc/resolv.conf \
                    #    -v /etc/hosts:/etc/hosts -v /harbor/workspace/harbor_nightly_executor_1/test-case:/drone -v /harbor/ca:/ca
                    #    -v /data/cert:/helm_ca -v /data/ca_download:/notary_ca \
                    #    -w /drone harbor-repo.vmware.com/harbor-ci/goharbor/harbor-e2e-engine:2.6.3 \
                    #    /bin/bash /drone/nightly_test.sh
        */
    }
}