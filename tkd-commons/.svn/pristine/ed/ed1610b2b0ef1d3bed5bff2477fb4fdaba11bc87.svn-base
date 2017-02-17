package com.lc.zy.common.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.dozer.loader.api.TypeDefinition;
import org.dozer.loader.api.TypeMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;

public abstract class BeanCopyUtil<T> {
	
	private DozerBeanMapper mapper = new DozerBeanMapper();
	
	private Class<T> clazz;
	
	/**
	 * @param mapNull 是否copy值为null或者空串的属性, 默认值为false.
	 * @param byRefs 需要copyByReference(按引用拷贝)的属性数组.
	 * @param excludes 忽略拷贝的属性数组.
	 */
	public BeanCopyUtil(boolean mapNull, String[] byRefs, String[] excludes) {
		clazz = getGenericTypeClass();
		addMapping(mapNull, byRefs, excludes);
	}
	
	@SuppressWarnings("unchecked")
	private Class<T> getGenericTypeClass() {
		Type sType = getClass().getGenericSuperclass();
		Type[] generics = ((ParameterizedType) sType).getActualTypeArguments();
		return (Class<T>) (generics[0]);
	}
	
	public BeanCopyUtil(String[] byRefs, String[] excludes) {
		this(false, byRefs, excludes);
	}
	
	public BeanCopyUtil(String[] byRefs) {
		this(false, byRefs, new String[]{});
	}
	
	public BeanCopyUtil(boolean mapNull) {
		this(mapNull, new String[]{}, new String[]{});
	}
	
	public BeanCopyUtil() {
		this(false, new String[]{}, new String[]{});
	}
	
	private void addMapping(final boolean mapNull, final String[] byRefs,
			final String[] excludes) {
		
		BeanMappingBuilder mappingBuilder = new BeanMappingBuilder() {
			@Override
			protected void configure() {
				TypeDefinition type = new TypeDefinition(clazz);
				type.mapNull(mapNull);
				type.mapEmptyString(mapNull);
				
				TypeMappingBuilder builder = mapping(type, type, TypeMappingOptions.oneWay());
				
				//copyByReference fields
				for (int i = 0; i < byRefs.length; i++) {
					String src = byRefs[i];
					String dest = src;
					builder.fields(src, dest, FieldsMappingOptions.copyByReference());
				}
				
				//excluded fields
				for (int i = 0; i < excludes.length; i++) {
					builder.exclude(excludes[i]);
				}
			}
		};
		
		mapper.addMapping(mappingBuilder);
	}

	/**
	 * 把源对象的属性拷贝到目标对象的同名属性.
	 * @param s 源对象.
	 * @param t 目标对象.
	 */
	public void copy(T s, T t) {
		mapper.map(s, t);
	}

}
