/*
 *  This file is part of the Wayback archival access software
 *   (http://archive-access.sourceforge.net/projects/wayback/).
 *
 *  Licensed to the Internet Archive (IA) by one or more individual 
 *  contributors. 
 *
 *  The IA licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.archive.wayback.exception;

import javax.servlet.http.HttpServletResponse;

/**
 * Exception class for content inaccessible due to HTTP auth problems, or
 * user coming from the wrong network.
 *
 * @author brad
 * @version $Date$, $Revision$
 */
public class AuthenticationControlException extends WaybackException {
	private static final long serialVersionUID = 1L;
	protected static final String ID = "authenticationControl";
	
	protected boolean requestAuth = true;

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public AuthenticationControlException(String message) {
		super(message,"Authenication Problem");
		id = ID;
	}
	
	/**
	 * Constructor with option to request auth
	 * 
	 * @param message
	 * @param requestAuth
	 */	
	public AuthenticationControlException(String message, boolean requestAuth) {
		super(message,"Authenication Problem");
		this.requestAuth = requestAuth;
		id = ID;
	}
	/** 
	 * Constructor with message and details
	 * 
	 * @param message
	 * @param details
	 */
	public AuthenticationControlException(String message, String details) {
		super(message,"Authenication Problem",details);
		id = ID;
	}
	
	public void setupResponse(HttpServletResponse response) {
		if (requestAuth) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.addHeader("WWW-Authenticate","Basic realm=\"Secured-Wayback\"");
		} else {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}
}
