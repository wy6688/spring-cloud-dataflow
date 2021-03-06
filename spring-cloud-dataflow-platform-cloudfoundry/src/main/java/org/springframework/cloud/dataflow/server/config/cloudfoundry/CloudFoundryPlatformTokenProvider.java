/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.dataflow.server.config.cloudfoundry;

import org.cloudfoundry.reactor.TokenProvider;
import org.cloudfoundry.reactor.tokenprovider.PasswordGrantTokenProvider;

import org.springframework.cloud.deployer.spi.cloudfoundry.CloudFoundryConnectionProperties;

/**
 * @author David Turanski
 **/
public class CloudFoundryPlatformTokenProvider {

	private final CloudFoundryPlatformProperties platformProperties;

	public CloudFoundryPlatformTokenProvider(
		CloudFoundryPlatformProperties platformProperties) {
		this.platformProperties = platformProperties;
	}

	public TokenProvider tokenProvider(String account) {
		CloudFoundryConnectionProperties connectionProperties = platformProperties.accountProperties(account).getConnection();
		return PasswordGrantTokenProvider.builder()
			.username(connectionProperties.getUsername())
			.password(connectionProperties.getPassword())
			.loginHint(connectionProperties.getLoginHint())
			.build();
	}
}
