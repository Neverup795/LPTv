/**
 * <p>文件名称：test.java</p>
 * <p>文件描述：</p>
 * <p>版权所有：版权所有(C)2012-2018</p>
 * <p>公    司：金证财富南京科技有限公司</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2019/3/20</p>
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
package com.szkingdom.jros.ump;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * @author pwl
 * @version 1.0    2019/3/20
 * @see [相关类/方法]
 * @since 产品/模块版本
 */
public class test
{
    public static void main(String[] args)
    {
        //101
        int a = 0b100;  //5
        int b = 0b001; //1
        System.out.println((a & b) == 1);
        
    }
    
    public static boolean isOdd(int i)
    {
        return (i & 1) == 1;
    }
    
    public static boolean isOdd1(int i)
    {
        return (i % 2) == 1;
    }
}
