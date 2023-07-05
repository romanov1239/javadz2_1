
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
public class dz2_2 {
    

/*Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.*/
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ВВедите длинну массива: ");
        int sizeArray = scanner.nextInt();
        int[] inputArray = new int[sizeArray];
        System.out.println("ВВедите элементы массива: ");
        for (int i = 0; i < sizeArray; i++) {
            inputArray[i]=scanner.nextInt();
        }
        System.out.println(Arrays.toString(inputArray));
        writeFile(inputArray);
        bubbleSort(inputArray);
        System.out.println(Arrays.toString(inputArray));
    }

    public static void writeFile(int[] input) {
        try {
            String pathProject = System.getProperty("user.dir");
            String pathFile = pathProject.concat(("/iteration.txt"));
            FileWriter fw = new FileWriter(pathFile, true);
            for (int i: input) {
                fw.write(String.valueOf(i) + " ");
            }
            fw.append('\n');
            fw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void bubbleSort(int[] inputArray) {
        boolean unsorted = true;
        int temp;
        while (unsorted) {
            unsorted = false;
            for (int i = 0; i < inputArray.length - 1; i++) {
                if (inputArray[i] > inputArray[i + 1]) {
                    temp = inputArray[i];
                    inputArray[i] = inputArray[i + 1];
                    inputArray[i + 1] = temp;
                    unsorted = true;
                    writeFile(inputArray);
                }
            }
        }
    }
}

