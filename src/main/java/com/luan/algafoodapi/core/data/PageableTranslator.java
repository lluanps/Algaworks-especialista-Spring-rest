package com.luan.algafoodapi.core.data;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableTranslator {
	
	public static Pageable translate(Pageable pageable, Map<String, String> fieldsMapping) {
		var orders = pageable.getSort().stream()
			.filter(order -> fieldsMapping.containsKey(order.getProperty()))
			.map(order -> new Sort.Order(order.getDirection(), fieldsMapping.get(order.getProperty())))
			.collect(Collectors.toList());
		
			
		//instanciando novo pageablo e passando um pagesort
		return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				Sort.by(orders));
	}
	


}