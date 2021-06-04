package bryan.tool;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.nio.charset.StandardCharsets;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.XmlUtil;

public class App {
    public static void main(String[] args) {
        File dir = new File("/Users/bsp/Desktop/Workplaces/IdeaProjects/leetcode/java");
        Document templateSet = XmlUtil.createXml("templateSet");
        Attr group = templateSet.createAttribute("group");
        group.setValue("interviewCracker");
        for (File file : dir.listFiles()) {
            String source = FileUtil.readString(file, StandardCharsets.UTF_8);
//            System.out.println(file.getName());
//            System.out.println(source);
            Element template = templateSet.createElement("template");
            template.setAttribute("value", source);
        }
        XmlUtil.toFile(templateSet, new File("~/Desktop/temp.xml").getAbsolutePath());
        System.out.println(XmlUtil.toStr(templateSet));
    }
}
