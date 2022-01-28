package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoader {
	private Class<?> classToLoad;

	public ClassLoader(Class<?> classToLoad) {
		this.classToLoad = classToLoad;
	}
	
	public Object runMethod (Object object, InvocableMethod invocableMethod) {
		try {
			String methodName = invocableMethod.getMethodName();
			
			Method executeMethod = getClassMethod(methodName);
			
			return executeMethod.invoke(object, invocableMethod.getParams());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private Method getClassMethod(String methodName) {
		for (Method method : classToLoad.getMethods()) {
			if (methodName.contains(method.getName())) {
				return method;
			}
		}
		return null;
	}
}
