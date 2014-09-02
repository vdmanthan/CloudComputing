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
package course.cloud.computing.service;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.wsaddressing.W3CEndpointReference;

@WebService
public class SOAPServiceImpl implements
		course.cloud.computing.service.SOAPService {
	private String message = new String("Hello, ");

	public SOAPServiceImpl() {
	}

	@WebMethod
	public String sayHello(String name) {
		return message + name + ".";
	}

	public void stop() {
		// Clean up any resources allocated for this server here
		// In this case there are none!
	}

	@SuppressWarnings("restriction")
	@WebMethod
	public String registerCallback(
			W3CEndpointReference reference) {

		WebServiceFeature[] wfs = new WebServiceFeature[] {};

		Notifications callback = (Notifications) reference.getPort(
				Notifications.class, wfs);

		System.out.println("Invoking callback object");
		boolean resp = callback.notifyClient(System.currentTimeMillis()
				+ " message from server");
		if (resp) {
			System.out
					.println("Displayed message on the client .... Response from callback object");
		} else {
			System.out
					.println("Did not display message on the client ... Response from callback object");
		}
		return null;
	}

}