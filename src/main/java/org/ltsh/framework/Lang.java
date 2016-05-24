/**
 * 
 */
package org.ltsh.framework;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Map.Entry;

import org.ltsh.framework.i.ContinueLoop;
import org.ltsh.framework.i.Each;
import org.ltsh.framework.i.ExitLoop;
import org.ltsh.framework.i.LoopException;
import org.ltsh.framework.util.ReflectUtils;

/**
 * @author fengjianzhong
 *
 */
public class Lang {
	/**
	 * 将多个数组，合并成一个数组。如果这些数组为空，则返回 null
	 * 
	 * @param arys
	 *            数组对象
	 * @return 合并后的数组对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] merge(T[]... arys) {
		Queue<T> list = new LinkedList<T>();
		for (T[] ary : arys)
			if (null != ary)
				for (T e : ary)
					if (null != e)
						list.add(e);
		if (list.isEmpty())
			return null;
		Class<T> type = (Class<T>) list.peek().getClass();
		return list.toArray((T[]) Array.newInstance(type, list.size()));
	}

	/**
	 * 将一个对象添加成为一个数组的第一个元素，从而生成一个新的数组
	 * 
	 * @param e
	 *            对象
	 * @param eles
	 *            数组
	 * @return 新数组
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] arrayFirst(T e, T[] eles) {
		try {
			if (null == eles || eles.length == 0) {
				T[] arr = (T[]) Array.newInstance(e.getClass(), 1);
				arr[0] = e;
				return arr;
			}
			T[] arr = (T[]) Array.newInstance(eles.getClass().getComponentType(), eles.length + 1);
			arr[0] = e;
			for (int i = 0; i < eles.length; i++) {
				arr[i + 1] = eles[i];
			}
			return arr;
		}
		catch (NegativeArraySizeException e1) {
			throw Lang.wrapThrow(e1);
		}
	}

	/**
	 * 将一个对象添加成为一个数组的最后一个元素，从而生成一个新的数组
	 * 
	 * @param e
	 *            对象
	 * @param eles
	 *            数组
	 * @return 新数组
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] arrayLast(T[] eles, T e) {
		try {
			if (null == eles || eles.length == 0) {
				T[] arr = (T[]) Array.newInstance(e.getClass(), 1);
				arr[0] = e;
				return arr;
			}
			T[] arr = (T[]) Array.newInstance(eles.getClass().getComponentType(), eles.length + 1);
			for (int i = 0; i < eles.length; i++) {
				arr[i] = eles[i];
			}
			arr[eles.length] = e;
			return arr;
		}
		catch (NegativeArraySizeException e1) {
			throw Lang.wrapThrow(e1);
		}
	}
	/**
	 * 打断 each 循环
	 */
	public static void Break() throws ExitLoop {
		throw new ExitLoop();
	}

	/**
	 * 继续 each 循环，如果再递归，则停止递归
	 */
	public static void Continue() throws ExitLoop {
		throw new ContinueLoop();
	}

	/**
	 * 用回调的方式，遍历一个对象，可以支持遍历
	 * <ul>
	 * <li>数组
	 * <li>集合
	 * <li>Map
	 * <li>单一元素
	 * </ul>
	 * 
	 * @param obj
	 *            对象
	 * @param callback
	 *            回调
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static <T> void each(Object obj, Each<T> callback) {
		if (null == obj || null == callback)
			return;
		try {
			Class<T> eType =ReflectUtils.getTypeParam(callback.getClass(),0);
			if (obj.getClass().isArray()) {
				int len = Array.getLength(obj);
				for (int i = 0; i < len; i++)
					try {
						callback.invoke(i, (T) Array.get(obj, i), len);
					}
					catch (ContinueLoop e) {}
					catch (ExitLoop e) {
						break;
					}
			} else if (obj instanceof Collection) {
				int len = ((Collection) obj).size();
				int i = 0;
				for (Iterator<T> it = ((Collection) obj).iterator(); it.hasNext();)
					try {
						callback.invoke(i++, it.next(), len);
					}
					catch (ContinueLoop e) {}
					catch (ExitLoop e) {
						break;
					}
			} else if (obj instanceof Map) {
				Map map = (Map) obj;
				int len = map.size();
				int i = 0;
				if (null != eType && eType != Object.class && eType.isAssignableFrom(Entry.class)) {
					for (Object v : map.entrySet())
						try {
							callback.invoke(i++, (T) v, len);
						}
						catch (ContinueLoop e) {}
						catch (ExitLoop e) {
							break;
						}

				} else {
					for (Object v : map.entrySet())
						try {
							callback.invoke(i++, (T) ((Entry) v).getValue(), len);
						}
						catch (ContinueLoop e) {}
						catch (ExitLoop e) {
							break;
						}
				}
			} else if (obj instanceof Iterator<?>) {
				Iterator<?> it = (Iterator<?>) obj;
				int i = 0;
				while (it.hasNext()) {
					try {
						callback.invoke(i++, (T) it.next(), -1);
					}
					catch (ContinueLoop e) {}
					catch (ExitLoop e) {
						break;
					}
				}
			} else
				try {
					callback.invoke(0, (T) obj, 1);
				}
				catch (ContinueLoop e) {}
				catch (ExitLoop e) {}
		}
		catch (LoopException e) {
			throw Lang.wrapThrow(e.getCause());
		}
	}
	
	/**
	 * 根据格式化字符串，生成运行时异常
	 * 
	 * @param format
	 *            格式
	 * @param args
	 *            参数
	 * @return 运行时异常
	 */
	public static RuntimeException makeThrow(String format, Object... args) {
		return new RuntimeException(String.format(format, args));
	}
	/**
	 * 将抛出对象包裹成运行时异常，并增加自己的描述
	 * 
	 * @param e
	 *            抛出对象
	 * @param fmt
	 *            格式
	 * @param args
	 *            参数
	 * @return 运行时异常
	 */
	public static RuntimeException wrapThrow(Throwable e, String fmt, Object... args) {
		return new RuntimeException(String.format(fmt, args), e);
	}

	/**
	 * 用运行时异常包裹抛出对象，如果抛出对象本身就是运行时异常，则直接返回。
	 * <p>
	 * 如果是 InvocationTargetException，那么将其剥离，只包裹其 TargetException
	 * 
	 * @param e
	 *            抛出对象
	 * @return 运行时异常
	 */
	public static RuntimeException wrapThrow(Throwable e) {
		if (e instanceof RuntimeException)
			return (RuntimeException) e;
		if (e instanceof InvocationTargetException)
			return wrapThrow(((InvocationTargetException) e).getTargetException());
		return new RuntimeException(e);
	}
	public static void sleep(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
