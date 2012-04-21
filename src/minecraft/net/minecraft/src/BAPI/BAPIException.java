package net.minecraft.src.BAPI;

public class BAPIException extends RuntimeException
{
    private String error;

    public BAPIException()
    {
        super();
        error = "unknown";
    }

    public BAPIException(String err)
    {
        super(err);
        error = err;
    }

    public String getError()
    {
        return error;
    }
}
