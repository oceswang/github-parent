package com.github.common.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {
		ConfigConstants.ENTITY_PKG,
        "org.springframework.data.jpa.convert.threeten"
})
@EnableJpaRepositories(ConfigConstants.JPA_PKG)
public class JPAConfigutation
{

}
