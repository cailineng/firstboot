package com.lineng.page;

import java.util.Optional;

public class PageContext {  
	
	
    //定义两个threadLocal变量：pageNumber和pageSize  
    private static final ThreadLocal<Integer> PAGE_NUMBER_THREAD_LOCAL = new ThreadLocal<>();  
    
    private static final ThreadLocal<Integer> PAGE_SIZE_THREAD_LOCAL = new ThreadLocal<>();  
    
    private static final ThreadLocal<String> ORDER_BY_LOCAL = new ThreadLocal<>();  
    
    private static final ThreadLocal<Boolean> CANCEL_LOCAL = new ThreadLocal<>();
    
    private static final ThreadLocal<Boolean> DEFAULT_COUNT_LOCK = new ThreadLocal<>();
 
    /**
     * 缓存标识 : true, 当前线程变量参与缓存key的变化, false则不参加
     */
    private static final ThreadLocal<Boolean> CACHE_FLAG = new ThreadLocal<>();
    
    public static final Integer PAGE_NUM = 1;
    
    public static final Integer PAGE_SIZE = 10;
    
    public static final String ORDER_BY = " id DESC";
    
    public static void remove() {
    	PageContext.removePageNumber();
		PageContext.removePageSize();
		PageContext.removeCancel();
		PageContext.removeDefaultCount();
		PageContext.removeOrderBy();
		PageContext.removeCacheFlag();
    }
    
    /**
     * 获取是否取消分页处理
     * @return
     */
    public static Boolean isCancel() {
    	Boolean cancel = CANCEL_LOCAL.get();
    	return Optional.ofNullable(cancel).orElse(false);
    }
    
    public static void setCancel(boolean cancel) {
    	CANCEL_LOCAL.set(cancel);
    }
    
    /**
     * 获取是否使用默认的统计SQL
     * @return
     */
    public static Boolean isDefaultCount() {
    	Boolean defaultCount = DEFAULT_COUNT_LOCK.get();
    	return Optional.ofNullable(defaultCount).orElse(true);
    }
    
    public static void setDefaultCount(boolean defaultCount) {
    	DEFAULT_COUNT_LOCK.set(defaultCount);
    }
    
    public static void removeDefaultCount() {
    	DEFAULT_COUNT_LOCK.remove();
    }
 
    
    /* 
     * 获取当前是第几页 
     */  
    public static Integer getPageNumber() {  
        Integer pageNumber = PAGE_NUMBER_THREAD_LOCAL.get();  
        if (pageNumber == null) {  
            return PAGE_NUM;  
        }  
        return pageNumber;  
    }  
    
    /**
     * 方法名: setPageNumber</br>
     * 详述: 设置当前是第几页</br>
     * 开发人员：ruibiaozhong</br>
     * 创建时间：2016年6月15日</br>
     * @param pageNumber
     */
    public static void setPageNumber(int pageNumber) {  
    	PAGE_NUMBER_THREAD_LOCAL.set(pageNumber);  
    }  
    public static void removePageNumber(){  
    	PAGE_NUMBER_THREAD_LOCAL.remove();  
    }   
    /* 
     * pageSize ：get、set、remove 
     */  
    public static Integer getPageSize() {
        Integer ps = PAGE_SIZE_THREAD_LOCAL.get();  
        if (ps == null) {  
            return PAGE_SIZE;  
        }  
        return ps;  
    }  
    public static void setPageSize(int pageSizeValue) {  
    	PAGE_SIZE_THREAD_LOCAL.set(pageSizeValue);  
    }  
    public static void removePageSize(){  
    	PAGE_SIZE_THREAD_LOCAL.remove();  
    }  
    public static void removeCancel() {
    	CANCEL_LOCAL.remove();
    }
    
    public static String getOrderBy() {  
        return ORDER_BY_LOCAL.get(); 
    }  
    public static void setOrderBy(String orderBy) {  
    	ORDER_BY_LOCAL.set(orderBy);  
    } 
    
    public static void removeOrderBy() {
    	ORDER_BY_LOCAL.remove();
    }
    
    public static boolean getCacheFlag() {
    	return Optional.ofNullable(CACHE_FLAG.get()).orElse(false);
    }
    
    public static void setCacheFlag(Boolean flag) {
    	CACHE_FLAG.set(flag);
    }
    
    public static void removeCacheFlag() {
    	CACHE_FLAG.remove();
    }
    
}  