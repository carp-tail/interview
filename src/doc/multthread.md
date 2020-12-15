1、启动线程的三种方式
（1）继承Thread类，重写run方法(当然可以使用匿名类的写法)；
（2）实现Runable接口，实现run方法体，将实例对象传入Thread启动；
 (3) 通过线程池，Executors.newCachedThreadPool() 
`其实还可以通过Callable接口实现`
2、synchronized 是可重入锁
3、同步方法执行的过程中，出现异常时会释放锁
4、synchronized底层实现原理
jdk早期是重量级锁，jdk1.6经过优化，出现了锁升级的概念
偏向锁（将线程id记录到所对象的对象头markword中） --> 自旋锁（CAS，其实是while循环，占用CPU，默认自旋10后升级）  --> 重量级锁（操作系统级别，阻塞队列的实现）
锁只能升级而不能降级！！！
执行时间短（加锁代码）、线程数量少，用自旋锁；执行时间长、线程数量多用系统锁（重量级锁）；
5、volitale的作用：①保证线程的可见性(缓存一致性协议)；②防止指令的重排序（无法禁止CPU级别的，能禁止虚拟机级别的）。
volitale实际开发很少使用
6.Lock 获取锁后需要手动释放锁，synchronized代码块执行完成之后自动释放锁
7、ReadWriteLock(读写锁：实现共享锁和排它锁)
8、wait() 会释放锁，notify() 和 notifyAll()不释放锁