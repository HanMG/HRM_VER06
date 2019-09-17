package kr.co.mghan.ex;

public class RunException
{
	public void runException() throws CodeValueNotFoundException{
		throw new CodeValueNotFoundException("Value Not Found");
	}
}

