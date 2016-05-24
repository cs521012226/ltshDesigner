package org.ltsh.framework.i;

import java.util.List;

/**
 * 树类型数据结构接口
 * @author Charles
 *
 * @param <ID>
 * @param <T>
 */
public interface Tree<ID, T> {
	
	public ID getId();
    public void setId(ID id);
    
	public ID getParentId();
    public void setParentId(ID parentId);
    
    public List<T> getLeafNode();
	public void setLeafNode(List<T> leafNode);

}
