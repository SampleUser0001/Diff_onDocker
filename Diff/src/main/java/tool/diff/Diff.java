package tool.diff;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Diff {

    private static List<String> allList = new ArrayList<String>();
    private static Map<String,List<String>> filesMap = new LinkedHashMap<String,List<String>>();

    public static void main(String[] args) throws IOException{
        int argsIndex = 0;
        Path inputDir = Paths.get(args[argsIndex++]);
        Path outputFile = Paths.get(args[argsIndex++]);

        try(Stream<Path> walk = Files.walk(inputDir, FileVisitOption.FOLLOW_LINKS)){
            walk.sorted(Comparator.reverseOrder())
                .forEach( path -> {
                    try {
                        if(!Files.isDirectory(path)) {
                            allList.addAll(Files.lines(path).collect(Collectors.toList()));
                            filesMap.put(inputDir.relativize(path).toString(), Files.readAllLines(path));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        }
        allList = allList.stream().distinct().collect(Collectors.toList());
        try {

            _builder.append("\t");
            StringBuilder _builder = new StringBuilder();
            for(String filePath : filesMap.keySet()) {
                _builder.append(filePath+"\t");
            }
            _builder.append("\r\n");

            for(String word : allList) {
                _builder.append(word).append("\t");
                for(List<String> list : filesMap.values()) {
                    writeOrNot(_builder , word , list).append("\t");
                }
                _builder.append("\r\n");
            }

            BufferedWriter writer = Files.newBufferedWriter(outputFile,StandardCharsets.UTF_8);
            writer.write(_builder.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static StringBuilder writeOrNot(StringBuilder _builder , String str , List<String> list1){
        if(list1.contains(str)){
            _builder.append("○");
        } else {
            _builder.append("×");
        }
        return _builder;
    }
}