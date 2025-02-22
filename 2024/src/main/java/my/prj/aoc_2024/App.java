package my.prj.aoc_2024;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 * 
 */
public class App {
    public static List<String> findAllSolClasses() {
        String packageName = "/my/prj/aoc_2024";
        InputStream stream = App.class.getResourceAsStream(packageName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class") && line.startsWith("Day") && !line.contains("$"))
                .map(line -> line.split("[.]")[0])
                .sorted((c1, c2) -> c1.compareTo(c2))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
        System.out.println("---< AOC 2024 >---");
        List<String> solClasses = findAllSolClasses();
        Iterator<String> it = solClasses.iterator();
        while (it.hasNext()) {
            String cn = "my.prj.aoc_2024." + it.next();
            Class<?> c = Class.forName(cn);
            Constructor<?> con = c.getConstructor();
            AbstractSolution sol = (AbstractSolution) con.newInstance();
            sol.solve();

        }
    }
}
