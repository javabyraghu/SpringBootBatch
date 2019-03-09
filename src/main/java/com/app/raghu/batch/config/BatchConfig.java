package com.app.raghu.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.app.raghu.batch.listener.MyJobListener;
import com.app.raghu.batch.processor.DataProcessor;
import com.app.raghu.batch.reader.DataReader;
import com.app.raghu.batch.writer.DataWriter;

@Configuration
public class BatchConfig {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job jobA() {
		return jobBuilderFactory.get("jobA")
				.incrementer(new RunIdIncrementer())
				.listener(listener())
				.flow(stepA())
				.end()
				.build();
	}
	
	@Bean
	public Step stepA() {
		return stepBuilderFactory.get("stepA")
				.<String, String>chunk(1)
				.reader(new DataReader())
				.processor(new DataProcessor())
				.writer(new DataWriter())
				.build();
	}
	
	@Bean
	public JobExecutionListener listener() {
		return new MyJobListener();
	}
}
