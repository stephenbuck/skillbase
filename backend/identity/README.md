The identity service will provide skillbase-specific functions itself, but will delegate authentication and authorization to a third-party IAM: KeyCloak.

Integration with KeyCloak will be through its REST endpoints. Skillbase entities will be associated with KeyCloak entities via a peer_id.
