package co.org.karianov.transportmanagementapi.service;

import java.util.List;

public interface MapperService {

	public <T> T map(Object source, Class<T> target);

	public <F, T> List<T> mapList(List<F> sources, Class<T> type);

}
