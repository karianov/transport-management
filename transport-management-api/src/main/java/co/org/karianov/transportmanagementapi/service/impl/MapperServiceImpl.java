package co.org.karianov.transportmanagementapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.org.karianov.transportmanagementapi.service.MapperService;

@Service(value = "mapperService")
public class MapperServiceImpl implements MapperService {

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	@Override
	public <T> T map(Object source, Class<T> target) {
		if (source == null)
			return null;
		return dozerBeanMapper.map(source, target);
	}

	@Override
	public <F, T> List<T> mapList(List<F> sources, Class<T> type) {
		if (sources == null)
			return null;
		return sources.stream().map(source -> map(source, type)).collect(Collectors.toList());
	}

}
