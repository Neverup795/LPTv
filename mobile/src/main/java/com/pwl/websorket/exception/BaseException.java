/**
 * <p>文件名称：LoginException.java</p>
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
 * <异常>
 * @author pwl
 * @version 1.0    2019/3/22
 * @see [相关类/方法]
 * @since 产品/模块版本
 */
public class BaseException extends RuntimeException
{
    String code = "-1";
    
    public BaseException(String code, String message, Throwable cause)
    {
        super(message, cause);
        this.code = code;
    }
    
    public BaseException(String code, String message)
    {
        super(message);
        this.code = code;
    }
    
    public BaseException(ErrorCode errorCode)
    {
        super(errorCode.getValue());
        this.code = errorCode.getKey();
    }
    
    public BaseException(ErrorCode errorCode, Throwable cause)
    {
        super(errorCode.getValue(), cause);
        this.code = errorCode.getKey();
    }
}
