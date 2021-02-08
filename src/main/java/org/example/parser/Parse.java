package org.example.parser;


public class Parse<T, U> {

    public String[] tokenize(String line) {
        return line.split(" ");
    }

    public Data<T, U> wrapData(String[] data) {
        if(data.length == 0) {
            return null;
        }
        Data<T, U> command = new Data<T, U>();
        command.setCommand(data[0]);
        if (data.length == 2) {
            if(!command.getCommand().equals("NUMEQUALTO")) {
                command.setKey((T) data[1]);
            } else {
                command.setValue((U) data[1]);
            }
        } else if (data.length == 3) {
            command.setKey((T) data[1]);
            command.setValue((U) data[2]);
        }
        return command;
    }
}
