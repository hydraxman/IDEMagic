package bryan.tool;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.XmlUtil;

public class App {

    public static void main(String[] args) {
        File dir = new File(args[0]);
        Document document = XmlUtil.createXml();
        Element templateSet = document.createElement("templateSet");
        templateSet.setAttribute("group", "interviewCracker");

        Pattern indexFinder = Pattern.compile("\\d+");
        for (File file : dir.listFiles()) {
            if (!file.getName().endsWith(".java")) {
                continue;
            }
            String source = FileUtil.readString(file, StandardCharsets.UTF_8);
            Element template = document.createElement("template");
            template.setAttribute("value", source);
            Matcher matcher = indexFinder.matcher(file.getName());
            matcher.find();
            String index = matcher.group();
            template.setAttribute("name", "leetcode" + index);
            template.setAttribute("description", file.getName().replace(".java", ""));
            template.setAttribute("toReformat", "false");
            template.setAttribute("toShortenFQNames", "true");
            templateSet.appendChild(template);
            Element context = document.createElement("context");
            Element option = document.createElement("option");
            option.setAttribute("name", "JAVA_CODE");
            option.setAttribute("value", "true");
            context.appendChild(option);
            template.appendChild(context);
        }
        String absolutePath = new File("temp.xml").getAbsolutePath();
        document.appendChild(templateSet);
        System.out.println(absolutePath);
        XmlUtil.toFile(document, absolutePath);
    }
}
