package kr.co.mghan.view;


// 화면 출력을 할 수 있도록 구성하는 틀
public abstract class Search extends CommonMethod
{
	// 전체 내용 볼 수 있도록 하는 메소드 선언
	public abstract void AllView();
	
	
	// 선택 내용을 볼 수 있도록 하는 메소드 선언
	public abstract void selView(Object ob);
	
}
