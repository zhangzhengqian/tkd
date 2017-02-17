package com.lc.zy.common.easemobile;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class JsonUtil {
	
	public static <T> T objFromString(String jsonStr, Class<T> cls) 
		throws  JsonMappingException, JsonParseException, IOException
	{
		ObjectMapper om = new ObjectMapper();
		return om.readValue(jsonStr, cls);
	}
	
	public static <T> T objFromString(String jsonStr, TypeReference<T> typeReference) 
		throws  JsonMappingException, JsonParseException, IOException
	{
		ObjectMapper om = new ObjectMapper();
		return om.readValue(jsonStr, typeReference);
	}
	
	public static String toString(Object obj)
		throws JsonMappingException, JsonGenerationException, IOException
	{
		ObjectMapper om = new ObjectMapper();
		return om.writeValueAsString(obj);
	}
}
