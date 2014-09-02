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

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import course.cloud.computing.service.HelloService;

/**
 * Hello world!
 * 
 */
public class DynamicWSClient {

	public static void main(String[] args) {
		final DynamicWSClient instance = new DynamicWSClient();
		instance.startDynamicClient("http://localhost:9090/helloService?wsdl",
				"Dynamic Binding");
	}

	private void startDynamicClient(String serviceLocation, String msg) {

		try {

			URL location = new URL(serviceLocation);
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory
					.newInstance();
			if (dcf != null) {
				dcf.setSimpleBindingEnabled(true);
				Client client = dcf.createClient(location);
				Object[] res = client.invoke("sayHello", msg);
				System.out.println("Dynamic Method response : " + res[0]);
			} else {
				System.out
						.println("Unable to create a dynamic factory instance");
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
