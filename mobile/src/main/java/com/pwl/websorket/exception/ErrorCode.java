/**
 * <p>文件名称：ErrorCode.java</p>
 * <p>文件描述：</p>
 * <p>版权所有：版权所有(C)2012-2018</p>
 * <p>公    司：金证财富南京科技有限公司</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2019/3/22</p>
 * <p>修改记录1：</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：…</p>
 * @version 1.0
 * @author pwl
 */
package com.pwl.websorket.exception;

/**
 * <一句话功能简述>
 * @author pwl
 * @version 1.0    2019/3/22
 * @see [相关类/方法]
 * @since 产品/模块版本
 */
public enum ErrorCode
{
    ERROR("-1", "统一错误"), NO_LOGIN("1", "未登录");
    //防止字段值被修改，增加的字段也统一final表示常量
    private final String key;
    private final String value;
    
    public String getKey()
    {
        return key;
    }
    
    public String getValue()
    {
        return value;
    }
    
    private ErrorCode(String key, String value)
    {
        this.key = key;
        this.value = value;
    }
}
