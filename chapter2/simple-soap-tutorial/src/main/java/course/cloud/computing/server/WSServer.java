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
package course.cloud.computing.server;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import course.cloud.computing.service.HelloService;
import course.cloud.computing.service.HelloServiceImpl;

/**
 * Hello world!
 * 
 */
public class WSServer {

	HelloServiceImpl server = null;

	public static void main(String[] args) {
		final WSServer instance = new WSServer();
		instance.startServer("localhost", 9090);
	}

	private void startServer(String serverName, int port) {

		if (server == null) {
			server = new HelloServiceImpl();
		}

		// Register the handle for termination signal from the OS
		Runtime runtime = Runtime.getRuntime();
		runtime.addShutdownHook(new Thread() {
			public void run() {
				System.out.println("Received the termination signal..exiting");
				System.out.flush();
				if (server != null) {
					server.stop();
				}
			}
		});

		// Note that the factory.create is a blocking call and will exit only with TERM
		// signal
		try {
			URL publishURL = new URL("http://" + serverName + ":" + port
					+ "/helloService");

			System.out.println("Starting Service at " + publishURL);

			JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
			factory.setAddress(publishURL.toString());

			factory.setServiceClass(course.cloud.computing.service.HelloService.class);
			factory.setServiceBean(server);

			factory.getInInterceptors().add(new LoggingInInterceptor());
			factory.getOutInterceptors().add(new LoggingOutInterceptor());
			factory.create();

		} catch (MalformedURLException e) {
			System.out.println(e);
		}
	}
}
