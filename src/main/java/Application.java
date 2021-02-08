import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.example.commands.BasicCommands;
import org.example.commands.BasicCommandsImpl;
import org.example.parser.Data;
import org.example.parser.Parse;

public class Application<T, U> {

    private static final String SETV = "SET";
    private static final String GET = "GET";
    private static final String UNSET = "UNSET";
    private static final String NUMEQUALTO = "NUMEQUALTO";
    private static final String END = "END";



    private static BasicCommands<String, Integer> basicCommands = new BasicCommandsImpl<>();
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
    }
}
