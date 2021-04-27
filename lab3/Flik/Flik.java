/** An Integer tester created by Flik Enterprises.
 * @author Josh Hug
 * */
public class Flik {
    /** @param a Value 1
     *  @param b Value 2
     *  @return Whether a and b are the same */
    public static boolean isSameNumber(Integer a, Integer b) {
        return a == b;
    }
}


/**   Java Integer 类中 128 陷阱
 * 在整数的包装类当中，在第一次创建 Integer 类的对象的时候，都会首先创建好缓存数组。JVM 默认设置数组的范围为 -128 ~ 127
 * 当需要包装的值是在 IntegerCache 数组当中的元素的时候，就会返回数组当中的 Integer 对象。而当超出缓存数组的时候，就会创建新的对象。
 * 所以小于128的调用的是缓存数组中的对象，两个一样大的数是同一个对象。
 * 而大于128的是新建的对象，两者地址不一样，不能直接用 `==` 比较，要用 `equals()` 方法
 */
