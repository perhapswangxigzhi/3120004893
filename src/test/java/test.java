import java.io.File;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class test {
    //测试整体程序运行结果
    String root = System.getProperty("user.dir");
    String AnswersName = "Answers.txt";
    String AnswersPath = root + File.separator + File.separator + AnswersName; //答案相对路径
    String ExercisesName = "Exercises.txt";
    String ExercisesPath = root + File.separator + File.separator + ExercisesName; //题目相对路径
    FileReader fr;

    {
        try {
            fr = new FileReader(AnswersPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    } //抛出路径错误异常

    @Test
    public void all() throws IOException {
        String s = Generate.work(100, 10, AnswersPath, ExercisesPath);
        System.out.println(s);
    }

    //测试不同的表达式计算结果
    @Test
    public void calculate() {
        String re;
        //加法
        String f1 = "100 + 2 * 1/6";
        re = new Calculate(f1).result;
        System.out.println("加法：" + re);

        //减法出现负数
        String f2 = "5 - 25";
        boolean ok = Generate.end(f2);
        if (!ok) System.out.println("结果为负数，请重新输入");

        //乘法
        String f3 = "3 * ( 2/5 * 10 )";
        re = new Calculate(f3).result;
        System.out.println("乘法：" + re);

        //除法
        String f4 = "100 ÷ 4 ÷ 2/5 ÷ 6";
        re = new Calculate(f4).result;
        System.out.println("除法：" + re);

        //除法分母为0，注：分母为0的条件下自定义结果为0，虽然可能是无穷大
        String f5 = "16 ÷ ( 2 - 2 )";
        re = new Calculate(f5).result;
        System.out.println("分母为0：" + re);

    }
}

