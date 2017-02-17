package com.lc.zy.common.data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springside.modules.persistence.SearchFilter;

public abstract class Criterias {

	public static void bySearchFilter(Object cri, Collection<SearchFilter> filters) {
		for (SearchFilter filter : filters) {
			String name = null;
			switch (filter.operator) {
			case EQ:
				name = "and" + StringUtils.capitalize(filter.fieldName) + "EqualTo";
				execute(cri, name, filter.value);
				break;
			case LIKE:
				name = "and" + StringUtils.capitalize(filter.fieldName) + "Like";
				execute(cri, name, "%" + filter.value + "%");
				break;
			case GT:
				name = "and" + StringUtils.capitalize(filter.fieldName) + "GreaterThan";
				execute(cri, name, filter.value);
				break;
			case LT:
				name = "and" + StringUtils.capitalize(filter.fieldName) + "LessThan";
				execute(cri, name, filter.value);
				break;
			case GTE:
				name = "and" + StringUtils.capitalize(filter.fieldName) + "GreaterThanOrEqualTo";
				execute(cri, name, filter.value);
				break;
			case LTE:
				name = "and" + StringUtils.capitalize(filter.fieldName) + "LessThanOrEqualTo";
				execute(cri, name, filter.value);
				break;
			case IN:
				name = "and" + StringUtils.capitalize(filter.fieldName) + "In";
				execute(cri, name, filter.value);
				break;
			case NEQ:
				name = "and" + StringUtils.capitalize(filter.fieldName) + "NotEqualTo";
				execute(cri, name, filter.value);
				break;
			}
		}
	}


	private static void execute(Object cri, String name, Object... args) {
		Method m = getMethod(cri, name);
		if (m==null) return ;
		try {
			//modify by liangc 150812 : 匹配 int 型参数
			Class<?>[] cts = m.getParameterTypes();
			if(cts!=null && cts.length>0 && cts[0].equals(Integer.class) ){
				Integer[] is = new Integer[args.length];
				for(int i=0;i<args.length;i++){
					is[i] = Integer.parseInt(args[i].toString());
				}
				m.invoke(cri, is);
			}else if(cts!=null && cts.length>0 && cts[0].equals(List.class) ){
				List list = new ArrayList();
				if(args != null && args.length > 0 && ((String)args[0]).contains(";")){
					list = Arrays.asList(((String)args[0]).split(";"));
				}else{
					list.add(args[0]);
				}
				m.invoke(cri, list);
			}else{
				m.invoke(cri, args);
			}
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	private static Method getMethod(Object cri, String name) {
		Method[] methods = cri.getClass().getMethods();
		for (Method m : methods) {
			if (m.getName().equals(name)) {
				return m;
			}
		}
		return null;
	}
}
