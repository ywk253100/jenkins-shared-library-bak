package io.goharbor.harbor.jenkins.library

public class HarborInstance implements Serializable{
    public String coreServiceURL;
    public String notaryServiceURL;
    public String systemAdminPassword;
    public Map ipHostnameMappings; // the mapping of IP address and hostname
    // TODO add certificates, so that the tester can trust them in the OS level?
}