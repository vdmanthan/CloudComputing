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

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("session")
public class HelloWorld {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
	 * 
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Path("login")
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@Context HttpServletRequest req) {
		Long id = null;
		try {
			if (req == null) {
				System.out.println("Null request in context");
			}
			HttpSession session = req.getSession();
			id = (Long) session.getAttribute("userid");
			if (id == null) {
				id = System.currentTimeMillis();
				session.setAttribute("userid", id);
				return "Allocating a new id :" + id;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "This is your existing session id :" + id;
	}

	@GET
	@Path("id")
	@Produces(MediaType.TEXT_PLAIN)
	public String getMyId(@Context HttpServletRequest req) {
		HttpSession session = req.getSession();
		long id = (long) session.getAttribute("userid");
		return "Is this your session Id " + id;
	}

}
