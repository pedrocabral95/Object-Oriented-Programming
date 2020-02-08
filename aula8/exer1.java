//original
public static void cat(File file) {
    RandomAccessFile input = null;
    String line = null;

    try {
        input = new RandomAccessFile(file, "r");
        while ((line = input.readLine()) != null) {
            System.out.println(line);
        }
        return;
    } finally {
        if (input != null) {
            input.close();
        }
    }
}

public RandomAccessFile(String name, String mode) throws FileNotFoundException

public final String readLine() throws IOException

public void close() throws IOException




//1ª pergunta - Modifique a declaração do método cat por forma a que ele compile.
public static void cat(File file) throws FileNotFoundException, IOException {
    RandomAccessFile input = null;
    String line = null;

    try {
        input = new RandomAccessFile(file, "r");
        while ((line = input.readLine()) != null) {
            System.out.println(line);
        }
        return;
    } finally {
        if (input != null) {
            input.close();
        }
    }
}


//2ª pergunta - Modifique o corpo do método cat por forma a que ele compile.
public static void cat(File file) {
    RandomAccessFile input = null;
    String line = null;

    try {
        input = new RandomAccessFile(file, "r");
        while ((line = input.readLine()) != null) {
            System.out.println(line);
        }
    } catch(FileNotFoundException x){
        //do something
        System.out.println("File not found.");
    }catch(IOException x){
        //do something
        System.out.println("IO Exception");
    }finally {
        try{
            if (input != null) {
                input.close();
            }
        } catch(IOException x){
            //do something
            System.out.println("IO Exception");
        }
    }
}


//3ª pergunta - E qual é a alteração a realizar na solução anterior caso utilize a versão try-with-resources da instrução try?
public static void cat(File file){
 
    String line = null;

    try (RandomAccessFile input = new RandomAccessFile(file, "r")){
        while ((line = input.readLine()) != null) {
            System.out.println(line);
        }
    } catch(FileNotFoundException x){
        System.out.println("File not found.");
    }catch(IOException x){
        System.out.println("IO Exception");
    }
}

