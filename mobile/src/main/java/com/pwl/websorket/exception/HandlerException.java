/**
 * <p>文件名称：ExceptionHandler.java</p>
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

import com.pwl.websorket.bean.DataResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <异常处理>
 * @author pwl
 * @version 1.0    2019/3/22
 * @see [相关类/方法]
 * @since 产品/模块版本
 */
@ControllerAdvice
public class HandlerException
{
    @ExceptionHandler()
    @ResponseBody
    public DataResult handleException(Exception e)
    {
        if (e instanceof BaseException)
        {
            BaseException base = (BaseException)e;
            return new DataResult(base.code, base.getMessage());
        }
        else if (e instanceof NullPointerException)
        {
            e.printStackTrace();
            return new DataResult("-1", "未知异常");
        }
        else
        {
            return new DataResult("-1", e.getMessage());
        }
    }
}