package com.head;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.json.bind.annotation.JsonbTransient;

@JsonbPropertyOrder({
"type",
"proofPurpose",
"verificationMethod",
"created",
"domain",
"challenge",
"proofValue"
})
@Generated("jsonschema2pojo")
public class Proof {

/**
*
* (Required)
*
*/
@JsonbProperty("type")
private Object type;
/**
*
* (Required)
*
*/
@JsonbProperty("proofPurpose")
private String proofPurpose;
/**
*
* (Required)
*
*/
@JsonbProperty("verificationMethod")
private Object verificationMethod;
/**
*
* (Required)
*
*/
@JsonbProperty("created")
private String created;
@JsonbProperty("domain")
private String domain;
@JsonbProperty("challenge")
private String challenge;
@JsonbProperty("proofValue")
private String proofValue;
@JsonbTransient
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

/**
*
* (Required)
*
*/
@JsonbProperty("type")
public Object getType() {
return type;
}

/**
*
* (Required)
*
*/
@JsonbProperty("type")
public void setType(Object type) {
this.type = type;
}

/**
*
* (Required)
*
*/
@JsonbProperty("proofPurpose")
public String getProofPurpose() {
return proofPurpose;
}

/**
*
* (Required)
*
*/
@JsonbProperty("proofPurpose")
public void setProofPurpose(String proofPurpose) {
this.proofPurpose = proofPurpose;
}

/**
*
* (Required)
*
*/
@JsonbProperty("verificationMethod")
public Object getVerificationMethod() {
return verificationMethod;
}

/**
*
* (Required)
*
*/
@JsonbProperty("verificationMethod")
public void setVerificationMethod(Object verificationMethod) {
this.verificationMethod = verificationMethod;
}

/**
*
* (Required)
*
*/
@JsonbProperty("created")
public String getCreated() {
return created;
}

/**
*
* (Required)
*
*/
@JsonbProperty("created")
public void setCreated(String created) {
this.created = created;
}

@JsonbProperty("domain")
public String getDomain() {
return domain;
}

@JsonbProperty("domain")
public void setDomain(String domain) {
this.domain = domain;
}

@JsonbProperty("challenge")
public String getChallenge() {
return challenge;
}

@JsonbProperty("challenge")
public void setChallenge(String challenge) {
this.challenge = challenge;
}

@JsonbProperty("proofValue")
public String getProofValue() {
return proofValue;
}

@JsonbProperty("proofValue")
public void setProofValue(String proofValue) {
this.proofValue = proofValue;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}
