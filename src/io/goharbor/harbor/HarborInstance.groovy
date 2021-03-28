package io.goharbor.harbor

public class HarborInstance implements Serializable{
    public String coreServiceURL; // the URL of core service
    public String notaryServiceURL; // the URL of notary service
    public Map ipHostnameMappings; // the mapping of IP address and hostname

    public void setCoreServiceURL(String url){
        this.coreServiceURL = url
    }

    public String getCoreServiceURL(){
        return this.coreServiceURL
    }

    public void setNotaryServiceURL(String url){
        this.notaryServiceURL = url
    }

    public String getNotaryServiceURL(){
        return this.notaryServiceURL
    }

    public void setIPHostnameMappings(Map mappings){
        this.ipHostnameMappings = mappings
    }

    public Map getIPHostnameMappings(){
        return this.ipHostnameMappings
    }
}