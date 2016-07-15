package com.am.generic;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ComponentScanner {
	
	public static void main(String [] args){
		List <SettingInfo> infoList;
		ComponentScanner scanner = new ComponentScanner();
		infoList = scanner.scanComponent(SettingType.class);
		
		for(SettingInfo info:infoList){
			System.out.println("Name "+info.getName());
			System.out.println("isDynamic "+info.isDynamic());
			System.out.println("Value "+info.getValue());
		}
	}
	
	public List<SettingInfo> scanComponent(Class<?> clas){
		String fieldName;
		Class<?> obj = clas;
		
		Field[] fields = obj.getFields();
		for(Field field : fields){
			System.out.println("Field : "+field);
			if(field.isAnnotationPresent(Setting.class))
			{
				
				fieldName = field.getName();
				if(field.PUBLIC!=0){
					
					System.out.println(Modifier.toString(field.getModifiers()));
					String getterName = publicGetter(obj,fieldName);
					/*System.out.println("Getter name "+getterName);
					System.out.println("Field "+field);*/
					return getAnnotatedValues(obj);
				}
				else{
				
					System.out.println(Modifier.toString(field.getModifiers()));
					return getAnnotatedValues(obj);
				}
				
				
			}
		}
		return getAnnotatedValues(obj);
		
		
		
	}

	
	public List<SettingInfo> getAnnotatedValues(Class<?> obj) {
		
		
		List<SettingInfo> settingInfoList = new ArrayList<SettingInfo>();

		for (Method method : obj.getDeclaredMethods()) {
			SettingInfo settingInfo = new SettingInfo();
			if (method.PUBLIC == 0) {
				if (method.isAnnotationPresent(Setting.class)) {
					Annotation[] annotations = method.getAnnotations();

					for (Annotation annotation : annotations) {
						System.out.println("annotation = "+annotation);
						if (annotation instanceof Setting) {
							Setting setting = (Setting) annotation;
							settingInfo.setDynamic(setting.isDynamic());
							settingInfo.setName(setting.name());
							settingInfo.setValue(setting.value());
							settingInfoList.add(settingInfo);
							//System.out.println(setting.value());
						}
					}

				

				}
			}
			
			
		}

		
		return settingInfoList;
	}
	
	
	private String publicGetter(Class<?> obj, String fieldName) {
		
		StringBuilder getterNameBuilder = new StringBuilder();
		Method getMethod = null;
        
        getterNameBuilder.append("get" + fieldName.substring(0,1).toUpperCase());
        if(fieldName.length()> 1)
        {
             getterNameBuilder.append(fieldName.substring(1));
        }
        
        String getterName = getterNameBuilder.toString();
        try {
			//getMethod = obj.getClass().getMethod(getterName);
        	
        	getMethod = Class.forName(obj.getName()).getMethod(getterName);
        	//return  getMethod.getName();
			
		} catch (NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		}
        
        if(getMethod.PUBLIC==0){
		return  getMethod.getName();
		}
		
        return null;
	}

}
