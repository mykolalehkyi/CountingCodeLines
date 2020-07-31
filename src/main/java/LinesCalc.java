import org.jetbrains.annotations.NotNull;

public class LinesCalc {

    public static int calculateCodeLines(@NotNull String str){
        StringBuilder stringBuilder = new StringBuilder(str);
        deleteAllComments(stringBuilder);
        return countCodeLines(stringBuilder);
    }

    private static int countCodeLines(StringBuilder stringBuilder) {
        int counter=0;
        int i=0;
        while (i<stringBuilder.length() && stringBuilder.charAt(i)=='\n')i++;
        while (i<stringBuilder.length() && i!=-1){
            if (containsSymbolsInLine(stringBuilder,i)){
                counter++;
            }
            int pos = stringBuilder.indexOf("\n", i);
            i = pos==-1?-1:pos+1;
        }
        return counter;
    }

    private static boolean containsSymbolsInLine(StringBuilder stringBuilder, int pos) {
        for (int i=pos;i<stringBuilder.length();i++){
            char symbol=stringBuilder.charAt(i);
            if (symbol=='\n'){
                return false;
            }
            else if (symbol!=' ' && symbol!='\t' && symbol!='\r'){
                return true;
            }
        }
        return false;
    }

    private static void deleteAllComments(StringBuilder stringBuilder){
        char prevSymbol=stringBuilder.length()>=1?stringBuilder.charAt(0):'\u0000';
        char nextSymbol;
        int i=1;
        while(i<stringBuilder.length()){
            nextSymbol=stringBuilder.charAt(i);
            if (prevSymbol=='/' && nextSymbol=='/'){
                deleteSymbolsToTheEndOfLine(stringBuilder,i-1);
                prevSymbol='\u0000';
                nextSymbol='\u0000';
                i--;
            }
            else if (prevSymbol=='/' && nextSymbol=='*'){
                deleteSlashStar(stringBuilder,i-1);
                prevSymbol='\u0000';
                nextSymbol='\u0000';
                i--;
            }
            else if(nextSymbol=='\"'){
                i= consumeQuotes(stringBuilder,i);
            }
            else {
                prevSymbol=nextSymbol;
                i++;
            }
        }
    }

    private static int consumeQuotes(StringBuilder stringBuilder, int pos) {
        char symbol;
        int i=pos+1;
        while (i<stringBuilder.length()){
            symbol=stringBuilder.charAt(i);
            if (symbol=='\"') {
                i++;
                break;
            }
            i++;
        }
        return i;
    }

    private static void deleteSymbolsToTheEndOfLine(StringBuilder stringBuilder, int pos) {
        char symbol;
        int i=pos;
        while (i<stringBuilder.length()){
            symbol=stringBuilder.charAt(i);
            if (symbol=='\n') break;
            stringBuilder.deleteCharAt(i);
        }
    }


    private static void deleteSlashStar(StringBuilder stringBuilder, int pos) {
        char prevSymbol=stringBuilder.charAt(pos);
        char nextSymbol;
        int i=pos+1;
        while (i<stringBuilder.length()){
            nextSymbol=stringBuilder.charAt(i);
            if (prevSymbol=='*'&& nextSymbol=='/'){
                stringBuilder.delete(i-1,i+1);
                break;
            }
            stringBuilder.deleteCharAt(i-1);
            prevSymbol=nextSymbol;
        }
    }
}
