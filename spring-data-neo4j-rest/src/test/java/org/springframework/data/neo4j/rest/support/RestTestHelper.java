/**
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.data.neo4j.rest.support;


import java.net.URISyntaxException;

import org.apache.log4j.BasicConfigurator;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;

public class RestTestHelper
{

    protected SpringRestGraphDatabase graphDb;
    private static final String HOSTNAME = "localhost";
    private static final int PORT = 7470;
    private static LocalTestServer neoServer;
    private static final String SERVER_ROOT_URI = "http://" + HOSTNAME + ":" + PORT + "/db/data/";

    public void startServer() throws Exception {
        BasicConfigurator.configure();
        neoServer = new LocalTestServer(HOSTNAME,PORT).withPropertiesFile("test-db.properties");
        neoServer.start();
    }

    public GraphDatabase createGraphDatabase() throws URISyntaxException {
        return new SpringRestGraphDatabase(SERVER_ROOT_URI);
    }

    public void cleanDb() {
        neoServer.cleanDb();
    }

    public static void shutdownServer() {
        neoServer.stop();
        neoServer = null;
    }
}
