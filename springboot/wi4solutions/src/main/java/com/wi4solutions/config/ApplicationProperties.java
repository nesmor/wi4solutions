package com.wi4solutions.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Wi 4 Solutions.
 * <p>
 * Properties are configured in the application.yml file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
	
	private final AsteriskProperties asterisk = new AsteriskProperties() ;
	
	
	public  ApplicationProperties() {
	}
	
	public AsteriskProperties getAsterisk() {
		return asterisk;
	}

	public static class AsteriskProperties {

		private String username;
		
		private String password;
		
		private String host;
		
		private String context;
		
		private String channel;
		
		private String extend;
		
		private String callerId;
		
		private String priority;
		
		private String timeout;
		
		private String application;
		

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public String getContext() {
			return context;
		}

		public void setContext(String context) {
			this.context = context;
		}

		public String getChannel() {
			return channel;
		}

		public void setChannel(String channel) {
			this.channel = channel;
		}

		public String getExtend() {
			return extend;
		}

		public void setExtend(String extend) {
			this.extend = extend;
		}

		public String getCallerId() {
			return callerId;
		}

		public void setCallerId(String callerId) {
			this.callerId = callerId;
		}

		public String getPriority() {
			return priority;
		}

		public void setPriority(String priority) {
			this.priority = priority;
		}

		public String getTimeout() {
			return timeout;
		}

		public void setTimeout(String timeout) {
			this.timeout = timeout;
		}

		public String getApplication() {
			return application;
		}

		public void setApplication(String application) {
			this.application = application;
		}	
		
		
		
		
		
		
	}
}
