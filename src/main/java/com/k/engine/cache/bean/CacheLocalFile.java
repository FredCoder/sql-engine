package com.k.engine.cache.bean;

import java.io.Serializable;

/**
 * 缓存临时文件包装类
 * @author Fren
 *
 */
public class CacheLocalFile implements Serializable {
	private static final long serialVersionUID = 666257453272686909L;

	private Class<?> clazz;
	
	private Object object;
	
	public CacheLocalFile() {
		super();
	}

	public CacheLocalFile(Class<?> clazz, Object object) {
		super();
		this.clazz = clazz;
		this.object = object;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
}
