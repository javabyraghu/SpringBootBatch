package com.app.raghu.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class DataWriter implements ItemWriter<String> {

	@Override
	public void write(List<? extends String> items) throws Exception {
		for(String item:items) {
			System.out.println(item);
		}
	}

}
