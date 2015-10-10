package CodeRunner;

public class Result {

	private int status;
    private long id;
    private String compilationErrors;
    private String testCaseFailed;

    public Result(int status, long id, String compilationErrors,  String testCaseFailed) 
    {
        this.status = status;
        this.id = id;
        this.compilationErrors = compilationErrors;
        this.testCaseFailed = testCaseFailed;
    }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompilationErrors() {
		return compilationErrors;
	}

	public void setCompilationErrors(String compilationErrors) {
		this.compilationErrors = compilationErrors;
	}

	public String getTestCaseFailed() {
		return testCaseFailed;
	}

	public void setTestCaseFailed(String testCaseFailed) {
		this.testCaseFailed = testCaseFailed;
	}

   
}
