/*                                                                                                                                                                                      
 * Licensed under Apache Software Foundation V2 license agreement.  
 * See the NOTICE file distributed with this work for additional 
 * information regarding copyright ownership.  The ASF licenses this 
 * file to you under the Apache License, Version 2.0 (the                                                                                                                                    
 * "License"); you may not use this file except in compliance                                                                                                                          
 * with the License.  You may obtain a copy of the License at                                                                                                                          
 *                                                                                                                                                                                      
 *  http://www.apache.org/licenses/LICENSE-2.0                                                                                                                                         
 *                                                                                                                                                                                      
 * Unless required by applicable law or agreed to in writing,                                                                                                                          
 * software distributed under the License is distributed on an                                                                                                                          
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY                                                                                                                              
 * KIND, either express or implied.  See the License for the                                                                                                                            
 * specific language governing permissions and limitations                                                                                                                              
 * under the License.
 *
 *
 * Copyright(c) 2012-2014, Vijay Dialani. ALL RIGHTS RESERVED.
 *                                                                                                                                                                 
 */

package course.cloud.computing.rest;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * App class.
 * 
 */
public class App {
	// Base URI the Grizzly HTTP server will listen on
	public static String BASE_URI = null;

	/**
	 * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
	 * application.
	 * 
	 * @return Grizzly HTTP server.
	 */
	public static HttpServer startServer() {
		// create a resource config that scans for JAX-RS resources and
		// providers
		// in com.example package
		final ResourceConfig rc = new ResourceConfig()
				.packages("course.cloud.computing.rest");

		// create and start a new instance of grizzly http server
		// exposing the Jersey application at BASE_URI
		return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI),
				rc);
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		if (args.length < 1) {
			System.out.println("Usage: App [port]");
			System.exit(-1);
		}
		int port = Integer.parseInt(args[0]);
		App.BASE_URI = "http://localhost:" + port + "/myapp/";

		final HttpServer server = startServer();
		System.out.println(String.format(
				"Jersey app started with WADL available at "
						+ "%sapplication.wadl\nHit enter to stop it...",
				BASE_URI));
		System.in.read();
		server.stop();
	}
}
