package com.headspin.skillbase.identity.interfaces.rest;

import org.keycloak.representations.JsonWebToken;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.auth.LoginConfig;

import jakarta.security.enterprise.authentication.mechanism.http.OpenIdAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.openid.ClaimsDefinition;

/*
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.auth.LoginConfig;
import jakarta.security.enterprise.authentication.mechanism.http.openid.ClaimsDefinition;
import jakarta.security.enterprise.identitystore.openid.OpenIdContext;
import jakarta.security.enterprise.jwt.JsonWebToken;

*/

/*
 * IdentityREST implements a REST resource for the identity
 * domain.
 */

@ApplicationPath("/api/v1/skillbase/identity")
@LoginConfig(authMethod = "MP-JWT")

@OpenIdAuthenticationMechanismDefinition(clientId = "${oidcConfig.clientId}", clientSecret = "${oidcConfig.clientSecret}", redirectURI = "${baseURL}/callback", providerURI = "${oidcConfig.issuerUri}", jwksConnectTimeout = 5000, jwksReadTimeout = 5000, extraParameters = {
        "audience=https://<your-auth0-domain>/api/v2/" }, // <-- YOUR DOMAIN HERE
        claimsDefinition = @ClaimsDefinition(callerGroupsClaim = "http://www.jakartaee.demo/roles"))
public class IdentityREST extends Application {

    /*
     * @Inject
     *
     * @Claim("language") String lang;
     *
     * JsonWebToken jwt = (JsonWebToken) securityContext.getUserPrincipal()
     */

}
