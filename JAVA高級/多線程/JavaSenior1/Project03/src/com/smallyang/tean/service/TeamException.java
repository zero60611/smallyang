package com.smallyang.tean.service;

/**
 * 
* <p>Title: TeamException</p>  

* <p>Description: 自定義異常類</p> 
 * @author USER
 * 2023年7月22日
 */
public class TeamException extends Exception{
    static final long serialVersionUID = -3387514229948L;

    public TeamException() {
    	super();
    }

    public TeamException(String msg) {
    	super(msg);
    }
    
}
