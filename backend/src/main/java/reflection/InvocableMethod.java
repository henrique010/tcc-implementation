package reflection;

import java.io.Serializable;

public class InvocableMethod implements Serializable {
	private static final long serialVersionUID = 3362683008039519996L;
	
	private String methodName;
	private String className;
	private String operationName;
	private Object[] params;
	
	public InvocableMethod(String methodName, String className) {
		this.methodName = methodName;
		this.className = className;
	}
	
	public InvocableMethod(String methodName, String className, Object[] params) {
		this.methodName = methodName;
		this.className = className;
		this.params = params;
	}
	
	public InvocableMethod(String methodName, String className, String operationName,Object[] params) {
		this.methodName = methodName;
		this.className = className;
		this.operationName = operationName;
		this.params = params;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
}
