package com.threedsoft.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.threedsoft.user.streams.UserStreams;
import com.threedsoft.util.service.EventPublisher;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableBinding(UserStreams.class)
@EnableAutoConfiguration
@EnableScheduling
@EnableJpaAuditing
@EntityScan(
        basePackageClasses = {UserApplication.class, Jsr310JpaConverters.class}
)
@Slf4j
public class UserApplication {
	@Autowired
	UserStreams userStreams;
	
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}
	@Bean
	public EventPublisher eventPublisher() {
		return new EventPublisher(userStreams.outboundUser());
	}	
		
/*	@Bean
	@InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "5000", maxMessagesPerPoll = "1"))
	public MessageSource<String> timerMessageSource() {
		return () -> MessageBuilder.withPayload("hello").build();
	}	
*/	
}
