package com.lc.zy.ball.boss.common.enums;

/**
 * 系统内置角色
 * @author liangc
 */
public enum Roles {
	
	//=============================================
	//== 枚举值 
	//=============================================
	ADMIN("系统管理员","admin"),
	CUSTOMER("客服人员","customer"),
	CUSTOMER_MANAGER("客服经理","customer_manager"),
	SUPPORT("运营人员","support"),
	SUPPORT_MANAGER("运营经理","support_manager");
	
	
    private Roles(String name, String code) {  
        this.name = name;  
        this.code = code;  
    }
	
	//=============================================
	//== 结构 
	//=============================================
    private String name;
    private String code;
    
    public static String getName(String code) {  
        for (Roles st : Roles.values()) {  
            if (code.equals(st.getCode())) {
                return st.getName();
            }  
        }
        return null;
    }  

    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}
	
}
