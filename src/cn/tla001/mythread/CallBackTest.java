package cn.tla001.mythread;

/*
 * Java回调都是基于接口实现的，包含以下步骤：
 * 1.定义需要回调的函数接口
 * 2.定义一个包含回调引用的类，即回调的调用者
 * 3.定义一个回调的接口实现，即被回调的函数,也可以通过匿名类实现
 */
interface CallbackInterface {
	public void printName();
}

class Caller {
	private CallbackInterface cb;

	public Caller() {

	}

	public void setCallbackFunc(CallbackInterface cb) {
		this.cb = cb;
	}

	public void call() {
		cb.printName();
	}
}

class RealFunc implements CallbackInterface {
	@Override
	public void printName() {
		System.out.println(RealFunc.class.getName());
	}
}

public class CallBackTest {
	public static void main(String[] args) {
		Caller caller = new Caller();
		caller.setCallbackFunc(new RealFunc());
		caller.call();
	}
}
