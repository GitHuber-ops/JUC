package xyz.blog.JUC;

/**
 * @Project: JUC
 * @Author: Unknown
 * @Create: 2020--09--06--10:38 AM
 */

interface Foo {
    public void sayHello();
}

public class LambdaExpressDemo {

    public static void main(String[] args) {
//        Foo foo = new Foo() {
//            @Override
//            public void sayHello() {
//                System.out.println("hello");
//            }
//        };

        Foo foo = () -> {
            System.out.println("hello");
        };
    }
}
