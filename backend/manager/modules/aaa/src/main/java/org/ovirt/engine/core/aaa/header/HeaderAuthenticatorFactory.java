package org.ovirt.engine.core.aaa.header;

import org.ovirt.engine.core.aaa.Authenticator;
import org.ovirt.engine.core.aaa.AuthenticatorFactory;
import org.ovirt.engine.core.extensions.mgr.Configuration;
import org.ovirt.engine.core.extensions.mgr.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeaderAuthenticatorFactory extends AuthenticatorFactory {
    private static final Logger log = LoggerFactory.getLogger(HeaderAuthenticatorFactory.class);

    /**
     * The type supported by this factory.
     */
    private static final String TYPE = "header";

    /**
     * The name of the configuration parameter that contains the name of the header.
     */
    private static final String HEADER_PARAMETER = "header";

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Authenticator createImpl(String profileName, Configuration config) throws ConfigurationException {
        // Get the name of the header:
        String header = config.getString(HEADER_PARAMETER);
        if (header == null) {
            throw new ConfigurationException(
                "The configuration file \"" + config.getFile().getAbsolutePath() + "\" doesn't contain the " +
                "parameter \"" + config.getAbsoluteKey(HEADER_PARAMETER) + "\" that specifies the name of " +
                "the header containing the remote user name."
            );
        }

        // We are good, create the authenticator:
        return new HeaderAuthenticator(profileName, header);
    }
}
