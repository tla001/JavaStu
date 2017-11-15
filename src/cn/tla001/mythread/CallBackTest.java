package cn.tla001.mythread;

/*
 * Java�ص����ǻ��ڽӿ�ʵ�ֵģ��������²��裺
 * 1.������Ҫ�ص��ĺ����ӿ�
 * 2.����һ�������ص����õ��࣬���ص��ĵ�����
 * 3.����һ���ص��Ľӿ�ʵ�֣������ص��ĺ���,Ҳ����ͨ��������ʵ��
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
