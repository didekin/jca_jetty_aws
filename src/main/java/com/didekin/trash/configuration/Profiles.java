package com.didekin.trash.configuration;

/**
 * User: pedro@didekin
 * Date: 20/04/16
 * Time: 16:13
 */
@SuppressWarnings("WeakerAccess")
public abstract class Profiles {

    public static final String JETTY_LOCAL = "jetty-local";
    public static final String DB_PRE = "db-pre";
    public static final String AWS_PRE = "aws-pre";

    public final static class JksInAppClient {

        /**
         * Name of a file in the local file system
         */
        public String jksUri;
        public String jksPswd;

        public JksInAppClient(String jksUri, String jksPswd)
        {
            this.jksUri = jksUri;
            this.jksPswd = jksPswd;
        }
    }
}
