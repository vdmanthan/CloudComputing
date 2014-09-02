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
package course.cloud.computing.client;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import course.cloud.computing.service.NotificationsImpl;
import course.cloud.computing.service.SOAPService;

import javax.xml.ws.wsaddressing.W3CEndpointReference;
import javax.xml.ws.Endpoint;

public class Client {

	public static void main(String[] args) {
		if (args.length < 4) {
			usage();
			System.exit(-1);
		}

		// Register the handle for termination signal from the OS
		Runtime runtime = Runtime.getRuntime();
		runtime.addShutdownHook(new Thread() {
			public void run() {
				System.out.println("Received the termination signal..exiting");
				System.out.flush();
			}
		});

		String serverName = args[0];
		int port = Integer.parseInt(args[1]);
		String nServerName = args[2];
		int nPort = Integer.parseInt(args[3]);

		String WSDLUrl = "http://" + serverName + ":" + port
				+ "/SOAPService?wsdl";

		String PublishUrl = "http://" + nServerName + ":" + nPort
				+ "/Notification";

		System.out.println("Running the SOAP Callback example");

		Endpoint callbackImpl = Endpoint.create(new NotificationsImpl());
		callbackImpl.publish(PublishUrl);
		W3CEndpointReference wpr = callbackImpl
				.getEndpointReference(W3CEndpointReference.class);

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setAddress(WSDLUrl);
		SOAPService client = factory.create(SOAPService.class);
		System.out.println(client.sayHello("World"));
		client.registerCallback(wpr);
	}

	private static void usage() {
		System.out
				.println("java course.cloud.computing.App [serverName] [port] [callbackServerName] [callbackPort]");
	}

}
