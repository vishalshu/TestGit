package resourcepool;

public interface ExpensiveObjectFactory
{
	public ExpensiveObject acquireExpensiveObject();
	
	public void releaseExpensiveObject(ExpensiveObject expensiveObject);
}
