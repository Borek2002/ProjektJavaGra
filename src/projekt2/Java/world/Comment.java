package projekt2.Java.world;

public class Comment {
    private static String text="";
    public static void AddComment(String comment){
        text+=comment+"\n";
    }

    public static String getText() {
        return text;
    }
    public static void RemoveComments(){
        text="";
    }
}
