package org.pflood.model;

public class HealthCheckResult
{
    private String campaign;
    private String ID;
    private String value;
    private String message;
    private String rule;

    public HealthCheckResult(String campaign, String ID, String value, String message, String rule){
        this.campaign = campaign;
        this.ID = ID;
        this.value = value;
        this.message = message;
        this.rule = rule;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}