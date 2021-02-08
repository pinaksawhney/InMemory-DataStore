import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.example.commands.BasicCommands;
import org.example.commands.BasicCommandsImpl;
import org.example.commands.TransactionCommands;
import org.example.commands.TransactionCommandsImpl;
import org.example.parser.Data;
import org.example.parser.Parse;

public class Application<T, U> {

    private static final String SETV = "SET";
    private static final String GET = "GET";
    private static final String UNSET = "UNSET";
    private static final String NUMEQUALTO = "NUMEQUALTO";
    private static final String END = "END";
    private static final String BEGIN = "BEGIN";
    private static final String ROLLBACK = "ROLLBACK";
    private static final String COMMIT = "COMMIT";


    private static BasicCommands<String, Integer> basicCommands = new BasicCommandsImpl<>();
    private static TransactionCommands<String, Integer> transactionCommands = new TransactionCommandsImpl<>();

    public static void main(String[] args) {
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                line = br.readLine();
                Parse<String, Integer> parse = new Parse<String, Integer>();
                Data<String, Integer> command =  parse.wrapData(parse.tokenize(line));
                runCommand(command);

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    private static void runCommand(Data<String, Integer> command) {

        if(command.getCommand().equals(SETV)) {
            basicCommands.set(command.getKey(), Integer.parseInt(String.valueOf(command.getValue())));
        }

        if(command.getCommand().equals(GET)) {
            System.out.println(basicCommands.get(command.getKey()));
        }

        if(command.getCommand().equals(UNSET)) {
            basicCommands.unset(command.getKey());
        }

        if(command.getCommand().equals(NUMEQUALTO)) {
            System.out.println(basicCommands.numEqualTo(Integer.parseInt(String.valueOf(command.getValue()))));
        }

        if(command.getCommand().equals(END)) {
            basicCommands.end();
        }

        if(command.getCommand().equals(BEGIN)) {
            transactionCommands.transactionBegin(command);
        }

        if(command.getCommand().equals(ROLLBACK)) {
            transactionCommands.transactionRollback();
        }

        if(command.getCommand().equals(COMMIT)) {
            transactionCommands.transactionCommit();
        }
    }
}
