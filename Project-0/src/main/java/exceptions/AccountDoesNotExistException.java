package exceptions;

public class CustomerDoesNotExistException extends Exception{
    public CustomerDoesNotExistException()
    {
        super("Cuas does not exist with that username. Please register or try again with a different username.");
    }

    public CustomerDoesNotExistException(String message)
    {
        super(message);
    }
}
