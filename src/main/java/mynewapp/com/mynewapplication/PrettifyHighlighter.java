package mynewapp.com.mynewapplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import prettify.PrettifyParser;
import syntaxhighlight.ParseResult;
import syntaxhighlight.Parser;

public class PrettifyHighlighter {
    private static final Map<String, String> COLORS = buildColorsMap();

    private static final String FONT_PATTERN = "<font color=\"#%s\">%s</font>";

    private final Parser parser = new PrettifyParser();

    private boolean insideFor = false;

    int indentTimes = 0, indentSize=7;

    StringBuilder highlighted = new StringBuilder();


    public String highlight(String fileExtension, String sourceCode) {




        List<ParseResult> results = parser.parse(fileExtension, sourceCode);

        highlighted.append("<p>");

        for (ParseResult result : results) {

            String type = result.getStyleKeys().get(0);
            String content = sourceCode.substring(result.getOffset(), result.getOffset() + result.getLength());

            if (content.startsWith("//")){
          //      highlighted.append("<br>");
            }

            if (content.startsWith("}")){
                indentTimes = indentTimes-indentSize;
                //      highlighted.append("<br>");
            }

            /*
            // PRE PROCESSING

            if (content.trim().equals("{")) {
                indentTimes = indentTimes + 4;
            }
            if (result.getStyleKeysString().equals("com")) {
                highlighted.append("<b>");
            } else if (content.startsWith("//") || content.startsWith("/*")) {
                highlighted.append("<br>");
                for (int i = 0; i < indentTimes; i++) {
                    highlighted.append("&nbsp;");
                }
            }




            if (content.trim().equals("for")) {
                insideFor = true;
            }

            if (insideFor && content.trim().equals(")")) {
                insideFor = false;
            }
*/

            highlighted.append(String.format(FONT_PATTERN, getColorNew(type), content));

            if(content.endsWith("{")){
                indentTimes = indentTimes+indentSize;
            }

            if (content.endsWith(";") || content.endsWith("*/") || content.startsWith("//") || content.endsWith("{") || content.endsWith("}")){
                highlighted.append("<br>");
                putIndent();
            }

//
//            // POST PROCESSING
//            if (content.trim().equals("}")) {
//                indentTimes = indentTimes - 4;
//            }
//
//
//            if (result.getStyleKeysString().equals("com")) {
//                highlighted.append("</b>");
//                highlighted.append("<br>");
//                for (int i = 0; i < indentTimes; i++) {
//                    highlighted.append("&nbsp;");
//                }
//            } else if (content.trim().equals(";") || content.trim().endsWith(";") || content.trim().equals("\n") || content.trim().endsWith("{") || content.trim().endsWith("}") || content.trim().endsWith("*/") || content.trim().startsWith("//")) {
//                if (insideFor) {
//                    if (!(content.trim().equals(";"))) {
//                        highlighted.append("<br>");
//                        for (int i = 0; i < indentTimes; i++) {
//                            highlighted.append("&nbsp;");
//                        }
//                    }
//
//                } else {
//                    if (content.trim().equals(";")) {
//                        highlighted.append("<br>");
//                        for (int i = 0; i < indentTimes; i++) {
//                            highlighted.append("&nbsp;");
//                        }
//                    }
//                    highlighted.append("<br>");
//                    for (int i = 0; i < indentTimes; i++) {
//                        highlighted.append("&nbsp;");
//                    }
//                }
//            }
//

        }



        highlighted.append("</p>");

        return highlighted.toString();
    }

    private String getColor(String type) {
        return COLORS.containsKey(type) ? COLORS.get(type) : COLORS.get("pln");
    }

    private String getColorNew(String newType) {
        if (newType.equals("typ"))
            return "87cefa";
        else if (newType.equals("kwd"))
            return "00ff00";
        else if (newType.equals("lit"))
            return "ffff00";
        else if (newType.equals("com"))
            return "999999";
        else if (newType.equals("str"))
            return "ff4500";
        else if (newType.equals("pun"))
            return "ff0000";
        else if (newType.equals("pln"))
            return "800000";

        return "800000";
    }

    private void putIndent() {
        for(int i=0;i<indentTimes;i++){
            highlighted.append("&nbsp;");
        }

    }

    private static Map<String, String> buildColorsMap() {
        Map<String, String> map = new HashMap<>();
        map.put("typ", "87cefa");
        map.put("kwd", "00ff00");
        map.put("lit", "ffff00");
        map.put("com", "999999");
        map.put("str", "ff4500");
        map.put("pun", "eeeeee");
        map.put("pln", "800000");
        return map;
    }
}