package nl.jmuijsenberg.androiddemo.viewmodels.base;

public abstract class Command {
    public final PropertyField<Boolean> canExecute;

    public Command()
    {
        canExecute = new PropertyField<>();
    }

    public abstract void execute();
}
