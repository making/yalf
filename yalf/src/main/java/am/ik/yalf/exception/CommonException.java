package am.ik.yalf.exception;

@SuppressWarnings("serial")
public class CommonException extends Exception {
    private final String errorId;
    private final Object[] args;

    public CommonException(String errorId, Throwable e, Object... args) {
        super(ExceptionConfig.getMessage(errorId, args), e);
        this.errorId = errorId;
        this.args = args;
    }

    public CommonException(String errorId, Object... args) {
        super(ExceptionConfig.getMessage(errorId, args));
        this.errorId = errorId;
        this.args = args;
    }

    public String getErrorId() {
        return errorId;
    }

    public Object[] getArgs() {
        return args;
    }

}
