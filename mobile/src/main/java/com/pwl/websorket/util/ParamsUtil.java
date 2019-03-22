/**
 * <p>文件名称：ParamsUtil.java</p>
 * <p>文件描述：</p>
 * <p>版权所有：版权所有(C)2012-2018</p>
 * <p>公    司：金证财富南京科技有限公司</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2019/3/18</p>
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
package com.pwl.websorket.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * @author pwl
 * @version 1.0    2019/3/18
 * @see [相关类/方法]
 * @since 产品/模块版本
 */
public class ParamsUtil
{
    public static Map<String, Object> getInMap(HttpServletRequest request)
    {
        Map<String, Object> param = new HashMap<>();
        param.put("g_ip", request.getRemoteHost()); // 设置IP地址
        param.put("token", request.getHeader("token"));
        String method = request.getMethod();
        BufferedInputStream in = null;
        BufferedReader reader = null;
        try
        {
            if ("GET".equals(method) || "DELETE".equals(method))
            {
                param = new HashMap<>();
                Enumeration<String> enumeration = request.getParameterNames();
                while (enumeration.hasMoreElements())
                {
                    String key = enumeration.nextElement();
                    String value = request.getParameter(key);
                    value = new String(value.getBytes("iso-8859-1"), "UTF-8");
                    param.put(key, value);
                }
            }
            else
            {
                reader = request.getReader();
                in = new BufferedInputStream(request.getInputStream());
                byte[] buffer = new byte[1024];
                int bytesRead = 0;
                StringBuilder sb = new StringBuilder();
                //从文件中按字节读取内容，到文件尾部时read方法将返回-1
                while ((bytesRead = in.read(buffer)) != -1)
                {
                    sb.append(new String(buffer, 0, bytesRead, "UTF-8"));
                }
                String paramStr = sb.toString();
                if (paramStr.length() != 0)
                {
                    param = (Map<String, Object>)JSON.parse(paramStr);
                }
            }
        }
        catch (IOException e)
        {
        
        }
        finally
        {
            try
            {
                in.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return param;
    }
}
