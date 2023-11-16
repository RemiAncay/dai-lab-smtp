public class Main {
    public static void main(String[] args) {
        String victimsPath, messagesPath;
        int groupsCount;

        if (args.length != 3)
            throw new RuntimeException("Arguments must are <victimsPath> <messagesPath> <groupsCount>");

        //Get information from the args
        try {
            victimsPath = args[0];
            messagesPath = args[1];
            groupsCount = Integer.parseInt(args[2]);
        } catch (Exception e) {
            throw new RuntimeException("Arguments do not have the correct format");
        }

        if (groupsCount < 0)
            throw new RuntimeException("Invalid number of groups");

        //Create the prankster
        PranksterProgram prankster = new PranksterProgram(victimsPath, messagesPath, groupsCount);
        prankster.runOnce();
    }
}